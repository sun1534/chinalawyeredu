/**
 * MemService.java
 */
package com.sxit.communicateguard.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.annotation.Transactional;

import com.sxit.common.BasicService;
import com.sxit.common.PaginationSupport;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;
import com.sxit.models.mem.MemUserdevice;
import com.sxit.models.mem.MemUserdeviceId;
import com.sxit.models.system.SysUser;

/**
 * @author 华锋 Nov 16, 20109:40:42 PM
 * 
 */
public class MemService extends BasicService {
	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 删除一个命令,根据设备来显示命令
	 * 
	 * @param commandId
	 */
	public void deleteCommand(int commandId) {
		String sql = "delete from mem_devicecommand where commandid=" + commandId;
		jdbcTemplate.execute(sql);
	}

	/**
	 * 新增命令,关联到不同的终端
	 * 
	 * @param deviceid
	 * @param name
	 * @param command
	 * @param type
	 * @param typename
	 */
	@Transactional
	public void addCommand(int[] deviceids, String name, String command, int type, String typename) {
		for (int deviceid : deviceids) {
			MemDevicecommand dcommand = new MemDevicecommand();
			dcommand.setCommananame(name);
			dcommand.setCommandscript(command);
			dcommand.setCommandtype(type);
			dcommand.setTypename(typename);
			dcommand.setDeviceid(deviceid);

			super.save(dcommand);
		}
	}

	/**
	 * 删除一个设备
	 * 
	 * @param deviceId
	 */
	public void deleteDevice(int deviceId) {

		String sql1 = "delete from mem_devicecommand where deviceid=" + deviceId;
		String sql2 = "delete from mem_userdevice where deviceid=" + deviceId;
		String sql3 = "delete from mem_device where deviceid=" + deviceId;

		String sql[] = new String[] { sql1, sql2, sql3 };
		jdbcTemplate.batchUpdate(sql);

	}

	/**
	 * 获取设备列表,根据设备名查询
	 * 
	 * @param deviceName
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public com.sxit.common.PaginationSupport getDeviceList(String deviceName, int pageNo, int pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(MemDevice.class);
		if (deviceName != null && !deviceName.equals(""))
			dc.add(Restrictions.like("devicename", deviceName, MatchMode.ANYWHERE));
		dc.addOrder(Order.desc("deviceid"));

		return basicDAO.findPageByCriteria(dc, pageSize, pageNo);

	}
	
	/**
	 * 
	 * @param deviceId
	 * @return
	 */
	public List getDeviceUserList(int deviceId){
		String sql="select userid from mem_userdevice where deviceid="+deviceId;
		return basicDAO.findBySqlQuery(sql);
	}

	/**
	 * 得到这个用户的设备列表
	 * 
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public com.sxit.common.PaginationSupport getUserDeviceList(int userId, int pageNo, int pageSize) {
		String sql = "select a.* from mem_device a inner join mem_userdevice b on a.deviceid=b.deviceid  where b.userid="
				+ userId;
		String ordersqy = sql + " order by a.deviceid desc";
		String cntsql = "select count(aa.rowid) from (" + sql + ") aa";
		int totalCount = 0;

		int startIndex = (pageNo - 1) * pageSize;
		String querysql = "select * from(select a.*,rownum rn from(" + ordersqy + ") a " + " where rownum<="
				+ (startIndex + pageSize) + ") where rn>" + startIndex;

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();

				while (rs.next()) {
					MemDevice model = new MemDevice();
					model.setCreatetime(rs.getTimestamp("createtime"));
					model.setCreateuser(rs.getInt("createuser"));
					model.setCreateusername(rs.getString("createusername"));
					model.setDescription(rs.getString("description"));
					model.setDeviceid(rs.getInt("deviceid"));
					model.setDevicename(rs.getString("devicename"));
					model.setIp(rs.getString("ip"));
					model.setLoginName(rs.getString("status"));
					model.setLoginPwd(rs.getString("status"));
					model.setPort(rs.getInt("port"));
					model.setStatus(rs.getInt("status"));

					list.add(model);
				}

				return list;
			}
		});
		List list = (List) object;

		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		} else {

			totalCount = jdbcTemplate.queryForInt(cntsql);
		}

		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;

	}

	/**
	 * 得到设备的命令列表
	 * 
	 * @param deviceId
	 * @param command
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public com.sxit.common.PaginationSupport getCommandList(int deviceId, String name, int pageNo, int pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(MemDevicecommand.class);
		if (deviceId != 0)
			dc.add(Restrictions.eq("deviceid", deviceId));
		if (name != null && !name.equals(""))
			dc.add(Restrictions.like("commananame", name, MatchMode.ANYWHERE));
		dc.addOrder(Order.desc("commandid"));
		return basicDAO.findPageByCriteria(dc, pageSize, pageNo);
	}

	/**
	 * 新增设备
	 * 
	 * @param name
	 * @param ip
	 * @param port
	 * @param loginname
	 * @param pwd
	 * @param description
	 * @param user
	 */
	public void addDevice(String name, String ip, int port, String loginname, String pwd, String description,
			SysUser user) {
		MemDevice device = new MemDevice();
		device.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		device.setCreateuser(user.getUserid());
		device.setCreateusername(user.getUsername());
		device.setDescription(description);
		device.setDevicename(name);
		device.setLoginName(loginname);
		device.setLoginPwd(pwd);
		device.setIp(ip);
		device.setPort(port);

		device.setStatus(1);

		super.save(device);

	}

	/**
	 * 设置设备和哪些个用户关联
	 * 
	 * @param deviceId
	 * @param userIds
	 */
	@Transactional
	public void setUserDevice(int deviceId, int[] userIds) {

		for (int userId : userIds) {
			MemUserdeviceId deviceids = new MemUserdeviceId();
			deviceids.setDeviceid(deviceId);
			deviceids.setUserid(userId);

			MemUserdevice userdevice = new MemUserdevice();
			userdevice.setId(deviceids);

			super.save(userdevice);
		}

	}

}

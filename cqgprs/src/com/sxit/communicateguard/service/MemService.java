/**
 * MemService.java
 */
package com.sxit.communicateguard.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import com.sxit.models.mem.MemCommand;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;
import com.sxit.models.mem.MemLog;
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

	public List getDevicesByCommandId(int commandId) {
		String sql = "select deviceid from mem_devicecommand where batchid=" + commandId;
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();

				while (rs.next()) {

					list.add(rs.getInt("deviceid"));
				}

				return list;
			}
		});
		List list = (List) object;
		return list;
	}

	/**
	 * 删除一个命令,根据设备来显示命令
	 * 
	 * @param commandId
	 */
	@Transactional
	public void deleteCommand(int commandId) {
		String sql = "delete from mem_devicecommand where batchid=" + commandId;
		jdbcTemplate.execute(sql);
		sql = "delete from mem_command where commandid=" + commandId;
		jdbcTemplate.execute(sql);
		sql = "delete from mem_usercommoad where commandid=" + commandId;
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
	public void addCommand(List deviceids, MemCommand command) {
		basicDAO.save(command);
		for (int i = 0; i < deviceids.size(); i++) {
			int deviceid = (Integer.parseInt(deviceids.get(i).toString()));
			MemDevicecommand dcommand = new MemDevicecommand();
			dcommand.setCommananame(command.getCommananame());
			dcommand.setCommandscript(command.getCommandscript());
			dcommand.setCommandtype(command.getCommandtype());
			dcommand.setTypename(command.getTypename());
			dcommand.setPlugin(command.getPlugin());
			dcommand.setBatchid(command.getCommandid());
			dcommand.setCreatetime(command.getCreatetime());
			dcommand.setCreateuser(command.getCreateuser());
			dcommand.setCreateusername(command.getCreateusername());
			dcommand.setDeviceid(deviceid);
			dcommand.setRemarks(command.getRemarks());
			super.save(dcommand);
		}

		if (command.getCommandtype() == 3) {// 自定义命令，插入到mem_usercommand表中
			String sql = "insert into mem_usercommoad(userid,commandid) values(" + command.getCreateuser() + ","
					+ command.getCommandid() + ")";
			int s = jdbcTemplate.update(sql);
			System.out.println("自定义命令:::" + s);

		}
	}

	/**
	 * 新增命令
	 * 
	 * @param deviceids
	 * @param command
	 */
	@Transactional
	public void updateCommand(List deviceids, MemCommand command) {
		basicDAO.update(command);
		// 先删除，再新增
		String sql = "delete from mem_devicecommand where batchid=" + command.getCommandid();
		int result = jdbcTemplate.update(sql);

		for (int i = 0; i < deviceids.size(); i++) {
			int deviceid = (Integer.parseInt(deviceids.get(i).toString()));
			MemDevicecommand dcommand = new MemDevicecommand();
			dcommand.setCommananame(command.getCommananame());
			dcommand.setCommandscript(command.getCommandscript());
			dcommand.setCommandtype(command.getCommandtype());
			dcommand.setTypename(command.getTypename());
			dcommand.setPlugin(command.getPlugin());
			dcommand.setBatchid(command.getCommandid());
			dcommand.setCreatetime(command.getCreatetime());
			dcommand.setCreateuser(command.getCreateuser());
			dcommand.setCreateusername(command.getCreateusername());
			dcommand.setDeviceid(deviceid);
			dcommand.setRemarks(command.getRemarks());
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
		System.out.println("deviceName==="+deviceName);
		if (deviceName != null && !deviceName.equals(""))
			dc.add(Restrictions.like("devicename", deviceName, MatchMode.ANYWHERE));
		dc.addOrder(Order.desc("deviceid"));
		if(pageNo<=0)
			pageNo=1;
		return basicDAO.findPageByCriteria(dc, pageSize, pageNo);

	}

	/**
	 * 
	 * @param deviceId
	 * @return
	 */
	public List getDeviceUserList(int deviceId) {
		String sql = "select userid from mem_userdevice where deviceid=" + deviceId;
		List list = basicDAO.findBySqlQuery(sql);
		if (list != null && list.size() > 0) {
			List newlist = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				newlist.add(Integer.parseInt(list.get(i).toString()));
			}
			return newlist;
		}
		return null;
		// return basicDAO.findBySqlQuery(sql);
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
		
		return this.getDeviceList(null, pageNo, pageSize);

	}

	public com.sxit.common.PaginationSupport getUserDeviceList(int userId, String name, int pageNo, int pageSize) {
		return this.getDeviceList(name, pageNo, pageSize);

	}

	public com.sxit.common.PaginationSupport getUserCommandList(int deviceId, int userId, String name, int pageNo,
			int pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(MemDevicecommand.class);
		DetachedCriteria dc1 = DetachedCriteria.forClass(MemCommand.class);
		boolean commanddirect = true;
		if (name != null && !name.equals("")) {
			dc.add(Restrictions.like("commananame", name, MatchMode.ANYWHERE));
			dc1.add(Restrictions.like("commananame", name, MatchMode.ANYWHERE));
		}

		if (deviceId != 0) {
			commanddirect = false;
			dc.add(Restrictions.eq("deviceid", deviceId));
		}

		if (userId != 0) {
			dc.add(Restrictions.eq("createuser", userId));
			dc1.add(Restrictions.eq("createuser", userId));

		}

		dc.add(Restrictions.eq("commandtype", 3));
		dc1.add(Restrictions.eq("commandtype", 3));

		if (commanddirect) {

			dc1.addOrder(Order.desc("commandid"));
			return basicDAO.findPageByCriteria(dc1, pageSize, pageNo);
		} else {
			dc.addOrder(Order.desc("commandid"));
			return basicDAO.findPageByCriteria(dc, pageSize, pageNo);
		}
	}

	/**
	 * 得到设备的命令列表
	 * 
	 * 这里要distinct的方式展示
	 * 
	 * 
	 * @param deviceId
	 * @param command
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public com.sxit.common.PaginationSupport getCommandList(int deviceId, int commandtype, String name, int pageNo,
			int pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(MemDevicecommand.class);
		DetachedCriteria dc1 = DetachedCriteria.forClass(MemCommand.class);
		boolean commanddirect = true;
		if (name != null && !name.equals("")) {
			dc.add(Restrictions.like("commananame", name, MatchMode.ANYWHERE));
			dc1.add(Restrictions.like("commananame", name, MatchMode.ANYWHERE));
		}

		if (deviceId != 0) {
			commanddirect = false;
			dc.add(Restrictions.eq("deviceid", deviceId));
		}
		if (commandtype != 0) {
			dc.add(Restrictions.eq("commandtype", commandtype));
			dc1.add(Restrictions.eq("commandtype", commandtype));
		} else {
			dc.add(Restrictions.ne("commandtype", 3));
			dc1.add(Restrictions.ne("commandtype", 3)); // 不显示自定义的命令
		}

		System.out.println("commanddirect========" + commanddirect + ",commandtype=" + commandtype);
		if (commanddirect) {

			dc1.addOrder(Order.desc("commandid"));
			return basicDAO.findPageByCriteria(dc1, pageSize, pageNo);
		} else {
			dc.addOrder(Order.desc("commandid"));
			return basicDAO.findPageByCriteria(dc, pageSize, pageNo);
		}
	}

	// /**
	// * 得到设备的命令列表
	// *
	// * @param deviceId
	// * @param command
	// * @param pageNo
	// * @param pageSize
	// * @return
	// */
	// public com.sxit.common.PaginationSupport getCommandList(int deviceId,
	// String name, int commandtype, int pageNo,
	// int pageSize) {
	// DetachedCriteria dc = DetachedCriteria.forClass(MemDevicecommand.class);
	// if (deviceId != 0)
	// dc.add(Restrictions.eq("deviceid", deviceId));
	// if (name != null && !name.equals(""))
	// dc.add(Restrictions.like("commananame", name, MatchMode.ANYWHERE));
	// if (commandtype != 0)
	// dc.add(Restrictions.eq("commandtype", commandtype));
	// dc.addOrder(Order.desc("commandid"));
	// return basicDAO.findPageByCriteria(dc, pageSize, pageNo);
	// }

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
		// if(userIds==null||userIds.length==0){
		String sql = "delete from mem_userdevice where deviceid=" + deviceId;
		jdbcTemplate.execute(sql);
		// }
		for (int userId : userIds) {
			MemUserdeviceId deviceids = new MemUserdeviceId();
			deviceids.setDeviceid(deviceId);
			deviceids.setUserid(userId);

			MemUserdevice userdevice = new MemUserdevice();
			userdevice.setId(deviceids);

			super.save(userdevice);
		}

	}

	/**
	 * <pre>
	 * commandId=0登录
	 *           -1代表设置自定义命令
	 *           -2自己加设备
	 * </pre>
	 * 
	 * @param userId
	 * @param deviceId
	 * @param commandId
	 * @param start
	 * @param end
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public com.sxit.common.PaginationSupport getLogList(int userId, int deviceId, int commandId, Timestamp start,
			Timestamp end, int pageNo, int pageSize) {

		DetachedCriteria dc = DetachedCriteria.forClass(MemLog.class);
		if (deviceId != 0)
			dc.add(Restrictions.eq("deviceid", deviceId));
		if (userId != 0)
			dc.add(Restrictions.eq("userid", userId));
		if (commandId != -5)
			dc.add(Restrictions.eq("commandid", commandId));
		if (start != null)
			dc.add(Restrictions.ge("createtime", start));
		if (end != null)
			dc.add(Restrictions.le("createtime", end));
		dc.addOrder(Order.desc("logid"));
		return basicDAO.findPageByCriteria(dc, pageSize, pageNo);

	}

}

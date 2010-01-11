/**
 * SysUserService.java
 */

package com.changpeng.lawyers.service;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.lawyers.dao.LawyersDAO;
import com.changpeng.models.Lawyers;
import com.changpeng.models.LawyersDelete;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUser;
import com.changpeng.system.service.SysGroupService;

/**
 * @author 华锋 2008-2-27 上午10:43:29
 * 
 */
public class LawyersService extends BasicService {

	// private static Log LOG = LogFactory.getLog(LawyersService.class);

	private LawyersDAO lawyersDAO;

	private Lawyers lawyers;

	/**
	 * @return the lawyers
	 */
	public Lawyers getLawyers() {
		return lawyers;
	}

	/**
	 * @param lawyersDAO
	 *            the lawyersDAO to set
	 */
	public void setLawyersDAO(LawyersDAO lawyersDAO) {
		this.lawyersDAO = lawyersDAO;
	}

	@Transactional
	public void updateLawyers(Lawyers user) throws ServiceException {
		try {
			// String md5pass = MD5.md5(user.getPasswd());
			// user.setPasswd(md5pass);
			lawyersDAO.update(user);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Transactional
	public Lawyers deleteLawyers(int lawyerid,SysUser user) throws ServiceException {
		try {
			Lawyers lawyers=(Lawyers)lawyersDAO.get(Lawyers.class, lawyerid);
			lawyersDAO.delete(lawyers);
		
			LawyersDelete delete=new LawyersDelete();
			delete.setLawyername(lawyers.getLawyername());
			delete.setLawyerenname(lawyers.getLawyerenname());
			delete.setLawyerno(lawyers.getLawyerno());
			delete.setLoginname(lawyers.getLoginname());
			delete.setPasswd(lawyers.getPasswd());
			delete.setBirthday(lawyers.getBirthday());
			delete.setCarddate(lawyers.getCarddate());
			delete.setCardno(lawyers.getCardno());
			delete.setCertno(lawyers.getCardno());
			delete.setCityid(lawyers.getCityid());
			delete.setCountryid(lawyers.getCountryid());
			delete.setCreatetime(lawyers.getCreatetime());
			delete.setCreateuser(lawyers.getCreateuser());
			delete.setCreateusername(lawyers.getCreateusername());
			delete.setDabiaofen(lawyers.getDabiaofen());
			delete.setDegree(lawyers.getDegree());
			delete.setDirectunion(lawyers.getDirectunion());
			delete.setDistrictid(lawyers.getDistrictid());
			delete.setEmail(lawyers.getEmail());
			delete.setFax(lawyers.getFax());
			delete.setForeignlan(lawyers.getForeignlan());
			delete.setForeignlevel(lawyers.getForeignlevel());
			delete.setGender(lawyers.getGender());
			delete.setHomephone(lawyers.getHomephone());
			delete.setIsmarrige(lawyers.getIsmarrige());
			delete.setLawyerenname(lawyers.getLawyerenname());
			delete.setLawyerid(lawyers.getLawyerid());
			delete.setLawyerenname(lawyers.getLawyerenname());
			delete.setMobile1(lawyers.getMobile1());
			delete.setMobile2(lawyers.getMobile2());
			delete.setMsn(lawyers.getMsn());
			delete.setOfficephone(lawyers.getOfficephone());
			delete.setPasswd(lawyers.getPasswd());
			delete.setPhoto(lawyers.getPhoto());
			delete.setPhotoname(lawyers.getPhotoname());
			delete.setPolicy(lawyers.getPolicy());
			delete.setPostcode(lawyers.getPostcode());
			delete.setProvinceid(lawyers.getProvinceid());
			delete.setProvinceunion(lawyers.getProvinceunion());
			delete.setQq(lawyers.getQq());
			delete.setRegsrc(lawyers.getRegsrc());
			delete.setRemarks(lawyers.getRemarks());
			delete.setSchool(lawyers.getSchool());
			delete.setSpecility(lawyers.getSpecility());
			delete.setStatus(lawyers.getStatus());
			delete.setSystemno(lawyers.getSystemno());
			delete.setTheoffice(lawyers.getTheoffice());
			delete.setWorktime(lawyers.getWorktime());
			delete.setZhiyedate(lawyers.getZhiyedate());
			delete.setDeletetime(new java.sql.Timestamp(System.currentTimeMillis()));
			delete.setDeleteuser(user.getLoginname());
		
			lawyersDAO.save(delete);
			
			//律师删除引起的,删除积分以及将这个人的积分移动到另外一个表里面
			lawyersDAO.executeSql("insert into lawyerlessonxf_delete select * from lawyerlessonxf where lawyerid="+lawyerid);
			lawyersDAO.executeSql("delete from lawyerlessonxf where lawyerid="+lawyerid);
			
			return lawyers;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	/**
	 * 修改密码
	 * 
	 * @param userid
	 * @param oldpass
	 * @param newpass
	 * @return
	 * @throws ServiceException
	 */
	public int changePass(int userid, String oldpass, String newpass) throws ServiceException {
		try {
			Lawyers user = (Lawyers) lawyersDAO.get(Lawyers.class, userid);
			String pass = user.getPasswd();
			// String md5pass = MD5.md5(oldpass);
			String md5pass = oldpass;
			if (!pass.equals(md5pass)) {
				return 1;// 输入的旧密码不对
			}
			// String newmd5pass = MD5.md5(newpass);
			user.setPasswd(newpass);
			lawyersDAO.update(user);
			return 0;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 这里要注意<br/> 1、现在的删除，可能是逻辑删除，如果设置loginname唯一的话，看怎么处理 2、新增删除和更新了后，怎么保留修改的痕迹
	 * 
	 * @param user
	 * @throws ServiceException
	 */
	public void addLawyer(Lawyers user) throws ServiceException {
		try {

			// String md5pass = MD5.md5(user.getPasswd());
			// user.setPasswd(md5pass);
			lawyersDAO.save(user);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public static void main(String[] args) throws Exception {
		String s = "2009-8-21";
		System.out.println(df.parse(s));
	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static Map<String, String> GENDER = new HashMap<String, String>();
	static {
		GENDER.put("G", "G");
		GENDER.put("g", "G");
		GENDER.put("女", "G");
		GENDER.put("M", "M");
		GENDER.put("男", "M");
		GENDER.put("m", "M");

	}

	public List<String> addLawyerBatch(SysUser sysUser, int provinceunion, int directunion, List<Lawyers> lawyerslist)
			throws ServiceException {
		List<String> result = new ArrayList<String>();
		try {
			SysGroupService service = (SysGroupService) Globals.getBean("sysGroupService");
			List<String> oldlawyerno = new ArrayList<String>();
			List<SysGroup> groups = service.getChildGroup(directunion);// 所有的事务所
			Map<String, Integer> map = new HashMap<String, Integer>();
			for (SysGroup group : groups) {
				map.put(group.getGroupname(), group.getGroupid());
			}

			java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
			for (Lawyers lawyer : lawyerslist) {

				StringBuffer sb = new StringBuffer();
				String officename = lawyer.getOfficename();
				String gender = lawyer.getGender();
				lawyer.setDirectunion(directunion);
				lawyer.setProvinceunion(provinceunion);
				lawyer.setCreatetime(timestamp);
				lawyer.setCreateuser(sysUser.getUserid());
				lawyer.setCreateusername(sysUser.getUsername());
				lawyer.setLawyerenname(com.changpeng.common.util.Chinese2Pinyin.to2pinyin(lawyer.getLawyername()));
				lawyer.setPasswd(lawyer.getCertno());
				lawyer.setRegsrc(11); // 后台批量导入
				lawyer.setRemarks("批量导入");
				lawyer.setLoginname(lawyer.getLawyerno());
				Date date = null;
				if (oldlawyerno.contains(lawyer.getLawyerno())) {
					result.add("第" + lawyer.getExcelline() + "行错误:执业证号在此文件中已经存在:" + lawyer.getLawyerno() + "|||");
					continue;
				}
				oldlawyerno.add(lawyer.getLawyerno());
				if (lawyer.getZhiyedatestr() != null && !"".equals(lawyer.getZhiyedate())) {
					try {
						date = df.parse(lawyer.getZhiyedatestr());
					} catch (Exception e) {
						sb.append("执业日期必须为yyyy-MM-dd的形式:" + lawyer.getZhiyedatestr());
					}
				}
				if (!map.containsKey(officename)) {
					sb.append("事务所" + officename + "在系统中不存在|||");
				}
				if (gender != null && !"".equals("gender") && !map.containsKey(officename)) {
					sb.append("性别" + gender + "错误,必须为(男,女,G,M)|||");
				}
				if (lawyer.getLawyerno() == null || lawyer.getLawyerno().equals("")) {
					sb.append("执业证号为空|||");
				}
				if (lawyer.getCertno() == null || lawyer.getCertno().equals("")) {
					sb.append("身份证号为空|||");
				}

				if (sb.toString().length() == 0) {
					try {
						lawyer.setZhiyedate(date);
						lawyer.setGender(GENDER.get(lawyer.getGender()));
						lawyer.setPasswd(lawyer.getCertno());
						lawyer.setTheoffice(map.get(officename));
						lawyersDAO.save(lawyer);
					} catch (Exception e) {
						e.printStackTrace();
						result.add("第" + lawyer.getExcelline() + "行新增错误::" + e.getMessage());
					}
				} else {
					result.add("第" + lawyer.getExcelline() + "行::" + sb);
				}
			}
			return result;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * 
	 * 在这里面获取所有的信息,权限,菜单,用户等等 放在一个事务里面进行处理
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws ServiceException
	 */

	@Transactional
	public int userLogin(final String loginName, final String password) throws ServiceException {
		try {

			int i = lawyersDAO.userLogin(loginName, password);
			if (i > 0) {// i<0的话,登录错误
				// 得到rightcodelist
				lawyers = lawyersDAO.getLawyers();// 登录成功了，获取其他信息等
			}
			return new Integer(i);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public boolean isexist(String lawyerno, int cityid) throws ServiceException {
		try {

			return lawyersDAO.isexist(lawyerno, cityid);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public Lawyers getLawyerBySystemno(String systemno) throws ServiceException {
		try {
			return lawyersDAO.getLawyerBySystemno(systemno);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据loginname得到律师
	 * 
	 * @param loginname
	 * @return
	 * @throws ServiceException
	 */
	public Lawyers getLawyerByLoginname(String loginname) throws ServiceException {
		try {
			return lawyersDAO.getLawyerByLoginname(loginname);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 编辑时判断用户名是否重复
	 * 
	 * @param loginname
	 * @param userid
	 * @return
	 * @throws ServiceException
	 */
	public Lawyers getLawyer(String loginname, int userid) throws ServiceException {
		try {
			return lawyersDAO.getLawyer(loginname, userid);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @param lawyerno
	 * @param thegroup
	 * @return
	 * @throws ServiceException
	 */
	public Lawyers getLawyerbyLawyerno(String lawyerno, int thegroup) throws ServiceException {
		try {
			return lawyersDAO.getLawyerbyLawyerno(lawyerno, thegroup);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @param lawyerno
	 * @param province
	 * @param cityid
	 * @return
	 * @throws ServiceException
	 */
	public Lawyers getLawyerbyLawyerno(String lawyerno, int province, int cityid) throws ServiceException {
		try {
			return lawyersDAO.getLawyerbyLawyerno(lawyerno, province, cityid);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public int getFieldLawyerCnt(String field, int fieldvalue) throws ServiceException {
		try {
			return lawyersDAO.getFieldLawyerCnt(field, fieldvalue);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public int getOfficeLawyerCnt(int officeid) throws ServiceException {
		try {
			return lawyersDAO.getOfficeLawyerCnt(officeid);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public int getCityLwyerCnt(int cityid) throws ServiceException {
		try {
			return lawyersDAO.getOfficeLawyerCnt(cityid);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public int getProvinceLwyerCnt(int provinceid) throws ServiceException {
		try {
			return lawyersDAO.getOfficeLawyerCnt(provinceid);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
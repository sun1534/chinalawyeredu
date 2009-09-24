/**
 * 
 */
package com.changpeng.common.sync.website;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.sync.lgpt.GuildUserInfo;
import com.changpeng.common.sync.lgpt.LgptDAO;
import com.changpeng.common.sync.lgpt.OrgOfficeInfo;
import com.changpeng.common.sync.lgpt.OrgUserInfo;
import com.changpeng.common.sync.lgpt.PersonInfo;

/**
 * @author 华锋 Jul 11, 2009-4:37:09 PM
 * 
 */
public class WebSiteTransfer {

	private static Log LOG = LogFactory.getLog(WebSiteTransfer.class);

	public static void syncLawyers(Connection con, PersonInfo person, int staffid) throws SQLException {
		TopmStaff staff = new TopmStaff();
		Trususer user = new Trususer();
		TopmCorporation group = new TopmCorporation();
		try {
			long id = staffid;

			staff.create(con, person.getPersonCode());
			user.create(con, person.getPersonCode());

			boolean isnew = !staff.getIsexist();

			group.create(con, Long.parseLong("9" + person.getOrgId()));

			if (group.getIsExist()) {
				LOG.info("律师所在的部门:::name=" + group.getCorporationname() + "," + group.getParentid());
			} else {
				LOG.warn("(9+)" + person.getOrgId() + "对应的部门不存在");
				return;
			}
			if (isnew) {
				staff.setStaffid(id); // 主键;
			}
			staff.setCorporationid(group.getCorporationid());
			staff.setCorporationname(group.getCorporationname());
			staff.setCreatedate(new java.sql.Timestamp(System.currentTimeMillis()));
			staff.setDepartmentid(1);
			staff.setDepartmentname("默认");
			staff.setDescription(person.getPersonCode());
			staff.setEmail(person.getEmail());
			staff.setMobileno(person.getMobilePhone());
			staff.setPhoneno(person.getMobilePhone());
			staff.setQuartersid(1);
			staff.setQuartersname("默认");
			staff.setStaffname(person.getPersonName());
			// staff.setser(Integer.parseInt(person.getRoleCode()));
			staff.setStafftype(4);// stafftype=2 是管理员 stafftype=4 是律师
			// staff.setStatusid(Integer.parseInt(person.getPersonStatus()));
//			staff.setStatusid(2);
			if(person.getPersonStatus()!=null&&person.getPersonStatus().equals("6"))
			staff.setStatusid(Integer.parseInt(person.getPersonStatus()));
			else
				staff.setStatusid(2);

			user.setBirthday(new java.sql.Timestamp(System.currentTimeMillis()));
			user.setAddress(person.getAddress());
			user.setCertificatecode(person.getIdentityCard());
			user.setCertificatename(staff.getStaffname());
			user.setCompanyname(group.getCorporationname());
			user.setContacteremail(person.getEmail());
			user.setContacterfax(person.getHomeTelephone());
			user.setContactertel(person.getHomeTelephone());
			user.setCorporationid(group.getCorporationid());
			user.setCorporationname(group.getCorporationname());
			user.setCreatedate(staff.getCreatedate());
			user.setDescription(person.getPersonCode());
			user.setEmail(person.getEmail());
			user.setLicenceno(person.getCertificateNo());
			user.setLicenceno2(person.getQualificationNo());
			user.setLoginname(user.getLicenceno());
			user.setMobileno(person.getMobilePhone());
			user.setNation(person.getNationId());
			user.setNativeplace(person.getResidence());
			user.setNickname(person.getPersonName());
			// user.setPassword(person.getIdentityCard());
			user.setPassword(com.changpeng.common.util.MD5.md5(person.getIdentityCard()));
			user.setPhoneno(person.getHomeTelephone());
			user.setPoliticsid(Integer.parseInt(person.getPoliticsCode()));
			user.setPostalcode(person.getPost());
			user.setResidentplace(person.getAddress());
			user.setSex(Integer.parseInt(person.getSex()));
			user.setSpeciality(person.getSpeciality());
			user.setStaffid(staff.getStaffid());
			user.setStaffname(staff.getStaffname());
			user.setEducationname(Distionary.KNOWLEDGE.get(person.getKnowlegeCode()));
		    user.setNation(Distionary.NATION.get(person.getNationId()));
		    user.setEducationid(Integer.parseInt(person.getKnowlegeCode()));
//			user.setStatusid(2);
			if(person.getPersonStatus()!=null&&person.getPersonStatus().equals("6"))
		    	user.setStatusid(Integer.parseInt(person.getPersonStatus()));
			else
				user.setStatusid(2);
				
			
			user.setRoleid(5);
			user.setUniversity(person.getGraduateSchool());
			user.setUserid(staff.getStaffid());
			user.setUsername(staff.getStaffname());
			user.setDescription(staff.getDescription());
			user.setServicedomain(group.getParentid());
			user.setServicehour(Long.parseLong(person.getRoleCode()));
			

			String image = "";
			if (person.getFilename() != null && !person.getFilename().equals("")
					&& !person.getFilename().equalsIgnoreCase("null")) {
				image = LgptDAO.LOGOPATH + person.getFilename();
			}

			user.setFilename(image);
			// 0:非; 1：是
			user.setIsunits(Integer.parseInt(person.getMasterFlag()));
			user.setIsexperience(Integer.parseInt(person.getLawyerType()));

			// ISUNITS 设置为是否主任
			// ISEXPERIENCE 1：专职；2：兼职

			String flag = person.getDataType();

			if (flag != null && flag.equalsIgnoreCase("inserted") || flag.equalsIgnoreCase("updated")) {
				LOG.info("isnew:::" + isnew + ",flag::" + flag);
				if (isnew) {

					staff.insert(con);
					user.insert(con);
				} else {
					staff.update(con);
					LOG.info("servicedomain:::" + user.getServicedomain());
					user.update(con);
				}
			} else if (flag != null && flag.equals("deleted")) {
				if (!isnew) {
					Trususer.delete(con, id);
					TopmStaff.delete(con, id);
				} else {
					LOG.warn("网站删除律师,但是对应的律师不存在:" + person.getPersonCode());
				}
			} else {
				LOG.warn("ExchangeFlag错误:::" + flag);
			}
			LOG.error("网站同步律师成功:" + person.getPersonCode() + "=>" + person.getPersonName());
		} catch (SQLException e) {
			LOG.error("网站同步律师异常:" + person.getPersonCode() + "=>" + e);
			e.printStackTrace();
			throw e;
		}
	}

	public static void syncOrgs(Connection con, OrgOfficeInfo org) throws SQLException {

		TopmCorporation group = new TopmCorporation();
		try {
			group.create(con, Long.parseLong("9" + org.getOrgId()));

			boolean isnew = !group.getIsExist();

			String parentOrg = org.getGuildId();
			long parentId = Long.parseLong("-1" + parentOrg);
			if (parentId == -10408) {
//				parentId = 101;
				parentId= -10000;
			}

			group.setCorporationid(Long.parseLong("9" + org.getOrgId()));
			group.setDescription("律管平台同步");
			group.setCorpaddr(org.getAddress());
			group.setPostcode(org.getZipcode());
			group.setLicenceno(org.getFileNo());

			group.setRegisterdate(org.getFormDate() == null ? new java.sql.Timestamp(System.currentTimeMillis())
					: new java.sql.Timestamp(org.getFormDate().getTime()));
			// group.setRegfund(org.getEnrollCapital());
			group.setContactertel(org.getOfficePhone());
			group.setTypeid(Integer.parseInt(org.getOrgType()));
			group.setCorpnameshort(org.getCertCode());
			// group.setStatusid(org.getCurStatus())
			group.setCreatedate(new java.sql.Timestamp(System.currentTimeMillis()));
			group.setConfirmdate(group.getCreatedate());
			group.setContacterfax(org.getFax());
			group.setCorporationname(org.getOrgName());
			group.setParentid(parentId);
			group.setContactertel(org.getOfficePhone());
			group.setTypeid(2);
			group.setStatusid(2);
			String flag = org.getDataType();

			if (flag != null && flag.equalsIgnoreCase("inserted") || flag.equalsIgnoreCase("updated")) {
				if (isnew) {
					group.insert(con);
					LOG.info("网站新增部门:" + group.getCorporationid() + ",," + group.getParentid());
				} else {
					group.update(con);
					LOG.info("网站修改部门:" + group.getCorporationid() + ",," + group.getParentid());
				}
			} else if (flag != null && flag.equals("deleted")) {
				if (!isnew) {
					TopmCorporation.delete(con, group.getCorporationid());

					// topmdepartment
					// topm
				} else
					LOG.warn("网站删除部门,但是对应的部门不存在:" + group.getDescription());

			} else {
				LOG.warn("ExchangeFlag错误:::" + flag);
			}
		} catch (SQLException e) {
			LOG.error("网站部门同步异常:" + group.getDescription() + "=>" + e);
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 
	 */
	public static void syncLXManagers(Connection con, GuildUserInfo info, int staffid) throws SQLException {
		TopmStaff staff = new TopmStaff();
		Topmuser opmuser = new Topmuser();
		Trususer rususer = new Trususer();
		TopmCorporation group = new TopmCorporation();
		try {
			long id = staffid;
			long lxid = Long.parseLong("-1" + info.getDepatId());
			if (lxid == -10408) {
				if (info.getLoginName().equals("gxlxadmin"))
					lxid = 101;
				else
					lxid = -10000;
			}
			group.create(con, lxid);

			if (!group.getIsExist()) {
				LOG.warn(info.getUserName() + "对应的律协(-1+)" + info.getDepatId() + "在系统中不存在");
				return;
			}
			staff.create(con, info.getUserId());
			opmuser.create(con, info.getUserId());
			rususer.create(con, info.getUserId());
			boolean isnew = !staff.getIsexist();

			if (isnew) {
				staff.setStaffid(id); // 主键;
			}
			staff.setCorporationid(group.getCorporationid());
			staff.setCorporationname(group.getCorporationname());
			staff.setCreatedate(new java.sql.Timestamp(System.currentTimeMillis()));
			staff.setDepartmentid(1);
			staff.setDepartmentname("默认");
			staff.setDescription(info.getUserId());
			staff.setEmail(null);
			staff.setMobileno(null);
			staff.setPhoneno(null);
			staff.setQuartersid(1);
			staff.setQuartersname("默认");

			staff.setStaffname(info.getUserName());
			staff.setStafftype(2);// stafftype=2 是管理员 stafftype=4 是律师
			staff.setStatusid(2);

			opmuser.setCreatedate(staff.getCreatedate());
			opmuser.setLoginname(info.getLoginName());
			opmuser.setPassword(info.getPassword());
			opmuser.setPowerid(15);
			opmuser.setUserid(staff.getStaffid());
			opmuser.setStaffid(staff.getStaffid());
			opmuser.setStaffname(staff.getStaffname());
			opmuser.setStatusid(2);
			opmuser.setUserid(staff.getStaffid());
			opmuser.setUsername(staff.getStaffname());
			opmuser.setDescription(staff.getDescription());

			rususer.setBirthday(new java.sql.Timestamp(System.currentTimeMillis()));
			rususer.setAddress(null);
			rususer.setCertificatecode(info.getUserId());
			rususer.setCertificatename(staff.getStaffname());
			rususer.setCompanyname(group.getCorporationname());
			rususer.setContacteremail(null);
			rususer.setContacterfax(null);
			rususer.setContactertel(null);
			rususer.setCorporationid(group.getCorporationid());
			rususer.setCorporationname(group.getCorporationname());
			rususer.setCreatedate(staff.getCreatedate());
			rususer.setEmail(null);
			rususer.setLicenceno(null);
			rususer.setLicenceno2(null);
			rususer.setLoginname(info.getLoginName());
			rususer.setMobileno(null);
			rususer.setNation(null);
			rususer.setNativeplace(null);
			rususer.setNickname(null);
			rususer.setPassword(info.getPassword());
			rususer.setPhoneno(null);
			rususer.setPoliticsid(0);
			rususer.setPostalcode(null);
			rususer.setResidentplace(null);
			rususer.setSex(1);
			rususer.setSpeciality(null);
			rususer.setStaffid(staff.getStaffid());
			rususer.setStaffname(staff.getStaffname());
			rususer.setStatusid(2);
			rususer.setUniversity(null);
			rususer.setUserid(staff.getStaffid());
			rususer.setUsername(staff.getStaffname());
			rususer.setDescription(staff.getDescription());
			rususer.setRoleid(6);
			rususer.setServicedomain(group.getCorporationid());
			rususer.setServicehour(101);

			String flag = info.getDataType();
			if (flag != null && flag.equalsIgnoreCase("inserted") || flag.equalsIgnoreCase("updated")) {
				LOG.info("isnew:::" + isnew + ",flag::" + flag);
				if (isnew) {
					staff.insert(con);
					rususer.insert(con);
					opmuser.insert(con);
				} else {
					staff.update(con);
					rususer.update(con);
					opmuser.update(con);
				}
			} else if (flag != null && flag.equals("deleted")) {
				if (!isnew) {
					Topmuser.delete(con, id);
					TopmStaff.delete(con, id);
					Trususer.delete(con, id);
				} else {
					LOG.warn("网站删除律协管理员,但是对应管理员不存在:" + staff.getStaffname());
				}
			} else {
				LOG.warn("ExchangeFlag错误:::" + flag);
			}

		} catch (SQLException e) {
			LOG.error("网站律协管理员同步异常:" + info.getUserId() + "=>" + e);
			e.printStackTrace();
			throw e;
		}
	}

	public static void syncOrgManagers(Connection con, OrgUserInfo info, int staffid) throws SQLException {

		TopmStaff staff = new TopmStaff();
		Topmuser opmuser = new Topmuser();
		Trususer rususer = new Trususer();
		TopmCorporation group = new TopmCorporation();
		try {
			long id = staffid;
			group.create(con, Long.parseLong("9" + info.getOrgId()));
			if (!group.getIsExist()) {
				LOG.warn(info.getUserName() + "对应的事务所(9+)" + info.getOrgId() + "在系统中不存在");
				return;
			}
			staff.create(con, info.getUserId());
			opmuser.create(con, info.getUserId());
			rususer.create(con, info.getUserId());

			boolean isnew = !staff.getIsexist();
			if (isnew) {
				staff.setStaffid(id); // 主键;
			}
			staff.setCorporationid(group.getCorporationid());
			staff.setCorporationname(group.getCorporationname());
			staff.setCreatedate(new java.sql.Timestamp(System.currentTimeMillis()));
			staff.setDepartmentid(1);
			staff.setDepartmentname("默认");
			staff.setDescription(info.getUserId());
			staff.setEmail(null);
			staff.setMobileno(null);
			staff.setPhoneno(null);
			staff.setQuartersid(1);
			staff.setQuartersname("默认");

			staff.setStaffname(info.getUserName());
			// staff.setStafftype(99); // 律协管理员
			staff.setStafftype(2);// stafftype=2 是管理员 stafftype=4 是律师
			staff.setStatusid(2);

			opmuser.setCreatedate(staff.getCreatedate());
			opmuser.setLoginname(info.getUserName());
			opmuser.setPassword(info.getPassword());
			opmuser.setPowerid(15);
			opmuser.setUserid(staff.getStaffid());
			opmuser.setStaffid(staff.getStaffid());
			opmuser.setStaffname(staff.getStaffname());
			opmuser.setStatusid(2);
			opmuser.setUserid(staff.getStaffid());
			opmuser.setUsername(staff.getStaffname());
			opmuser.setDescription(staff.getDescription());

			rususer.setBirthday(new java.sql.Timestamp(System.currentTimeMillis()));
			rususer.setAddress(null);
			rususer.setCertificatecode(info.getUserId());
			rususer.setCertificatename(staff.getStaffname());
			rususer.setCompanyname(group.getCorporationname());
			rususer.setContacteremail(null);
			rususer.setContacterfax(null);
			rususer.setContactertel(null);
			rususer.setCorporationid(group.getCorporationid());
			rususer.setCorporationname(group.getCorporationname());
			rususer.setCreatedate(staff.getCreatedate());
			rususer.setDescription(info.getUserId());
			rususer.setEmail(null);
			rususer.setLicenceno(null);
			rususer.setLicenceno2(null);
			rususer.setLoginname(info.getUserName());
			rususer.setMobileno(null);
			rususer.setNation(null);
			rususer.setNativeplace(null);
			rususer.setNickname(null);
			rususer.setPassword(info.getPassword());
			rususer.setPhoneno(null);
			rususer.setPoliticsid(0);
			rususer.setPostalcode(null);
			rususer.setResidentplace(null);
			rususer.setSex(1);
			rususer.setSpeciality(null);
			rususer.setStaffid(staff.getStaffid());
			rususer.setStaffname(staff.getStaffname());
			rususer.setStatusid(2);
			rususer.setUniversity(null);
			rususer.setUserid(staff.getStaffid());
			rususer.setUsername(staff.getStaffname());
			rususer.setDescription(staff.getDescription());
			rususer.setRoleid(6);
			rususer.setServicedomain(group.getParentid());
			rususer.setServicehour(100);
			String flag = info.getDataType();
			if (flag != null && flag.equalsIgnoreCase("inserted") || flag.equalsIgnoreCase("updated")) {
				LOG.info("isnew:::" + isnew + ",flag::" + flag);
				if (isnew) {
					staff.insert(con);
					rususer.insert(con);
					opmuser.insert(con);
				} else {
					staff.update(con);
					rususer.update(con);
					opmuser.update(con);
				}
			} else if (flag != null && flag.equals("deleted")) {
				if (!isnew) {
					Topmuser.delete(con, id);
					TopmStaff.delete(con, id);
					Trususer.delete(con, id);
				} else {
					LOG.warn("网站删除事务所管理员,但是对应的律师不存在:" + staff.getStaffname());
				}
			} else {
				LOG.warn("ExchangeFlag错误:::" + flag);
			}
			LOG.info("网站事务所管理员同步成功:" + info.getUserId() + "=>" + info.getUserName());
		} catch (SQLException e) {
			LOG.error("网站事务所管理员同步异常:" + info.getUserId() + "=>" + e);
			e.printStackTrace();
			throw e;
		}
	}
}

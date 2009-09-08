/**
 * SysUserService.java
 */

package com.changpeng.lawyers.service;

import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.exception.ServiceException;
import com.changpeng.lawyers.dao.LawyersDAO;
import com.changpeng.models.Lawyers;

/**
 * @author 华锋 2008-2-27 上午10:43:29
 * 
 */
public class LawyersService {

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
//			String md5pass = MD5.md5(user.getPasswd());
			String md5pass=user.getPasswd();
			user.setPasswd(md5pass);
			lawyersDAO.update(user);
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
//			String md5pass = MD5.md5(oldpass);
			String md5pass=oldpass;
			if (!pass.equals(md5pass)) {
				return 1;// 输入的旧密码不对
			}
//			String newmd5pass = MD5.md5(newpass);
			String newmd5pass=newpass;
			user.setPasswd(newmd5pass);
			lawyersDAO.update(user);
			return 0;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public Lawyers getLawyerBySystemno(String systemno)throws ServiceException {
		try {
			return lawyersDAO.getLawyerBySystemno(systemno);
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
		
//			String md5pass = MD5.md5(user.getPasswd());
			String md5pass =user.getPasswd();
			user.setPasswd(md5pass);
			lawyersDAO.save(user);
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

	public boolean isexist(String lawyerno,int cityid)throws ServiceException{
		try {

			return lawyersDAO.isexist(lawyerno, cityid);
			

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
	
	public int getFieldLawyerCnt(String field,int fieldvalue) throws ServiceException{
		try {
			return lawyersDAO.getFieldLawyerCnt(field,fieldvalue);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public int getOfficeLawyerCnt(int officeid) throws ServiceException{
		try {
			return lawyersDAO.getOfficeLawyerCnt(officeid);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public int getCityLwyerCnt(int cityid) throws ServiceException{
		try {
			return lawyersDAO.getOfficeLawyerCnt(cityid);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public int getProvinceLwyerCnt(int provinceid)throws ServiceException{
		try {
			return lawyersDAO.getOfficeLawyerCnt(provinceid);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
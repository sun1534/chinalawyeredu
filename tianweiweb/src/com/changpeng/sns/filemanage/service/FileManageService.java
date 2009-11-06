package com.changpeng.sns.filemanage.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.sns.filemanage.model.SnsDir;
import com.changpeng.sns.filemanage.model.SnsFile;

public class FileManageService extends BasicService {
	Logger log = Logger.getLogger(FileManageService.class);
	
	/**
	 * 创建目录
	 * @param dirname
	 * @param userid
	 * @param dirtype 1视频   2 音频  3 文件
	 */
	public void createDir(String dirname,String description,int dirtype,int userid){
		SnsDir dir=new SnsDir();
		dir.setDirname(dirname);
		dir.setDescription(description);
		dir.setUserid(userid);
		dir.setDirtype(dirtype);
		save(dir);
	}
	/**
	 * 修改目录
	 * @param dirname
	 * @param userid
	 * @param dirtype 1视频   2 音频
	 */
	public void editDir(int id ,String dirname,String description){
		SnsDir dir=(SnsDir)this.get(SnsDir.class, id);
		dir.setDirname(dirname);
		dir.setDescription(description);
		
		update(dir);
	}
	/**
	 * 删除目录
	 * @param dirid
	 */
	public void deleteDir(int dirid){
		SnsDir dir=(SnsDir)this.get(SnsDir.class, dirid);
		delete(dir);
	}
	/**
	 * 删除目录
	 * @param dirid
	 */
	public void deleteDir(int dirid,int userid){
		SnsDir dir=(SnsDir)this.get(SnsDir.class, dirid);
		if(dir.getUserid()==userid){
			delete(dir);
		}
	}
	/**
	 * 删除文件
	 * @param dirid
	 */
	public void deleteFile(int fileid,int userid){
		SnsFile file=(SnsFile)this.get(SnsFile.class, fileid);
		if(file.getUserid()==userid){
			delete(file);
		}
	}
	/**
	 * 获取所有目录
	 * @param dirid
	 */
	public List getDirs(int userid,int dirtype){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsDir.class);
		detachedCriteria.add(Restrictions.eq("userid", userid));
		if(dirtype!=0){
			detachedCriteria.add(Restrictions.eq("dirtype", dirtype));
		}
		return this.findByCriteria(detachedCriteria);
	}
	/**
	 * 获取用户的文件列表
	 * @param userId
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginationSupport getUserFilelist(boolean isself,int userId,int pageSize, int pageNo) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsFile.class);
		detachedCriteria.add(Restrictions.eq("userid", userId));
		if(!isself){
			detachedCriteria.add(Restrictions.eq("statusid", 0));
		}
		detachedCriteria.addOrder(Order.desc("createTime"));
		
		return this.findPageOnPageNo(detachedCriteria, pageSize, pageNo);
	}
	
	/**
	 * 获取用户的一个目录的文件列表
	 * @param dirid
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginationSupport getDirFilelist(boolean isself,int dirid,int pageSize, int pageNo) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsFile.class);
		detachedCriteria.add(Restrictions.eq("dirid", dirid));
		if(!isself){
			detachedCriteria.add(Restrictions.eq("statusid", 0));
		}
		detachedCriteria.addOrder(Order.desc("createTime"));
		return this.findPageOnPageNo(detachedCriteria, pageSize, pageNo);
	}
	
	
}

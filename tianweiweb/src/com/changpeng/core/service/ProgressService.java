package com.changpeng.core.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.core.model.CoreProduct;
import com.changpeng.core.model.CorePublish;
import com.changpeng.core.model.CorePublishContent;
import com.changpeng.sns.diary.model.SnsDiary;
import com.changpeng.sns.filemanage.model.PublishFile;
import com.changpeng.sns.filemanage.model.SnsFile;
import com.changpeng.sns.photo.model.PublishPhoto;
import com.changpeng.sns.photo.model.SnsPhoto;

/**
 * 订购流程表
 * 订购
 * 填充资料
 * 审核记录
 * @author mfq
 *
 */
public class ProgressService extends BasicService {
	
	/**
	 * 获取订购列表
	 * @param userid
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginationSupport getProgressList(int userid,int pageSize,int pageNo){
		DetachedCriteria dc = DetachedCriteria.forClass(CorePublish.class, "u");
		dc.add(Restrictions.eq("u.userid", userid));
		dc.addOrder(Order.desc("u.id"));
		return this.findPageOnPageNo(dc, pageSize, pageNo);
	}
	/**
	 * 获取订购列表
	 * @param userid
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginationSupport getPublishList(int userid,int pageSize,int pageNo){
		DetachedCriteria dc = DetachedCriteria.forClass(CorePublish.class, "u");
		dc.add(Restrictions.eq("u.userid", userid));
		dc.add(Restrictions.eq("u.statusid", (short)99));
		dc.addOrder(Order.desc("u.id"));
		return this.findPageOnPageNo(dc, pageSize, pageNo);
	}
	/**
	 * 付费列表,列出除了初始订购之外的所有内容
	 * @param userid
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginationSupport getPayList(int userid,int pageSize,int pageNo){
		DetachedCriteria dc = DetachedCriteria.forClass(CorePublish.class, "u");
		dc.add(Restrictions.eq("u.userid", userid));
		dc.add(Restrictions.not(Restrictions.eq("statusid", (short)1)));
		dc.addOrder(Order.desc("u.id"));
		return this.findPageOnPageNo(dc, pageSize, pageNo);
	}
	
	/**
	 * 列出除了订购的文章列表
	 * @param userid
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public List getDiaryList(int publishid){
		List diarys=new ArrayList();
		
		DetachedCriteria dc = DetachedCriteria.forClass(CorePublishContent.class, "u");
		dc.add(Restrictions.eq("publishid", publishid));
		dc.add(Restrictions.eq("serviceid", 3));
		
		
		List d=this.findByCriteria(dc);
		
		if(d!=null){
			for(int i=0;i<d.size();i++){
				CorePublishContent cont=(CorePublishContent)d.get(i);
				SnsDiary diary=(SnsDiary)this.get(SnsDiary.class, cont.getContentid());
				if(diary!=null){
					diarys.add(diary);
				}
			}
		}
		return diarys;
	}
	/**
	 * 列出除了订购的图片列表
	 * @param userid
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public List getPhotoList(int publishid){
		List photos=new ArrayList();
		
		DetachedCriteria dc = DetachedCriteria.forClass(CorePublishContent.class, "u");
		dc.add(Restrictions.eq("publishid", publishid));
		dc.add(Restrictions.eq("serviceid", 2));
		
		
		List d=this.findByCriteria(dc);
		
		if(d!=null){
			for(int i=0;i<d.size();i++){
				CorePublishContent cont=(CorePublishContent)d.get(i);
				SnsPhoto photo=(SnsPhoto)this.get(SnsPhoto.class, cont.getContentid());
				PublishPhoto publishphoto=new PublishPhoto();
				publishphoto.setContentid(cont.getId());
				publishphoto.setAlbumid(photo.getAlbumid());
				publishphoto.setPhotoid(photo.getPhotoid());
				publishphoto.setPhotoName(photo.getPhotoName());
				publishphoto.setRemark(cont.getRemark());
				publishphoto.setUrl(photo.getUrl());
				publishphoto.setSmallUrl(photo.getSmallUrl());
				if(photo!=null){
					photos.add(publishphoto);
				}
			}
		}
		return photos;
	}
	
	/**
	 * 列出除了订购的音频视频列表   后期分开
	 * @param userid
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public List getAudioList(int publishid){
		List audios=new ArrayList();
		
		DetachedCriteria dc = DetachedCriteria.forClass(CorePublishContent.class, "u");
		dc.add(Restrictions.eq("publishid", publishid));
		dc.add(Restrictions.eq("serviceid", 4));
		
		
		List d=this.findByCriteria(dc);
		
		if(d!=null){
			for(int i=0;i<d.size();i++){
				CorePublishContent cont=(CorePublishContent)d.get(i);
				SnsFile audio=(SnsFile)this.get(SnsFile.class, cont.getContentid());
				if(audio!=null){
					PublishFile pfile=new PublishFile();
					pfile.setFileName(audio.getFileName());
					pfile.setId(cont.getId());
					pfile.setPublishstatus((short)cont.getStatusid());
					pfile.setTypeid(audio.getTypeid());
					pfile.setUrl(audio.getUrl());
					audios.add(pfile);
				}
			}
		}
		return audios;
	}
	/**
	 * 
	 * 检查订购的内容是否完整
	 */
	public boolean checkContent(int publishid){
		CorePublish publish=(CorePublish)get(CorePublish.class, publishid);
		
		CoreProduct product = (CoreProduct)get(CoreProduct.class, publish.getProductid());
		
		int photocount=getPhotoList(publishid).size();
		int diarycount=getDiaryList(publishid).size();
		
		if(product.getMaxpic()==photocount&&product.getMaxcontent()==diarycount){
			return true;
		}else{
			return false;
		}
	}
}
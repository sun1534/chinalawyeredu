/**
 * 
 */
package com.sxit.content.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.sxit.common.BasicService;
import com.sxit.common.Globals;
import com.sxit.common.exception.ServiceException;
import com.sxit.models.content.SnsDiarytype;
import com.sxit.models.content.SnsDir;
import com.sxit.models.content.SnsPhotoAlbum;

/**
 * @author 华锋 Jul 12, 2009 4:16:49 PM
 * 
 */
public class CommonDatas {

	public static Map<Short, String> STATUS = new HashMap<Short, String>();

	public static Map<Integer, String> ALBUMIDNAME = new HashMap<Integer, String>();
	public static Map<Integer, String> DIARYTYPE = new HashMap<Integer, String>();
	public static Map<Integer, String> FILEDIR = new HashMap<Integer, String>();

	private static BasicService basicService = (BasicService) Globals.getBean("basicService");
	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(CommonDatas.class);

	public static Map<Short,String> PUBLISHSTATUS=new HashMap<Short,String>();
	
	
	private static long Interactive = 0;

	static {
		STATUS.put((short) 0, "审核通过");
		// 0,审核通过，1 新内容，需要审核的，2 审核不通过
		STATUS.put((short) 1, "待审核");
		STATUS.put((short) 2, "审核不通过");
		
//		0 通过 可以发布到电视 1 初订购，等待完善内容 2 订购，内容完整，需要付费 3 付费完成，待审核状态 5 审核未通过 99 正在发布电视中 100 发布完成，已经从电视上撤下来了
		
		PUBLISHSTATUS.put((short)0, "审批通过");
		PUBLISHSTATUS.put((short)1, "初订购");
		PUBLISHSTATUS.put((short)2, "待付费");
		PUBLISHSTATUS.put((short)3, "付费完毕待审核");
		PUBLISHSTATUS.put((short)4, "审核未通过");
		PUBLISHSTATUS.put((short)5, "审核通过");
		PUBLISHSTATUS.put((short)99, "发布中");
		PUBLISHSTATUS.put((short)100, "业务到期");
		
		
	}

	public static void getAllContentTypes() {

		long now = System.currentTimeMillis();
//		if (now - Interactive > 5 * 60 * 1000) {
			try {

				List diarylist = basicService.findAll(SnsDiarytype.class);
				List dirlist = basicService.findAll(SnsDir.class);
				List albumlist = basicService.findAll(SnsPhotoAlbum.class);
				synchronized (DIARYTYPE) {
					DIARYTYPE.clear();
					for (int i = 0; diarylist != null && i < diarylist.size(); i++) {
						SnsDiarytype type = (SnsDiarytype) diarylist.get(i);
						DIARYTYPE.put(type.getId(), type.getTypename());
					}
				}
				synchronized (FILEDIR) {
					FILEDIR.clear();
					for (int i = 0; dirlist != null && i < dirlist.size(); i++) {
						SnsDir type = (SnsDir) dirlist.get(i);
						FILEDIR.put(type.getId(), type.getDirname());
					}
				}

				synchronized (ALBUMIDNAME) {
					ALBUMIDNAME.clear();
					for (int i = 0; albumlist != null && i < albumlist.size(); i++) {
						SnsPhotoAlbum type = (SnsPhotoAlbum) albumlist.get(i);
						ALBUMIDNAME.put(type.getAlbumid(), type.getAlbumName());
					}
					System.out.println("ALBUMIDNAME:"+ALBUMIDNAME);
				}
			} catch (ServiceException e) {
				LOG.error("getAllContentTypes::" + e);
			}
			Interactive = now;
//		}
	}
}

/**
 * 
 */
package com.changpeng.lawcase.action;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawFiles;
import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawSusong;
import com.changpeng.lawcase.util.Clob2String;
import com.changpeng.lawcase.util.LawcaseCreateBatch;
import com.sxit.workflow.model.TwflNode;

/**
 * 
 * 承办人填写或修改诉状材料 这里的诉讼信息为clob类型
 * 
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class SusongCreateEditAction extends com.changpeng.lawcase.workflow.WorkFlowAction {
	private String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		// ServletActionContext c=null;
		if (thecontent == null) {
			this.message = "材料内容不能为空,请务必输入";
			return "message";
		}

		Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());

		if (upload != null) {
			for (int i = 0; i < upload.length; i++) {
				File uploadfile = upload[i];
				if (uploadfile == null)
					continue;
				String extendPath = "/uploads/";
				String name = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
				String toPath = ServletActionContext.getServletContext().getRealPath("") + extendPath;
				String ext = getExtention(this.uploadFileName[i]);
				String filename = name + ext;
				try {
					FileUtils.forceMkdir(new File(toPath)); // 创建目录
					File dest = new File(toPath + filename);
					FileUtils.copyFile(uploadfile, dest); // 移动文件

					// 将上传的文件保存
					TlawFiles file = new TlawFiles();
					file.setFilename(this.uploadFileName[i]);
					file.setPath(toPath + filename);
					file.setRemarks("上传的诉讼材料文件");
					file.setUploadtime(new java.sql.Timestamp(System.currentTimeMillis()));
					file.setUploaduserid(this.curuser.getUserid());
					file.setUploadusername(this.curuser.getUsername());
					file.setType(2);
					file.setNodeid(nodeid);
					file.setActionid(actionid);
					file.setActionid(actionid);
					getSession().save(file);
				} catch (IOException e) {
					message = "文件上传错误：" + e.getMessage();
				}
			}
		}

		if (isedit) {
			susong = (TlawSusong) getSession().get(TlawSusong.class, caseid);
			susong.setThecontent(Hibernate.createClob(thecontent));
			getSession().update(susong);
			message = "诉讼案件诉状材料修改成功";
			super.saveOperlog("修改案件诉状材料", 2);
		} else {
			susong = new TlawSusong();
			susong.setCaseid(caseid);
			susong.setThecontent(Hibernate.createClob(thecontent));
			susong.setCreatetime(timestamp);
			susong.setCreateuserid(this.curuser.getUserid());
			susong.setCreateusername(this.curuser.getUsername());

			susong.setCreateuserid(this.curuser.getUserid());
			susong.setCreateusername(this.curuser.getUsername());
			super.saveOperlog("记录案件诉状材料", 1);
			getSession().save(susong);
			// 提交了诉状材料后,更新案件的状体为审批状态
			// getSession().createSQLQuery("update tlaw_lawcase set nodeid=21004
			// where caseid="+caseid).executeUpdate();
			com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(), caseid, "susongtijiaodate",
					dfyyyymmdd.format(new java.util.Date()));

			this.message = "诉讼案件诉状材料新增成功";
		}

		// TwflNode node = (TwflNode) getSession().get(TwflNode.class, nodeid);
		// Set<TwflDirection> directons = node.getToNode();
		// Iterator<TwflDirection> tonodes = directons.iterator();
		// if (directons.size() == 1) {
		// nodeid = tonodes.next().getToNode().getNodeid();
		// } else {
		// while (tonodes.hasNext()) {
		// TwflDirection d = tonodes.next();
		// if (d.getDescription() != null && tonext != null &&
		// d.getDescription().equals(tonext))
		// nodeid = d.getToNode().getNodeid();
		// }
		// }
		TwflNode nextnode = com.changpeng.lawcase.util.LawcaseUtil.getNextNode(getSession(), nodeid, tonext);
		if (nextnode == null) {
			this.message = "下一个节点为空,请联系管理员";
			return "message";
		}
		this.nodeid = nextnode.getNodeid();
		this.btnvalue = "提交到下一节点:" + nextnode.getNodename();
		return "flowmessage";
	}

	public String input() throws Exception {
		this.susong = (TlawSusong) getSession().get(TlawSusong.class, caseid);
		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		if (this.lawcase == null) {
			this.message = "该案件不存在,请返回";
			return "message";
		}
		if (this.susong != null) {
			isedit = true;
			this.thecontent = Clob2String.clob2String(susong.getThecontent());
			this.filelist = getSession().createQuery("from TlawFiles a where a.type=2 and a.caseid=" + caseid).list();

		} else {
			susong = new TlawSusong();
		}
		return "input";
	}

	private List filelist;

	public List getFilelist() {
		return this.filelist;
	}

	private long caseid;
	private boolean isedit;
	private String thecontent;

	private com.changpeng.lawcase.model.TlawSusong susong;

	public TlawSusong getSusong() {

		return susong;
	}

	private TlawLawcase lawcase;

	public TlawLawcase getLawcase() {
		return this.lawcase;
	}

	/**
	 * @return the caseid
	 */
	public long getCaseid() {
		return caseid;
	}

	/**
	 * @param caseid
	 *            the caseid to set
	 */
	public void setCaseid(long caseid) {
		this.caseid = caseid;
	}

	/**
	 * @return the isedit
	 */
	public boolean getIsedit() {
		return isedit;
	}

	/**
	 * @param isedit
	 *            the isedit to set
	 */
	public void setIsedit(boolean isedit) {
		this.isedit = isedit;
	}

	/**
	 * @return the thecontent
	 */
	public String getThecontent() {
		return thecontent;
	}

	/**
	 * @param thecontent
	 *            the thecontent to set
	 */
	public void setThecontent(String thecontent) {
		this.thecontent = thecontent;
	}

	// 用File数组来封装多个上传文件域对象
	protected File[] upload;
	// 用String数组来封装多个上传文件名
	protected String[] uploadFileName;

	/**
	 * @return the upload
	 */
	public File[] getUpload() {
		return upload;
	}

	/**
	 * @param upload
	 *            the upload to set
	 */
	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	/**
	 * @return the uploadFileName
	 */
	public String[] getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 *            the uploadFileName to set
	 */
	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
}

package com.changpeng.nonlaw.action;


import java.io.*;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.TnlwNonlaw;
import com.changpeng.operation.model.*;

/**
 * 资料对碰，主要为更新当前逾期期数，逾期本金，当前逾期利息，(逾期金额为本金+利息)及当前贷款余额等
 * @author sinhoo
 * Jul 18, 2009
 */

public class NonlawUpdateBatchAction extends AbstractAction {
	private long bankid;
	private String consigndate;
	private File file;
	private String fileName;
	private String contenttype;
	
	public String getConsigndate() {
		return consigndate;
	}

	public File getFile() {
		return file;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public void setBankid(long bankid) {
		this.bankid = bankid;
	}

	public void setConsigndate(String consigndate) {
		this.consigndate = consigndate;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
	public NonlawUpdateBatchAction() {
           rights="nlw1,2";

	}
	private static String getExtention(String fileName){
    	int pos=fileName.lastIndexOf(".");
    	return fileName.substring(pos);
    }
	public String go() throws HibernateException {
		//getSession();
		if(fileName!=null&&!"".equals(fileName)){
			String extendPath="/uploads/";
			String name="NonlawUpdateBatch"+new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
			String toPath=ServletActionContext.getServletContext().getRealPath("")+extendPath;
			String ext=getExtention(fileName);		
			String filename=name+ext;		
			try {
				FileUtils.forceMkdir(new File(toPath)); //创建目录
				File dest=new File(toPath+filename);
				FileUtils.copyFile(file, dest); //移动文件
				if(!ext.equalsIgnoreCase(".xls")){
					message = "对碰文件必须为.xls格式文件";
					FileUtils.forceDelete(dest);
					return ERROR;
				}else{
					int count=com.changpeng.nonlaw.util.NonlawUpdateBatch.update(dest, bankid, getSession());
					if(count>0){
						message="本次成功更新催收任务信息"+count+"条";
					}else{
						message="系统中未有找到匹配记录！";
					}
				}
			}catch(IOException e){
				message = "上传对碰文件错误："+e.getMessage();
				return ERROR;
			}		
		}else{			
			message="上传文件不能为空";
			return ERROR;
		}	
        nextpage="nonlawList.action";
      //  message="保存成功！";
		return SUCCESS;
	}

        public String input() throws Exception {
        	//listBank=com.changpeng.operation.util.OperationUtil.listBank;
            return "input";
    }
}

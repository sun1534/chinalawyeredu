package com.changpeng.operation.action;


import java.io.*;
import java.text.DateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;


/**
 * 更新信用卡记录信息
 * @author sinhoo
 * Jul 18, 2009
 */
public class CreditcardUpdateBatchAction extends AbstractAction {
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
	public CreditcardUpdateBatchAction() {
           rights="opr2,2";

	}
	private static String getExtention(String fileName){
    	int pos=fileName.lastIndexOf(".");
    	return fileName.substring(pos);
    }
	public String go() throws HibernateException {
		getSession();
		
		DateFormat df=new java.text.SimpleDateFormat("yyyyMM");
		String nowmonth=df.format(new Date());
		if (fileName != null && !"".equals(fileName)) {
			
//			String extendPath = "/uploads/"+nowmonth;
			String extendPath = "/uploads/"+nowmonth+"/";
			String toPath = ServletActionContext.getServletContext().getRealPath("") + extendPath;
			File dir=new File(toPath);
			if(!dir.exists())
			{
				dir.mkdirs();
			}
	
	
			String name="CreditcardUpdateBatch"+new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		
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
					int count=com.changpeng.operation.util.CreditcardUpdateBatch.update(dest, bankid,curuser.getUserid());
					
					if(count>0){
						message="本次成功更新信用卡信息"+count+"条";
					}else{
						message="系统中未有找到匹配记录！";
					}
				}
			}catch(Exception e){
				message = "处理对碰文件错误："+e.getMessage();
				return ERROR;
			}		
		}else{			
			message="上传文件不能为空";
			return ERROR;
		}	
        nextpage="creditcardList.action";
      //  message="保存成功！";
		return SUCCESS;
	}

        public String input() throws Exception {
        	//listBank=com.changpeng.operation.util.OperationUtil.listBank;
            return "input";
    }
}

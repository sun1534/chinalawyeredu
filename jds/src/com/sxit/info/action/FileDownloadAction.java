/**
 * 
 */
package com.sxit.info.action;

import java.io.InputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.sxit.common.action.AbstractListAction;
import com.sxit.info.model.TinfAttach;

/**
 * @author sg
 * @TODO 
 * @createtime 2008-9-2
 * @version v1.0
 */
public class FileDownloadAction extends AbstractListAction {
	
	private Long attachid=(long)0;
//	private List infoAttachList;
	private TinfAttach attach;
    private String fileName;
    private String inputPath;

	@Override
	protected String go() throws JDBCException, HibernateException {
		try {

			if(attachid!=0){
				attach = (TinfAttach)getSession().get(TinfAttach.class, attachid);
				this.setInputPath(attach.getFilepath());
				this.setFileName(attach.getFilename());
			}else{
				message = "没有这个附件";
				return "message";
			}
			System.out.println("file=========="
					+ ServletActionContext.getServletContext().getRealPath("")
					+ this.getInputPath());
			java.io.File file = new java.io.File(ServletActionContext
					.getServletContext().getRealPath("")
					+ this.getInputPath());
			if (!file.exists()) {
				message = "下载出错:文件不存在";
				return ERROR;
			} else {
				ServletActionContext.getResponse().setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ new String(this.fileName.getBytes("gb2312"),
										"iso-8859-1"));
				return SUCCESS;
			}
		} catch (Exception e) {
			message = "下载出错:" + e.toString();
			System.out.println("=======:" + e.toString());
			return ERROR;
		}
	}
	
	
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
    
    public void setInputPath(String value) {
        try {
            this.inputPath = value;// new String(value.getBytes("ISO-8859-1"), "UTF-8");
        }
        catch (Exception e) {
            System.out.println("====="+e.toString());
        }
    }

    public String getInputPath() {
        return this.inputPath;
    }
    
    private String contentTypeString;
    public void setContentTypeString(String contentTypeString) {
        this.contentTypeString = contentTypeString;
    }

    public String getContentTypeString() {
        return this.contentTypeString;
    }
    
    public Long getAttachid(){
    	return this.attachid;
    }
    
    public void setAttachid(Long attachid){
    	this.attachid= attachid;
    }

    /*
         下载用的Action应该返回一个InputStream实例，
         该方法对应在result里的inputName属性值为targetFile
     */
    public InputStream getTargetFile() throws Exception {
        return ServletActionContext.getServletContext().getResourceAsStream(
            this.inputPath);
    }

}

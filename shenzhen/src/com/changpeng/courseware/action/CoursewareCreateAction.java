package com.changpeng.courseware.action;
import java.io.*;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.*;
public class CoursewareCreateAction extends AbstractAction{
	private Courseware ware;
	private int typeid;
	private List typelist;
	
	private File uploadfile; //上传课件
	private String fileName;
	//private String contenttype;
	private String warecontent; //编辑课件内容
	
	private BasicService service;
	public CoursewareCreateAction(){
		this.rightCode="courseware";
		ware=new Courseware();
		service=(BasicService)this.getBean("basicService");
	}
	public Courseware getWare() {
		return ware;
	}
	public void setWare(Courseware ware) {
		this.ware = ware;
	}
	@Override
	protected String go() throws Exception {
		String indexDir = ServletActionContext.getServletContext().getRealPath("/coursewarefiles")+ System.getProperty("file.separator");
		String name=new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		
		try{
			if(ware.getIsupload()){ //上传课件
				int index = fileName.lastIndexOf(".");
				name += fileName.substring(index);
				
				File destFile = new File(indexDir + name);
				FileUtils.copyFile(uploadfile, destFile);
			
			}else{ //课件内容保存成html文件
				name+=".html";
				BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(new File(indexDir+name)));

				StringBuffer buffer=new StringBuffer();
				buffer.append("<html>\r\n<head>\r\n");
				buffer.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">\r\n");
				buffer.append("<link href=\"../css/css.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
				buffer.append("<style type=\"text/css\">\r\n");
				buffer.append("<!--");
				buffer.append("body {background-color: #DAE7F6;}");
				buffer.append("-->\r\n");
				buffer.append("</style>\r\n</head>\r\n<body>\r\n");
				warecontent=buffer.toString()+warecontent;
				warecontent+="\r\n</body>\r\n</html>";
				
				out.write(warecontent.getBytes());
				out.close();
			}
		}catch(IOException ex){
			this.message="处理课件出错，请联系管理员";
			return "message";
		}
		
		ware.setWarefile(name);
		ware.setCreateuser(getLoginUser().getLoginname());
		ware.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		ware.setReadcount(0);
		service.save(ware);
		
		//该课件类别下附件数量加1
		Coursetype type=(Coursetype)service.get(Coursetype.class, ware.getCoursetype().getTypeid());
		type.setWarecount(type.getWarecount()+1);
		service.update(type);
		
		this.nextPage="coursewareList.pl?typeid="+ware.getCoursetype().getTypeid();
		this.message="新增课件成功";
		return SUCCESS;
	}
	public String input() throws Exception{		
		this.ware.setWaretime(new Float(20.0));
		this.ware.setXuefen(new Float(0.0));
		typelist=service.findAll(Coursetype.class);
		
		return INPUT;
	}
	public List getTypelist() {
		return typelist;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	
	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}
	public void setUploadfileFileName(String fileName) {
		this.fileName = fileName;
	}
	//public void setUploadfileContenttype(String contenttype) {
	//	this.contenttype = contenttype;
	//}
	public void setWarecontent(String warecontent) {
		this.warecontent = warecontent;
	}

}

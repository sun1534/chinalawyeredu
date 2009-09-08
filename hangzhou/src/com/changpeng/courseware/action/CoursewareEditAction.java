package com.changpeng.courseware.action;

import java.io.*;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.common.util.StringUtil;

import com.changpeng.models.*;

public class CoursewareEditAction extends AbstractAction {
	private int wareid;
	private String warecontent;
	private BasicService service;
	private Courseware ware;
	private List typelist;
	public void setWareid(int wareid) {
		this.wareid = wareid;
	}
	public void setWare(Courseware ware) {
		this.ware = ware;
	}
	public Courseware getWare() {
		if (ware == null)
			ware = (Courseware) get("ware");
		return ware;
	}

	public CoursewareEditAction() {
		service = (BasicService) this.getBean("basicService");
		this.rightCode = "courseware";
	}

	@Override
	protected String go() throws Exception {
		try{
			String warefile=ServletActionContext.getServletContext().getRealPath("/coursewarefiles")+ System.getProperty("file.separator")+ware.getWarefile();
			BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(new File(warefile)));
			out.write(warecontent.getBytes());
			out.close();
		}catch(IOException e){
			debug(e.getMessage());
			this.message="编辑课件错误";
			return "message";
		}		
		service.update(ware);
		this.nextPage = "coursewareList.pl?typeid="+ware.getCoursetype().getTypeid();
		this.message = "课件编辑成功";
		return SUCCESS;
	}

	public String input() throws Exception {
		typelist=service.findAll(Coursetype.class);
		
		ware = (Courseware) service.get(Courseware.class, wareid);	
		String warefile=ServletActionContext.getServletContext().getRealPath("/coursewarefiles")+ System.getProperty("file.separator")+ware.getWarefile();
		warecontent="";
		//FileInputStream in=null;
		BufferedReader in=null;
		try{
			/*in=new FileInputStream(warefile);
			byte[] buf=new byte[1024];
			int m=0;
			while((m=in.read(buf))!=-1){
				warecontent+=new String(buf,0,m,"utf-8");
			}*/
			in=new BufferedReader(new FileReader(warefile));
			String line="";
			while((line=in.readLine())!=null){
				warecontent+=line;
			}
		}catch(IOException e){
			debug(e.getMessage());
			this.message="读取课件错误，请联系管理员";
			return "message";
		}finally{
			if(in!=null) in.close();
		}
		
		set("ware", ware);
		
		return INPUT;

	}
	public String getWarecontent() {
		return warecontent;
	}
	public void setWarecontent(String warecontent) {
		this.warecontent = warecontent;
	}
	public List getTypelist() {
		return typelist;
	}
	
}

package com.sxit.info.action;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;
import com.sxit.info.util.AttachFile;
import com.sxit.info.util.Power;
import com.sxit.system.model.TsysUser;
import com.sxit.system.model.TsysDepartment;


/**
 *
 * <p>功能： 创建信息</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-27</p>
 * @版本： V1.0
 * @修改：
 */

public class InfoCreateAction extends AbstractAction {

	private TinfInfo info;
	private Map powerMap = new HashMap();
	private String departmentid;
	private String typeid;
	private List departmentlist;//部门列表信息
	private File upload;
	private String fileName;
	List typelist = new ArrayList();


	public InfoCreateAction() {
           rights="inf3,2";
		info = new TinfInfo();
		departmentlist = new ArrayList();
	}
	
	

	public String go() throws HibernateException {
		
	
		info.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		info.setCreateuser((TsysUser)get("curuser"));
		getSession().save(info);
		
		if(upload != null&& fileName!=null&&!fileName.equals("")){
			
			String filepath2 = AttachFile.upload(upload, fileName);
			TinfAttach attach = new TinfAttach();
			attach.setTinfInfo(info);
			attach.setFilename(fileName);
			attach.setFilepath(filepath2);
			System.out.println(filepath2);
			getSession().save(attach);
		}
        

		set("info", info);
                nextpage="infoView.action?infoid="+info.getInfoid()+"&pagenumber=0";
                message="保存成功！";
		return SUCCESS;
	}


    public String input() throws Exception {

		typelist = Power.whichPower(getSession(), (TsysUser) get("curuser"),
				"newinf");

		TsysDepartment corp = (TsysDepartment) getSession().get(
				TsysDepartment.class, Integer.valueOf(1));
		if (corp != null) {
			selectSet(corp);
		} else {
			message = "部门缺少根，请与管理员联系！";
			return "message";
		}

		return "input";
    }
    
    private String tempstr = "";
    public void selectSet(TsysDepartment corp) {
        String diplayname = tempstr + "|";
        corp.setDisplayname((diplayname +"-"+ corp.getDepartmentname()).substring(2));
        this.departmentlist.add(corp);
        if (!corp.getChildren().isEmpty()) {
            for (TsysDepartment child : corp.getChildren()) {
                if (child.getDepartmentid() != 1) {
                    tempstr = diplayname+" ";
                    selectSet(child);
                }
            }
        }
    }
    
    public Map getPowerMap(){
    	return this.powerMap;
    }
    
	public TinfInfo getInfo() {
		return info;
	}
	
	public List getDepartmentlist(){
		return this.departmentlist;
	}
	
	public File getUpload(){
		return this.upload;
	}
	
	public void setUpload(File upload){
		this.upload = upload;
	}
	
	public String getUploadFileName(){
		return this.fileName;
	}
	
	public void setUploadFileName(String fileName){
		this.fileName = fileName;
	}
	
	public List getTypelist(){
		return this.typelist;
	}
	 
}

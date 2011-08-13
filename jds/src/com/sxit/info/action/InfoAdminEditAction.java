package com.sxit.info.action;


import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;
import com.sxit.info.util.Power;
import com.sxit.info.util.AttachFile;
import com.sxit.system.model.TsysDepartment;
import com.sxit.system.model.TsysUser;
import com.sxit.wait.util.WaitWork;




/**
 *
 * <p>功能： 编辑信息</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-27</p>
 * @版本： V1.0
 * @修改：
 */

public class InfoAdminEditAction extends AbstractAction {

	private TinfInfo info;
	private List departmentlist;//部门列表信息
	private File upload;
	private String fileName;
	List typelist = new ArrayList();
    private List infoAttachList;

	public InfoAdminEditAction() {
          rights="inf6,4";
  		departmentlist = new ArrayList();
	}

	public String go() throws HibernateException {
		System.out.println("################update");
		info.setModifytime(new java.sql.Timestamp(System.currentTimeMillis()));
		info.setModifyuser((TsysUser)get("curuser"));
		getSession().update(info);
		
		set("info", info);
		nextpage="infoAdminView.action?infoid="+info.getInfoid()+"&pagenumber=0";
		message = "保存成功！";
		return SUCCESS;
	}



	public String input() throws Exception {
		info = (TinfInfo) get("info");
		System.out.println("==============infoid" + info.getInfoid());
		typelist = queryTypeList().list();
		infoAttachList = (List)get("infoAttachList");

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
    
    private Query queryTypeList(){
        String queryName;
        queryName =
            "from TinfType as inftype order by inftype.typeid";
        Query query = getSession().createQuery(queryName);
        return query;
	}
    
	public TinfInfo getInfo() {
		if (info == null)
			info = (TinfInfo) get("info");
		return info;
	}
	
	public void setInfo(TinfInfo info){
		this.info = info;
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
	
	public List getInfoAttachList(){
		return this.infoAttachList;
	}
	


}

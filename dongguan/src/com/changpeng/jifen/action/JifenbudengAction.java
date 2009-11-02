/**
 * JifenbudengAction.java
 */

package com.changpeng.jifen.action;

import java.io.*;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.JifenbudengService;
import com.changpeng.models.Jifenbudeng;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;

/**
 * @author 华锋 2008-5-4 下午10:46:27
 * 
 */
public class JifenbudengAction extends AbstractAction {

	private Jifenbudeng budeng;
	
	private boolean beupload;
	private File uploadfile; //上传文件
	private String fileName;
	
	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}
	public void setUploadfileFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setBeupload(boolean beupload){
		this.beupload=beupload;
	}
	
	public Jifenbudeng getBudeng() {
		if (budeng == null)
			this.budeng = (Jifenbudeng) get("budeng");
		return this.budeng;
	}

	@Override
	public String go() throws Exception {
		// 积分的补登，同时应计入积分表里面，考虑事务来处理
		JifenbudengService budengservice = (JifenbudengService) getBean("jifenbudengService");
		SysUserService userservice = (SysUserService) getBean("sysUserService");
		SysUser user = (SysUser) this.getLoginUser();
		

		SysUser lawer = null;
		if (get("budengexist") != null && "0".equals(get("budengexist"))) {			

			if(!beupload){
				lawer=userservice.getSysUserByLawerNo(budeng.getLawerno());
				if (lawer == null) {
					this.message = "执业资格证号:" + budeng.getLawerno() + "对应的律师资料不存在,请返回";
					this.nextPage = "jifenbudeng!input.pl?budengid=" + this.budengid;
					return "message";
				}
				budeng.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				budeng.setCreateuser(user.getUsername());
				budeng.setLawer(lawer);
				budengservice.saveJifenbudeng(budeng);
			}else{ //解析上传文件
				/*int count=0;
				String temp="";
				BufferedReader reader=new BufferedReader(new FileReader(uploadfile));
				String lawerno="";
				while((lawerno=reader.readLine())!=null){
					lawerno=lawerno.trim();
					lawer=userservice.getSysUserByLawerNo(lawerno);
					if(lawer!=null){						
						budeng.setLawerno(lawerno);
						
						budeng.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
						budeng.setCreateuser(user.getUsername());
						budeng.setLawer(lawer);
						budengservice.saveJifenbudeng(budeng);
						count++;
					}else{
						temp+=lawerno+" ";
					}					
				}
				if(count==0){
					this.message = "文件列表中对应的律师资料不存在，请返回";
					this.nextPage = "jifenbudeng!input.pl?budengid=" + this.budengid;
					return "message";
				}
				else if(count>0&&!"".equals(temp)){
					this.message = "积分补登成功，其中执业资格证号为<font color='red'>\"" + temp.trim() + "\"</font>对应的律师资料不存在，被忽略";
					this.nextPage="jifenbudengList.pl";
					return SUCCESS;
				}*/
				this.message=budengservice.saveJifenbudeng(budeng.getTitle(),budeng.getXuefen(),budeng.getBudengdate(), uploadfile, getLoginUser());
				this.nextPage="jifenbudengList.pl";
				return SUCCESS;
			}		
		}
		else {
			Float oldxuefen=(Float)get("oldbudeng");
			
			debug("补登前后的积分差异为:::"+(budeng.getXuefen().floatValue()-oldxuefen.floatValue()));
			
			budengservice.updateJifenbudeng(budeng,oldxuefen);
		}
		this.message="积分补登成功";
		this.nextPage="jifenbudengList.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		BasicService basic = (BasicService) getBean("basicService");
		this.budeng = (Jifenbudeng) basic.get(Jifenbudeng.class, budengid);
		if (this.budeng == null) {
			set("budengexist", "0");
			this.budeng = new Jifenbudeng();
		}
		else {
			set("oldbudeng",this.budeng.getXuefen());
			set("budengexist", "1");
		}
		set("budeng",budeng);

		return INPUT;
	}

	private int budengid;

	public void setBudengid(int budengid) {
		this.budengid = budengid;
	}

	public int getBudengid() {
		return this.budengid;
	}



	
}

package com.xjdzjc.model;

public class Members {
	public  String tel_number,service,chepai,remark,banner;
	public int chepai_type,active, score, mt_flag;
	public boolean chepaiflag;
	
	public boolean isChepaiflag() {
		return chepaiflag;
	}
	public void setChepaiflag(boolean chepaiflag) {
		this.chepaiflag = chepaiflag;
	}
	public String getTel_number() {
		return tel_number;
	}
	public void setTel_number(String telNumber) {
		tel_number = telNumber;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getChepai() {
		return chepai;
	}
	public void setChepai(String chepai) {
		this.chepai = chepai;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		if(banner!=null&&!banner.equals(""))
		{
			if(!banner.endsWith(":")&&!banner.endsWith("："))
			  this.banner=banner+":";
			else
			 this.banner=banner;
		}else {
			this.banner="尊敬的【12580移动警务】会员：";
		}
		
	}
	public int getChepai_type() {
		return chepai_type;
	}
	public void setChepai_type(int chepaiType) {
		chepai_type = chepaiType;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getMt_flag() {
		return mt_flag;
	}
	public void setMt_flag(int mtFlag) {
		mt_flag = mtFlag;
	}
	
}

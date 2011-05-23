package com.changpeng.diaocha.action;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Diaochaoption;
import com.changpeng.models.Diaochatype;
import com.changpeng.models.Diaochawenti;
public class WentiEditAction extends AbstractAction{
	private int wentiid;
	private String optionscontent;
	private int listorder;
	private boolean others; //是否输入其他内容，对选择题有效
	private boolean hastype; //定义该调查是否有分类
	private List<Diaochatype> diaochatypes;// = new HashSet<Diaochatype>(0);
	private Integer typeid; //标记问题类型
	private Diaochawenti wenti;
	
	private BasicService service;
	
	
	
	private String title;
	private int wentileixing;
	private boolean isbixu;
	private String remarks;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getWentileixing() {
		return wentileixing;
	}
	public void setWentileixing(int wentileixing) {
		this.wentileixing = wentileixing;
	}
	public boolean isIsbixu() {
		return isbixu;
	}
	public void setIsbixu(boolean isbixu) {
		this.isbixu = isbixu;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public WentiEditAction(){
		this.rightCode="diaocha";
		service=(BasicService)this.getBean("basicService");
	}	
	public int getWentiid() {
		return wentiid;
	}
	public void setWentiid(int wentiid) {
		this.wentiid = wentiid;
	}
	public String getOptionscontent() {
		return optionscontent;
	}
	public void setOptionscontent(String optionscontent) {
		this.optionscontent = optionscontent;
	}
	public boolean isOthers() {
		return others;
	}
	public void setOthers(boolean others) {
		this.others = others;
	}
	public boolean isHastype() {
		return hastype;
	}
	public List<Diaochatype> getDiaochatypes() {
		return diaochatypes;
	}
	public void setTypeid(Integer typeid) {
		this.typeid=typeid;
	}
	public Integer getTypeid(){
		return typeid;
	}
	public Diaochawenti getWenti(){
		return wenti;
	}
	public void setWenti(Diaochawenti wenti){
		this.wenti=wenti;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String go() throws Exception {
		wenti=(Diaochawenti)service.get(Diaochawenti.class, wentiid);
		
		Diaochatype diaochatype=null;
		if(typeid!=null&&typeid!=0)
			 diaochatype=(Diaochatype)service.get(Diaochatype.class, typeid);
		
		java.sql.Timestamp now=new java.sql.Timestamp(System.currentTimeMillis());
		
		wenti.setTitle(title);
		wenti.setIsbixu(isbixu);
		wenti.setWentileixing(wentileixing);
		wenti.setRemarks(remarks);
		
		wenti.setDiaochatype(diaochatype);
		wenti.setCreateuser(getLoginUser().getUsername());
		wenti.setCreatetime(now);
		wenti.setListorder(listorder);
		
		System.out.println("==============wenti.getListorder():::"+listorder);
		
		service.update(wenti);
		
		//删除该问题原有选项
		service.execute("delete from Diaochaoption where diaochawenti.wentiid="+wenti.getWentiid()); 
		
		if(wenti.getWentileixing()==1||wenti.getWentileixing()==2){ //单选或多选题
			String str[]=optionscontent.split("\r\n");
			
			java.util.HashMap map=new java.util.HashMap();
			map.put(1, "A");
			map.put(2, "B");
			map.put(3, "C");
			map.put(4, "D");
			map.put(5, "E");
			map.put(6, "F");
			map.put(7, "G");
			map.put(8, "H");
			map.put(9, "I");
			map.put(10, "J");
			map.put(11, "K");
			map.put(12, "L");
			map.put(13, "M");
			map.put(14, "N");
			map.put(15, "O");
			map.put(16, "P");
			map.put(17, "Q");
			map.put(18, "R");
			map.put(19, "S");
			map.put(20, "T");
			map.put(21, "U");
			
			int i=0;
			for(String s:str){		
				if(s!=null&&!"".equals(s.trim())){
					i++;
					if(i>20){
						/*this.message="每道调查题最多只能创建6个选项";
						this.nextPage="wentiList.pl?diaochaid="+diaochaid;
						return SUCCESS;*/
						break;
					}	
					Diaochaoption option=new Diaochaoption();
					option.setDiaochawenti(wenti);
					option.setTitle(s);
					option.setOptions(map.get(i).toString());				
					option.setCreatetime(now);
					option.setOthers(false);
					service.save(option);
					/*if(i>6){
						this.message="每道调查题最多只能创建6个选项";
						this.nextPage="wentiList.pl?diaochaid="+diaochaid;
						return SUCCESS;
					}	*/				
				}
			}
			if(others){ //新增其他选项
				i=(i>=21)?21:(i+1);
				Diaochaoption option=new Diaochaoption();
				option.setDiaochawenti(wenti);
				option.setTitle("其他");
				option.setOptions(map.get(i).toString());				
				option.setCreatetime(now);
				option.setOthers(true);
				service.save(option);
			}
		}
		
		this.nextPage="wentiList.pl?diaochaid="+wenti.getDiaocha().getDiaochaid();
		this.message="编辑问题成功";
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String input() throws Exception{
		wenti=(Diaochawenti)service.get(Diaochawenti.class, wentiid);

		if(wenti.getDiaochatype()!=null)
			typeid=wenti.getDiaochatype().getTypeid();
		
		title=wenti.getTitle();
		isbixu=wenti.getIsbixu();
		remarks=wenti.getRemarks();
		wentileixing=wenti.getWentileixing();
		listorder=wenti.getListorder();
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Diaochatype.class);
		detachedCriteria.add(Restrictions.eq("diaocha.diaochaid", wenti.getDiaocha().getDiaochaid()));		
		detachedCriteria.addOrder(Order.asc("typeid"));
		
		diaochatypes=service.findAllByCriteria(detachedCriteria);
		
		
		detachedCriteria = DetachedCriteria.forClass(Diaochaoption.class);
		detachedCriteria.add(Restrictions.eq("diaochawenti.wentiid", wenti.getWentiid()));		
		detachedCriteria.addOrder(Order.asc("optionid"));
		List<Diaochaoption> options=service.findAllByCriteria(detachedCriteria);
		optionscontent="";
		others=false;
		if(options!=null){
			for(Diaochaoption option:options){
				if(!option.getOthers())
					optionscontent+=option.getTitle()+"\r\n";
				if(option.getOthers()==true)
					others=true;
			}
		}
		//拥有分类
		if(diaochatypes!=null&&diaochatypes.size()>0)
			hastype=true;
		
		return "input";
	}
	public int getListorder() {
		return listorder;
	}
	public void setListorder(int listorder) {
		this.listorder = listorder;
	}
}

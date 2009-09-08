package com.changpeng.diaocha.action;


import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.*;

import java.util.*;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
public class WentiCreateAction extends AbstractAction{
	private int diaochaid;
	private Diaochawenti wenti;
	private String optionscontent;
	
	private boolean others; //是否输入其他内容，对选择题有效
	private boolean hastype; //定义该调查是否有分类
	private List<Diaochatype> diaochatypes;// = new HashSet<Diaochatype>(0);
	private Integer typeid; //标记问题类型
	
	public void setOptionscontent(String optionscontent) {
		this.optionscontent = optionscontent;
	}
	public WentiCreateAction(){
		this.rightCode="diaocha";
		wenti=new Diaochawenti();
	}
	public Diaochawenti getWenti() {
		return wenti;
	}
	public void setWenti(Diaochawenti wenti) {
		this.wenti = wenti;
	}
	public int getDiaochaid() {
		return diaochaid;
	}
	public void setDiaochaid(int diaochaid) {
		this.diaochaid = diaochaid;
	}
	
	public List<Diaochatype> getDiaochatypes() {
		return diaochatypes;
	}
	public boolean isHastype() {
		return hastype;
	}
	
	public void setOthers(boolean others) {
		this.others = others;
	}
	public void setTypeid(Integer typeid){
		this.typeid=typeid;
	}
	@SuppressWarnings("unchecked")
	@Override
	protected String go() throws Exception {

		BasicService service=(BasicService)this.getBean("basicService");
		Diaocha diaocha=(Diaocha)service.get(Diaocha.class, diaochaid);
		Diaochatype diaochatype=null;
		if(typeid!=null&&typeid!=0)
			 diaochatype=(Diaochatype)service.get(Diaochatype.class, typeid);
		
		java.sql.Timestamp now=new java.sql.Timestamp(System.currentTimeMillis());
		wenti.setDiaocha(diaocha);
		wenti.setDiaochatype(diaochatype);
		wenti.setCreateuser(getLoginUser().getUsername());
		wenti.setCreatetime(now);
		service.save(wenti);
		
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
		
		this.nextPage="wentiList.pl?diaochaid="+diaochaid;
		this.message="新增问题成功";
				
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String input() throws ServiceException{	
		BasicService service=(BasicService)this.getBean("basicService");
		//Diaocha diaocha=(Diaocha)service.get(Diaocha.class, diaochaid);
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Diaochatype.class);
		detachedCriteria.add(Restrictions.eq("diaocha.diaochaid", diaochaid));		
		detachedCriteria.addOrder(Order.asc("typeid"));
		
		diaochatypes=service.findAllByCriteria(detachedCriteria);
		
		//拥有分类
		if(diaochatypes!=null&&diaochatypes.size()>0)
			hastype=true;
		
		return INPUT;
	}

}

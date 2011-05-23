/**
 * DiaochExport.java
 */
package com.changpeng.diaocha.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.changpeng.models.Diaochawenti;

/**
 * @author 刘哈哈
 * May 23, 201110:38:21 PM
 *
 */
public class DiaochaExport {

	
	
	private int diaochaid;
	private String batchid;
	private String lawyerno;
	private String lawyername;
	private String replytime;
	
	//初始化对象的的时候,应该根据问题列表，初始化这个列表
	private Map<Integer,List> answers=new LinkedHashMap<Integer,List>();

	
	public DiaochaExport(){
		
	}
	
	public DiaochaExport(Collection wentilist){
		int len=wentilist==null?0:wentilist.size();
		if(len>0){
			Iterator iterator=wentilist.iterator();
			while(iterator.hasNext()){
				Diaochawenti wenti=(Diaochawenti)iterator.next();
				answers.put(wenti.getWentiid(), new ArrayList());//所有的答案都为空
				
			}
		}
	}
	
	public static void main(String[] args){
		String s="A B ";
		String[] ss=s.split(" ");
		for(String sss:ss){
			System.out.println(sss);
		}

	}
	
	/**
	 * @return the diaochaid
	 */
	public int getDiaochaid() {
		return diaochaid;
	}

	/**
	 * @param diaochaid the diaochaid to set
	 */
	public void setDiaochaid(int diaochaid) {
		this.diaochaid = diaochaid;
	}

	/**
	 * @return the batchid
	 */
	public String getBatchid() {
		return batchid;
	}

	/**
	 * @param batchid the batchid to set
	 */
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

	/**
	 * @return the lawyerno
	 */
	public String getLawyerno() {
		return lawyerno;
	}

	/**
	 * @param lawyerno the lawyerno to set
	 */
	public void setLawyerno(String lawyerno) {
		this.lawyerno = lawyerno;
	}

	/**
	 * @return the lawyername
	 */
	public String getLawyername() {
		return lawyername;
	}

	/**
	 * @param lawyername the lawyername to set
	 */
	public void setLawyername(String lawyername) {
		this.lawyername = lawyername;
	}

	/**
	 * @return the answers
	 */
	public Map<Integer, List> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Map<Integer, List> answers) {
		this.answers = answers;
	}

	/**
	 * @return the replytime
	 */
	public String getReplytime() {
		return replytime;
	}

	/**
	 * @param replytime the replytime to set
	 */
	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}


	
}

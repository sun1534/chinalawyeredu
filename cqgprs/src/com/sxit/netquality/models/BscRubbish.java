/**
 * 
 */
package com.sxit.netquality.models;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 华锋
 * Oct 21, 2009-10:18:01 PM
 *
 */
public class BscRubbish extends Volumes{
	private String bvci;
	private long recordtime;
	private List<BscRubbishContent> correctList=new ArrayList<BscRubbishContent>();
	private List<BscRubbishContent> rubbishList=new ArrayList<BscRubbishContent>();
	int correctCnt=0;
	int rubbishCnt=0;
	public int getCorrectCnt(){
		return correctCnt;
	}
	public int getRubbishCnt(){
		return rubbishCnt;
	}
	public int getTrcnt(){
		if(correctCnt>=rubbishCnt)
			return correctCnt;
		else
			return rubbishCnt;
	}
	public int getRubbishSub(){
		return getTrcnt()-getRubbishCnt();
	}

	
	public int getCorrectSub(){
		return getTrcnt()-getCorrectCnt();
	}
	/**
	 * @return the bvci
	 */
	public String getBvci() {
		return bvci;
	}
	/**
	 * @param bvci the bvci to set
	 */
	public void setBvci(String bvci) {
		this.bvci = bvci;
	}
	/**
	 * @return the correctList
	 */
	public List getCorrectList() {
		return correctList;
	}
	/**
	 * @param correctList the correctList to set
	 */
	public void addCorrect(BscRubbishContent correct) {
		correctCnt++;
		this.correctList.add(correct);
	}
	/**
	 * @return the errorList
	 */
	public List getRubbishList() {
		return rubbishList;
	}
	/**
	 * @param errorList the errorList to set
	 */
	public void addRubbish(BscRubbishContent rubbish) {
		
		rubbishCnt++;
		this.rubbishList.add(rubbish);
	}
	/**
	 * @return the recordtime
	 */
	public long getRecordtime() {
		return recordtime;
	}
	/**
	 * @param recordtime the recordtime to set
	 */
	public void setRecordtime(long recordtime) {
		this.recordtime = recordtime;
	}
	public String getRecordtimestr(){
		return df.format(new java.sql.Timestamp(recordtime*1000));
	}
	private static final DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
}

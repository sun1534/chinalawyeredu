package com.changpeng.sns.diary.util;

import java.util.Comparator;
import java.util.Date;

import com.changpeng.sns.diary.model.SnsDiary;

public class Mycomparator implements Comparator{

	public int compare(Object o1, Object o2) {
		SnsDiary temp1 = (SnsDiary)o1;
		//HashMap<String,String> tempMap1 = temp1.getHashMap();
//		int totalCount1 = Integer.parseInt(tempMap1.get("合计").toString());
//		
		SnsDiary temp2 = (SnsDiary)o2;
		//HashMap<String,String> tempMap2 = temp2.getHashMap();
		 Date date1=temp1.getUpdateTime();
		//int totalCount2 = Integer.parseInt(tempMap2.get("合计").toString());
		Date date2=temp2.getUpdateTime();
		if(date1.after(date2)){
			return 1;
		}else{
			return 0;
		}		
	}

}

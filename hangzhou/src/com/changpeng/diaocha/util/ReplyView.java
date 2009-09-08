package com.changpeng.diaocha.util;
import com.changpeng.models.*;
import java.util.*;
public class ReplyView {
	public static String getReplycontent(List<Diaochareply> replys,int wentiid){
		String reply="";
		
		for(Diaochareply diaochareply:replys){
			if(diaochareply.getDiaochawenti().getWentiid()==wentiid)
				reply=diaochareply.getReplycontent();
		}
		
		return reply;
	}
	
	public static String getOthers(List<Diaochareply> replys,int wentiid){
		String other="";
		for(Diaochareply diaochareply:replys){
			if(diaochareply.getDiaochawenti().getWentiid()==wentiid)
				other=diaochareply.getOthers();
		}
		return other;
	}
}

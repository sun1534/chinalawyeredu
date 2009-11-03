package com.changpeng.diaocha.util;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import com.changpeng.models.Diaochareply;
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
	private final static DecimalFormat nf = new DecimalFormat("#####0.00");

	private final static DecimalFormat nff = new DecimalFormat("#####0.00%");
	public static String toMoney(String s) {
		if (s == null || s.equals("")) {
			return "0.00";
		}
		try {
			return toMoney(Double.parseDouble(s));
		} catch (Exception e) {
			return s;
		}
	}

	public static String toMoney(double d) {
		try {
			if(d==0)
				return "0.00";
			return nf.format(d);
		} catch (Exception e) {
			return String.valueOf(d);
		}
	}

	public static String toMoney(BigDecimal d) {
		try {
			if (d == null)
				return "";
			return nf.format(d);
		} catch (Exception e) {
			return String.valueOf(d);
		}
	}
	
    public static String toPercent(double s){
    	
    	try {
			return nff.format(s);
		} catch (Exception e) {
			return String.valueOf(s);
		}
    }

    public static String toPercent(String s){
    	try {
    		int index=s.indexOf("/");
    		double d1=Double.parseDouble(s.substring(0, index));
    		double d2=Double.parseDouble(s.substring(index+1));
    		
    		
			return nff.format(d1/d2);
		} catch (Exception e) {
			System.out.println(e);
			return String.valueOf(s);
		}
    }
    
    public static void main(String[] args){
    	System.out.println(5*100.00/3412);
    }
	
}

package com.sxit.memdevice.common;

import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

public class ClientTZHW {

	InputStream in;
	PrintStream out;
	String prompt = "";
	TelnetClient tc;
	boolean islogin=false;
	
	String ip;
	String username;
	String password;

	
	String tzusername;
	String tzpassword;
	public static String end1 ="~ #";
	public static String end2 ="$";
	public static String end3 =">";

	public ClientTZHW(String ip,String username,String password,String tzip,String tzusername,String tzpassword) throws Exception {
		System.out.println("TelnetClient:"+ip+","+username+","+password+","+tzip+","+tzusername+","+tzpassword);
		this.username=username;
		this.password=password;
		this.ip=ip;
		this.tzusername=tzusername;
		this.tzpassword=tzpassword;
		tc = new TelnetClient();
		tc.connect(tzip, 23);
		in = tc.getInputStream();
		out = new PrintStream(tc.getOutputStream());
		
		String loginresult=logintz();
		if(loginresult!=null&&loginresult.equals("login")){
			loginresult=login();
		}
		System.out.println("loginresult:"+loginresult);
		if(loginresult!=null&&loginresult.equals("login")){
			islogin=true;
		}else{
			islogin=false;
		}
		
	}

	public String login() {
		int step=0;
		try {
			byte[] buff = new byte[1024];
            int ret_read = 0;
            String tmpStr="";
            write("telnet "+ip);
            Thread.sleep(250);
            write("LGI:OP =\""+username+"\",PWD=\""+password+"\";");
            int i=0;
            do
            {
            	ret_read = in.read(buff);
                if(ret_read > 0)
                {
                	i++;
                	String bufstring=new String(buff, 0, ret_read);
                	tmpStr=tmpStr+bufstring;
//                    System.out.println("tmpStr:"+tmpStr);
                    if(tmpStr.contains("RETCODE = 0")){
                    	return "login";
                    }else if(tmpStr.contains("RETCODE = ")){
                    	return "password error";
                    }else if(i>8){
                    	return "time out!";
                    }
                    
                }
                Thread.sleep(250);
            } while (ret_read >= 0);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String logintz() {
		int step=0;
		try {
			byte[] buff = new byte[1024];
            int ret_read = 0;
            String tmpStr="";
            do
            {
                ret_read = in.read(buff);
                
                if(ret_read > 0)
                {
                	String bufstring=new String(buff, 0, ret_read);
                	tmpStr=tmpStr+bufstring;
                    System.out.println("tmpStr:"+tmpStr);
                    if(bufstring.endsWith("login: ")||bufstring.endsWith("login:")){
                    	write(tzusername);
                    	System.out.println("Client-login-tzusername:"+tzusername);
                    	step++;
                    }else if(bufstring.contains("Password: ")||bufstring.contains("Password:")){
                    	write(tzpassword);
                    	System.out.println("Client-login-tzpassword:"+tzpassword);
                    	islogin=true;
                    	step++;
                    }else if(bufstring.trim().endsWith(end1)||bufstring.trim().endsWith(end2)||bufstring.trim().endsWith(end3)){
                    	return "login";
                    }else if(bufstring.trim().toLowerCase().contains("login incorrect")){
                    	return "errcode -999:wrong password";
                    }else if(step>8){
                    	return "errcode -999:wrong password";
                    }
                }
                Thread.sleep(200);
            } while (ret_read >= 0);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String execute(String cmd){
		
		StringBuilder response=new StringBuilder();
		boolean isstart=false;
		boolean isend=false;
		
		byte[] buff = new byte[1024];
        int ret_read = 0;
        try{
        	write(cmd);
        	isstart=true;
			do{
				
	            ret_read = in.read(buff);
	            
	            if(ret_read > 0){
	            	String bufstring=new String(buff, 0, ret_read);
	                System.out.println("--"+bufstring+"E");
	                
					if(islogin&&!isstart&&(bufstring.trim().endsWith(end1)||bufstring.trim().endsWith(end2)||bufstring.trim().endsWith(end3))){
			        	
			        }else if(islogin&&isstart&&(bufstring.trim().endsWith(end1)||bufstring.trim().endsWith(end2)||bufstring.trim().endsWith(end3))){
			        	isend=true;
			        	response.append(bufstring);
			        	return response.toString();
			        }else if(isstart&&!isend){
			        	response.append(bufstring);
			        }
	            }
	        }while (ret_read >= 0);
        }catch(Exception e){
        	
        }
        return response.toString();
	}
	
	/**
	 * 写
	 * 
	 * @param value
	 */
	public void write(String value) {
		try {
			out.println(value);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接
	 * 
	 */
	public void disconnect() {
		try {
			tc.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getres(String ip,String username,String password,String tzip,String tzusername,String tzpassword,String cmd) {
		String string="";
		try {
			
			ClientTZHW telnet = new ClientTZHW(ip,username,password, tzip, tzusername, tzpassword);
			if(telnet.islogin){
				string=telnet.execute(cmd);
				if(string.indexOf(": not found\r\n")>-1||string.indexOf(": Command not found.\r\n")>-1){//
					string="errcode -998:command error";
				}
				System.out.println("response:\r\n"+string);
			}else{
				string="errcode -999:not login";
				System.out.println("not login");
			}
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}
	public static String testlogin(String ip,String username,String password,String tzip,String tzusername,String tzpassword) {
		String string="";
		try {
			
			ClientTZHW telnet = new ClientTZHW(ip,username,password, tzip, tzusername, tzpassword);
			if(telnet.islogin){
				return string="OK";
			}else{
				string="can not login!";
				System.out.println("not login");
			}
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}
	
//	public static String getresponse(String cmd){
//		String res="";
//		try {
//			Client telnet = new Client();
//			res=telnet.execute(cmd);
//			System.out.println("response:\r\n"+res);
//
//			telnet.disconnect();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return res;
//	}
}

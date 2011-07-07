package com.sxit.memdevice.common;

import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

public class Client {

	InputStream in;
	PrintStream out;
	String prompt = "";
	TelnetClient tc;
	boolean islogin=false;
	
	String username;
	String password;

	public static String end1 ="~ #";
	public static String end2 ="$";
	public static String end3 =">";

	public Client(String ip,String username,String password) throws Exception {
		System.out.println("TelnetClient:"+ip+","+username+","+password);
		this.username=username;
		this.password=password;
		tc = new TelnetClient();
		tc.connect(ip, 23);
		in = tc.getInputStream();
		out = new PrintStream(tc.getOutputStream());
		
		String loginresult=login();
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
            do
            {
                ret_read = in.read(buff);
                
                if(ret_read > 0)
                {
                	String bufstring=new String(buff, 0, ret_read);
                	tmpStr=tmpStr+bufstring;
                    System.out.println("tmpStr:"+tmpStr);
                    if(bufstring.endsWith("login: ")||bufstring.endsWith("login:")){
                    	write(username);
                    	System.out.println("Client-login-username:"+username);
                    	step++;
                    }else if(bufstring.contains("Password: ")||bufstring.contains("Password:")){
                    	write(password);
                    	System.out.println("Client-login-password:"+password);
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
//	                System.out.println("--"+bufstring+"E");
	                
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
        	e.printStackTrace();
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

	public static String getres(String ip,String username,String password,String cmd) {
		String string="";
		try {
			
			Client telnet = new Client(ip,username,password);
			if(telnet.islogin){
				string=telnet.execute(cmd);
				if(string.indexOf(": not found\r\n")>-1||string.indexOf(": Command not found.\r\n")>-1){//
					string="errcode -998:command error";
				}
//				System.out.println("response:\r\n"+string);
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
	public static String testlogin(String ip,String username,String password) {
		String string="";
		try {
			
			Client telnet = new Client(ip,username,password);
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

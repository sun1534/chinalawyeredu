package com.changpeng.jifen.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;





/**
 * <pre>
 *
 * </pre> 
 * @author 志平
 * Nov 29, 2011 3:14:57 PM 
 */
public  class Ping {
	public static void main(String args[])    
	{    
		get1();
		get2();
	}    
	
	
	public static int get1(){
		int avgNum=0;
		String[] addrs= {"videos.lawyeredu.com"};   
		if (addrs.length < 1)    
		{    
			System.out.println("syntax Error!");    
		}    
		else    
		{    
			for(int i=0;i<addrs.length;i++)
			{    
				String line = null;    
				try    
				{    
					Process pro = Runtime.getRuntime().exec("ping " + addrs[i]+" -l 1000 -n 4");    
					BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream()));    
					while((line = buf.readLine()) != null)
					{   
						int position=0;   
						if((position=line.indexOf("Average"))>=0)         
						{   							
							String value=line.substring(position+10,line.lastIndexOf("ms"));
							avgNum=Integer.parseInt(value);
							System.out.println("AAAA22:"+value);   
							//String value="/blog/line.substring(position+10,line.lastIndexOf(ms))";   
							//System.out.println("your speed is AAAA:"+(1000/Integer.parseInt(value))+"KB");                  
						}   
					}            
				}    
				catch(Exception ex){    
					ex.printStackTrace();
					System.out.println(ex.getMessage());    
				}    
			}    
		}
		return avgNum;
	}
		
		
		public static int get2(){
			int avgNum=0;
			String[] addrs= {"uc2.lawyeredu.com"};   
			if (addrs.length < 1)    
			{    
				System.out.println("syntax Error!");    
			}    
			else    
			{    
				for(int i=0;i<addrs.length;i++){    
					String line = null;    
					try    
					{    
						Process pro = Runtime.getRuntime().exec("ping " + addrs[i]+" -l 1000 -n 4");    
						BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream()));    
						while((line = buf.readLine()) != null){   
							int position=0;   
							if((position=line.indexOf("Average"))>=0)         
							{   
  
								String value=line.substring(position+10,line.lastIndexOf("ms"));
								avgNum=Integer.parseInt(value);
								System.out.println("BBBB22:"+value);   
								//String value="/blog/line.substring(position+10,line.lastIndexOf(ms))";   
								//System.out.println("your speed is BBBBB:"+(1000/Integer.parseInt(value))+"KB");                  
							}   
						}            
					}    
					catch(Exception ex)    
					{    
						ex.printStackTrace();
						System.out.println(ex.getMessage());    
					}    
				} 
				
			}
			return avgNum;
	}

}

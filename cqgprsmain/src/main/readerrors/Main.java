/**
 * 
 */
package main.readerrors;

import java.io.File;

/**
 * @author 华锋
 * Nov 15, 2009-6:05:41 PM
 *
 */
public class Main {

	public static void main(String[] args)throws Exception{
		File f=new File("c:\\");
String[] fs=f.list();
for(String ff:fs){
	System.out.println(ff);
}
	}
}

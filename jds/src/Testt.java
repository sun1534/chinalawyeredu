/**
 * Testt.java
 */

/**
 * @author 华锋
 * Jan 17, 20111:49:46 PM
 *
 */
public class Testt {

	public static void main(String[] args)throws Exception{
		System.out.println(System.getProperty("file.encoding"));
		System.out.println(System.getProperties());
		String s="xxt";
		String ss="校讯通";
		byte[] b=s.getBytes();
		for(byte bb:b){
			System.out.print(bb+" ");
		}
		System.out.println("");
		b=s.getBytes("gb2312");
		for(byte bb:b){
			System.out.print(bb+" ");
		}
		System.out.println("");
		
		b=ss.getBytes("gb2312");
		for(byte bb:b){
			System.out.print(bb+" ");
		}
		System.out.println("");
		
		b=ss.getBytes("gb2312");
		for(byte bb:b){
			System.out.print(bb+" ");
		}
	}
}

/**
 * Test.java
 */
package main.rau;

/**
 * @author 华锋 Apr 2, 201010:40:37 PM
 * 
 */
public class Test {

	public static void main(String[] args) {
		String s = "RAC0000.LAC3302|A|221.177.189.32";
		String[] ss = s.split("\\|");
		System.out.println(ss[0]);
		System.out.println(ss[1]);
		System.out.println(ss[2]);
	}
}

/**
 * HWSessionLogHandle.java
 */
package main.readerrors;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;

/**
 * @author 华锋 Feb 7, 201011:31:00 AM
 * 
 */
public class HWSessionLogHandle {

	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(HWSessionLogHandle.class);

	private String cmd;

	private String srcFileName;
	private String destFileName;

	public HWSessionLogHandle(String srcFileName, String destFileName) {
		this.srcFileName = srcFileName;
		this.destFileName = destFileName;
	}

	public boolean isover;

	public void convert() {
		cmd = main.readerrors.ReadHWErrorApns.SGSNCMD;
		BufferedReader br = null;
		try {
			String command=cmd + " " + srcFileName + " " + destFileName;
			System.out.println("我尻啊。。。。。。。。。。。。。");
			System.out.println(command);
			long now=System.currentTimeMillis();
			Process process = java.lang.Runtime.getRuntime().exec(command);
			br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				LOG.info("解析华为:" + srcFileName + ":" + line);
				System.out.println("解析华为:" + srcFileName + ":" + line);
			}
			LOG.info("解析时间:"+(System.currentTimeMillis()-now));
			
			br.close();
		
				
		} catch (Exception e) {
			System.out.println("解析异常:"+e);
			LOG.error("解析异常:", e);
		}
		isover = true;
	}
}

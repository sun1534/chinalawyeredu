package common;

/**
 * 
 */

/**
 * @author 华锋
 * Sep 10, 2009 4:24:02 PM
 *
 */
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IPUtil {
	private static final Log LOG = LogFactory.getLog(IPUtil.class);

	public static void main(String[] args) {

		System.out.println(getLocalIP());
		// System.out.println(getMacAddr());
		java.util.Properties p = System.getProperties();
		java.util.Enumeration e = p.keys();
		while (e.hasMoreElements()) {
			String s = e.nextElement().toString();
//			System.out.println(s + "==>" + p.getProperty(s));
		}

		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();// 获得本机IP
			String address = addr.getHostName().toString();// 获得本机名称

			System.out.println("addr=:" + ip + ",,," + address);
		} catch (Exception ee) {
			System.out.println("Bad IP Address!" + e);
		}

	}

	/**
	 * 得到linux系统的ip地址
	 * 
	 * @return
	 */
	public static String getMacAddr() {
		String MacAddr = "";
		String str = "";
		try {
			NetworkInterface NIC = NetworkInterface.getByName("eth0");
			byte[] buf = NIC.getHardwareAddress();
			for (int i = 0; i < buf.length; i++) {
				str = str + byteHEX(buf[i]);
			}
			MacAddr = str.toUpperCase();
		} catch (SocketException e) {
			// e.printStackTrace();
			LOG.error("得到MAC地址失误", e);
			System.exit(-1);
		}
		return MacAddr;
	}

	public static String getLocalIP() {
		String ip = "";
		try {
			Enumeration<?> e1 = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
			while (e1.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e1.nextElement();
				if (!ni.getName().equals("eth0")) {
					continue;
				} else {
					Enumeration<?> e2 = ni.getInetAddresses();
					while (e2.hasMoreElements()) {
						InetAddress ia = (InetAddress) e2.nextElement();
						if (ia instanceof Inet6Address)
							continue;
						ip = ia.getHostAddress();
					}
					break;
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
			// LOG.error("得到本地IP地址失误",e);
			// System.exit(-1);
		}
		return ip;
	}

	/* 一个将字节转化为十六进制ASSIC码的函数 */
	private static String byteHEX(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

}

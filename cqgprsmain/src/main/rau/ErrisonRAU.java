/**
 * GenerateRAU.java
 */
package main.rau;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 华锋 Mar 30, 201011:04:22 PM
 * 
 */
public class ErrisonRAU {

	public static void main(String args[]) {
		System.out.println(generateErrison());
	}

	/**
	 * <pre>
	 * 爱立信的,爱立信的现在只有2-6，没有sgsn1了
	 * 1、读取目录下每个sgsn的ip
	 * 2、读取2g的rac和lac值
	 * 3、读取3g的rac和lac值
	 * 4、组装这个sgsn的内容
	 * 循环跑sgsn2-6
	 * 
	 * </pre>
	 */

	public static Map<Integer, List<RauData>> generateErrison() {
		Map<Integer, List<RauData>> map = new LinkedHashMap<Integer, List<RauData>>();

		for (int i = 2; i <= 6; i++) {
			List<RauData> data = getErrisonRoute(i);
			map.put(i, data);
		}
		return map;
	}

	// public static void printlnErrision(Map<Integer, List<String>> raudatas)
	// throws Exception {
	// java.io.PrintWriter out = new java.io.PrintWriter(new
	// java.io.FileOutputStream(SRCNAMEDDIR + "errision_"
	// + df.format(new Date()) + ".txt", true), true);
	//
	// Iterator<Integer> iterator = raudatas.keySet().iterator();
	// while (iterator.hasNext()) {
	// int sgsnid = iterator.next();
	// List<String> raulist = raudatas.get(sgsnid);
	// out.println(";SGSNCQ0" + sgsnid);
	// for (int j = 0; j < raulist.size(); j++) {
	// String d = raulist.get(j);
	// out.println(d);
	// }
	// out.println("");
	// }
	//
	// out.flush();
	// out.close();
	// }

	/**
	 * 得到sgsn的ip
	 * 
	 * @param sgsnid
	 *            为SGSN1的形式,取值为
	 * @return
	 */
	private static String getErrisonSgsnIp(int sgsnid) throws Exception {
		String ip = "";
		String file = GenerateRAU.SRCDIR + "list_ip_service_address_SGSN" + sgsnid + ".txt";
		// String file="c:/list_ip_service_address_SGSN6.txt";
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(
				new java.io.FileInputStream(file)));
		String line = null;
		while ((line = br.readLine()) != null) {
			if (line.indexOf("Gn-GTP-C") != -1) {
				String[] s = line.split(" |\t");
				for (String str : s) {
					if (str.matches(RauUtil.IP_REGIX)) {
						ip = str;
						break;
					}
				}
				break;
			}
		}
		br.close();
		return ip;
	}

	/**
	 * 得到某个sgsnid的路由
	 * 
	 * @param sgsnid
	 * @return
	 * @throws Exception
	 */
	private static List<RauData> getErrisonRoute(int sgsnid) {
		List<RauData> raus = new ArrayList<RauData>();
		try {
			String ip = getErrisonSgsnIp(sgsnid);
			String _2gfilename = GenerateRAU.SRCDIR + "list_gras_SGSN" + sgsnid + ".txt";
			String _3gfilename = GenerateRAU.SRCDIR + "list_wras_SGSN" + sgsnid + ".txt";
			// String file="c:/list_gras_SGSN6.txt";
			java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(
					new java.io.FileInputStream(_2gfilename)));
			String line = null;

			while ((line = br.readLine()) != null) {
				// if (!line.startsWith("RAI")) {
				if (line.trim().startsWith("460")) {
					String[] s = line.trim().split(" |\t");
					if (!s[0].trim().equals("")) {
						// System.out.println(s[0]);
						String[] lacrac = s[0].split("-");
						String lac = RauUtil.tohex(Integer.parseInt(lacrac[2])).toUpperCase();
						String rac = RauUtil.tohex(Integer.parseInt(lacrac[3])).toUpperCase();

						String rau = "RAC" + rac + ".LAC" + lac + "|A|" + ip;
						RauData raudata=new RauData();
						raudata.rau=rau;
						raudata.line=line;
						raus.add(raudata);
					}
				}
			}
			br.close();
			// 上面为读取2g的数据
			br = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(_3gfilename)));

			while ((line = br.readLine()) != null) {
				String[] s = line.trim().split(" |\t");
				if (!s[0].trim().equals("")) {
					String[] lacrac = s[0].split("-");
					String lac = RauUtil.tohex(Integer.parseInt(lacrac[2])).toUpperCase();
					String rac = RauUtil.tohex(Integer.parseInt(lacrac[3])).toUpperCase();
					String rau = "RAC" + rac + ".LAC" + lac + "|A|" + ip;
					System.out.println("SGSN" + sgsnid + "之爱立信3G路由:" + rau);
//					raus.add(rau);
					RauData raudata=new RauData();
					raudata.rau=rau;
					raudata.line=line;
					raus.add(raudata);
				}
			}
			br.close();

		} catch (Exception e) {
			System.out.println("getErrisonRoute(" + sgsnid + ")错误:");
			e.printStackTrace();
		}
		return raus;

	}

	// private static List<String> getErrison3gRoute(int sgsnid) {
	// List<String> raus = new ArrayList<String>();
	// try {
	// String ip = getErrisonSgsnIp(sgsnid);
	// String filename = GenerateRAU.SRCDIR + "list_wras_SGSN" + sgsnid +
	// ".txt";
	// // String file="c:/list_wras_SGSN6.txt";
	// java.io.BufferedReader br = new java.io.BufferedReader(new
	// java.io.InputStreamReader(
	// new java.io.FileInputStream(filename)));
	// String line = null;
	//
	// while ((line = br.readLine()) != null) {
	// // if (!line.startsWith("RAI")) {
	// String[] s = line.trim().split(" |\t");
	// if (!s[0].trim().equals("")) {
	// String[] lacrac = s[0].split("-");
	// String lac = RauUtil.tohex(Integer.parseInt(lacrac[2])).toUpperCase();
	// String rac = RauUtil.tohex(Integer.parseInt(lacrac[3])).toUpperCase();
	//
	// String rau = "RAC" + rac + ".LAC" + lac + "|A|" + ip;
	// raus.add(rau);
	// }
	// // }
	// }
	// br.close();
	// } catch (Exception e) {
	// System.out.println("getErrison2gRoute(" + sgsnid + ")错误:");
	// e.printStackTrace();
	// }
	// return raus;
	//
	// }
}
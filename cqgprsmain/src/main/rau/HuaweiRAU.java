/**
 * HuaweiGenerate.java
 */
package main.rau;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 华锋 Apr 1, 20108:03:44 PM
 * 
 */
public class HuaweiRAU {

	private static String COMMONAD = "/export/home/jf/GPRS/script/huawei_ipservice.sh";
	private static String SGSN7 = "hdcjj*SGSN7 10.190.97.227";
	private static String SGSN8 = "hdcjj*SGSN8 10.190.97.228";
	private static String SGSN9 = "hdcjj*SGSN9 10.190.97.229";
	private static String COMMONAD7 = COMMONAD + " " + SGSN7;
	private static String COMMONAD8 = COMMONAD + " " + SGSN8;
	private static String COMMONAD9 = COMMONAD + " " + SGSN9;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (args[0].equals("7"))
			getHuaweiDatas(7, COMMONAD7);
		if (args[0].equals("8"))
			getHuaweiDatas(8, COMMONAD8);
		if (args[0].equals("9"))
			getHuaweiDatas(9, COMMONAD9);
	}

	public static Map<Integer, List<RauData>> generateHuawei(boolean sleep) {
		Map<Integer, List<RauData>> map = new LinkedHashMap<Integer, List<RauData>>();
		try {
			if (sleep)
				Thread.sleep(50 * 1000L);
			List<RauData> sgsn7 = getHuaweiDatas(7, COMMONAD7);
			if (sleep)
				Thread.sleep(50 * 1000L);
			List<RauData> sgsn8 = getHuaweiDatas(8, COMMONAD8);
			if (sleep)
				Thread.sleep(50 * 1000L);
			List<RauData> sgsn9 = getHuaweiDatas(9, COMMONAD9);
			if (sleep)
				Thread.sleep(50 * 1000L);

			map.put(7, sgsn7);
			map.put(8, sgsn8);
			map.put(9, sgsn9);
		} catch (Exception e) {
			System.out.println("generateHuawei()失败");
			e.printStackTrace();
		}
		return map;
	}

	private static List<RauData> getHuaweiDatas(int sgsnid, String command) {
		// 这里要获得连个ip
		List<String> ips = new ArrayList<String>();
		List<RauData> raus = new ArrayList<RauData>();
		List<RauData> result = new ArrayList<RauData>();
		List<String> list = new ArrayList<String>();
		try {
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec(command);

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			int iii = 0;
			// List<String> list = new ArrayList<String>();
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				// list.add(line);
				// 有46000和46018,46017开头的这些
				// 46017,18,19等都是垃圾数据,不需要
				if (line.trim().startsWith("460")) {
					int idx4600 = line.indexOf("460");
					int idx0x = line.indexOf("0x");

					String first = line.substring(idx4600, idx4600 + 5);
					if (first.equals("46000")) {

						String lac = line.substring(idx4600 + 5, idx4600 + 9);
						String rac = line.substring(idx0x + 2, idx0x + 2 + 1);
						String racint = "000" + rac;
						String s = "RAC" + racint + ".LAC" + lac;
						RauData raudata = new RauData();
						raudata.line = line;
						raudata.rau = s;
						if (!list.contains(s)) {
							raus.add(raudata);
						}
						
						list.add(s);
						
//						iii++;
					} else {
						String s = "SGSNCQ0" + sgsnid + "存在异常LAC数据:" + line;
						System.out.println(s);
						GenerateRAU.WARNINGS.add(s);
					}
				} else {
					String[] s = line.split(" |\t");
					for (String str : s) {
						if (str.matches(RauUtil.IP_REGIX)) {
							ips.add(str);
							break;
						}
					}
				}
			}
			br.close();
			for (int j = 0; j < raus.size(); j++) {
				// String lacrac = raus.get(j);
				RauData raudata = raus.get(j);
				String lacrac = raudata.rau;
				String _line = raudata.line;
				// 同一个sgsn的相同的raclac*不再进了
				for (int i = 0; i < ips.size(); i++) {
					RauData _raudata = new RauData();
					String s = lacrac + "|A|" + ips.get(i);
					_raudata.rau = s;
					_raudata.line = _line;
					_raudata.linenum = 10000 + j;
					// if (i >= 1)
					// s = lacrac.replaceAll(".", " ") + "|A|" + ips.get(i);
					result.add(_raudata);
				}
			}
			raus.clear();
			ips.clear();
			list.clear();
			
			System.out.println("Command:::" + result.size() + "===" + iii);
		} catch (Exception e) {
			System.out.println(command + "执行失败!");
			e.printStackTrace();
		}

		return result;
	}
}
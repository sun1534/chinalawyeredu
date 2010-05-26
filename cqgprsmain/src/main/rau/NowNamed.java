/**
 * GenerateRAU.java
 */
package main.rau;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 华锋 Mar 30, 201011:04:22 PM
 * 
 */
public class NowNamed {

	private static final java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	public static void main(String args[]) throws Exception {
		Map<Integer, List<RauData>> m = new LinkedHashMap<Integer, List<RauData>>();
		getNamedra(m);
	}

	/**
	 * 解析已有的dns配置文件，同时根据爱立信的和华为的生成新的配置文件 新的配置文件存储为named20100401.ra的形式
	 * 
	 * @param generates
	 *            爱立信和华为的数据
	 * @return 已有的sgsnid和对应的路由数据
	 * @throws Exception
	 */
	public static Map<Integer, List<RauData>> getNamedra(Map<Integer, List<RauData>> generates) throws Exception {

		Map<Integer, List<RauData>> result = new LinkedHashMap<Integer, List<RauData>>();
		Date now = new Date();

		String oldname = GenerateRAU.SRCNAMEDDIR + "named" + df.format(now) + ".ra";
		String newname = GenerateRAU.SRCNAMEDDIR + "new_named" + df.format(now) + ".ra";
		File file = new File(oldname);
		if (!file.exists()) {
			System.out.println("未曾获取到今天的配置文件：" + oldname);
			return result;
		}
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(
				new java.io.FileInputStream(file)));
		java.io.PrintWriter out = new java.io.PrintWriter(new java.io.FileOutputStream(newname, false), true);
		String line = null;
		int sgsnid = 0;
		int i = 0;
		String raclac = "";
		String temp = "";
		boolean shouldVerify = true;
		while ((line = br.readLine()) != null) {
			if (shouldVerify && line.indexOf("Serial") != -1) {
				String[] s = line.split(";");
				String serial = s[0].trim();
				long newserial = Long.parseLong(serial) + 1;
				String newserialline = line.replace(serial, newserial + "");
				System.out.println("新的序列号:" + newserialline);
				out.println(newserialline);
				shouldVerify = false;
			} else if (line.startsWith(";SGSNCQ")) {
				List<RauData> raudatas = new ArrayList<RauData>();
				sgsnid = Integer.parseInt(line.replace(";SGSNCQ", "").trim());
				// System.out.println(sgsnid + "=========");
				result.put(sgsnid, raudatas);
				// 将华为的和爱立信有的打印到这个下面
				out.println(line);
				List<RauData> raus = generates.get(sgsnid);
				if (raus != null) {
					for (RauData raudata : raus) {
						String rau=raudata.rau;
						i++;
//						String[] s = rau.split("\\|");
//						String _lacrac = s[0];
//						if (_lacrac.equals(temp)) {
//							String blanklacrac = _lacrac.replaceAll(".", " ");
//							String newrau = blanklacrac + "|A|" + s[2];
//							out.println(newrau.replace("|", "\t"));
//						} else {
//							out.println(rau.replace("|", "\t"));
//						}
//						temp = _lacrac;
						out.println(rau.replace("|", "\t"));
					}
				}

			} else if (line.startsWith("RAC")) { // 将他的换到我那里
				String rd = "";
				String type = "A";
				String ip = "";
				String[] s = line.trim().split(" |\t");
				for (int ii = 0; ii < s.length; ii++) {
					if (s[ii].trim().matches(RauUtil.IP_REGIX)) {
						ip = s[ii];
						break;
					}
				}
				raclac = s[0];
				rd = raclac + "|" + type + "|" + ip;
				// 加入到老的数据中
				if (result.get(sgsnid) != null){
					RauData raudata=new RauData();
					raudata.line=line;
					raudata.rau=rd;
					result.get(sgsnid).add(raudata);
				}

			} else {

				String[] s = line.trim().split(" |\t");
				boolean println = true;
				String type = "";
				String ip = "";

				for (int ii = 0; ii < s.length; ii++) {
					if (s[ii].trim().equals("A")) {
						println = false;
						type = s[ii];
					}
					if (s[ii].trim().matches(RauUtil.IP_REGIX)) {
						println = false;
						ip = s[ii];
					}
				}
				if (println || line.trim().startsWith("RNC"))
					out.println(line);
				else {
					String rd = raclac + "|" + type + "|" + ip;
					// System.out.println(rd);
					if (result.get(sgsnid) != null){
					
						RauData raudata=new RauData();
						raudata.line=line;
						raudata.rau=rd;
						result.get(sgsnid).add(raudata);
					}
				}
			}
		}

		out.flush();
		out.close();

		return result;
	}
}
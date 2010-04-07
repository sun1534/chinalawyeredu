/**
 * GenerateRAU.java
 */
package main.rau;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 华锋 Mar 30, 201011:04:22 PM
 * 
 */
public class GenerateRAU {
	public static String SRCDIR = "/export/home1/smlog/IPSERVICE/";
	public static String SRCNAMEDDIR = "/export/home1/smlog/IPSERVICE/named/";
	private static final java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	/**
	 * 警告信息
	 */
	public static final List<String> WARNINGS = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		// 获取爱立信的新系统的数据
		Map<Integer, List<RauData>> errison = ErrisonRAU.generateErrison();
		// System.out.println(errison);
		// 获取华为的新系统的数据

		System.out.println("产生完毕爱立信的路由数据");
		Map<Integer, List<RauData>> huawei = HuaweiRAU.generateHuawei(true);
		// System.out.println(huawei);
		System.out.println("产生完毕华为的路由数据");
		Map<Integer, List<RauData>> all = new LinkedHashMap<Integer, List<RauData>>();
		all.putAll(errison);
		all.putAll(huawei);
		// System.out.println(all+"\r\n"+all.size());

		// 获取现有系统中的数据并打印到文件new_named20100401.ra
		Map<Integer, List<RauData>> oldraudatas = NowNamed.getNamedra(all);
		System.out.println("产生完毕已有的路由数据并产生完毕新的路由文件");
		// 判断新获取的数据是否有重复
		// 和named.ra中的sgsncq02-sgsncq06匹配比较
		// 新旧比较，哪些在新的sgsn中没了，哪些在新的sgsn中新增了
		compare(oldraudatas, all);
		System.out.println("比较新旧路由数据完毕");
		// 判断路由是否有重复,也就是说这个rd在其他的sgsn中都有
		repeatRoute(all);
		System.out.println("判断RACLAC是否有重复完毕");
		printlnWarning();
		System.out.println("输出警告信息完毕");
	}

	private static void printlnWarning() {
		try {
			Date now = new Date();
			String newname = GenerateRAU.SRCNAMEDDIR + "warning" + df.format(now) + ".ra";
			java.io.PrintWriter out = new java.io.PrintWriter(new java.io.FileOutputStream(newname, false), true);
			for (String s : WARNINGS) {
				out.println(s);
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("打印告警信息失败");
			e.printStackTrace();
		}
	}

	/**
	 * 判断新产生的路由信息,是否在这个sgsn中有，在其他的sgsn中是否也有
	 * 
	 * @param news
	 */
	private static void repeatRoute(Map<Integer, List<RauData>> news) {
		// Iterator<List<String>> rds=news.values().iterator();
		Iterator<Integer> newsgsnid = news.keySet().iterator();
		// Map<String, String> alls = new HashMap<String, String>();
		Map<String, List<RauRepeat>> repeats = new HashMap<String, List<RauRepeat>>();
		while (newsgsnid.hasNext()) {
			int sgsnid = newsgsnid.next();
			List<RauData> raudatas = news.get(sgsnid); // 这里面华为的raclac有重复的
			int templine = -1;
			for (RauData raudata : raudatas) {
				String s = raudata.rau;
				String raclac = s.substring(0, s.indexOf("|"));
				int linenum = raudata.linenum;
			
				// System.out.println("repeat:::" + raclac);
				RauRepeat e = new RauRepeat();
				e.sgsnid = sgsnid;
				e.line = raudata.line;
			
				if (linenum != templine) {//排除掉华为2个ip的情况
					if (repeats.containsKey(raclac)) {
						// 这个路由有重复

						List<RauRepeat> repeat = repeats.get(raclac);
						repeat.add(e);

						// String _sgsnid = alls.get(raclac);
						// if (!_sgsnid.equals(sgsnid + "")) { //
						// 如果前后的sgsnid一样,则不考虑了
						// if (_sgsnid.indexOf(sgsnid + "") == -1) {
						// alls.remove(raclac);
						// alls.put(raclac, sgsnid + "," + _sgsnid);
						// }
					} else {
						// alls.put(raclac, sgsnid + "");
						List<RauRepeat> repeat = new ArrayList<RauRepeat>();
						repeat.add(e);
						repeats.put(raclac, repeat);
					}
				}
				templine = linenum;
			}
		}
		WARNINGS.add("===================================================");
//		System.out.println(repeats);
		Iterator<String> repeatsiterator = repeats.keySet().iterator();
		while (repeatsiterator.hasNext()) {
			String raclac = repeatsiterator.next();
			List<RauRepeat> sgsnids = repeats.get(raclac);
			if (sgsnids.size() > 1) {
//				int temp = 0;
				List<String> lines = new ArrayList<String>();
//				Map<Integer, Integer> sgsnidre = new HashMap<Integer, Integer>();
				String s = raclac + "数据有重复,同时存在于";
				for (RauRepeat rr : sgsnids) {
					int sgsnid = rr.sgsnid;
					String line = rr.line;

//					if (!sgsnidre.containsKey(sgsnid)) {
						s += "SGSNCQ" + sgsnid + ",";
//						sgsnidre.put(sgsnid, 1);
//					} else {
//						int rrr = sgsnidre.get(sgsnid);
//						sgsnidre.remove(sgsnid);
//						sgsnidre.put(sgsnid, rrr + 1);
//					}

					lines.add("\t\tSGSNCQ" + sgsnid + "中的数据为:" + line);
				}

				s = s.substring(0, s.lastIndexOf(",")) + "。";// 去掉最后一个逗号
//				Iterator<Integer> sgsnidreite = sgsnidre.keySet().iterator();
//				while (sgsnidreite.hasNext()) {
//					int sgsnid = sgsnidreite.next();
//					if (sgsnidre.get(sgsnid) > 1)
//						s += "SGSNCQ0" + sgsnid + "重复" + sgsnidre.get(sgsnid) + "个,";
//				}
//				s = s.substring(0, s.lastIndexOf(","));// 去掉最后一个逗号

				// WARNINGS.add(raclac + "数据有重复,在SGSN" + sgsnids + "中都有");
				WARNINGS.add(s);
				WARNINGS.addAll(lines);
			}
		}
		repeats.clear();
		repeats = null;
	}

	private static void compare(Map<Integer, List<RauData>> old, Map<Integer, List<RauData>> news) {
		// 判断旧的rac是否在新的里面，如果不在，表示删除了
		Iterator<Integer> oldsgsnid = old.keySet().iterator();
		Iterator<Integer> newsgsnid = news.keySet().iterator();

		WARNINGS.add("===================================================");
		Map<Integer, RauCompare> maps = new LinkedHashMap<Integer, RauCompare>();
		while (oldsgsnid.hasNext()) {
			int sgsnid = oldsgsnid.next(); // 旧的sgsnid

			RauCompare c = new RauCompare();
			c.setSgsnid(sgsnid);
			maps.put(sgsnid, c);

			List<RauData> oldraudata = old.get(sgsnid); // 旧的这个sgsnid的路由
			List<RauData> newraudata = news.get(sgsnid); // 新的这个sgsnid的路由

			List<String> newrd = new ArrayList<String>();
			List<String> oldrd = new ArrayList<String>();
			for (int i = 0; oldraudata != null && i < oldraudata.size(); i++) {
				oldrd.add(oldraudata.get(i).rau);
			}
			for (int i = 0; newraudata != null && i < newraudata.size(); i++) {
				newrd.add(newraudata.get(i).rau);
			}
			if (newrd == null || newrd.size() == 0) {
				c.setDelete(oldrd.size());
				for (String rd : oldrd) {
					c.addDeleteraus(rd);
				}
				// WARNINGS.add("SGSNCQ0" + sgsnid + "删除全部路由");
				break;
			}
			for (int i = 0; i < oldrd.size(); i++) {
				String rd = oldrd.get(i); // 老的每条路由信息
				if (!newrd.contains(rd)) { // 如果老的路由不在新的里面，则认为已删除
					// rd已删除
					c.setDelete(c.getDelete() + 1);
					c.addDeleteraus(rd);
					// WARNINGS.add("SGSNCQ0" + sgsnid + "删除路由：" + rd);
				}
			}
		}

		while (newsgsnid.hasNext()) {
			int sgsnid = newsgsnid.next(); // 新的sgsnid
			List<RauData> oldraudata = old.get(sgsnid); // 旧的这个sgsnid的路由
			List<RauData> newraudata = news.get(sgsnid); // 新的这个sgsnid的路由

			List<String> newrd = new ArrayList<String>();
			List<String> oldrd = new ArrayList<String>();
			for (int i = 0; oldraudata != null && i < oldraudata.size(); i++) {
				oldrd.add(oldraudata.get(i).rau);
			}
			for (int i = 0; newraudata != null && i < newraudata.size(); i++) {
				newrd.add(newraudata.get(i).rau);
			}

			RauCompare c = maps.get(sgsnid);
			if (c == null)
				c = new RauCompare();

			if (oldrd == null || oldrd.size() == 0) {
				// WARNINGS.add("SGSNCQ0" + sgsnid + "新增全部路由");
				c.setAdd(newrd.size());
				for (String rd : newrd) {
					c.addAddraus(rd);
				}
				break;
			}

			for (int i = 0; i < newrd.size(); i++) {
				String rd = newrd.get(i);
				if (!oldrd.contains(rd)) {
					// rd已新增
					c.setAdd(c.getAdd() + 1);
					c.addAddraus(rd);
					// WARNINGS.add("SGSNCQ0" + sgsnid + "新增路由：" + rd);
				}
			}
		}

		// 打印到WARNINGS中
		Iterator<Integer> compars = maps.keySet().iterator();
		while (compars.hasNext()) {
			int sgsnid = compars.next();
			RauCompare rc = maps.get(sgsnid);
			WARNINGS.addAll(rc.printlnContent());
		}
		maps.clear();

	}
}
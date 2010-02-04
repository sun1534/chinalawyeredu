/**
 * 
 */
package com.sxit.stat.action;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sxit.stat.models.SgsnStatModel;

/**
 * <pre>
 * SGSN分apn统计
 * （重庆目前9个SGSN，4个GGSN），匹配不上的都是外省的GGSN统一到其他中
 * 如果好做，考虑其他GGSN可以再单独出个报表，按流量排名
 * 
 * 根据sgsnid和ggsnid查询
 * 
 * </pre>
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class SgsnGgsnTopoSVGAction extends StatAction {

	/**
	 * 存储sgsn在svg中的x坐标
	 */
	private static Map<String, Integer> SGSNIDLOC = new LinkedHashMap<String, Integer>();
	/**
	 * 存储ggsn在svg中的x坐标
	 */
	private static Map<String, Integer> GGSNIDLOC = new LinkedHashMap<String, Integer>();
	/**
	 * 基本的量
	 */
//	private static int BASICSTREAM = 10 * 1024;
	private static int BASICSTREAM = 1024;
	private static int OTHERBASICSTREAM = 1024;
//	private static int STREAMDIFF = 3 * BASICSTREAM;
	private static int STREAMDIFF = 3 *10 * BASICSTREAM;
	private static int OTHERSTREAMDIFF = 2 * OTHERBASICSTREAM;
	static {
		SGSNIDLOC.put("SGSNCQ01", 50);
		SGSNIDLOC.put("SGSNCQ02", 150);
		SGSNIDLOC.put("SGSNCQ03", 250);
		SGSNIDLOC.put("SGSNCQ04", 350);
		SGSNIDLOC.put("SGSNCQ05", 450);
		SGSNIDLOC.put("SGSNCQ06", 550);
		SGSNIDLOC.put("SGSNCQ07", 650);
		SGSNIDLOC.put("SGSNCQ08", 750);
		SGSNIDLOC.put("SGSNCQ09", 850);

		GGSNIDLOC.put("GGSN02", 150);
		GGSNIDLOC.put("GGSN03", 450);
		GGSNIDLOC.put("GGSN04", 650);
		GGSNIDLOC.put("GGSN05", 750);
		GGSNIDLOC.put("GGSN其他", 850);
	}

	/*
	 * 得到某一天的各个sgsn的流量 显示柱状图和线图
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		if (startDate == null) {
			startDate = getPrevDate();
			this.start = df.format(startDate);
		}

		StringBuffer sb = new StringBuffer();
		Iterator<String> sgsniterator = SGSNIDLOC.keySet().iterator();

		sb.append("<?xml version=\"1.0\" standalone=\"no\"?>");
		sb
				.append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.0//EN\" \"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">");
		sb.append("<svg width=\"950\" height=\"350\" xmlns=\"http://www.w3.org/2000/svg\">");

		while (sgsniterator.hasNext()) {
			String sgsnid = sgsniterator.next();
			int sgsnloc = SGSNIDLOC.get(sgsnid);
			int sgsntextloc = sgsnloc + 10;
			sb.append("<rect x=\"" + sgsnloc
					+ "\" y=\"25\" width=\"80\" height=\"40\" fill=\"#FFFFFF\" stroke=\"#000000\"/>\r\n");
			sb.append("<text x=\"" + sgsntextloc
					+ "\" y=\"50\" xml:space=\"preserve\" font-size=\"14\" fill=\"black\">" + sgsnid + "</text>\r\n");
		}

		Iterator<String> iterator = GGSNIDLOC.keySet().iterator();
		while (iterator.hasNext()) {
			String ggsnid = iterator.next();
			int ggsnloc = GGSNIDLOC.get(ggsnid);
			int ggsntextloc = ggsnloc + 20;
			sb.append("<rect x=\"" + ggsnloc
					+ "\" y=\"150\" width=\"80\" height=\"40\" fill=\"#FFFFFF\" stroke=\"#000000\"/>\r\n");
			sb.append("<text x=\"" + ggsntextloc
					+ "\" y=\"175\" xml:space=\"preserve\" font-size=\"14\" fill=\"black\">" + ggsnid + "</text>\r\n");
		}

		List ggsnlist = statservice.getDaySgsnStreamGgsnApn(startDate);
		for (int i = 0; i < ggsnlist.size(); i++) {
			SgsnStatModel model = (SgsnStatModel) ggsnlist.get(i);

			double allvolume = model.getTotalStream();
			String sgsnid = model.getSgsnid();
			String ggsnid = model.getGgsnid();

			int sgsnidloc = SGSNIDLOC.get(sgsnid);
			int ggsnidloc = GGSNIDLOC.get(ggsnid);
			int sgsnaver = 60 / 3;
			int ggsnaver = 60 / 3;

			int sgsncmnetloc = sgsnidloc + sgsnaver;
			int sgsncmwaploc = sgsnidloc + 2 * sgsnaver;
			int sgsnotherloc = sgsnidloc + 3 * sgsnaver;

			int ggsncmnetloc = ggsnidloc + ggsnaver;
			int ggsncmwaploc = ggsnidloc + 2 * ggsnaver;
			int ggsnotherloc = ggsnidloc + 3 * ggsnaver;

			if (model.getApnni().equals("cmnet")) {
				if (allvolume > BASICSTREAM) {
					String width = com.sxit.system.util.NumberUtil.toMoney(allvolume / STREAMDIFF);
					sb.append("<line x1=\"" + sgsncmnetloc + "\" y1=\"65\" x2=\"" + ggsncmnetloc
							+ "\" y2=\"150\" stroke=\"RED\" stroke-width=\"" + width + "\"/>\r\n");
				}

			} else if (model.getApnni().equals("cmwap")) {
				if (allvolume > BASICSTREAM) {
					String width = com.sxit.system.util.NumberUtil.toMoney(allvolume / STREAMDIFF);
					sb.append("<line x1=\"" + sgsncmwaploc + "\" y1=\"65\" x2=\"" + ggsncmwaploc
							+ "\" y2=\"150\" stroke=\"BLUE\" stroke-width=\"" + width + "\"/>\r\n");
				}
			} else if (model.getApnni().equals("other")) {
				if (allvolume > OTHERBASICSTREAM) {
					String width = com.sxit.system.util.NumberUtil.toMoney(allvolume / OTHERSTREAMDIFF);
					sb.append("<line x1=\"" + sgsnotherloc + "\" y1=\"65\" x2=\"" + ggsnotherloc
							+ "\" y2=\"150\" stroke=\"GREEN\" stroke-width=\"" + width + "\"/>\r\n");

				}
			}
		}

		sb.append("<line x1=\"25\" y1=\"230\" x2=\"156\" y2=\"230\" stroke=\"red\" />");
		sb.append("<line x1=\"25\" y1=\"250\" x2=\"156\" y2=\"250\" stroke=\"blue\" />");
		sb.append("<line x1=\"25\" y1=\"270\" x2=\"156\" y2=\"270\" stroke=\"Green\" />");
		sb
				.append("<text x=\"170\" y=\"230\" xml:space=\"preserve\" font-family=\"宋体\" font-size=\"15\" fill=\"red\">CMNET</text>");
		sb
				.append("<text x=\"170\" y=\"250\" xml:space=\"preserve\" font-family=\"宋体\" font-size=\"15\" fill=\"blue\">CMWAP</text>");
		sb
				.append("<text x=\"170\" y=\"270\" xml:space=\"preserve\" font-family=\"宋体\" font-size=\"15\" fill=\"Green\">其他</text>");
		sb
				.append("<text x=\"300\" y=\"300\" xml:space=\"preserve\" font-family=\"黑体\" font-size=\"20\" fill=\"black\" stroke-width=\"2\">"+start+"之SGSN/GGSN流量拓扑示意图</text>");

		sb.append("</svg>");
		this.svgstr = sb.toString();
		System.out.println("svgstr.length()::::" + svgstr.length());
		return SUCCESS;

	}

	private InputStream inputStream;

	public InputStream getInputStream() {
		byte[] b = null;
		try {
			b = svgstr.getBytes("utf-8");
		} catch (Exception e) {
			b = svgstr.getBytes();
		}
		System.out.println(b.length);
		inputStream = new java.io.ByteArrayInputStream(b);
		return inputStream;
	}

	private String svgstr;

	public String getSvgstr() {
		return this.svgstr;
	}


}

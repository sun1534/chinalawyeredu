package com.changpeng.common.util;

import com.changpeng.common.PaginationSupport;

public class Pagefoot {

	String bfString = " <a href=\"javascript:";
	String midString = ")\"> ";
	String afString = " </a> ";

	private String pageString;

	/**
	 * 打包页面分页字符串,默认显示5页
	 * 
	 * @param page
	 *            分页类
	 * @param fun
	 *            页面调用的js函数名
	 * @param pageNo
	 *            当前页码
	 * @return
	 */
	public String packString(PaginationSupport page, int pageNo, String fun) {
		return packString(page, pageNo, fun, 5);
	}

	/**
	 * 打包页面分页字符串
	 * 
	 * @param page
	 *            分页类
	 * @param pageNo
	 *            当前页码
	 * @param fun
	 *            页面调用的javascript函数名
	 * @param pageShow
	 *            显示多少页
	 * @return
	 */
	public String packString(PaginationSupport page, int pageNo, String fun, final int pageShow) {
		PaginationSupport p = page;
		bfString += fun + "(";
		// final int pageShow = 5; // 总共要显示的页码
		int count = p.getCount(); // 总共的分页数
		int firstpage = 0;
		int lastpage = 0;
		if (count <= 1) {
			return "";
		}

		if (count <= pageShow) {
			firstpage = 1;
			lastpage = count;
			pageString = getpString(firstpage, lastpage, pageNo, count);
		} else if (count > pageShow) {
			if (pageNo < (pageShow / 2 + 1)) {
				firstpage = 1;
				lastpage = pageShow;
				pageString = getpString(firstpage, lastpage, pageNo, count);
			} else if (pageNo > (count - (pageShow / 2 + 1))) {
				firstpage = count - pageShow + 1;
				lastpage = count;
				pageString = getpString(firstpage, lastpage, pageNo, count);
			} else {
				firstpage = pageNo - pageShow / 2;
				lastpage = pageNo + pageShow / 2;
				pageString = getpString(firstpage, lastpage, pageNo, count);
			}
		}

		return pageString;
	}

	/**
	 * 根据起终页取得分页字符串
	 * 
	 * @param firstpage
	 * @param lastpage
	 * @return
	 */
	private String getpString(int firstpage, int lastpage, int pageNo, int count) {
		String pString = "";
		for (int i = firstpage; i <= lastpage; i++) {
			if (i == pageNo) {
				pString += i;
				if (i != lastpage) {
					pString += " | ";
				}
				continue;
			}
			pString += bfString + i + midString + i + afString;
			if (i != lastpage) {
				pString += " | ";
			}
		}
		String syy = "";
		if (pageNo > 1) {
			syy = bfString + (pageNo - 1) + midString + " <<上一页" + afString;
		}

		String xyy = "";
		if (pageNo < count && count > 1) {
			xyy = bfString + (pageNo + 1) + midString + " 下一页>>" + afString;
		}

		// System.out.println("pageString-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
		// + pString);
		return syy + pString + xyy;

	}
}

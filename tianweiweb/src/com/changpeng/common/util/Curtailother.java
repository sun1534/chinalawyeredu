package com.changpeng.common.util;

import java.io.IOException;

import javax.swing.ImageIcon;

public class Curtailother {

	/**
	 * 生成个人相册或班级相册的缩略图
	 * 
	 * @param strpath
	 *            源图片目录
	 * @param strfname
	 *            源图片文件名
	 * @return "OK" "error"
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String suoluetu(String strpath, String strfname) throws IOException, InterruptedException {
		String str = "";
		ImageIcon icon = new ImageIcon(strpath + strfname);

		if (icon != null) {
			int h = icon.getIconHeight();
			int w = icon.getIconWidth();

			Process pm = null;
			Process ps = null;
			Process pl = null;
			Process pr = null;
			// /操作系统为WINDOWS
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") >= 0) {
				if (w >= 600) // 大于中图的尺寸，开始进行缩略
				{
					str = "convert " + strpath + strfname + " -resize 600 " + strpath + "m_" + strfname; // 生成中图。宽550高不限制
					pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
					if (h > w) {
						int y = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+" + "0+" + y + " -resize 75x75 " + strpath + "r_"
								+ strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
					if (w == h) {
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}

				}
				if (w < 600 && w >= 180) // 小于中图,大于L缩略图
				{
					str = "convert " + strpath + strfname + " -resize " + w + " " + strpath + "m_" + strfname; // 生成中图。宽550高不限制
					pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成缩略图。宽高都为100
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
					if (h > w) {
						int y = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x180 " + strpath + "s_" + strfname; // 生成缩略图。宽高都为100
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+0+" + y + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
					if (w == h) {
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成缩略图。宽高都为100
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
				}
				if (w < 180 && w >= 100) // 小于180L缩略图,大于100缩略图
				{
					str = "convert " + strpath + strfname + " -resize " + w + " " + strpath + "m_" + strfname; // 生成中图。宽550高不限制
					pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
					if (h > w) {
						int y = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+0+" + y + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
					if (w == h) {
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}

				}
				if (w < 100 && w >= 75) // 小于缩略图，大于方框图
				{
					str = "convert " + strpath + strfname + " -resize " + w + " " + strpath + "m_" + strfname; // 生成中图。宽550高不限制
					pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽550高不限制
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
					if (h > w) {
						int y = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 生成缩略图。宽550高不限制
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+0+" + y + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
					if (w == h) {
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽550高不限制
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}

				}
				if (w < 75) {
					str = "convert " + strpath + strfname + " -resize " + w + "x" + h + " " + strpath + "m_" + strfname; // 生成中图。宽550高不限制
					pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽550高不限制
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
					if (h > w) {
						int y = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 生成缩略图。宽550高不限制
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+0+" + y + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
					if (w == h) {
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽550高不限制
						pl = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
				}
			}
			// //////////操作系统为其他操作系统
			else {
				if (w >= 600) // 大于中图的尺寸，开始进行缩略
				{
					str = "convert " + strpath + strfname + " -resize 600 " + strpath + "m_" + strfname; // 生成中图。宽550高不限制
					pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
					if (h > w) {
						int y = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+" + "0+" + y + " -resize 75x75 " + strpath + "r_"
								+ strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
					if (w == h) {
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}

				}
				if (w < 600 && w >= 180) // 小于中图,大于L缩略图
				{
					str = "convert " + strpath + strfname + " -resize " + w + " " + strpath + "m_" + strfname; // 生成中图。宽550高不限制
					pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成缩略图。宽高都为100
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
					if (h > w) {
						int y = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x180 " + strpath + "s_" + strfname; // 生成缩略图。宽高都为100
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+0+" + y + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
					if (w == h) {
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成缩略图。宽高都为100
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
				}
				if (w < 180 && w >= 100) // 小于180L缩略图,大于100缩略图
				{
					str = "convert " + strpath + strfname + " -resize " + w + " " + strpath + "m_" + strfname; // 生成中图。宽550高不限制
					pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
					if (h > w) {
						int y = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+0+" + y + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
					if (w == h) {
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽高都为100
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}

				}
				if (w < 100 && w >= 75) // 小于缩略图，大于方框图
				{
					str = "convert " + strpath + strfname + " -resize " + w + " " + strpath + "m_" + strfname; // 生成中图。宽550高不限制
					pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽550高不限制
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
					if (h > w) {
						int y = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 生成缩略图。宽550高不限制
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+0+" + y + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
					if (w == h) {
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽550高不限制
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}

				}
				if (w < 75) {
					str = "convert " + strpath + strfname + " -resize " + w + "x" + h + " " + strpath + "m_" + strfname; // 生成中图。宽550高不限制
					pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽550高不限制
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
					if (h > w) {
						int y = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 生成缩略图。宽550高不限制
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+0+" + y + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
					if (w == h) {
						str = "convert " + strpath + strfname + " -resize 180 " + strpath + "s_" + strfname; // 生成中图。宽550高不限制
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 生成缩略图。宽550高不限制
						pl = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -resize 75x75 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
				}
			}
			if (pm.waitFor() == 0 && ps.waitFor() == 0 && pl.waitFor() == 0 && pr.waitFor() == 0) {
				return "OK";
			} else {
				return "error";
			}

		}
		return "error";
	}

	/**
	 * 日志，班级留言等的插入图片
	 * 
	 * @param strpath
	 *            图片路径
	 * @param strfname
	 *            图片名称
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String editPicture(String strpath, String strfname) throws IOException, InterruptedException {
		ImageIcon icon = new ImageIcon(strpath + strfname);

		String str = "";

		if (icon != null) {
			Integer h = icon.getIconHeight();
			Integer w = icon.getIconWidth();
			Process pm = null;
			// /操作系统为WINDOWS
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") >= 0) {
				if (w >= 400) {
					if (w > h) {
						str = "convert " + strpath + strfname + " -resize 400 " + strpath + "m_" + strfname; // 生成方框图，宽为400,原比例缩放
						pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					} else {
						str = "convert " + strpath + strfname + " -resize " + w + "x400 " + strpath + "m_" + strfname; // 生成方框图，宽为400,原比例缩放
						pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
				} else {
					if (w > h) {
						str = "convert " + strpath + strfname + " -resize " + w + "x" + h + " " + strpath + "m_" + strfname; // 生成方框图，宽为400,原比例缩放
						pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					} else {
						if (h > 400) {
							str = "convert " + strpath + strfname + " -resize " + w + "x400 " + strpath + "m_" + strfname; // 生成方框图，宽为400,原比例缩放
							pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						} else {
							str = "convert " + strpath + strfname + " -resize " + w + "x" + h + " " + strpath + "m_" + strfname; // 生成方框图，宽为400,原比例缩放
							pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						}
					}
				}
			} else {
				if (w >= 400) {
					if (w > h) {
						str = "convert " + strpath + strfname + " -resize 400 " + strpath + "m_" + strfname; // 生成方框图，宽为400,原比例缩放
						pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					} else {
						str = "convert " + strpath + strfname + " -resize " + w + "x400 " + strpath + "m_" + strfname; // 生成方框图，宽为400,原比例缩放
						pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
				} else {
					if (w > h) {
						str = "convert " + strpath + strfname + " -resize " + w + "x" + h + " " + strpath + "m_" + strfname; // 生成方框图，宽为400,原比例缩放
						pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					} else {
						if (h > 400) {
							str = "convert " + strpath + strfname + " -resize " + w + "x400 " + strpath + "m_" + strfname; // 生成方框图，宽为400,原比例缩放
							pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						} else {
							str = "convert " + strpath + strfname + " -resize " + w + "x" + h + " " + strpath + "m_" + strfname; // 生成方框图，宽为400,原比例缩放
							pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						}
					}
				}
			}
			if (pm.waitFor() == 0) {
				return "OK";
			} else {
				return "error";
			}
		}
		return "error";

	}

	/**
	 * 群组，班级等的图标
	 * 
	 * @param path
	 *            图片路径
	 * @param fileName
	 *            图片名称包括后缀
	 * @param width
	 *            传过来的宽
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String editLogo(String path, String fileName) throws IOException, InterruptedException {
		ImageIcon icon = new ImageIcon(path + fileName);

		String str = "";

		if (icon != null) {
			Integer h = icon.getIconHeight();
			Integer w = icon.getIconWidth();
			Process ps = null;
			Process pr = null;
			// /操作系统为WINDOWS
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") >= 0) {
				if (w >= 170) {
					str = "convert " + path + fileName + " -resize 170 " + path + "s_" + fileName;
					ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str);
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + path + fileName + " -crop " + h + "x" + h + "+" + x + "+0 -resize 48x48 " + path + "r_" + fileName; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					} else {
						int y = (h - w) / 2;
						str = "convert " + path + fileName + " -crop " + w + "x" + w + "+" + "0+" + y + " -resize 48x48 " + path + "r_" + fileName; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
				} else {
					str = "convert " + path + fileName + " -resize 170 " + path + "s_" + fileName;
					ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str);
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + path + fileName + " -crop " + h + "x" + h + "+" + x + "+0 -resize 48x48 " + path + "r_" + fileName; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					} else {
						int y = (h - w) / 2;
						str = "convert " + path + fileName + " -crop " + w + "x" + w + "+" + "0+" + y + " -resize 48x48 " + path + "r_" + fileName; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
				}
			} else {
				if (w >= 170) {
					str = "convert " + path + fileName + " -resize 170 " + path + "s_" + fileName;
					ps = java.lang.Runtime.getRuntime().exec(str);
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + path + fileName + " -crop " + h + "x" + h + "+" + x + "+0 -resize 48x48 " + path + "r_" + fileName; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					} else {
						int y = (h - w) / 2;
						str = "convert " + path + fileName + " -crop " + w + "x" + w + "+" + "0+" + y + " -resize 48x48 " + path + "r_" + fileName; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
				} else {
					str = "convert " + path + fileName + " -resize 170 " + path + "s_" + fileName;
					ps = java.lang.Runtime.getRuntime().exec(str);
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + path + fileName + " -crop " + h + "x" + h + "+" + x + "+0 -resize 48x48 " + path + "r_" + fileName; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					} else {
						int y = (h - w) / 2;
						str = "convert " + path + fileName + " -crop " + w + "x" + w + "+" + "0+" + y + " -resize 48x48 " + path + "r_" + fileName; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
				}
			}
			if (ps.waitFor() == 0 && pr.waitFor() == 0) {
				return "OK";
			} else {
				return "error";
			}
		}
		return "error";
	}

	/***************************************************************************
	 * 获取个人图像的缩略图
	 * 
	 * @param strpath
	 *            图片的地址。。
	 * @param strfname
	 *            图片的名称包括后缀名。。
	 * @throws IOException
	 */
	public String personlogo(String strpath, String strfname) throws IOException, InterruptedException // 个人头像的缩略图
	{
		String str = "";
		ImageIcon icon = new ImageIcon(strpath + strfname);
		if (icon != null) {
			int h = icon.getIconHeight();
			int w = icon.getIconWidth();
			Process pm = null;
			Process pr = null;
			Process ps = null;
			// ///////操作系统为WINDOWS
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") >= 0) {
				if (w <= 200) {
					str = "convert " + strpath + strfname + " -resize 200 " + strpath + "m_" + strfname; // 缩略图(200×150)
																											// 用于好友、群组、评论等
					pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "s_" + strfname; // 缩略图(200×150)
																												// 用于好友、群组、评论等
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 48x48 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					} else {
						int x = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "s_" + strfname; // 缩略图(200×150)
																														// 用于好友、群组、评论等
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+" + "0" + "+" + x + " -resize 48x48 " + strpath + "r_"
								+ strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
				} else // 此处是高和宽都大于200
				{
					str = "convert " + strpath + strfname + " -resize 200 " + strpath + "m_" + strfname; // 缩略图(200×150)
																											// 用于好友、群组、评论等
					pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "s_" + strfname; // 缩略图(200×150)
																												// 用于好友、群组、评论等
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 48x48 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					} else {
						int x = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "s_" + strfname; // 缩略图(200×150)
																														// 用于好友、群组、评论等
						ps = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+" + "0" + "+" + x + " -resize 48x48 " + strpath + "r_"
								+ strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
				}
			}
			// ////////操作系统为其他操作系统
			else {
				if (w <= 200) {
					str = "convert " + strpath + strfname + " -resize 200 " + strpath + "m_" + strfname; // 缩略图(200×150)
																											// 用于好友、群组、评论等
					pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "s_" + strfname; // 缩略图(200×150)
																												// 用于好友、群组、评论等
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 48x48 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					} else {
						int x = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "s_" + strfname; // 缩略图(200×150)
																														// 用于好友、群组、评论等
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+" + "0" + "+" + x + " -resize 48x48 " + strpath + "r_"
								+ strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
				} else // 此处是高和宽都大于200
				{
					str = "convert " + strpath + strfname + " -resize 200 " + strpath + "m_" + strfname; // 缩略图(200×150)
																											// 用于好友、群组、评论等
					pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					if (w > h) {
						int x = (w - h) / 2;
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "s_" + strfname; // 缩略图(200×150)
																												// 用于好友、群组、评论等
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + h + "x" + h + "+" + x + "+0 -resize 48x48 " + strpath + "r_" + strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					} else {
						int x = (h - w) / 2;
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "s_" + strfname; // 缩略图(200×150)
																														// 用于好友、群组、评论等
						ps = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
						str = "convert " + strpath + strfname + " -crop " + w + "x" + w + "+" + "0" + "+" + x + " -resize 48x48 " + strpath + "r_"
								+ strfname; // 生成方框图，宽高都为60
						pr = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
				}
			}
			if (pm.waitFor() == 0 && pr.waitFor() == 0 && ps.waitFor() == 0) {
				return "OK";
			} else {
				return "error";
			}

		} else {
			return "error";
		}

	}

	/**
	 * 此方法班级大事记使用
	 * 
	 * @param strpath
	 *            图片的地址。。
	 * @param strfname
	 *            图片的名称包括后缀名。。
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String square(String strpath, String strfname) throws IOException, InterruptedException {
		String str = "";
		ImageIcon icon = new ImageIcon(strpath + strfname);
		if (icon != null) {
			int h = icon.getIconHeight();
			int w = icon.getIconWidth();
			Process pm = null;
			// ///////操作系统为WINDOWS
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") >= 0) {
				if (w > 100) {
					if (w > h) {
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 缩略图(100)
																												// 班级大事记
						pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					} else {
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 缩略图(200×150)
																														// 用于好友、群组、评论等
						pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
				} else {
					if (w > h) {
						str = "convert " + strpath + strfname + " -resize " + w + " " + strpath + "l_" + strfname; // 缩略图(100)
																													// 班级大事记
						pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					} else {
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 缩略图(100)
																														// 班级大事记
						pm = java.lang.Runtime.getRuntime().exec("cmd /c " + str); // 调用控制台
					}
				}
			} else // 操作系统为其它的操作系统
			{
				if (w > 100) {
					if (w > h) {
						str = "convert " + strpath + strfname + " -resize 100 " + strpath + "l_" + strfname; // 缩略图(100)
																												// 班级大事记
						pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					} else {
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 缩略图(200×150)
																														// 用于好友、群组、评论等
						pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
				} else {
					if (w > h) {
						str = "convert " + strpath + strfname + " -resize " + w + " " + strpath + "l_" + strfname; // 缩略图(100)
																													// 班级大事记
						pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					} else {
						str = "convert " + strpath + strfname + " -resize " + w + "x100 " + strpath + "l_" + strfname; // 缩略图(100)
																														// 班级大事记
						pm = java.lang.Runtime.getRuntime().exec(str); // 调用控制台
					}
				}
			}
			if (pm.waitFor() == 0) {
				return "OK";
			} else {
				return "error";
			}
		} else {
			return "error";
		}
	}
}

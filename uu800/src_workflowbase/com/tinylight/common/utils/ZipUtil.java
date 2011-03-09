package com.tinylight.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * Zip压缩工具类，提供zip文件的压缩解压缩功能
 * 计划用作数据库的导出/导入，对MyISAM格式的数据库表进行操作，具体位置在<安装路径/pm-mysql/data/tlpm>下
 * @author Vince
 * @since 2009-4-13
 */
public class ZipUtil {
	/**
	 * 压缩文件
	 * @param zipFileName 生成的zip文件名
	 * @param inputFile 需要压缩的文件、文件夹
	 * @throws Exception
	 */
	public static void zip(String zipFileName, String inputFile)
			throws Exception {
		File f = new File(inputFile);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		zip(out, f, null);
		System.out.println("zip done");
		out.close();
	}

	/**
	 * 压缩文件函数具体实现
	 * @param out
	 * @param f
	 * @param base
	 * @throws Exception
	 */
	private static void zip(ZipOutputStream out, File f, String base)
			throws Exception {
		System.out.println("zipping " + f.getAbsolutePath());
		if (f.isDirectory()) {
			File[] fc = f.listFiles();
			if (base != null)
				out.putNextEntry(new ZipEntry(base + "/"));
			base = base == null ? "" : base + "/";
			for (int i = 0; i < fc.length; i++) {
				zip(out, fc[i], base + fc[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			while ((b = in.read()) != -1)
				out.write(b);
			in.close();
		}
	}
 
	/**
	 * 解压文件
	 * @param zipFileName 需要解压的文件名称
	 * @param outputDirectory 解压到的目录
	 * @throws Exception
	 */
	public static void unzip(String zipFileName, String outputDirectory)
			throws Exception {
		ZipInputStream in = new ZipInputStream(new FileInputStream(zipFileName));
		ZipEntry z;
		while ((z = in.getNextEntry()) != null) {
			String name = z.getName();
			if (z.isDirectory()) {
				name = name.substring(0, name.length() - 1);
				File f = new File(outputDirectory + File.separator + name);
				f.mkdir();
				System.out.println("MD " + outputDirectory + File.separator
						+ name);
			} else {
				System.out.println("unziping " + z.getName());
				File f = new File(outputDirectory + File.separator + name);
				f.createNewFile();
				FileOutputStream out = new FileOutputStream(f);
				int b;
				while ((b = in.read()) != -1)
					out.write(b);
				out.close();
			}
		}
		in.close();
	}

	/**
	 * 测试main函数
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//ZipUtil.zip("c:/test.database","c:/test");
			//ZipUtil.unzip("c:/test.database", "c:/test");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}

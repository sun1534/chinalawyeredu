package com.sxit.common.util;


import java.text.DecimalFormat;

/**
 * @author 华锋 Jun 6, 2009 4:29:05 PM
 * 
 */
public class FileDirUtil {

	private static DecimalFormat uidDf = new DecimalFormat("00000000");
	private static DecimalFormat seqDf = new DecimalFormat("00000000");

	/**
	 * <pre>
	 * 头像Logo，根据uid来设置目录
	 * uid为8位,已百千和w，10w来分2层目录
	 * 
	 * 这里暂时考虑小于1000w的情况，因为只分2层目录，如果大于1000w，一定新增一个文件服务器
	 *
	 * </pre>
	 * 
	 * @param uid
	 * @return
	 */
	public static String getDirByUid(int uid) {
		String uidstr = uidDf.format(uid);
		StringBuffer sb = new StringBuffer();

//		sb.append(uidstr.substring(2, 4)).append(File.separator).append(uidstr.substring(4, 6)).append(File.separator);
		sb.append("/").append(uidstr.substring(2, 4)).append("/").append(uidstr.substring(4, 6)).append("/");

		return sb.toString();
	}
	
	/**
	 * <pre>
	 * 根据序列id来生成目录，控制的是1个目录下放1k个文件
	 * 也只分2层目录
	 * </pre>
	 * @param seqid
	 * @return
	 */
	public static String getDirBySeq(int seqid){
		String uidstr = seqDf.format(seqid);
		StringBuffer sb = new StringBuffer();
		
		sb.append("/").append(uidstr.substring(2, 4)).append("/").append(uidstr.substring(4, 6)).append("/");

		return sb.toString();
	}
}
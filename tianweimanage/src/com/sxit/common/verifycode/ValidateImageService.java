/**
 * ValidateImageService.java
 */

package com.sxit.common.verifycode;

import java.io.ByteArrayOutputStream;

/**
 * @author 华锋 2008-1-6 下午03:04:45
 * 
 */

public interface ValidateImageService {
	/**
	 * 默认验证字符串
	 */
//	String Default_ValidateCode = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//	String DEFAULT_VALIDATECODE="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789\u4E00\u4E8C\u4E09\u56DB\u4E94\u516D\u4E03\u516B\u4E5D\u5341\u767E\u5343\u4E07\u5E7F\u897F\u5357\u5B81\u79D1\u56ED\u5927\u9053\u8F6F\u4EF6\u4E2D\u56FD\u4EBA\u6C11\u4E07\u5C81";
//	String DEFAULT_VALIDATECODE="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	String DEFAULT_VALIDATECODE="0123456789";
	
	/**
	 * 默认绘制干扰线的类型（不绘制干扰线）
	 */
	int Disturb_Type_Default = 0;
	/**
	 * 绘制单一色调的干扰线
	 */
	int Disturb_Type_Simple = 1;
	/**
	 * 绘制复杂的干扰线
	 */
	int Disturb_Type_Complex = 2;

	/**
	 * 生成验证图片并返回验证码
	 * 
	 * @param disturbType
	 * @param fontSize
	 * @param bos
	 * @param width
	 * @param height
	 * @param validateCode
	 * @param codeLength
	 * @return
	 */
	public abstract String createValidateCode(int disturbType, int fontSize, ByteArrayOutputStream bos, int width, int height, String validateCode,
			int codeLength);

}

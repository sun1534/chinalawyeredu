/**
 * ValidateCodeImage.java
 */

package com.sxit.common.verifycode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sxit.common.Constants;

/**
 * 
 * 生成验证码
 * 
 * @author 华锋 2009-1-5  下午03:03:41
 * 
 */
@SuppressWarnings("unchecked")
// public class ValidateImgAction extends StreamBasicAction implements SessionAware{
public class ValidateImgAction extends ActionSupport {
	private static final long serialVersionUID = 6894525175454169331L;
	private static final String Default_ValidateCode = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private int width;
	private int height;
	private int fontSize;
	private int codeLength;
	private String contentType;
	private InputStream inputStream;
	private ValidateImageService validateImageService;
	private int disturbType;

	public ValidateImgAction() {
//		LOG.info("执行 sssssssssssValidateImpAction...");
		System.out.println("Excute the default");
		this.contentType = "image/jpeg";
		this.width = 100;
		this.height = 40;
		this.fontSize = 20;
		this.codeLength = 5;
		this.validateImageService = new ValidateImageServiceImpl();
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setCodeLength(int codeLength) {
		this.codeLength = codeLength;
	}
	public void setDisturbType(int type){
		this.disturbType=type;
	}

	// 不通过spring来处理
	// public void setValidateImageService(ValidateImageService validateImageService) {
	// this.validateImageService = validateImageService;
	// }

	public InputStream getInputStream() {
		return this.inputStream;
	}

	public String execute() throws Exception {
//		LOG.info("执行 ValidateImpAction...");
//		System.out.println("HEREEEEE");
//		this.disturbType = 2;
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		// 清除系统缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
//		System.out.println("HEREEEEEDDDDDDDDDDDDDDDD:"+disturbType);
		
		//String validateCode = createInputStream(ValidateImageService.Disturb_Type_Default);
		String validateCode = createInputStream(this.disturbType);
//		System.out.println("validateCode:"+validateCode);
		LOG.debug("validateCode::::::::::::" + validateCode);
		// 此处的session设置为会话信息
		// session.put(Constants.VALIDATE_CODE, validateCode);
		ctx.getSession().put(Constants.VALIDATE_CODE, validateCode);
//		System.out.println("validateCode:"+ctx.getSession().get(Constants.VALIDATE_CODE));
		return SUCCESS;
	}

	private String createInputStream(int disturbType) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		String validateCode = null;
		// validateCode = validateImageService.createValidateCode(disturbType, fontSize, bos, width, height, getText("System.validateCode",
		// Default_ValidateCode), codeLength);
		validateCode = validateImageService.createValidateCode(disturbType, fontSize, bos, width, height, ValidateImageService.DEFAULT_VALIDATECODE,
				codeLength);
		inputStream = new ByteArrayInputStream(bos.toByteArray());
		bos.close();
		return validateCode;
	}
}
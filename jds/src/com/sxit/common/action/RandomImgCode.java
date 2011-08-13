package com.sxit.common.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.common.util.ImageCode;
import com.sxit.system.model.*;
import org.hibernate.Query;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * <p>
 * 功能： 设置个人页面风格
 * </p>
 * <p>
 * 作者： 张如兵
 * </p>
 * <p>
 * 公司： 深圳信科
 * </p>
 * <p>
 * 日期： 2004.9.29
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class RandomImgCode extends ActionSupport {
	private InputStream imageStream;

	public RandomImgCode() {
	}

	@Override
	public String execute() throws Exception {
		
		

		
		
    	ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) 
		ctx.get(ServletActionContext.HTTP_RESPONSE);
		
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		//第一种方式
//		ImageCode image = new ImageCode();
//		BufferedImage img = image.creatImage();
//		set("rand", image.getSRand());
		//ImageIO.write(img, "JPEG", output);
		//this.imageStream = new ByteArrayInputStream(output.toByteArray());
		
		//第二种方式
        String checkCode = getCheckCodeImage("123456789", 4, output);   
        set("rand", checkCode);
        //这里将output stream转化为 inputstream   
        this.imageStream = new ByteArrayInputStream(output.toByteArray());   
		
		
		output.close();  
		return SUCCESS;
	}

	public InputStream getImageStream() {
		return imageStream;
	}
	protected void set(String name, Object value) {
		ActionContext.getContext().getSession().put(name, value);
	}
	
    public String getCheckCodeImage(String str, int show, ByteArrayOutputStream output) {   
        Random random = new Random();   
        BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_3BYTE_BGR);   
        Font font = new Font("Arial", Font.PLAIN, 24);   
        int distance = 18;   
        Graphics d = image.getGraphics();   
        d.setColor(Color.WHITE);   
        d.fillRect(0, 0, image.getWidth(), image.getHeight());   
        d.setColor(new Color(random.nextInt(100) + 100, random.nextInt(100) + 100, random.nextInt(100) + 100));   
        for (int i = 0; i < 10; i++) {   
            d.drawLine(random.nextInt(image.getWidth()), random.nextInt(image.getHeight()), random.nextInt(image.getWidth()),   
                    random.nextInt(image.getHeight()));   
        }   
        d.setColor(Color.BLACK);   
        d.setFont(font);   
        String checkCode = "";   
        char tmp;   
        int x = -distance;   
        for (int i = 0; i < show; i++) {   
            tmp = str.charAt(random.nextInt(str.length() - 1));   
            checkCode = checkCode + tmp;   
            x = x + distance;   
            d.setColor(new Color(random.nextInt(100) + 50, random.nextInt(100) + 50, random.nextInt(100) + 50));   
            d.drawString(tmp + "", x, random.nextInt(image.getHeight() - (font.getSize())) + (font.getSize()));   
        }   
        d.dispose();   
        try {   
            ImageIO.write(image, "jpg", output);   
        } catch (IOException e) {   
  
        }   
        return checkCode;   
    }   

}

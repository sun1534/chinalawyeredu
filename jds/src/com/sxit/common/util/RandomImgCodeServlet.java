package com.sxit.common.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionContext;

/**
 * 生成随机验证码图片
 *
 * @author zrb 2007-7-16 11:16:50
 *
 */
public class RandomImgCodeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("image/jpeg");
        ImageCode image = new ImageCode();
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
            // 输出图象到页面
            BufferedImage img = image.creatImage();
            request.getSession().setAttribute("rand", image.getSRand());
            //System.out.println("image Rand Code = "+image.getSRand());
            ImageIO.write(img, "JPEG", response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            System.out.println("错误:" + e);
        }
    }

}

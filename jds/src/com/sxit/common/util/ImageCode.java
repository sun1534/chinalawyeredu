package com.sxit.common.util;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class ImageCode {

    public String sRand = "";

    public Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public BufferedImage creatImage() {
        // 在内存中创建图象
        int width = 80, height = 24;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(220, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 26));

        // 随机产生155条干扰线，
        g.setColor(getRandColor(190, 230));
        for (int i = 0; i < 60; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 取随机产生的认证码(4位数字)
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(10 + random.nextInt(80), 10 + random
                    .nextInt(80), 10 + random.nextInt(80)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 16 * i + 6, 22);
        }
        // 图象生效
        g.dispose();
        return image;
    }

    /**
     * @return Returns the sRand.
     */
    public String getSRand() {
        return sRand;
    }
}

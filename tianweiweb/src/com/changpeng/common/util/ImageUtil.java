package com.changpeng.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ImageUtil {
	public static void resize(String picFrom, String picTo, int _height, int _width) {
//		System.setProperty("jmagick.systemclassloader", "no");
		try {
			// Resize
			Runtime.getRuntime().exec("convert -resize "+_width+"x"+_height+" "+picFrom+" "+picTo);
			System.out.println("convert -resize "+_width+"x"+_height+" "+picFrom+" "+picTo);
//			File tofile = new File(picTo);
//			File todir = new File(tofile.getParent());
//			if (!todir.exists()) {
//				todir.mkdirs();
//			}
//
//			ImageInfo info = new ImageInfo(picFrom);
//			MagickImage image = new MagickImage(new ImageInfo(picFrom));
//			
//			// PixelPacket[] pp= image.getColormap();
//			// for(int i=0;i<pp.length;i++){
//			//        	
//			// }
//
//			int height = image.getDimension().height;
//			int width = image.getDimension().width;
//			// System.out.println(height + "===" + width);
//
//			// int _height = (int) (height * 0.1);
//			// int _width = (int) (width * 0.1);
//
//			MagickImage scaled = image.scaleImage(_width, _height);// 小图片文件的大小.
//			scaled.setFileName(picTo);
//			scaled.writeImage(info);

//		} catch (MagickApiException ex) {
//			System.out.println("MagickApiException::::" + ex);
//			ex.printStackTrace();
//		} catch (MagickException ex) {
//			System.out.println("MagickException::::" + ex);
//			ex.printStackTrace();
//		}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 图片缩放，尺寸最大为maxsize 另一尺寸按比例缩放
	 * 
	 * @param picFrom
	 * @param picTo
	 * @param maxsize
	 */
	public static void resize(String picFrom, String picTo, int maxsize) {
		try {
			resize(picFrom, picTo, maxsize,maxsize);
//			File tofile = new File(picTo);
//			File todir = new File(tofile.getParent());
//			if (!todir.exists()) {
//				todir.mkdirs();
//			}
//			System.out.println("原始地址" + picFrom);
//			System.out.println("存放地址" + picTo);
//			ImageInfo info = new ImageInfo(picFrom);
//			MagickImage image = new MagickImage(new ImageInfo(picFrom));
//
//			// 获取现在的宽度和长度
//			int height = image.getDimension().height;
//			int width = image.getDimension().width;
//			int _height = height;
//			int _width = width;
//			if (height > width) {
//				if (height > maxsize) {
//					_height = maxsize;
//					_width = (int) (width * ((float) _height / height));
//				}
//			} else {
//				if (width > maxsize) {
//					_width = maxsize;
//					_height = (int) (height * ((float) _width / width));
//				}
//			}
//
//			MagickImage scaled = image.scaleImage(_width, _height);// 小图片文件的大小.
//			scaled.setFileName(picTo);
//			scaled.writeImage(info);

		} catch (Exception ex) {
			System.out.println("Exception::::" + ex);
			ex.printStackTrace();

		}
//		System.setProperty("jmagick.systemclassloader", "no");
//		try {
//			File tofile = new File(picTo);
//			File todir = new File(tofile.getParent());
//			if (!todir.exists()) {
//				todir.mkdirs();
//			}
//			System.out.println("原始地址" + picFrom);
//			System.out.println("存放地址" + picTo);
//			ImageInfo info = new ImageInfo(picFrom);
//			MagickImage image = new MagickImage(new ImageInfo(picFrom));
//
//			// 获取现在的宽度和长度
//			int height = image.getDimension().height;
//			int width = image.getDimension().width;
//			int _height = height;
//			int _width = width;
//			if (height > width) {
//				if (height > maxsize) {
//					_height = maxsize;
//					_width = (int) (width * ((float) _height / height));
//				}
//			} else {
//				if (width > maxsize) {
//					_width = maxsize;
//					_height = (int) (height * ((float) _width / width));
//				}
//			}
//
//			MagickImage scaled = image.scaleImage(_width, _height);// 小图片文件的大小.
//			scaled.setFileName(picTo);
//			scaled.writeImage(info);
//
//		} catch (MagickApiException ex) {
//			System.out.println("MagickApiException::::" + ex);
//			ex.printStackTrace();
//
//		} catch (MagickException ex) {
//			System.out.println("MagickException::::" + ex);
//			ex.printStackTrace();
//		}
	}

	public static int getHeight(String picpath) {
//		System.setProperty("jmagick.systemclassloader", "no");
		int height=0;
		try{
			Process p=Runtime.getRuntime().exec("identify "+picpath);
//			OutputStream os=p.getOutputStream();
			InputStream is=p.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			String line;
			while((line=br.readLine())!=null){
				String[] rets=line.split(" ");
				height=Integer.parseInt(rets[2].substring(rets[2].indexOf("x")+1));
				System.out.println(line);
				System.out.flush();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return height;
//		try {
//			MagickImage image = new MagickImage(new ImageInfo(picpath));
//			return image.getDimension().height;
//		} catch (MagickApiException ex) {
//			System.out.println("MagickApiException::::" + ex);
//			ex.printStackTrace();
//			return 0;
//		} catch (MagickException ex) {
//			System.out.println("MagickException::::" + ex);
//			ex.printStackTrace();
//			return 0;
//		}
	}

	public static int getWidth(String picpath) {
		int width=0;
		try{
			Process p=Runtime.getRuntime().exec("identify "+picpath);
//			OutputStream os=p.getOutputStream();
			InputStream is=p.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			String line;
			while((line=br.readLine())!=null){
				String[] rets=line.split(" ");
				width=Integer.parseInt(rets[2].substring(0,rets[2].indexOf("x")));
				System.out.println(line);
				System.out.flush();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return width;
//		System.setProperty("jmagick.systemclassloader", "no");
//		try {
//			MagickImage image = new MagickImage(new ImageInfo(picpath));
//			return image.getDimension().width;
//		} catch (MagickApiException ex) {
//			System.out.println("MagickApiException::::" + ex);
//			ex.printStackTrace();
//			return 0;
//		} catch (MagickException ex) {
//			System.out.println("MagickException::::" + ex);
//			ex.printStackTrace();
//			return 0;
//		}
	}

	/**
	 * 图片切割
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param picFrom源图片地址
	 * @param picto处理后的图片地址
	 */
	public static void cutimage(int x, int y, int width, int height, String picFrom, String picto) {
		
		try {
//			System.out.println("x=" + x + "...y=" + y + "....width=" + width + "...height=" + height + "...picFrom=" + picFrom + "...picto=====" + picto);

			Runtime.getRuntime().exec("convert -crop "+width+"x"+height+"+"+x+"+"+y+" "+picFrom+" "+picto);
			System.out.println("convert -crop "+width+"x"+height+"+"+x+"+"+y+" "+picFrom+" "+picto);
//			ImageInfo info = new ImageInfo(picFrom);
//
//			System.out.println("执行了ImageInfo");
//			MagickImage image = new MagickImage(new ImageInfo(picFrom));
//			System.out.println("执行了MagickImage");
//			
//			Rectangle ra = new Rectangle(x, y, width, height);
//
//			MagickImage cropped = image.cropImage(ra);
//
//			cropped.setFileName(picto);
//			cropped.writeImage(info);

		} catch (Exception e) {

			System.out.println("MagickException::::" + e);
			e.printStackTrace();
		}
		
		
//		if(width+x<getWidth(picFrom)||height+y<getHeight(picFrom)){
//			x=0;
//			y=0;
//			width=0;
//			height=0;
//		}
//		System.setProperty("jmagick.systemclassloader", "no");
//		try {
//			System.out.println("x=" + x + "...y=" + y + "....width=" + width + "...height=" + height + "...picFrom=" + picFrom + "...picto====="
//					+ picto);
//
//			ImageInfo info = new ImageInfo(picFrom);
//
//			System.out.println("执行了ImageInfo");
//			MagickImage image = new MagickImage(new ImageInfo(picFrom));
//			System.out.println("执行了MagickImage");
//			// Rectangle ra =new Rectangle(x, y, width, height);
//
//			Rectangle ra = new Rectangle(x, y, width, height);
//			//System.out.println("............");
//			MagickImage cropped = image.cropImage(ra);
//			//System.out.println("222222222");
//			cropped.setFileName(picto);
//			cropped.writeImage(info);
//
//		} catch (Exception e) {
//
//			System.out.println("MagickException::::" + e);
//			e.printStackTrace();
//		}
	}
	public static void main(String[] args){
		//resize("/home/www/jxq2.0/henan/apache-tomcat-6.0.18/webapps/ROOT/WEB-INF/classes/a.jpg","/home/www/jxq2.0/henan/apache-tomcat-6.0.18/webapps/ROOT/WEB-INF/classes/b.jpg",10);
		System.out.println(getHeight("/home/www/jxq2.0/henan/apache-tomcat-6.0.18/webapps/ROOT/WEB-INF/classes/a.jpg"));
		System.out.println(getWidth("/home/www/jxq2.0/henan/apache-tomcat-6.0.18/webapps/ROOT/WEB-INF/classes/a.jpg"));
	}
}

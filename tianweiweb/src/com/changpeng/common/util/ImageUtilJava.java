package com.changpeng.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class ImageUtilJava {
	public static void resize(String picFrom, String picTo, int _height, int _width) {
		// System.setProperty("jmagick.systemclassloader", "no");
		try {
			// Resize
			ImageCut cut=new ImageCut();
			
			
			java.io.File file = new java.io.File(picFrom);
			BufferedImage bi = null;
		
		
				bi = javax.imageio.ImageIO.read(file);
			
				int originwidth= bi.getWidth(); // 获得 宽度
				int originheight=bi.getHeight();
			
				System.out.println(originwidth+"--"+originheight);
				
				float per ;
				
				//如果比要缩放的尺寸小就不要缩放
				if(_height>=originheight && _width >= originwidth){
//					_height = (int)originheight;
//					_width = (int)_width;
					File dest=new java.io.File(picTo);
					org.apache.commons.io.FileUtils.copyFile(file, dest);
					return;
					
				}else{
//					if( needper ){
						if(originheight/_height >originwidth/_width){ //如果高度的缩放比例较大
							per =  (float)_height/originheight;
						}else{
							per =  (float)_width/originwidth;
						}
						
						System.out.println(per);
						
						_height = (int)(originheight * per );
						_width = (int)(originwidth * per);
//					}else{
//						to_height = _height;
//						to_width = _width;
//					}
				}
//				if(_height>_width)
//					_width=_height;
				cut.scale(picFrom, picTo, _width, _height);
	
		} catch (IOException e) {
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
			resize(picFrom, picTo, maxsize, maxsize);
		

		} catch (Exception ex) {
			System.out.println("Exception::::" + ex);
			ex.printStackTrace();

		}
	
	}

	public static int getHeight(String picpath) {
		// // System.setProperty("jmagick.systemclassloader", "no");
		// int height=0;
		// try{
		// Process p=Runtime.getRuntime().exec("identify "+picpath);
		// // OutputStream os=p.getOutputStream();
		// InputStream is=p.getInputStream();
		// InputStreamReader isr=new InputStreamReader(is);
		// BufferedReader br=new BufferedReader(isr);
		// String line;
		// while((line=br.readLine())!=null){
		// String[] rets=line.split(" ");
		// height=Integer.parseInt(rets[2].substring(rets[2].indexOf("x")+1));
		// System.out.println(line);
		// System.out.flush();
		// }
		//			
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		// return height;

		java.io.File file = new java.io.File(picpath);
		BufferedImage bi = null;
		boolean imgwrong = false;
		try {
			// 读取图片
			bi = javax.imageio.ImageIO.read(file);
			try {
				// 判断文件图片是否能正常显示,有些图片编码不正确
				int i = bi.getType();
				imgwrong = true;
			} catch (Exception e) {
				imgwrong = false;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (imgwrong) {
			// bi.getWidth(); //获得 宽度
			return bi.getHeight(); // 获得 高度
		} else {
			return 0;
		}

	}

	public static int getWidth(String picpath) {
		// int width=0;
		// try{
		// Process p=Runtime.getRuntime().exec("identify "+picpath);
		// // OutputStream os=p.getOutputStream();
		// InputStream is=p.getInputStream();
		// InputStreamReader isr=new InputStreamReader(is);
		// BufferedReader br=new BufferedReader(isr);
		// String line;
		// while((line=br.readLine())!=null){
		// String[] rets=line.split(" ");
		// width=Integer.parseInt(rets[2].substring(0,rets[2].indexOf("x")));
		// System.out.println(line);
		// System.out.flush();
		// }
		//			
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		// return width;
		// System.setProperty("jmagick.systemclassloader", "no");
		// try {
		// MagickImage image = new MagickImage(new ImageInfo(picpath));
		// return image.getDimension().width;
		// } catch (MagickApiException ex) {
		// System.out.println("MagickApiException::::" + ex);
		// ex.printStackTrace();
		// return 0;
		// } catch (MagickException ex) {
		// System.out.println("MagickException::::" + ex);
		// ex.printStackTrace();
		// return 0;
		// }

		java.io.File file = new java.io.File(picpath);
		BufferedImage bi = null;
		boolean imgwrong = false;
		try {
			// 读取图片
			bi = javax.imageio.ImageIO.read(file);
			try {
				// 判断文件图片是否能正常显示,有些图片编码不正确
				int i = bi.getType();
				imgwrong = true;
			} catch (Exception e) {
				imgwrong = false;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (imgwrong) {
			return bi.getWidth(); // 获得 宽度
			// return bi.getHeight(); //获得 高度
		} else {
			return 0;
		}
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
			File from=new File(picFrom);
		BufferedImage	srcImage	 = javax.imageio.ImageIO.read(from);
		BufferedImage dest=  srcImage.getSubimage(x, y, width, height);
		OutputStream fos=new FileOutputStream(picto);
		ImageIO.write(dest, "JPEG", fos);
		
	
			
			
//			System.out.println("x=" + x + "...y=" + y + "....width=" + width + "...height=" + height + "...picFrom=" + picFrom + "...picto====="
//					+ picto);
//
//			Runtime.getRuntime().exec("convert -crop " + width + "x" + height + "+" + x + "+" + y + " " + picFrom + " " + picto);
//			System.out.println("convert -crop " + width + "x" + height + "+" + x + "+" + y + " " + picFrom + " " + picto);
//			// ImageInfo info = new ImageInfo(picFrom);
//			//
//			// System.out.println("执行了ImageInfo");
//			// MagickImage image = new MagickImage(new ImageInfo(picFrom));
//			// System.out.println("执行了MagickImage");
//			//			
//			// Rectangle ra = new Rectangle(x, y, width, height);
//			//
//			// MagickImage cropped = image.cropImage(ra);
//			//
//			// cropped.setFileName(picto);
//			// cropped.writeImage(info);
			
//			ImageCut ic=new ImageCut();
//			ic.cut(picFrom, picto, destWidth, destHeight);

		} catch (Exception e) {

			System.out.println("MagickException::::" + e);
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// resize("/home/www/jxq2.0/henan/apache-tomcat-6.0.18/webapps/ROOT/WEB-INF/classes/a.jpg","/home/www/jxq2.0/henan/apache-tomcat-6.0.18/webapps/ROOT/WEB-INF/classes/b.jpg",10);
		System.out.println(getHeight("/home/www/jxq2.0/henan/apache-tomcat-6.0.18/webapps/ROOT/WEB-INF/classes/a.jpg"));
		System.out.println(getWidth("/home/www/jxq2.0/henan/apache-tomcat-6.0.18/webapps/ROOT/WEB-INF/classes/a.jpg"));
	}
}

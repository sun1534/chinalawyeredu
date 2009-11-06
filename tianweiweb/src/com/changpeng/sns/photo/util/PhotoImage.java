package com.changpeng.sns.photo.util;

import com.changpeng.common.util.ImageUtil;

public class PhotoImage {
	public static void resize(String picFrom, String picTo,int _height,int _width, boolean needper ) {

//		float per ;
//
//		float height = getHeight(picFrom); //相片的实际高度
//		float width = getWidth(picFrom); //相片的实际宽度
//		
//		int to_height = 0;
//		int to_width = 0;
//		
//		//如果比要缩放的尺寸小就不要缩放
//		if(height<_height && width < _width){
//			to_height = (int)height;
//			to_width = (int)width;
//		}else{
//			if( needper ){
//				if(height/_height > width/_width){ //如果高度的缩放比例较大
//					per =  _height/height;
//				}else{
//					per = _width/width;
//				}
//				
//				to_height = (int)(height * per );
//				to_width = (int)(width * per);
//			}else{
//				to_height = _height;
//				to_width = _width;
//			}
//		}
//		if(to_height>to_width)
//			to_width=to_height;
		ImageUtil.resize(picFrom, picTo, _height,_width);
	}
	
	public static int getHeight(String picpath){
		return ImageUtil.getHeight(picpath);
	}
	
	public static int getWidth(String picpath){
		return ImageUtil.getWidth(picpath);

	}
}


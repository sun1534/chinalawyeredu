package com.changpeng.sns.photo.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.action.AbstractSwfUploadAction;
import com.changpeng.common.util.DesPlus;
import com.changpeng.common.util.FileDirUtil;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.sns.photo.model.SnsPhotoAlbum;
import com.changpeng.sns.photo.service.PhotoService;
import com.changpeng.sns.photo.util.PhotoImage;

/**
 * 
 * 上传照片到某个地方,同时在表里面对这条数据加1
 * 
 * @author 华锋 May 20, 2009 9:52:52 PM
 * 
 */
public class UploadPhotoAction extends AbstractSwfUploadAction {
	private static Logger LOG = Logger.getLogger(UploadPhotoAction.class);
	private static final DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");

//	private String albumName;
	private SnsPhotoAlbum album;
	private List albumlist;
	HttpSession session;

	public UploadPhotoAction() {
		this.rightCode="PUBLIC";
		this.moduleid = 16;
		
	}

	@Override
	protected String go() throws Exception {
		try {
			System.setProperty("jmagick.systemclassloader", "no");
			
			String picFrom;
			String picTo;
			String picTo_s;
			
			final int s_height = 85;
			final int s_width = 85;
			final int _height = 1024;
			final int _width = 620;

			if (albumid == 0) {
				this.redirectURL = "../photo/albumlist.action";
				message = "您没有选择相册,请先选择";
				return "message";
			}

			PhotoService photoService = (PhotoService) this.getBean("photoService");
			UserService userService =  (UserService) this.getBean("userService");
			for (int i = 0; i < filenames.length; i++) {

				String smllname = newFileName[i].replace(".", "_s.");

				String picrealpath = ServletActionContext.getServletContext().getRealPath(com.changpeng.common.sysdata.CommonData.DEFAULT_PIC_URL + FileDirUtil.getDirBySeq(albumid));

				picFrom = dstPath[i]; // 上传的原图片地址
				
				System.out.println("======================="+picFrom);
				
				picTo = picrealpath + "/" + newFileName[i];// 要生成的大图的存放路径
				picTo_s = picrealpath + "/" + smllname; // 小图 的存放路径
//				Thread.sleep(500);
				PhotoImage.resize(picFrom, picTo, _height, _width, true);
//				Thread.sleep(500);
				PhotoImage.resize(picFrom, picTo_s, s_height, s_width, true);

				String photoName = FiledataFileName[i].substring(0, FiledataFileName[i].lastIndexOf("."));

				if(photoName.length()>20){
					photoName=photoName.substring(0, 20);
				}
				Userinfo user = (Userinfo) userService.getUserinfoById(userid);
				if(user.getUserRole()==4)
					user.setUserRole((short)3);
				photoService.addPhoto(photoName, smllname, albumid, userid,  userip, newFileName[i]);
				// log.debug("filenames:"+filenames[i]);
				// log.debug("uploadFileName:"+uploadFileName[i].substring(0,
				// uploadFileName[i].lastIndexOf(".")));
				
				//发个动态吧
//				1=姓名;2=相片url;
			
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		this.result="successed";
		return "result";
	}

	@Override
	public String getin() {

		PhotoService service = (PhotoService) this.getBean("photoService");
		this.album = service.getAlbumById(albumid1);
		HttpServletRequest request = ServletActionContext.getRequest(); 
		session = request.getSession();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		
		/*    加密前的字符串: "用户ID,相册ID,当前时间戳" 格式   */
		String beforeEncrypt = String.valueOf(this.currentUserid)+","+albumid1+","+sdf.format(new Date());
		System.out.println("加密前的======"+beforeEncrypt);
		/*   加密的密钥，为当天的时间   */
		String passwd = df.format(new Date());
		
		try {
			DesPlus des = new DesPlus(passwd);
			jxq_ = des.encrypt(beforeEncrypt);
			System.out.println("加密后的======"+jxq_);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "input";
	}


//	public String getAlbumName() {
//		return albumName;
//	}
//
//	public void setAlbumName(String albumname) {
//		this.albumName = albumname;
//	}

	public SnsPhotoAlbum getAlbum() {
		return album;
	}

	public List getAlbumlist() {
		return albumlist;
	}

	public void setAlbumlist(List albumlist) {
		this.albumlist = albumlist;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	private int albumid1;

	public int getAlbumid() {
		return albumid1;
	}

	public void setAlbumid(int albumid) {
		this.albumid1 = albumid;
	}
	
	
}

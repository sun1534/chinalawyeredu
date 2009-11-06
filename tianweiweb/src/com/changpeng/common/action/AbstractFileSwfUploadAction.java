package com.changpeng.common.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.changpeng.common.util.DesPlus;
import com.changpeng.common.util.FileDirUtil;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("unused")
public abstract class AbstractFileSwfUploadAction extends AbstractAction {

	private static Logger log = Logger.getLogger(AbstractSwfUploadAction.class);
	private static final DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
	private static final long serialVersionUID = 572146812454l;
	private static final int BUFFER_SIZE = 16 * 1024;

	// 文件标题
	private String title;

	// 用File数组来封装多个上传文件域对象
	protected File[] Filedata;

	// 用String数组来封装多个上传文件名
	protected String[] FiledataFileName;

	// 用String数组来封装多个上传文件类型
	protected String[] FiledataContentType;

	// 保存文件的目录路径(通过依赖注入)
	private String savePath;

	// 上传后的临时文件路径 ,每个类中操作这个路径
	protected String[] filenames;

	protected String[] dstPath;
	protected String[] saveurl;

	protected String[] newFileName;

	protected long photoid;

	protected String jxq_;

	protected int userid;

	protected int albumid;

	@Override
	public String execute() throws Exception {
		photoid=System.currentTimeMillis();
		// userip = ServletActionContext.getRequest().getRemoteAddr();
		String passwd = df.format(new Date());
		log.info("jxq_:" + jxq_);

		try {
			DesPlus des = new DesPlus(passwd);
			String jxqstr = des.decrypt(jxq_);
			log.info("上传图片：" + jxqstr);

			String[] uploadparams = jxqstr.split(",");
			if (uploadparams.length == 3) {
				userid = Integer.parseInt(uploadparams[0]);
				albumid = Integer.parseInt(uploadparams[1]);
			} else {
				message = "上传失败，请稍候重试！";
				return "message";
			}

			String result = "message";

			ActionContext ac = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);

			if (request instanceof MultiPartRequestWrapper) {
				MultiPartRequestWrapper multi = (MultiPartRequestWrapper) request;
				// fieldname
				Enumeration en = multi.getFileParameterNames();

				
				
				
				while (en != null && en.hasMoreElements()) {

					String inputName = (String) en.nextElement();
					
					
				
					
					// System.out.println(inputName);
					// 文件
					Filedata = multi.getFiles(inputName);
					// 文件名
//					FiledataFileName = multi.getFileSystemNames(inputName);
					
					System.out.println("inputName::::::::::"+inputName+",,,"+FiledataFileName[0]);
					
					
					// 类型
					FiledataContentType = multi.getContentTypes(inputName);

					if (Filedata != null) {
						filenames = new String[Filedata.length];
						dstPath = new String[Filedata.length];
						saveurl=new String[Filedata.length];
						newFileName = new String[Filedata.length];
						for (int i = 0; i < Filedata.length; i++) {
							System.out.println(i+"=========="+Filedata[i].getName());

							if (FiledataFileName[i].lastIndexOf(".") + 1 == FiledataFileName[i].length()) {
								this.message = "您上传的文件格式错误";
								return ERROR;
							}

							String regix = FiledataFileName[i].substring(FiledataFileName[i].lastIndexOf("."));
							// regix="."+FiledataContentType[i];
//							regix = ".jpg";

							
							newFileName[i] = String.valueOf(this.userid) + "_" + photoid + regix;

							String realpath = ServletActionContext.getServletContext().getRealPath(
									"userfile" + FileDirUtil.getDirBySeq(albumid));
							String savepath="/userfile" + FileDirUtil.getDirBySeq(albumid);
							File fp = new File(realpath);
							if (!fp.exists()) {
								fp.mkdirs();
//								try{
//									Runtime.getRuntime().exec("chmod 777 "+realpath);
//								}catch(Exception e){
//									e.printStackTrace();
//								}
							}
							dstPath[i] = realpath + "/" + newFileName[i];
							saveurl[i]=savepath+"/"+newFileName[i];
							filenames[i] = newFileName[i];
							File dstFile = new File(dstPath[i]);
							copy(Filedata[i], dstFile);
//							break;

						}
//						break;
					}
				}

				result = go();

			}

			return result;

		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
			return ERROR;
		} finally {
		}

	}

	/**
	 * 将文件拷贝到目标位置
	 * 
	 * @param src
	 * @param dst
	 */
	private static void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setFiledata(File[] Filedata) {
		this.Filedata = Filedata;
	}

	public void setFiledataFileName(String[] FiledataFileName) {
		this.FiledataFileName = FiledataFileName;
	}

	public void setFiledataContentType(String[] FiledataContentType) {
		this.FiledataContentType = FiledataContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getJxq_() {
		return jxq_;
	}

	public void setJxq_(String jxq_) {
		this.jxq_ = jxq_;
	}

}
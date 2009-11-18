/**
 * 
 */
package com.sxit.service.action;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.service.CoreProduct;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class ProductCreateEditAction extends AbstractAction {

	protected String go() throws Exception {
//System.out.println("===========>"+upload);
		if (upload != null && upload.length() != 0) {
			try {
				if (upload.length() > 500 * 1024) {
					this.message = "上传的图片大小超出了规定的最大大小500K，请重新选择";
					this.nextPage = "productCreateEdit.action?productid=" + product.getId();
					return "message";
				}
				int index = uploadFileName.lastIndexOf(".");
				String name = System.currentTimeMillis() + uploadFileName.substring(index);
				// sysUser.setPhoto(name);
            	String indexDir = ServletActionContext.getServletContext().getRealPath("/upload/product/");
				File dir = new File(indexDir);
				if (!dir.exists())
					dir.mkdirs();
				File file = new File(indexDir + System.getProperty("file.separator") + name);
				upload.renameTo(file);
				product.setPic("/upload/product/"+name);
			} catch (Exception e) {
				System.out.println("照片上传失败..." + e);
				product.setPic("/css/images/product4.jpg");
				e.printStackTrace();
			}
		}else{
			product.setPic(oldpic);
			
		}
		
		if (exist == 1) {
			basicService.update(product);

			this.message = "产品信息修改成功";
		} else {
			product.setCreateuserid(this.getLoginUser().getUserid());
			product.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			basicService.save(product);
			this.message = "产品信息新增成功";
		}
		
		com.sxit.service.util.CommonDatas.getAllProducts();
		
		this.nextPage = "productList.action";

		return SUCCESS;

	}

	@Override
	public String input() {

		this.product = (CoreProduct) basicService.get(CoreProduct.class, productid);

		

		if (product == null) {
			product = new CoreProduct();
			exist = 0;
			oldpic="/css/images/product4.jpg";
		} else {
			exist = 1;
			oldpic=product.getPic();
			
		}
		set("product", product);

		return INPUT;

	}

	private String oldpic;
	
	
	private File upload;
	private String uploadFileName;

	private int productid;
	private CoreProduct product;
	private int exist;

	public int getExist() {
		return exist;
	}

	public void setExist(int exist) {
		this.exist = exist;
	}

	public CoreProduct getProduct() {
		if (product == null)
			product = (CoreProduct) get("product");
		return product;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getOldpic() {
		return oldpic;
	}

	public void setOldpic(String oldpic) {
		this.oldpic = oldpic;
	}
}

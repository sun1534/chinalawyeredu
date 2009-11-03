/**
 * ChangeUserPhoto.java
 */

package com.changpeng.common.mssql;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.models.system.SysUser;

/**
 * @author 华锋 2008-6-21 下午02:57:32
 * 
 */
public class ChangeUserPhoto {

	private static String path = "z:";

	public static void main1(String args[])throws Exception{
		
		File file = new File("Z:\\upfiles\\Images\\287\\2003\\12\\29\\33333.jpg");
		// FileOutputStream fos=new FileOutputStream("c://lawyerphotos/"+savename);

		file.renameTo(new File("Y:/33333.jpg"));
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Globals g = new Globals();
		BasicService bs = (BasicService) g.getBean("basicService");

		DetachedCriteria dc = DetachedCriteria.forClass(SysUser.class).add(Restrictions.eq("roleid", 1));
		List list = bs.findAllByCriteria(dc);
		java.io.PrintWriter log = new java.io.PrintWriter("c:\\lawyerphoto.sql");
		for (int i = 0; i < list.size(); i++) {
			SysUser lawyer = (SysUser) list.get(i);
			if (lawyer.getPhoto() != null && !"".equals(lawyer.getPhoto())) {

				String picurl = lawyer.getPhoto();
				int userid = lawyer.getUserid();

				int endfixindex = picurl.lastIndexOf(".");
				String endfix = picurl.substring(endfixindex);
				String savename = userid + endfix;

				File file = new File(path + picurl);
				// FileOutputStream fos=new FileOutputStream("c://lawyerphotos/"+savename);

				file.renameTo(new File("Y:\\" + savename));
				log.println("update sys_user set photo='"+savename+"' where userid="+userid);

				System.out.println(picurl + "剪切到" + savename + "成功");
			}

		}
		log.flush();
		log.close();

	}

}

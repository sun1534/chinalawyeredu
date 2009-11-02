package com.changpeng.courseware.action.ajax;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.*;

import java.util.*;

public class CoursewareLookAction extends AbstractAction {
	private Courseware ware;
	private BasicService service;
	private float pxminutes; //已学时间
	private float xuefen; //已或学分

	public void setWare(Courseware ware) {
		this.ware = ware;
	}

	public void setPxminutes(float pxminutes) {
		this.pxminutes = pxminutes;
	}

	public CoursewareLookAction() {
		service = (BasicService) this.getBean("basicService");
		this.rightCode = "document";
	}
	
	
	public float getPxminutes(){
		return pxminutes;
	}
	public float getXuefen(){
		return xuefen;
	}
	@Override
	protected String go() throws Exception {
		if (ware == null)
			ware = (Courseware) get("ware");

		pxminutes = Float.parseFloat(com.changpeng.jifen.util.NumberUtil
				.toMoney(pxminutes / 60));
		xuefen = Float.parseFloat(com.changpeng.jifen.util.NumberUtil
				.toMoney(pxminutes * ware.getXuefen() / ware.getWaretime()));

		debug("pxminutes.............................." + pxminutes);
		debug("xuefen.............................." + xuefen);

		List list = service.find(" from Lawyerlessonxf where lawer.userid="
				+ getLoginUser().getUserid() + " and wareid="
				+ ware.getWareid() + "");
		
		Lawyerlessonxf lessonxf=null;
		try {

			if (list.size() > 0) {
				 lessonxf = (Lawyerlessonxf) list.get(0);
				lessonxf.setPxminutes(lessonxf.getPxminutes() + pxminutes); // 修改学习时间
				if ((xuefen + lessonxf.getPxxf()) >= ware.getXuefen()) // 修改获得学分
					lessonxf.setPxxf(ware.getXuefen());
				else
					lessonxf.setPxxf(xuefen + lessonxf.getPxxf());
				service.update(lessonxf);
			} else {
				lessonxf = new Lawyerlessonxf();
				lessonxf.setLawer(getLoginUser());
				lessonxf.setTitle(ware.getWarename());
				lessonxf.setWareid(ware.getWareid());
				lessonxf.setLearnmode("文本课件");
				lessonxf.setPxreqminutes(ware.getWaretime());
				lessonxf.setPxminutes(pxminutes);
				if (xuefen >= ware.getXuefen()) // 修改获得学分
					lessonxf.setPxxf(ware.getXuefen());
				else
					lessonxf.setPxxf(xuefen);
				lessonxf.setRemarks("学习\"" + ware.getWarename() + "\"所获学分");
				lessonxf.setLastupdate(new java.sql.Timestamp(System
						.currentTimeMillis()));
				service.save(lessonxf);
			}
			
			pxminutes=lessonxf.getPxminutes();
			xuefen=lessonxf.getPxxf();
		} catch (Exception e) {
			debug(e.getMessage());
		}
		
		
		return SUCCESS;
	}
}

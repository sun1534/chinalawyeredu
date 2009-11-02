package com.changpeng.diaocha.action;

import com.changpeng.models.*;
import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.diaocha.service.DiaochareplyService;
import java.util.*;

public class DiaochaReplyAction extends AbstractAction {
	private String[] replys;
	private int[] wentiid;
	private int diaochaid;
	private String[] other;
	
	//	private List<Diaochawenti> wentilist;

	public void setOther(String[] other) {
		this.other = other;
	}

	public void setDiaochaid(int diaochaid) {
		this.diaochaid = diaochaid;
	}

	public void setReplys(String[] replys) {
		this.replys = replys;
	}
	public void setWentiid(int[] wentiid) {
		this.wentiid = wentiid;
	}

	public DiaochaReplyAction() {
		this.rightCode = "diaochareply";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected String go() throws Exception {

		BasicService service = (BasicService) this.getBean("basicService");
		List list = service
				.find("from Diaochareply where diaochawenti.diaocha.diaochaid="
						+ diaochaid + " and replyuser='"
						+ getLoginUser().getLoginname() + "'");
		if (list.size() > 0) {
			this.message = "您已经参与过该调查";
			return "message";
		}
		
		//修改为事务处理
		DiaochareplyService replyService = (DiaochareplyService) this.getBean("diaochareplyService");
		//if (wentilist == null)
			//wentilist = (List<Diaochawenti>) get("wentilist");	
		
		
		int count=replyService.saveReply(wentiid, replys,other, getLoginUser());
		
	
		this.nextPage = "diaochaStat.pl?diaochaid=" + diaochaid;
		if (count > 0) {
			Diaocha diaocha = (Diaocha) service.get(Diaocha.class, diaochaid);
			diaocha.setReplycount(diaocha.getReplycount() + 1);
			service.update(diaocha);
			this.message = "调查提交成功，感谢合作";

			return SUCCESS;
		} else {

			this.message = "您此次调查未提交任何选项";
			return "message";
		}

	}

}

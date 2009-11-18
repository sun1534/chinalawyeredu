/**
 * 
 */
package com.sxit.service.action;

import java.util.List;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.service.CoreAdvPos;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class AdvPosCreateEditAction extends AbstractListAction {

	protected String go() throws Exception {

		if (exist == 1) {
			basicService.update(advpos);
			this.message = "广告位修改成功";
		} else {
			
			System.out.println("========="+advpos.getPos());
			
			advpos.setCreateid(this.getLoginUser().getUserid());
			advpos.setCreatetime(new java.util.Date());
			basicService.save(advpos);
			this.message = "广告位新增成功";
		}
		this.nextPage = "advPosList.action";
		return SUCCESS;

	}

	@Override
	public String input() {

		this.advpos = (CoreAdvPos) basicService.get(CoreAdvPos.class, pos);
		
this.allPos=basicService.findAll(CoreAdvPos.class);
		
		if (advpos == null) {
			advpos = new CoreAdvPos();
			exist = 0;
		} else {
			exist = 1;
		}
		set("advpos", advpos);

		return INPUT;

	}
private List allPos;
private int pos;
	private CoreAdvPos advpos;
	private int exist;

	

	public int getExist() {
		return exist;
	}

	public void setExist(int exist) {
		this.exist = exist;
	}



	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public CoreAdvPos getAdvpos() {
		if (advpos == null)
			advpos = (CoreAdvPos) get("advpos");
		return advpos;
	}

	public List getAllPos() {
		return allPos;
	}
}

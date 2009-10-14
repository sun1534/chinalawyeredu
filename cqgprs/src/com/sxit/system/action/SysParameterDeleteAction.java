/**
 * TSysUserAddAction.java
 */
package com.sxit.system.action;

import com.sxit.common.BasicService;
import com.sxit.common.CommonDatas;
import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysParameter;

/**
 * 
 * 用户信息删除
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class SysParameterDeleteAction extends AbstractAction {

	
    private String paramname;
    public SysParameterDeleteAction() {
        this.rightCode = "sysParameterDelete";
       
    }

    /**
     * 选中的checkbox的名称
     * @param check
     */
    public void setParamname(String paramname) {
        this.paramname = paramname;
    }
	
	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		
		BasicService service = (BasicService) this.getBean("basicService");
		service.delete(SysParameter.class, paramname);
		synchronized (CommonDatas.SysParameter) {
			CommonDatas.SysParameter.remove(paramname);
    	}
		message="删除参数成功";
		this.nextPage="sysParameterList.action";
		// TODO Auto-generated method stub
		return SUCCESS;
	}
//	@Override
//	public String input() throws Exception {
//		return INPUT;
//	}
}

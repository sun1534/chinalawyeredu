//$Id: ModuleCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;




/**
 *
 * @author zrb
 */
public class ModuleDeleteAction extends AbstractAction {

	private TsysModule module;

	public ModuleDeleteAction() {
          rights="sys6,8";
	}
	public String go() throws HibernateException {
                TsysModule module = (TsysModule) get("module");
                getSession().delete(module);
                commit();
                message="删除成功！";
                nextpage="moduleList.action?pagenumber="+pagenumber;
		return SUCCESS;
	}

	public TsysModule getModule() {
         if (module==null)
            module = (TsysModule) get("module");
          return module;
	}
}

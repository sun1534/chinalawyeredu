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
public class ModuleCreateAction extends AbstractAction {

	private TsysModule module;


	public ModuleCreateAction() {
          rights="sys6,2";
		module = new TsysModule();
	}

	public String go() throws HibernateException {
		//module.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		getSession().save(module);
		set("module", module);
                nextpage="moduleList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TsysModule getModule() {
		return module;
	}
        public String input() throws Exception {
            return "input";
    }
}

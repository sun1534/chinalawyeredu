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
public class ModuleEditAction extends AbstractAction {

	private TsysModule module;

	public ModuleEditAction() {
          rights="sys6,4";
	}

	public String go() throws HibernateException {
                getSession().update(module);
		set("module", module);
                nextpage="moduleList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TsysModule getModule() {
         if (module==null)
            module = (TsysModule) get("module");
          return module;
	}

        public String input() throws Exception {
          return "input";
        }


}

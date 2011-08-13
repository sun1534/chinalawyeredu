package com.changpeng.service.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看考勤管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-07-07</p>
 * @版本： V1.0
 * @修改：
 */

public class KaoqinViewAction extends AbstractAction {
	private TserKaoqin kaoqin;
        private long kaoqinid;
	public KaoqinViewAction() {
          rights="ser4,1";
	   kaoqin = new TserKaoqin();
	}

	public String go() throws HibernateException {
           nextpage="kaoqinList.action?pagenumber="+pagenumber;
           kaoqin=(TserKaoqin)getSession().get(TserKaoqin.class,kaoqinid);
           if(kaoqin==null){
             message="未找到此考勤管理";
             return "message";
           }
           set("kaoqin", kaoqin);
           return SUCCESS;
	}
	public TserKaoqin getKaoqin() {
		return kaoqin;
	}
        public void setKaoqinid(long kaoqinid) {

          this.kaoqinid = kaoqinid;
        }
        public long getKaoqinid() {
          return this.kaoqinid;
        }

}

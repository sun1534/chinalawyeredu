package com.sxit.member.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看简历录入</p>
 * <p>作者： 雷华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-05-16</p>
 * @版本： V1.0
 * @修改：
 */

public class StudyexpViewAction extends AbstractAction {
	private TmemStudyexp studyexp;
        private int studyexpid;
	public StudyexpViewAction() {
          rights="mem4,1";
	   studyexp = new TmemStudyexp();
	}

	public String go() throws HibernateException {
           nextpage="studyexpList.action?pagenumber="+pagenumber;
           studyexp=(TmemStudyexp)getSession().get(TmemStudyexp.class,Integer.valueOf(studyexpid));
           if(studyexp==null){
             message="未找到此用户";
             return "message";
           }
           set("studyexp", studyexp);
           return SUCCESS;
	}
	public TmemStudyexp getStudyexp() {
		return studyexp;
	}
        public void setStudyexpid(int studyexpid) {

          this.studyexpid = studyexpid;
        }
        public int getStudyexpid() {
          return this.studyexpid;
        }

}

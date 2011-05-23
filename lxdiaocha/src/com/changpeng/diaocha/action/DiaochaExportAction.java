package com.changpeng.diaocha.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Diaocha;
import com.changpeng.models.Diaochaoption;
import com.changpeng.models.Diaochareply;
import com.changpeng.models.Diaochawenti;

/**
 * 导出一个调查的问卷
 * 
 * @author 刘哈哈 May 23, 201110:00:11 PM
 * 
 */
public class DiaochaExportAction extends AbstractAction {
	private int diaochaid;
	private Set<Diaochawenti> wentilist = new HashSet<Diaochawenti>(0);
	private Diaocha diaocha;
	private int columnsize;
	public Set<Diaochawenti> getWentilist() {
		return wentilist;
	}

	public Diaocha getDiaocha() {
		return diaocha;
	}

	private BasicService service;

	public DiaochaExportAction() {
		service = (BasicService) this.getBean("basicService");
		this.needsession = false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected String go() throws Exception {

		diaocha = (Diaocha) service.get(Diaocha.class, diaochaid);

		wentilist = diaocha.getDiaochawentis();
		if (wentilist == null || wentilist.size() == 0) {
			this.message = "暂未有调查内容";
			return "message";
		}
		
		columnsize=3+wentilist.size();
		
		// a1,a2,a3,a4,a5
		// 姓名,

		DetachedCriteria dc = DetachedCriteria.forClass(Diaochareply.class);
		dc.add(Restrictions.eq("diaochaid", diaochaid));

		int count = service.getCountByCriteria(dc);
		System.out.println("回答问题总数::::" + count);
		// 性能考虑,1次只从数据库获取200条
		int _pageSize = 20000;
		int _pagecount = (count - 1) / _pageSize + 1; // 页数，也就是要执行的次数
		Map<String, DiaochaExport> map = new HashMap<String, DiaochaExport>();
//		List resultlist=new ArrayList();
//		int _pagecount=1;
		
		for (int i = 1; i <= _pagecount; i++) {
			dc = DetachedCriteria.forClass(Diaochareply.class);
			dc.add(Restrictions.eq("diaochaid", diaochaid));
			List list = service.findPageByCriteria(dc, _pageSize, i).getItems();
//			List list = service.findAllByCriteria(dc);
			int len = (list == null ? 0 : list.size());
			for (int ii = 0; ii < len; ii++) {
				Diaochareply reply = (Diaochareply) list.get(ii);
				String batchid = reply.getBatchid();
				String name = reply.getReplyuser();
				String lawyerno = reply.getLawyerno();
				String datetime = df.format(reply.getReplytime());
				DiaochaExport export = null;
				if (map.containsKey(batchid)) {
					export = map.get(batchid);
				} else {
					export=new DiaochaExport(wentilist);
					export.setReplytime(datetime);
					export.setDiaochaid(diaochaid);
					export.setLawyername(name);
					export.setLawyerno(lawyerno);
					export.setBatchid(batchid);
					map.put(batchid, export);
				}
				Diaochawenti wenti = reply.getDiaochawenti();
				String content = reply.getReplycontent();

				

				String others = reply.getOthers();
				int wentiid = wenti.getWentiid();
				
				List result = null;
				if (export.getAnswers().containsKey(wentiid))
					result = export.getAnswers().get(wentiid);
				else {
					result = new ArrayList();
					export.getAnswers().put(wentiid, result);
				}

				if (!(others == null || others.equals(""))) {
					result.add(others);
				}
				String[] ans = content.split(" ");
				
				Iterator iterator = wenti.getDiaochaoptions().iterator();
				while (iterator.hasNext()) { //如果这里不需要加具体的title的话，就可以取消掉这个
					Diaochaoption option = (Diaochaoption) iterator.next();
					String o = option.getOptions();
					String t = option.getTitle();
					for(String an:ans){
						if(!(an==null||an.equals(""))){ //如果我的答案里面有这个，则将title加进来
							if(an.equals(o)){
								result.add(t);
							}
						}
					}
				}
				
			}
		}

		this.exportlist = map.values();
		
		
		
		return SUCCESS;
	}

	private DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private Collection exportlist = null;

	public Collection getExportlist() {
		return this.exportlist;
	}

	public void setDiaochaid(int diaochaid) {
		this.diaochaid = diaochaid;
	}

	public int getDiaochaid() {
		return diaochaid;
	}

	/**
	 * @return the df
	 */
	public DateFormat getDf() {
		return df;
	}

	/**
	 * @param df the df to set
	 */
	public void setDf(DateFormat df) {
		this.df = df;
	}

	/**
	 * @return the columnsize
	 */
	public int getColumnsize() {
		return columnsize;
	}

}

package com.sxit.common.action.ajax;

import java.net.URLDecoder;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.sxit.common.BasicService;
import com.sxit.common.action.AbstractAction;


public class CheckCreateNameAction extends AbstractAction{

	private String columnValue;
	private String createType;
	private String propertyName;
	private int count;
	
	

	
	/**
	 * 传入要查询的POJO的路径和名称，列名，列值查询在数据库中是否已存在,返回行数
	 * @serialData 2009-03-24
	 * @author yuanwentao
	 */
	@Override
	protected String go() throws Exception {
		String cValue = new String(columnValue.getBytes("ISO8859-1"), "UTF-8");
		BasicService service = (BasicService) getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Class.forName(createType));
		detachedCriteria.setFetchMode("sysGroup", FetchMode.JOIN);
		detachedCriteria.add(Restrictions.eq(propertyName, cValue));
		count= service.getCountByCriteria(detachedCriteria);
		return SUCCESS;
	}
	
	
	//getter and setter
	
	public String getColumnValue() {
		return columnValue;
	}
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public String getCreateType() {
		return createType;
	}
	public void setCreateType(String createType) {
		this.createType = createType;
	}

}

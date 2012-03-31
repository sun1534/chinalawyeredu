package com.changpeng.shopping.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.PaginationSupport;
import com.changpeng.models.ShopCart;
import com.changpeng.shopping.util.*;
/***
 * 
 * @author 刘志平 2012-01-09 上午10:55:55
 *
 */
public class ShopOrderDAO extends BasicDAO {
	
	
	private ShopCart getShopCartObject(Object[] obj) {
		ShopCart shopcart = new ShopCart();
		shopcart.setCartid(Integer.parseInt(obj[0].toString()));
		shopcart.setTitle(obj[1].toString());
		shopcart.setTeachers(obj[2].toString());		
		shopcart.setXuefen(Float.parseFloat(NumberUtil.toMoney(obj[3])));
		shopcart.setPrice(Float.parseFloat(NumberUtil.toMoney(obj[4])));
		return shopcart;
	}
	
	public PaginationSupport getMyOrderLesson(final String sql, final int pageNo,final int pageSize) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

				Query queryObject = session.createSQLQuery(sql);// .addEntity(Jifentongji.class);
				int startIndex = (pageNo - 1) * pageSize;
				
				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();

				List newlist = new ArrayList();
				for (int i = 0; items != null && i < items.size(); i++) {
					Object[] obj = (Object[]) items.get(i);

					newlist.add(getShopCartObject(obj));
				}

				PaginationSupport ps = new PaginationSupport(newlist,  pageSize, startIndex);

				return ps;
			}
		});
		return ((PaginationSupport) object);
	}
}

package com.changpeng.address.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;


/**
 * 电话查询
 * @author sinhoo
 * Sep 9, 2009
 */
public class AddressListAction extends AbstractListAction  {
        private List addresslist;
        private String phone;
       
        public List getAddresslist() {
			return addresslist;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String go() throws HibernateException {
                if(phone!=null&&!"".equals(phone))
                	addresslist=getSession().createQuery(" from TusrAddress where phone like '"+phone.trim()+"%'").list();
                return SUCCESS;
        }
      
}

package com.changpeng.diaocha.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.*;

public class DiaochaService  extends BasicService{
	private BasicDAO basicDAO ;
	private PlatformTransactionManager transactionManager;
	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}
	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	/**
	 * 保存调查类别
	 * @param typename
	 * @return
	 * @throws ServiceException
	 */
	public void saveType(final  List<String> typename,final Diaocha diaocha) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(
					this.transactionManager);
			Object object = transactionTemplate
					.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {
							if(typename!=null&&typename.size()>0){
								for(String str:typename){
									if(str!=null&&!"".equals(str.trim())){
										Diaochatype type=new Diaochatype();
										type.setDiaocha(diaocha);
										type.setTypename(str);
										basicDAO.save(type);
									}
								}
							}
							return null;
						}
					});
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}

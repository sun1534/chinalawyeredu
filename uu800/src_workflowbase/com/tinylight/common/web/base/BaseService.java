
package com.tinylight.common.web.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.tinylight.common.dao.base.IBaseDao;

/**
 * @author Scott.cgi
 * @since  2008-4-12
 * 所有service的基类提供通用方法
 *
 */
public abstract class BaseService {
	//让子类拥有日志记录的能力
	protected  Logger log=LoggerFactory.getLogger(this.getClass());
    //获得hibernate batch_size的值在批量更新的时候使用
	protected static int BATCH_SIZE ;
	
	static{
		try {
			BATCH_SIZE = Integer.parseInt(
					     PropertiesLoaderUtils.loadAllProperties("database.properties")
					     .getProperty("hibernate.jdbc.batch_size"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	}
	
	/**
	 * spring bean的后处理器,如果子类是spring初始化的bean,则在初始化后设置注解dao的实体类类型
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@PostConstruct
	@SuppressWarnings("unchecked")
	protected void preparedDao() throws IllegalArgumentException, IllegalAccessException{
		Class<? extends BaseService> cl = this.getClass();
		for(Field f : cl.getDeclaredFields()){
			log.info("Service类注解属性名称  = {},类型 = {}",f.getName(),f.getGenericType());
			if(f.isAnnotationPresent(Resource.class) && f.getAnnotation(Resource.class).name().equals("baseDao")){
				f.setAccessible(true);//修改private修饰权限
				Type[] params = ((ParameterizedType)f.getGenericType()).getActualTypeArguments();
				log.info("实体类的参数类型  = {}",params[0].toString());
			    ((IBaseDao<?,?>)f.get(this)).changeEntityClass((Class)params[0]);//设置实体类类型
			    f.setAccessible(false);//恢复private修饰权限
			}
		}
	}
}

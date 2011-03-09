package com.opensymphony.workflow.spi.jdbc;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.module.propertyset.PropertySetManager;
import com.opensymphony.module.propertyset.database.DefaultJDBCTemplateConfigurationProvider;
import com.opensymphony.workflow.util.PropertySetDelegate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 
 */
public class DefaultJDBCTemplatePropertySetDelegate implements PropertySetDelegate {


	private DefaultJDBCTemplateConfigurationProvider configurationProvider;
	
    public void setConfigurationProvider(
			DefaultJDBCTemplateConfigurationProvider configurationProvider) {
		this.configurationProvider = configurationProvider;
	}


    
	public DefaultJDBCTemplatePropertySetDelegate() {
        super();
    }


    @SuppressWarnings("unchecked")
	public PropertySet getPropertySet(long entryId) {
        Map args = new HashMap(1);
        args.put("globalKey", "osff_temp_" + entryId);

        args.put("configurationProvider", configurationProvider);

        return PropertySetManager.getInstance("jdbcTemplate", args);
    }

}

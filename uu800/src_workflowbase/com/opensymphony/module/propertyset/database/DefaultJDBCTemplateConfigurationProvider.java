package com.opensymphony.module.propertyset.database;



/**
 * @author 
 */
public class DefaultJDBCTemplateConfigurationProvider implements JDBCTemplateConfigurationProvider {


    private JDBCTemplatePropertySetDAO propertySetDAO;


    public JDBCTemplatePropertySetDAO getPropertySetDAO() {

        return this.propertySetDAO;
    }

    public void setPropertySetDAO(JDBCTemplatePropertySetDAO propertySetDAO) {
        this.propertySetDAO = propertySetDAO;
    }

}

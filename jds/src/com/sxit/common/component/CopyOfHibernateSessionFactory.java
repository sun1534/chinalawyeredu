package com.sxit.common.component;

import java.io.Serializable;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 * @author Gavin King
 */
public class CopyOfHibernateSessionFactory implements Serializable {

	private static final Log LOG = LogFactory.getLog(CopyOfHibernateSessionFactory.class);

   private Resource[] mappingLocations;

   private Properties hibernateProperties;

   private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private SessionFactory factory;

	public SessionFactory getSessionFactory() {
		return factory;
	}

   public void setMappingResources(String[] mappingResources) {
		this.mappingLocations = new Resource[mappingResources.length];
		for (int i = 0; i < mappingResources.length; i++) {
			this.mappingLocations[i] = new ClassPathResource(mappingResources[i].trim());
		}
	}

   public void setHibernateProperties(Properties hibernateProperties) {
		this.hibernateProperties = hibernateProperties;
	}

	public void init() {
		try {
            Configuration config = new Configuration();
            for (int i = 0; i < this.mappingLocations.length; i++) {
                config.addInputStream(this.mappingLocations[i].getInputStream());
            }
            config.addProperties(this.hibernateProperties);

            //this follows example from http://www.javalobby.org/java/forums/t18406.html
            //is there a better way of doing this?
            config.setProperty(Environment.CONNECTION_PROVIDER, "com.sxit.common.component.ConnectionProvider");

            ConnectionProvider.dataSource = this.dataSource;

            factory = config.buildSessionFactory();
		}
		catch (Exception e) {
			LOG.error("error configuring", e);
			throw new RuntimeException( e.getMessage() );
		}
	}

    public void initForTest() {
		try {

            factory = new Configuration().configure().buildSessionFactory();
		}
		catch (Exception e) {
			LOG.error("error configuring", e);
			throw new RuntimeException( e.getMessage() );
		}
	}

	public void dispose() {
		try {
			factory.close();
		}
		catch (Exception e) {
			LOG.error("error closing", e);
		}
	}

	public String getDialect() {
		return Environment.getProperties().getProperty(Environment.DIALECT);
	}

}

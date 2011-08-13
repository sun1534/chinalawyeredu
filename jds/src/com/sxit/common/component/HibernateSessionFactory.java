package com.sxit.common.component;

import java.io.File;
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
public class HibernateSessionFactory implements Serializable {

	private static final Log LOG = LogFactory.getLog(HibernateSessionFactory.class);

   private Resource[] mappingLocations;
   
   private Resource[] mappingDirectoryLocations;
   
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
	   if(mappingResources!=null){
		   this.mappingLocations = new Resource[mappingResources.length];
			for (int i = 0; i < mappingResources.length; i++) {
				this.mappingLocations[i] = new ClassPathResource(mappingResources[i].trim());
			}
	   }
		
	}
   /**
	 * Set locations of directories that contain Hibernate mapping resources,
	 * like "WEB-INF/mappings".
	 * <p>Can be used to add to mappings from a Hibernate XML config file,
	 * or to specify all mappings locally.
	 * @see org.hibernate.cfg.Configuration#addDirectory(java.io.File)
	 */
	/*public void setMappingDirectory(String[] mappingDirectory) {
		if(mappingDirectory!=null){
			this.mappingDirectoryLocations = new Resource[mappingDirectory.length];
			for (int i = 0; i < mappingDirectory.length; i++) {
				this.mappingDirectoryLocations[i] = new ClassPathResource(mappingDirectory[i].trim());
			}
		}
		
	}*/
   public void setMappingDirectoryLocations(Resource[] mappingDirectoryLocations) {
		this.mappingDirectoryLocations = mappingDirectoryLocations;
	}
	
   public void setHibernateProperties(Properties hibernateProperties) {
		this.hibernateProperties = hibernateProperties;
	}

	public void init() {
		try {
            Configuration config = new Configuration();
            if(this.mappingLocations!=null){
            	 for (int i = 0; i < this.mappingLocations.length; i++) {
                     config.addInputStream(this.mappingLocations[i].getInputStream());
                 }
            }
           
            
            if (this.mappingDirectoryLocations != null) {
				// Register all Hibernate mapping definitions in the given directories.
				for (int i = 0; i < this.mappingDirectoryLocations.length; i++) {
					File file = this.mappingDirectoryLocations[i].getFile();
					if (!file.isDirectory()) {
						throw new IllegalArgumentException(
								"Mapping directory location [" + this.mappingDirectoryLocations[i] +
								"] does not denote a directory");
					}
					config.addDirectory(file);
				}
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

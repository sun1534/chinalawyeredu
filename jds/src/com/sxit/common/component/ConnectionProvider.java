package com.sxit.common.component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.HibernateException;

public class ConnectionProvider implements org.hibernate.connection.ConnectionProvider {
   static DataSource dataSource;

   public void configure(Properties props) throws HibernateException {

   }

   public Connection getConnection() throws SQLException {
      return dataSource.getConnection();
   }

   public void closeConnection(Connection conn) throws SQLException {
      conn.close();
   }

   public void close() {
   }

   public boolean supportsAggressiveRelease() {
      return false;
   }

}

package com.changpeng.report.util;
import java.sql.*;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class JdbcTemplate {
	private static final DBProps dbProp=DBProps.getInstance();
	protected static final Log LOG = LogFactory.getLog(JdbcTemplate.class);
	private Connection conn;
	public JdbcTemplate(){
		conn=dbProp.getConnection();
	}
	/**
	 * 开始执行事务
	 * @throws DaoException
	 */
    public void begin() throws  SQLException{
        if(conn != null) {
            conn.setAutoCommit(false);
        } else {
            throw new SQLException("connection not opened!");
        }
    }
    
    /**
     * 事务提交
     * @throws DaoException
     */
    public void commit() throws SQLException {
     
        if (conn != null && !conn.getAutoCommit()) {
            conn.commit();
            conn.setAutoCommit(true);
        } else {
            if (conn == null) {
                throw new SQLException("connection not opened!");
            } else {
                throw new SQLException("first begin then commit please!");
            }
        }
    }
    
    /**
     * 事务回滚
     * @throws DaoException
     */
    public void rollback() throws SQLException {
            if (conn != null && !conn.getAutoCommit()) {
                conn.rollback();
                conn.setAutoCommit(true);
            } else {
                if (conn == null) {
                    throw new SQLException("connection not opened!");
                } else {
                    throw new SQLException("first begin then rollback please!");
                }
            }
    }
    
    /**
     * 
     * @param rs
     * @return
     * @throws DaoException
     */
	private List<HashMap<String,Object>> convert(ResultSet rs) throws SQLException {

        // record list
        List<HashMap<String,Object>> retList = new ArrayList<HashMap<String,Object>>();

            ResultSetMetaData meta = rs.getMetaData();

            // column count
            int colCount = meta.getColumnCount();

            // each record
            while (rs.next()) {

            	HashMap<String,Object> recordMap = new HashMap<String,Object>();

                // each column
                for (int i = 1; i <= colCount; i++) {
                    // column name
                    String name = meta.getColumnName(i).toLowerCase();
                    // column value
                    Object value = rs.getObject(i);
                    // add column to record
                    recordMap.put(name, value);
                }
                // ad record to list
                retList.add(recordMap);
            }
     
        return retList;
    }

    /**
     * 将参数置于list中 用PreparedStatement防止注入攻击
     * @param pstmt
     * @param params
     * @throws DaoException
     */
	private void apply(PreparedStatement pstmt, List<Object> params) throws SQLException {
  
            // if params exist
            if (params != null && params.size() > 0) {
                // parameters iterator
                Iterator<Object> it = params.iterator();
                
                // parameter index
                int index = 1;
                while(it.hasNext()) {
                    
                    Object obj = it.next();
                    // if null set ""
                    if (obj == null) {
                        pstmt.setObject(index, "");
                    } else {
                        // else set object
                        pstmt.setObject(index, obj);
                    }
                    //next index
                    index++;
                }
            }
    }

    /**
     * 查询指定sql的返回结果集
     * @param sql
     * @param params
     * @return
     * @throws DaoException
     */
	public List<HashMap<String,Object>> query(String sql, List<Object> params) throws SQLException {
        List<HashMap<String,Object>> result = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            this.apply(pstmt, params);
            rs = pstmt.executeQuery();
            result = this.convert(rs);
        } finally {
        	safelyClose(rs);
        	safelyClose(pstmt);
        }
        return result;
    }
	/**
     * 查询指定sql的返回结果集
     * @param sql
     * @return
     * @throws DaoException
     */
	public List<HashMap<String,Object>> query(String sql) throws SQLException {
		return this.query(sql,null);
    }
    /**
     * 执行指定sql
     * @param sql
     * @param params
     * @return
     * @throws DaoException
     */
	public int execute(String sql, List<Object> params) throws SQLException {
        int ret = 0;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            this.apply(pstmt, params);
            ret = pstmt.executeUpdate();
        } finally {
        	safelyClose(pstmt);
        }
        
        return ret;
    }
	
	public int execute(String sql) throws SQLException {
        return this.execute(sql,null);
    }
    
	 /**
     * 获取指定行数据 如select cola,colc,cold from tab where colb=1;
     * @param sql
     * @param params
     * @return
     * @throws DaoException
     */
	public HashMap<String,Object> queryMap(String sql, List<Object> params) throws SQLException {
        List<HashMap<String,Object>> list = this.query(sql, params);       
        if(list == null || list.size() == 0) {
            //throw new DaoException("data not exist");
        	return null;
        } else {
        	return list.get(0);
        }
    }
	public HashMap<String,Object> queryMap(String sql) throws SQLException {
        return this.queryMap(sql, null);
    }
	/**
     * 获取指定列数据 如select cola from tab where colb=1;
     * @param sql
     * @param ms
     * @return
     * @throws DaoException
     */
	public Object queryOne(String sql, List<Object> params) throws SQLException {
		HashMap<String,Object> record = this.queryMap(sql, params);     
		 if(record == null || record.size() == 0 ) {
             return null;
         } else {
            return record.values().toArray()[0];        
        }
    }
	public Object queryOne(String sql) throws SQLException {
		return this.queryOne(sql,null);
    }
	
	/**
	 * 批处理执行
	 * @param sqlArray
	 * @param paramArray
	 * @return
	 * @throws DaoException
	 */
	public void executeBatch(Object[] sqlArray, List<Object>[] paramArray) throws SQLException {   
        if(sqlArray.length != paramArray.length) {   
            throw new SQLException("sql size not equal parameter size");   
        } else {   
        	this.begin();
            for(int i = 0; i < sqlArray.length; i++) {   
                this.execute(sqlArray[i].toString(), paramArray[i]);                  
            }   
            this.commit();
        }   
    }  
	
	public void executeBatch(Object[] sqlArray) throws SQLException {         
    	this.begin();
        for(int i = 0; i < sqlArray.length; i++) {   
            this.execute(sqlArray[i].toString());                  
        }   
        this.commit();      
    } 
	
    public void safelyClose() {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }
    
    public void safelyClose(PreparedStatement pstmt) {
        if(pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
            }
        }
    }
    
    public void safelyClose(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }
    }
    
   
}

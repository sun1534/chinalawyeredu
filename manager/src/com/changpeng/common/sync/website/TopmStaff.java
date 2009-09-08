/**
 * 
 */
package com.changpeng.common.sync.website;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author 华锋
 * Jul 8, 2009-4:54:17 PM
 *
 */
public class TopmStaff {
	 private long staffid;
	    private long corporationid;
	    long departmentid;
	    long quartersid;
	    private String staffname;
	    private int stafftype;
	    private String email;
	    private String phoneno;
	    private int statusid;
	    private String mobileno;
	    private Timestamp createdate;
	    private String corporationname;
	    String departmentname;
	    String quartersname;
	    String description;

	
private boolean isexist;
public boolean getIsexist(){
	return this.isexist;
}
	    public void setStaffid(long staffid)
	    {
	        this.staffid = staffid;
	    }

	    public long getStaffid()
	    {
	        return staffid;
	    }

	    public void setCorporationid(long corporationid)
	    {
	        this.corporationid = corporationid;
	    }

	    public long getCorporationid()
	    {
	        return corporationid;
	    }

	    public void setStaffname(String staffname)
	    {
	        this.staffname = staffname;
	    }

	    public String getStaffname()
	    {
	        return staffname;
	    }

	    public void setStafftype(int stafftype)
	    {
	        this.stafftype = stafftype;
	    }

	    public int getStafftype()
	    {
	        return stafftype;
	    }

	    public void setEmail(String email)
	    {
	        this.email = email;
	    }

	    public String getEmail()
	    {
	        return email;
	    }

	    public void setPhoneno(String phoneno)
	    {
	        this.phoneno = phoneno;
	    }

	    public String getPhoneno()
	    {
	        return phoneno;
	    }

	    public void setMobileno(String mobileno)
	    {
	        this.mobileno = mobileno;
	    }

	    public String getMobileno()
	    {
	        return mobileno;
	    }

	    public void setStatusid(int statusid)
	    {
	        this.statusid = statusid;
	    }

	    public int getStatusid()
	    {
	        return statusid;
	    }

	    public void setCreatedate(Timestamp createdate)
	    {
	        this.createdate = createdate;
	    }

	    public Timestamp getCreatedate()
	    {
	        return createdate;
	    }

	    public void setCorporationname(String corporationname)
	    {
	        this.corporationname = corporationname;
	    }

	    public String getCorporationname()
	    {
	        return corporationname;
	    }

	    public void create(Connection con, long staffid)
	        throws SQLException
	    {
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        String sql = "";
	        try
	        {
	            sql = "select staffid,corporationid,departmentid,quartersid,staffname,stafftype,email,p" +
	"honeno,mobileno,statusid,createdate,description from topmstaff where staffid=?"
	;
	            stmt = con.prepareStatement(sql);
	            stmt.setLong(1, staffid);
	            rs = stmt.executeQuery();
	            if(rs.next())
	            {
	            	isexist=true;
	                this.staffid = rs.getLong("staffid");
	                corporationid = rs.getLong("corporationid");
	                departmentid = rs.getLong("departmentid");
	                quartersid = rs.getLong("quartersid");
	                staffname = rs.getString("staffname");
	                stafftype = rs.getInt("stafftype");
	                email = rs.getString("email");
	                phoneno = rs.getString("phoneno");
	                mobileno = rs.getString("mobileno");
	                statusid = rs.getInt("statusid");
	                createdate = rs.getTimestamp("createdate");
	                description = rs.getString("description");
	            }else{
	            	isexist=false;
	            }
	            stmt.close();
	        }
	        finally
	        {
	            if(stmt != null)
	            {
	                stmt.close();
	            }
	        }
	    }

	    public void create(Connection con, String description)
        throws SQLException
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try
        {
            sql = "select staffid,corporationid,departmentid,quartersid,staffname,stafftype,email,p" +
"honeno,mobileno,statusid,createdate,description from topmstaff where description=?"
;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, description);
            rs = stmt.executeQuery();
            if(rs.next())
            {
            	isexist=true;
                this.staffid = rs.getLong("staffid");
                corporationid = rs.getLong("corporationid");
                departmentid = rs.getLong("departmentid");
                quartersid = rs.getLong("quartersid");
                staffname = rs.getString("staffname");
                stafftype = rs.getInt("stafftype");
                email = rs.getString("email");
                phoneno = rs.getString("phoneno");
                mobileno = rs.getString("mobileno");
                statusid = rs.getInt("statusid");
                createdate = rs.getTimestamp("createdate");
                description = rs.getString("description");
            }else{
            	isexist=false;
            }
            stmt.close();
        }
        finally
        {
            if(stmt != null)
            {
                stmt.close();
            }
        }
    }


	    public int insert(Connection con)
	        throws SQLException
	    {
	        PreparedStatement stmt = null;
	        String sql = "";
	        int cnt = 0;
	        try
	        {
	            sql = "insert into topmstaff (staffid,corporationid,departmentid,quartersid,staffname,s" +
	"tafftype,email,phoneno,mobileno,statusid,createdate,description) values (?,?,?,?" +
	",?,?,?,?,?,?,?,?)"
	;
	            stmt = con.prepareStatement(sql);
	            stmt.setLong(1, staffid);
	            stmt.setLong(2, corporationid);
	            stmt.setLong(3, departmentid);
	            stmt.setLong(4, quartersid);
	            stmt.setString(5, staffname);
	            stmt.setInt(6, stafftype);
	            stmt.setString(7, email);
	            stmt.setString(8, phoneno);
	            stmt.setString(9, mobileno);
	            stmt.setInt(10, statusid);
	            stmt.setTimestamp(11, createdate);
	            stmt.setString(12, description);
	            cnt = stmt.executeUpdate();
	            stmt.close();
	        }
	        finally
	        {
	            if(stmt != null)
	            {
	                stmt = null;
	            }
	        }
	        return cnt;
	    }

	 

	    public int update(Connection con)
	        throws SQLException
	    {
	        PreparedStatement stmt = null;
	        String sql = "";
	        int cnt = 0;
	        try
	        {
	            sql = "update topmstaff set corporationid=?,departmentid=?,quartersid=?,staffname=?,sta" +
	"fftype=?,email=?,phoneno=?,mobileno=?,statusid=?,createdate=?,description=? wher" +
	"e staffid=?"
	;
	            stmt = con.prepareStatement(sql);
	            stmt.setLong(1, corporationid);
	            stmt.setLong(2, departmentid);
	            stmt.setLong(3, quartersid);
	            stmt.setString(4, staffname);
	            stmt.setInt(5, stafftype);
	            stmt.setString(6, email);
	            stmt.setString(7, phoneno);
	            stmt.setString(8, mobileno);
	            stmt.setInt(9, statusid);
	            stmt.setTimestamp(10, createdate);
	            stmt.setString(11, description);
	            stmt.setLong(12, staffid);
	            cnt = stmt.executeUpdate();
	            stmt.close();
	        }
	        finally
	        {
	            if(stmt != null)
	            {
	                stmt = null;
	            }
	        }
	        return cnt;
	    }

	    public static int delete(Connection con, long staffid)
	        throws SQLException
	    {
	        PreparedStatement stmt = null;
	        String sql = "";
	        int cnt = 0;
	        try
	        {
	            sql = "delete from topmstaff where staffid=?";
	            stmt = con.prepareStatement(sql);
	            stmt.setLong(1, staffid);
	            cnt = stmt.executeUpdate();
	            stmt.close();
	        }
	        finally
	        {
	            if(stmt != null)
	            {
	                stmt.close();
	            }
	        }
	        return cnt;
	    }

	

	  


	 

	    public long getDepartmentid()
	    {
	        return departmentid;
	    }

	    public void setDepartmentid(long departmentid)
	    {
	        this.departmentid = departmentid;
	    }

	    public String getDepartmentname()
	    {
	        return departmentname;
	    }

	    public void setDepartmentname(String departmentname)
	    {
	        this.departmentname = departmentname;
	    }

	    public String getDescription()
	    {
	        return description;
	    }

	    public void setDescription(String description)
	    {
	        this.description = description;
	    }

	    public long getQuartersid()
	    {
	        return quartersid;
	    }

	    public void setQuartersid(long quartersid)
	    {
	        this.quartersid = quartersid;
	    }

	    public String getQuartersname()
	    {
	        return quartersname;
	    }

	    public void setQuartersname(String quartersname)
	    {
	        this.quartersname = quartersname;
	    }
}

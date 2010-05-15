/**
 * UserFaBao.java
 */
package com.changpeng.ws;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.sxit.common.ConnPoolHandler;
import com.sxit.util.SxitMD5;

/**
 * @author 华锋
 * Apr 22, 201011:04:42 PM
 *
 */
public class UserFabao
{

    public UserFabao()
    {
    }

    public String login(String loginname, String createtime, String md5encoder)
        throws Exception
    {
        Connection con = null;
        User user = null;
        String s = "0";
        try
        {
            String str = "";
            String password = "";
            String keys = "szlxbdfb";
            String id = "";
            String xm = "";
            String xb = "";
            String sfzhm = "";
            String email = "";
            String lxdh = "";
            String sj = "";
            String zp = "";
            String swsmc = "";
            String kszysj = "";
            String zynx = "";
            String lszt = "";
            String zsjl = "";
            String jljl = "";
            String cfjl = "";
            String sm = "";
            String editable = "";
            con = ConnPoolHandler.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String sql = "";
            sql = "select * from lawer where lawno=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, loginname);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                password = rs.getString("psd");
                id = rs.getString("lawno");
                xm = rs.getString("lawername");
                xb = rs.getString("lawersex");
                sfzhm = rs.getString("PeopleNo");
                email = rs.getString("eml");
                lxdh = rs.getString("bgdh");
                sj = rs.getString("phone");
                zp = rs.getString("lsxp");
                swsmc = rs.getString("enterprise");
                kszysj = rs.getString("beginwork");
                zynx = rs.getString("worktime");
                lszt = rs.getString("thzt");
                zsjl = rs.getString("zsjl");
                jljl = rs.getString("sgjl");
                cfjl = rs.getString("sgcf");
                sm = rs.getString("Auditing");
            }
            stmt.close();
            rs.close();
            long l1 = System.currentTimeMillis();
            long l2 = l1 - 0x5265c00L;
            long l3 = l1 + 0x5265c00L;
            Timestamp d2 = new Timestamp(l2);
            Timestamp d3 = new Timestamp(l3);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
            String t2 = df.format(d2);
            String t3 = df.format(d3);
            long tmp1 = Long.parseLong(createtime);
            long tmp2 = Long.parseLong(t2);
            long tmp3 = Long.parseLong(t3);
            if(tmp1 < tmp2 || tmp1 > tmp3)
            {
                throw new Exception();
            }
            if(password == null || "".equals(password))
            {
                throw new Exception();
            }
            String temp = SxitMD5.MD5Encode(loginname + password + createtime + keys);
            if(md5encoder.equals(temp))
            {
                s = "1";
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            ConnPoolHandler.closeConnection(con);
        }
        return s;
    }
}


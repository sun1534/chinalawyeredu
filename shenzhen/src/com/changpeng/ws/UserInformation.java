package com.changpeng.ws;

import com.sxit.common.ConnPoolHandler;
import com.sxit.util.SxitMD5;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.changpeng.ws:
//            User

public class UserInformation
{

    public UserInformation()
    {
    }

    public User login(String loginname, String createtime, String md5encoder)
        throws Exception
    {
        Connection con = null;
        User user = null;
        try
        {
            String str = "";
            String password = "";
            String keys = "szjcy&szlx@2008";
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
            sql = "select * from lawer where lawno=? and Auditing='\u6B63\u5E38'";
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
                user = new User();
//                if(zp != null && !"".equals(zp))
//                {
//                    File file = new File("F:/Website/www.szlawyers.com/photo/" + zp);
//                    long l = file.length();
//                    byte buf[] = new byte[(int)l];
//                    FileInputStream fis = new FileInputStream(file);
//                    fis.read(buf);
//                    user.setZp(buf);
//                }
                user.setId(id);
                user.setXm(xm);
                user.setXb(xb);
                user.setSfzhm(sfzhm);
                user.setEmail(email);
                user.setLxdh(lxdh);
                user.setSj(sj);
                user.setSwsmc(swsmc);
                user.setKszysj(kszysj);
                user.setZynx(zynx);
                user.setLszt(lszt);
                user.setZsjl(zsjl);
                user.setJljl(jljl);
                user.setCfjl(cfjl);
                user.setSm(sm);
                user.setEditable("N");
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
        return user;
    }

    public User[] userlist(String xm)
        throws Exception
    {
        Connection con = null;
        User u[] = null;
        try
        {
            if(xm == null || "".equals(xm))
            {
                throw new Exception();
            }
            List list = new ArrayList();
            String str = "";
            String password = "";
            String keys = "szjcy&szlx@2008";
            String id = "";
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
            sql = "select * from lawer where lawername like '%" + xm + "%' and Auditing='\u6B63\u5E38'";
            stmt = con.prepareStatement(sql);
            User user;
            for(rs = stmt.executeQuery(); rs.next(); list.add(user))
            {
                user = new User();
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
                user.setId(id);
                user.setXm(xm);
                user.setXb(xb);
                user.setEmail(email);
                user.setEditable("N");
            }

            int len = list.size();
            u = new User[len];
            for(int i = 0; i < len; i++)
            {
                u[i] = (User)list.get(i);
            }

            stmt.close();
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            ConnPoolHandler.closeConnection(con);
        }
        return u;
    }

    public User[] userlist(int beginIndex, int endIndex)
        throws Exception
    {
        Connection con = null;
        User u[] = null;
        try
        {
            List list = new ArrayList();
            String str = "";
            String password = "";
            String keys = "szjcy&szlx@2008";
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
            sql = "select * from lawer where 1=1 and Auditing='\u6B63\u5E38'";
            stmt = con.prepareStatement(sql, 1004, 1007);
            rs = stmt.executeQuery();
            if(beginIndex == 0)
            {
                rs.beforeFirst();
            } else
            {
                rs.absolute(beginIndex);
            }
            for(int m = beginIndex; rs.next() && m < endIndex; m++)
            {
                User user = new User();
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
                user.setId(id);
                user.setXm(xm);
                user.setXb(xb);
                user.setEmail(email);
                user.setEditable("N");
                list.add(user);
            }

            int len = list.size();
            u = new User[len];
            for(int i = 0; i < len; i++)
            {
                u[i] = (User)list.get(i);
            }

            stmt.close();
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            ConnPoolHandler.closeConnection(con);
        }
        return u;
    }

    public User user(String id)
        throws Exception
    {
        Connection con = null;
        User user = null;
        try
        {
            String str = "";
            String password = "";
            String keys = "szjcy&szlx@2008";
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
            sql = "select * from lawer where lawno=? and Auditing='\u6B63\u5E38'";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                user = new User();
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
//                if(zp != null && !"".equals(zp))
//                {
//                    File file = new File("F:/Website/www.szlawyers.com/photo/" + zp);
//                    long l = file.length();
//                    byte buf[] = new byte[(int)l];
//                    FileInputStream fis = new FileInputStream(file);
//                    fis.read(buf);
//                    user.setZp(buf);
//                }
                user.setId(id);
                user.setXm(xm);
                user.setXb(xb);
                user.setSfzhm(sfzhm);
                user.setEmail(email);
                user.setLxdh(lxdh);
                user.setSj(sj);
                user.setSwsmc(swsmc);
                user.setKszysj(kszysj);
                user.setZynx(zynx);
                user.setLszt(lszt);
                user.setZsjl(zsjl);
                user.setJljl(jljl);
                user.setCfjl(cfjl);
                user.setSm(sm);
                user.setEditable("N");
            }
            stmt.close();
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            ConnPoolHandler.closeConnection(con);
        }
        return user;
    }

    public String isuser(String id)
        throws Exception
    {
        Connection con = null;
        String str = "0";
        try
        {
            String password = "";
            String keys = "szjcy&szlx@2008";
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
            sql = "select * from lawer where lawno=? and Auditing='\u6B63\u5E38'";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                str = "1";
            }
            stmt.close();
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            ConnPoolHandler.closeConnection(con);
        }
        return str;
    }
}

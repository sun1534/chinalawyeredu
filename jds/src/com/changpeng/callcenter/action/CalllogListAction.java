package com.changpeng.callcenter.action;

import java.text.ParseException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;
import com.changpeng.common.*;
import java.sql.*;
/**
 * 呼叫记录查询
 * @author sinhoo
 * Jun 26, 2009
 */

public class CalllogListAction extends AbstractListAction  {
	
		private static Globals global=Globals.getInstance();
		private static java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
		//private List userlist; //用户列表
        private List callloglist; //呼叫结果集
      //  private long userid; //用户ID
        private String extid; //分机号
        private String callphone; //呼叫号码
        private String calltype; //呼叫类型(I,O,X)呼入，呼出，转移      
        private String starttime; //开始时间
        private String endtime; //结束时间
        public CalllogListAction() {
          starttime=endtime=sdf.format(new java.util.Date());
          this.maxperpage=20;
          rights="ccl1,1";
        }
        
    /*    public List getUserlist() {
			return userlist;
		}*/

		public String getStarttime() {
			return starttime;
		}

		public String getEndtime() {
			return endtime;
		}

		public void setStarttime(String starttime) {
			this.starttime = starttime;
		}

		public void setEndtime(String endtime) {
			this.endtime = endtime;
		}

		public List getCallloglist() {
			return callloglist;
		}

		/*public long getUserid() {
			return userid;
		}*/

		public String getExtid() {
			return extid;
		}

		public String getCallphone() {
			return callphone;
		}

		public String getCalltype() {
			return calltype;
		}

		/*public void setUserid(long userid) {
			this.userid = userid;
		}
*/
		public void setExtid(String extid) {
			this.extid = extid;
		}

		public void setCallphone(String callphone) {
			this.callphone = callphone;
		}

		public void setCalltype(String calltype) {
			this.calltype = calltype;
		}

		public String go() throws HibernateException {				
              //  userlist=getSession().createQuery(" from TsysUser where statusid=1").list();
               try {
				query();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                return SUCCESS;
        }
		
/**
 *   
 存储过程"LKRecList"
    procedure [dbo].[LKRecList]
(
 * @throws Exception 
	@StartTime 	datetime	,--开始时间
	@EndTime	datetime	,   --结束时间
	@TelephoneNo	varchar(30)=''	, --呼叫号码
	@UserNo		varchar(20)=''	,    --工号
    @strLineid varchar(20)='',     --分机号码
    @strPlayAgt varchar(20)='',    --播放者
    @strProAgt varchar(20)='',     --处理座席号
    @callType varchar(20)='',      --呼叫类型(I,O,X)呼入，呼出，转移
    @isPro VARCHAR(20)=''          --处理标记，0表示没处理，1表示已经处理，3表示都显示
)
AS
SET 	NOCOUNT 	ON;
SELECT * 	FROM BASERECORD
WHERE	( start>@StartTime AND stop <@EndTime )
	AND( @TelephoneNo = ani OR @TelephoneNo = '' )
	AND( @UserNo = lineagt OR @UserNo ='' )
    AND(@strLineid=lineid or @strLineid='')
    AND(@strPlayAgt=playagt or @strPlayAgt='')
    AND(@strProAgt=proagt or @strProAgt='')
    AND(@callType=type or @callType='')
    AND(@isPro=protag or @isPro='3')
    //该存储过程就可以得到录音记录的信息
    包括，id,ani,type,lineid,lineagt,start,stop,playagt,playdate,proagt,prodate,protag,pronote,tag
          录音编号，对方号码，类型，分机号，工号，开始时间，结束时间，播放者，播放时间，处理者，处理时间，状态，处理意见，标记
          其中的id(录间编号，是蕴含录音文件的存放的相对路径，相对路径的解析如下。)
    string strPath = play.Substring(0, 2) + play.Substring(2, 2) + "\\" + play.Substring(4, 2) + "\\" + play.Substring(12, 3) + "\\" + play.Substring(0, 15);
   这样就可以获得录音文件的绝对路径，其中Resouraddress是服务器的路径
 * @throws SQLException
 * @throws ParseException 
 */
		public void query() throws Exception{
			Connection con=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			String sql="";
			String temp="";
			java.util.Date start=sdf.parse(starttime);
			java.util.Date end=sdf.parse(endtime);
			try{
				callloglist=new java.util.ArrayList();
				con=global.callCenter();
				sql="select count(*) from BASERECORD where start>? and stop<? ";
				if(callphone!=null&&!"".equals(callphone)) temp+=" and ani='"+callphone+"'";
				//if(userid!=0) temp+=" and lineagt="+userid;
				if(extid!=null&&!"".equals(extid)) temp+=" and lineid='"+extid+"'";
				if(calltype!=null&&!"".equals(calltype)) temp+=" and type='"+calltype+"'";		
				sql+=temp;
				stmt=con.prepareStatement(sql);
				stmt.setTimestamp(1, new Timestamp(start.getTime()));
				stmt.setTimestamp(2, new Timestamp(end.getTime()));
				rs=stmt.executeQuery();
				if(rs.next()){
					recordsize=rs.getInt(1);
				}
				//计算pageCount
				
				if(recordsize%maxperpage==0){
					pagesize=recordsize/maxperpage;
				}else{
					pagesize=recordsize/maxperpage+1;
				}
				
				int notin=maxperpage*(pagenumber-1);
				if(notin<0) notin=0;
				
				sql="select top "+maxperpage+" * from baserecord where start>? and stop<?  "+temp+" and id not in ( select top "+notin+" id from baserecord where start>? and stop<?  "+temp+" order by id ) order by id";
				System.out.println(sql);
				stmt=con.prepareStatement(sql);
				stmt.setTimestamp(1, new Timestamp(start.getTime()));
				stmt.setTimestamp(2, new Timestamp(end.getTime()));
				stmt.setTimestamp(3, new Timestamp(start.getTime()));
				stmt.setTimestamp(4, new Timestamp(end.getTime()));
				rs=stmt.executeQuery();
				while(rs.next()){
					//id,ani,type,lineid,lineagt,start,stop,playagt,playdate,proagt,prodate,protag,pronote,tag
					String str[]=new String[14];
					str[0]=rs.getString("id");
					str[1]=rs.getString("ani");
					str[2]=rs.getString("type");
					if(str[2].equals("I")) str[2]="转移";
					else if(str[2].equals("O")) str[2]="呼出";
					else if(str[2].equals("X")) str[2]="呼入";
					str[3]=rs.getString("lineid");
					str[4]=rs.getString("lineagt");
					str[5]=rs.getString("start");
					str[6]=rs.getString("stop");
					str[7]=rs.getString("playagt");
					str[8]=rs.getString("playdate");
					str[9]=rs.getString("proagt");
					str[10]=rs.getString("prodate");
					str[11]=rs.getString("protag");
					str[12]=rs.getString("pronote");
					str[13]=rs.getString("tag");
					callloglist.add(str);
				}
			}finally{
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			}
			
		}
		/*public void query2()throws SQLException,Exception{
			
			java.util.Date start=sdf.parse(starttime);
			java.util.Date end=sdf.parse(endtime);
			
			Connection con=null;
			CallableStatement proc = null;
			ResultSet rs=null;
			try{
				callloglist=new java.util.ArrayList();
				con=global.callCenter();
			

				proc = con.prepareCall("{ call p_LKRecList_page (?,?,?,?,?,?,?,?,?,?,?) }");
				proc.setTimestamp(1, new Timestamp(start.getTime()));
				proc.setTimestamp(2, new Timestamp(end.getTime()));
				proc.setString(3, callphone==null?"":callphone);
				proc.setString(4, userid==0?"":(""+userid));
				proc.setString(5, extid==null?"":extid);
				proc.setString(6, "");
				proc.setString(7, "");
				proc.setString(8, calltype==null?"":calltype);
				proc.setString(9, "3");
				proc.setInt(10, pagenumber==0?1:pagenumber);
				proc.setInt(11, maxperpage);
				proc.execute();
				
				
				rs = (ResultSet) proc.getObject(1); 
				if(rs.next()){
					recordsize=rs.getInt(1);
					pagesize = (recordsize - 1) / maxperpage + 1;
	                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
				}
				//rs=proc.executeQuery();
				rs = (ResultSet) proc.getObject(2); 
				while(rs.next()){
					//id,ani,type,lineid,lineagt,start,stop,playagt,playdate,proagt,prodate,protag,pronote,tag
					String str[]=new String[14];
					str[0]=rs.getString("id");
					str[1]=rs.getString("ani");
					str[2]=rs.getString("type");
					if(str[2].equals("I")) str[2]="转移";
					else if(str[2].equals("O")) str[2]="呼出";
					else if(str[2].equals("X")) str[2]="呼入";
					str[3]=rs.getString("lineid");
					str[4]=rs.getString("lineagt");
					str[5]=rs.getString("start");
					str[6]=rs.getString("stop");
					str[7]=rs.getString("playagt");
					str[8]=rs.getString("playdate");
					str[9]=rs.getString("proagt");
					str[10]=rs.getString("prodate");
					str[11]=rs.getString("protag");
					str[12]=rs.getString("pronote");
					str[13]=rs.getString("tag");
					callloglist.add(str);
				}
				
			}finally{
				if(rs!=null) rs.close();
				if(proc!=null) proc.close();
				if(con!=null) global.closeCon(con);
			}
		}*/
		public static void main(String args[]){
			System.out.println("...............");
		}
}

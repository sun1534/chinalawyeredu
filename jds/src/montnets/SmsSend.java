package montnets;
import java.text.*;
import java.sql.*;
public class SmsSend extends Thread{
  private final static String HOST="61.242.89.115";
  private final static int PORT=8018;
  private final static String LOGINNAME="20080005005";
  private final static String PASSWORD="cpsoft";
  private static SimpleDateFormat sdf=new SimpleDateFormat("MM-dd HH:mm:ss");
  static{
	  try {
		//加载数据库驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
		System.out.println(sdf.format(new java.util.Date())+"--加载数据库驱动错【"+e.getMessage()+"】.");
	}
  }
  //数据库连接类
  private Connection getConnection() {
		Connection con = null;
		try {	
	        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ora10", "jds", "jds");     
		} catch (Exception e) {
			System.out.println(sdf.format(new java.util.Date())+"--连接数据库失败【"+e.getMessage()+"】.");
		}
		return con;
  }
  //更新LOG表中短信发送时间，并从接口表中删除对应短信
  private void doSms(Connection con,long smslogid) throws SQLException{
	  PreparedStatement pstmt=null;
	  try{
		 
		  pstmt=con.prepareStatement("update sms_log set sendtime=sysdate where smslogid="+smslogid);
		  pstmt.execute();
		  pstmt=con.prepareStatement("delete from sms_queue where smslogid="+smslogid);
		  pstmt.execute();
	  }finally{
		  if(pstmt!=null) pstmt.close();
	  }
  }
  //扫描短信接口表，并发送短信
  private int sendMsg(int socket){
	  Connection con=null;
	  PreparedStatement pstmt=null;
	  ResultSet rs=null;
	  int count=0;
	  try{
		  con=getConnection();
		  System.out.println(sdf.format(new java.util.Date())+"--连接数据库成功，扫描短信接口表.");
		  pstmt=con.prepareStatement("select * from sms_queue");
		  rs=pstmt.executeQuery();
		  while(rs.next()){
			  long smslogid=rs.getLong("smslogid");
			  String mobile=rs.getString("mobile");
			  String smscontent=rs.getString("smscontent");
			  MWGateway.SendSms(socket,mobile,smscontent);  
			  doSms(con,smslogid);
			  count++;
		  }
		  if(count==0)
			  System.out.println(sdf.format(new java.util.Date())+"--暂无短信.");
		  else
			  System.out.println(sdf.format(new java.util.Date())+"--本次共发送短信【"+count+"】条.");
	  }catch(SQLException e){
		  System.out.println(sdf.format(new java.util.Date())+"--数据库操作异常【"+e.getMessage()+"】.");
	  }finally{
		  try{
			  if(rs!=null) rs.close();
			  if(pstmt!=null) pstmt.close();
			  if(con!=null) con.close();
		  }catch(SQLException e){}
	  }
	  return count;  
  }
  public void run(){
	  int sock=MWGateway.Connect(HOST, PORT, LOGINNAME, PASSWORD);
	  while(true){
		  if(sendMsg(sock)<=0){
			  System.out.println(sdf.format(new java.util.Date())+"--测试链路有效性.");
			  MWGateway.TestConn(sock);
			  try{
				  sleep(3*1000); 
			  }catch(Exception e){}
		  }
	  }
  }
	
  public static void main(String args[]){
	  new SmsSend().start();
  }
}

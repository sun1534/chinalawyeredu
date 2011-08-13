package com.changpeng.nonlaw.util;
import java.util.*;

import com.changpeng.nonlaw.model.*;
import com.changpeng.nonlaw.util.NonlawUtil;
import com.changpeng.operation.model.ToprCreditcard;
import com.changpeng.operation.util.OperationUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.changpeng.address.model.TusrAddress;
import com.changpeng.common.*;
import com.changpeng.customer.model.TusrCustomerNew;
import com.changpeng.customer.model.TusrCustomerService;
import com.changpeng.customer.util.NewCustomerUtil;
import com.opensymphony.xwork2.ActionContext;
import com.sxit.system.model.TsysUser;
@SuppressWarnings("unchecked")
public class NonlawCreateBatch {
	
	/**
	 * 获取EXCEL单元格中的值
	 * @param cell
	 * @return
	 */
	private static String cellVal(HSSFCell cell){
		java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
		String value = null;
		if(cell!=null){			
			
			switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC:
					value = df.format(cell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
					default:
			}			
		}
		if(value!=null) value=value.replaceAll(" ", "");
		return value;
	}
	/**
	 * 解析EXCEL文件
	 * @param excel
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private static List<TnlwNonlaw> parseXls(File excel) throws IOException{
		FileInputStream stream=null;
		List<TnlwNonlaw> nonlawList=null;
		try{
			nonlawList=new ArrayList<TnlwNonlaw>();
			stream=new FileInputStream(excel);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0); // 第一张工作表
			//第一行为表头 不用解析
			for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
				java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
				HSSFRow row = sheet.getRow(i); // 表中每一行
				if (row != null) {
				
					short cellnum=row.getLastCellNum(); //最大列数
					
					//第一列为客户姓名，第二列为帐单号，不允许为空 且列数不得小于39列
					//liuhuafeng 2010-12-01加上单据号不能为空且必须唯一
					if(/*cellnum<28||*/cellVal(row.getCell((short)0))==null||cellVal(row.getCell((short)1))==null||cellVal(row.getCell((short)2))==null)
					{
						System.out.println("非诉的不处理数据："+cellVal(row.getCell((short)0))+"==>"+cellVal(row.getCell((short)1))+"==>"+cellVal(row.getCell((short)2)));
						
						continue;
					
					}else{
						TnlwNonlaw nonlaw=new TnlwNonlaw();
						
						nonlaw.setConsigndate(new java.text.SimpleDateFormat("yyyy-MM-dd").format(row.getCell((short)0).getDateCellValue()));
						
					//	System.out.println(new java.text.SimpleDateFormat("yyyy-MM-dd").format(row.getCell((short)0).getDateCellValue()));
						//System.out.println(nonlaw.getConsigndate());
						
						nonlaw.setUsername(cellVal(row.getCell((short)1)));
						nonlaw.setDuebill(cellVal(row.getCell((short)2)));
						nonlaw.setLendaccount(cellVal(row.getCell((short)3)));
						nonlaw.setPayaccount(cellVal(row.getCell((short)4)));
						nonlaw.setSynergicname(cellVal(row.getCell((short)5)));
						nonlaw.setIdcard(cellVal(row.getCell((short)6)));
						nonlaw.setLendfee(cellVal(row.getCell((short)7)));
						nonlaw.setBalancefee(cellVal(row.getCell((short)8)));
						
						nonlaw.setOverfee(cellVal(row.getCell((short)9))); //逾期本金
						nonlaw.setAccrualfee(cellVal(row.getCell((short)10))); //逾期利息
						
						String overfee=nonlaw.getOverfee()!=null?nonlaw.getOverfee():"0";
						String accrualfee=nonlaw.getAccrualfee()!=null?nonlaw.getAccrualfee():"0";
						String overallfee=(Float.parseFloat(overfee)+Float.parseFloat(accrualfee))+"";
						nonlaw.setOverallfee(overallfee); //逾期金额
						
						nonlaw.setCastfee(cellVal(row.getCell((short)11)));
						nonlaw.setOverstat(cellVal(row.getCell((short)12)));
						try{
						nonlaw.setOvernum(Integer.parseInt(cellVal(row.getCell((short)13))));
						}catch(Exception e){
							nonlaw.setOvernum(0);
						}
						nonlaw.setMonthfee(cellVal(row.getCell((short)14)));
						nonlaw.setBreachfee(cellVal(row.getCell((short)15)));
						nonlaw.setLenddate(cellVal(row.getCell((short)16)));
						nonlaw.setLendoverdate(cellVal(row.getCell((short)17)));
						nonlaw.setProjectname(cellVal(row.getCell((short)18)));
						nonlaw.setBuyaddr(cellVal(row.getCell((short)19)));
						nonlaw.setHomeaddr(cellVal(row.getCell((short)20)));
						nonlaw.setHomephoneold(cellVal(row.getCell((short)21)));
						nonlaw.setMobileold(cellVal(row.getCell((short)22)));
						nonlaw.setCompany(cellVal(row.getCell((short)23)));
						nonlaw.setCompanyaddr(cellVal(row.getCell((short)24)));
						nonlaw.setCompanyphone(cellVal(row.getCell((short)25)));
						nonlaw.setAlterphone(cellVal(row.getCell((short)26)));
						
						nonlaw.setComments(cellVal(row.getCell((short)27)));
						nonlaw.setLendadmin(cellVal(row.getCell((short)28)));
						
						nonlaw.setBankname(cellVal(row.getCell((short)29)));
						
						nonlaw.setPici(cellVal(row.getCell((short)30)));
						if(cellVal(row.getCell((short)31))!=null&&!"".equals(cellVal(row.getCell((short)31)))) //原委托日期  对于新单，该值为空
							nonlaw.setOrisigndate(new java.text.SimpleDateFormat("yyyy-MM-dd").format(row.getCell((short)31).getDateCellValue()));
						
						nonlawList.add(nonlaw);						
					}
				}//end if judge row
			}//end for row
		}finally{
			if(stream!=null) stream.close();
		}	
		return nonlawList;
	}
	/**
	 * 将EXCEL中催收记录插入数据库，如果账号重复，做覆盖操作。
	 * @param excel
	 * @param consigndate
	 * @param bankid
	 * @param session
	 * @throws IOException
	 */
	public static List<TnlwNonlaw> save(File excel,String consigndate,long bankid,org.hibernate.Session session,List existCustomerList) throws IOException{
		List<TnlwNonlaw> nonlawList=parseXls(excel);
		List<TnlwNonlaw> reNonlaw=new ArrayList<TnlwNonlaw>();
		HashMap<String,TnlwNonlaw> nonlawMap=NonlawUtil.nonlawMap();
		NewCustomerUtil customerutil=new NewCustomerUtil();
		TsysUser sysUser=(TsysUser)ActionContext.getContext().getSession().get("curuser");
		for(TnlwNonlaw nonlaw:nonlawList){
			
			//nonlaw.setConsigndate(consigndate);
			nonlaw.setBankid(bankid);
			nonlaw.setCreatetime(new java.util.Date());
			nonlaw.setRefee("0"); //总还0
			nonlaw.setRemonthfee("0"); //当月已还0
			nonlaw.setRepaystatus(1); //部分还款
			nonlaw.setLawflag(0); //未转诉讼
			
			//当前逾期本金及利息等
			nonlaw.setCuraccrualfee(nonlaw.getAccrualfee());
			nonlaw.setCurbalancefee(nonlaw.getBalancefee());
			nonlaw.setCuroverfee(nonlaw.getOverfee());
			try{
				nonlaw.setCuroverstat(Float.parseFloat(nonlaw.getOverstat()));		
				}catch(Exception e){
					nonlaw.setCuroverstat(0f);
				}
			
			
			nonlaw.setState(0);		
			
			nonlaw.setTdflag(0); //退单标记
			
			if(nonlawMap.containsKey(nonlaw.getIdcard())){ //账户重复，覆盖操作。
				
				System.out.println("nonlaw.getIdcard()"+nonlaw.getIdcard());
				
				nonlaw.setUpdatetime(new java.util.Date());
				nonlaw.setNonlawid(nonlawMap.get(nonlaw.getIdcard()).getNonlawid());
				reNonlaw.add(nonlaw);
			}else{ //新增操作
				
				
				session.save(nonlaw);
				int nonlawid=(int)nonlaw.getNonlawid();

				TusrCustomerNew customer=NewCustomerUtil.getCustomer(session, nonlaw.getUsername(), nonlaw.getIdcard());
				int customerid=0;
				if(customer!=null){
					customerid=customer.getCustomerid();
					existCustomerList.add(customer);
				}else{//新增一个用户,同时将这个要加到那个service里面去
					 customer=new TusrCustomerNew();
//					 insert into tusr_customer_new(customerid,
//					 username,idcard,homeaddr,homephone,mobile1,company,compaddr,compphone,mobile2,createsrc,createsrcid,createtime) select tusrcustomerid.nextval,
//					 username,idcard,HOMEADDR,HOMEPHONEOLD,MOBILEOLD,company,COMPANYADDR,COMPANYPHONE,ALTERPHONE,2 as createsrc,nonlawid,createtime from tnlw_nonlaw;
					 customer.setUsername(nonlaw.getUsername());
					 customer.setIdcard(nonlaw.getIdcard());
					 customer.setMobile1(nonlaw.getMobileold());
					 customer.setHomeaddr(nonlaw.getHomeaddr());
					 customer.setHomephone(nonlaw.getHomephoneold());
					 customer.setCompphone(nonlaw.getCompanyphone());
					 customer.setCompany(nonlaw.getCompany());
					 customer.setCompaddr(nonlaw.getCompanyaddr());
					customer.setMobile2(nonlaw.getAlterphone());
					 customer.setCreatesrc(2);
					 customer.setCreatesrcid(nonlawid);
					 customer.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
					 customer.setCreateuser((int)sysUser.getUserid());
					 customer.setCustomerflag(2);//1:VIP 2:一般
					 customer.setCustomertype(3);//1:机构客户 2:个人客户 3当事人客户
					
					 session.save(customer);
					 customerid=customer.getCustomerid();
				}
				
				TusrCustomerService service=new TusrCustomerService();
				service.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				service.setCreateuser(sysUser.getUsername());
				service.setCreateuserid((int)sysUser.getUserid());
				service.setServiceid((int)nonlawid);
				service.setServicetype(2);
				service.setTusrCustomerNew(customer);
				service.setRemarks("");
				session.save(service);
				
				//保存住宅电话
				if(nonlaw.getHomephoneold()!=null&&!"".equals(nonlaw.getHomephoneold().trim())){
					TusrAddress p=new TusrAddress();
					p.setUsername(nonlaw.getUsername());
					p.setHomeaddr(nonlaw.getHomeaddr());
					p.setCompany(nonlaw.getCompany());
					p.setOprid(nonlaw.getNonlawid());
					p.setOprflag(2); //非诉业务
					
					p.setComments("住宅电话");
					p.setPhone(phone(nonlaw.getHomephoneold()));
					p.setCreatetime(new java.util.Date());
					p.setCustomerid(customerid);
					session.save(p);
				}
				//保存手机
				if(nonlaw.getMobileold()!=null&&!"".equals(nonlaw.getMobileold().trim())){
					TusrAddress p=new TusrAddress();
					p.setUsername(nonlaw.getUsername());
					p.setHomeaddr(nonlaw.getHomeaddr());
					p.setCompany(nonlaw.getCompany());
					p.setOprid(nonlaw.getNonlawid());
					p.setOprflag(2); //非诉业务
					
					p.setComments("手机");
					p.setPhone(phone(nonlaw.getMobileold()));
					p.setCreatetime(new java.util.Date());
					p.setCustomerid(customerid);
					session.save(p);
				}
				//保存单位电话
				if(nonlaw.getCompanyphone()!=null&&!"".equals(nonlaw.getCompanyphone().trim())){
					TusrAddress p=new TusrAddress();
					p.setUsername(nonlaw.getUsername());
					p.setHomeaddr(nonlaw.getHomeaddr());
					p.setCompany(nonlaw.getCompany());
					p.setOprid(nonlaw.getNonlawid());
					p.setOprflag(2); //非诉业务
					
					p.setComments("单位电话");
					p.setPhone(phone(nonlaw.getCompanyphone()));
					p.setCreatetime(new java.util.Date());
					p.setCustomerid(customerid);
					session.save(p);
				}
			}
		}
		session.flush();
		return reNonlaw;
	}
	public static String phone(String str){
		if(str!=null)
			str=str.replaceAll(" ", "").replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("（", "").replaceAll("）", "");
		return str;
	}
	
	
	public static String saveRepaylog(File excel,long userid) throws IOException, SQLException{
		String message="";
		FileInputStream stream=null;
		List<ToprCreditcard> cardList=null;
		Connection con=null;
		Statement stmt=null;
		try{
			con=OperationUtil.globals.getCon();
			stmt=con.createStatement();
			stream=new FileInputStream(excel);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0); // 第一张工作表
			//第一行为表头 不用解析
			for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
				java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
				HSSFRow row = sheet.getRow(i); // 表中每一行
				if (row != null) {		
					//第一列为催收任务ID，第二列为催收员，不允许为空
					if(cellVal(row.getCell((short)0))!=null){
						
//							long nonlawid=Long.parseLong(cellVal(row.getCell((short)0)));
						//第一列改为借据号
						String duebill=cellVal(row.getCell((short)0));
						long nonlawid=duebill2nonlawid(con,duebill);
						
							String status=cellVal(row.getCell((short)1)); //还款状态
							if(status==null) status="";
							int repaystatus=1;
							if(status.equals("全清"))
								repaystatus=2;
							if(status.equals("备注清零"))
								repaystatus=3;
							String fee=cellVal(row.getCell((short)2)); //还款人民币
							if(fee==null) fee="";
							String usafee=cellVal(row.getCell((short)3)); //还款美元
							if(usafee==null) usafee="";
							String hkfee=cellVal(row.getCell((short)4)); //还款港元
							if(hkfee==null) hkfee="";
							String eurfee=cellVal(row.getCell((short)5)); //还款欧元
							if(eurfee==null) eurfee="";
							String repaytime="";
							String cell8Val=cellVal(row.getCell((short)6));
							if(cell8Val!=null&&!"".equals(cell8Val))
								repaytime=new java.text.SimpleDateFormat("yyyy-MM-dd").format(row.getCell((short)6).getDateCellValue());
							
							String comments=cellVal(row.getCell((short)7));
							if(comments==null) comments="";
							
							String curfee=fee;
							//不是当月还款
							if(repaytime!=null&&!repaytime.substring(0, 7).equals(new java.text.SimpleDateFormat("yyyy-MM").format(new java.util.Date())))
								curfee="0";
							String curMonthFee=curMonthFee(con,nonlawid);
							
							long taskuserid=userid(con,nonlawid);
							
							//System.out.println("update tnlw_nonlaw set repaystatus="+repaystatus+",refee='"+curMonthFee+"'+'"+curfee+"' where nonlawid='"+nonlawid+"'");
							stmt.addBatch("update tnlw_nonlaw set repaystatus="+repaystatus+",refee='"+curMonthFee+"'+'"+curfee+"' where nonlawid='"+nonlawid+"'");
							
							if(repaystatus==2)
								stmt.addBatch("update tnlw_nonlaw set curoverfee=0,curaccrualfee=0 where nonlawid='"+nonlawid+"'");
							
							String sql="insert into tnlw_repaylog (repaylogid,nonlawid,fee,usafee,hkfee,eurfee,repaytime,comments,createtime,userid) values" +
							"(tnlwrepaylogid.nextval,"+nonlawid+",'"+fee+"','"+usafee+"','"+hkfee+"','"+eurfee+"','"+repaytime+"','"+comments+"',sysdate,"+taskuserid+")";
							
							//	System.out.println(sql);
							stmt.addBatch(sql);
						
					}
				}
			}
			stmt.executeBatch();
		}finally{
			if(stream!=null) stream.close();
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
		}		
		return message;
	}
	
	//获取任务当月已还金额
	private static String curMonthFee(Connection con,long nonlawid) throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String fee="0";
		try{
			pstmt=con.prepareStatement("select sum(fee) from Tnlw_Repaylog where substr(repaytime,0,7)=to_char(sysdate,'yyyy-mm') and nonlawid="+nonlawid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				fee=rs.getString(1);
				if(fee==null)
					fee="0";
			}
		}finally{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		}
		return fee;
	}
	
	/**
	 * 
	 * @param con
	 * @param duebill
	 * @return
	 * @throws SQLException
	 */
	public static long duebill2nonlawid(Connection con,String duebill) throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			pstmt=con.prepareStatement("select nonlawid from tnlw_nonlaw where duebill=?");
			pstmt.setString(1, duebill);
			rs=pstmt.executeQuery();
			if(rs.next())
			return rs.getLong(1);
			return 0L;
		}finally{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		}
	}
	
	/**
	 * 当前任务催收员ID
	 * @param con
	 * @param creditcard
	 * @return
	 * @throws SQLException
	 */
	private static long userid(Connection con,long nonlawid) throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			pstmt=con.prepareStatement("select userid from tnlw_nonlawtask where taskstat=0 and nonlawid=?");
			pstmt.setLong(1, nonlawid);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getLong(1);
		}finally{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		}
	}
}

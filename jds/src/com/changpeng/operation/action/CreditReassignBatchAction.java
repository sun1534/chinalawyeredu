package com.changpeng.operation.action;


import java.io.*;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.TsysUser;
import com.changpeng.common.Globals;
import com.changpeng.nonlaw.model.TnlwNonlaw;
import com.changpeng.operation.model.*;

import java.sql.*;
/**
 *
 * <p>功能： 创建催收记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
 */

public class CreditReassignBatchAction extends AbstractAction {
	private File file;
	private String fileName;
	private String contenttype;
	
	
	public File getFile() {
		return file;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}


	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
	public CreditReassignBatchAction() {
	}
	private static String getExtention(String fileName){
    	int pos=fileName.lastIndexOf(".");
    	return fileName.substring(pos);
    }
	public String go() throws HibernateException {
		getSession();
		if(fileName!=null&&!"".equals(fileName)){
			String extendPath="/uploads/";
			String name="CreditReassignBatch"+new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
			String toPath=ServletActionContext.getServletContext().getRealPath("")+extendPath;
			String ext=getExtention(fileName);		
			String filename=name+ext;		
			try {
				FileUtils.forceMkdir(new File(toPath)); //创建目录
				File dest=new File(toPath+filename);
				FileUtils.copyFile(file, dest); //移动文件
				if(!ext.equalsIgnoreCase(".xls")){
					message = "催收任务分配文件必须为.xls格式文件";
					FileUtils.forceDelete(dest);
					return ERROR;
				}else{
					message="任务分配成功"+reassigntask(dest);
					nextpage="credittaskList.action";
					return SUCCESS;
				}
			}catch(IOException e){
				message = "处理任务分配文件错误："+e.getMessage()+",请确认任务分配文件为正常excel格式文件。";
				return ERROR;
			}catch(SQLException e){
				message = "数据库连接错误："+e.getMessage();
				return ERROR;
			}		
		}else{			
			message="上传文件不能为空";
			return ERROR;
		}	
        
	}

    public String input() throws Exception {
        //listBank=com.changpeng.operation.util.OperationUtil.listBank;
         return "input";
    }
        
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
    		if("".equals(value)) value=null;
    		return value;
    	}
    	/**
    	 * 将用户姓名及ID至于哈希表中
    	 * @return
    	 */
    	private HashMap<String,Long> userList(){
    		HashMap<String,Long> map=new HashMap<String,Long>();
    		List<TsysUser> list=getSession().createQuery(" from TsysUser").list();
    		for(TsysUser user:list){
    			map.put(user.getUsername(), user.getUserid());
    		}
    		return map;
    	}
    	private void reassigntask(Connection con,long creditcardid,long userid) throws SQLException{
    		Statement stmt=null;
    		ResultSet rs=null;
    		try{
    			con.setAutoCommit(false);
    			stmt=con.createStatement();
    			//更新任务状态为催收中
    			stmt.addBatch("update topr_creditcard set state=1 where creditcardid="+creditcardid);
    			 //如果任务存在  需将原始催收任务更改为撤单
    			stmt.addBatch("update topr_credittask set taskstat=1 where creditcardid="+creditcardid);
    			//插入用户任务表
    			stmt.addBatch("insert into topr_credittask (credittaskid,creditcardid,userid,createtime,taskstat) values (ToprCredittaskid.nextval,"+creditcardid+","+userid+",sysdate,0)");
    			//将对应的客户信息记录同步到该用户通讯录
    			stmt.addBatch("update tusr_address set userid="+userid+" where oprid="+creditcardid+" and oprflag=1");
        		//将对应的日志记录更改到新接单人
    			stmt.addBatch("update topr_creditlog set userid="+userid+" where creditcardid="+creditcardid);
        		stmt.executeBatch();
    			con.commit();
    		}finally{
    			if(rs!=null) rs.close();
    			if(stmt!=null) stmt.close();
    			con.setAutoCommit(true);
    		}
    	}
    	/**
    	 * 解析EXCEL文件
    	 * @param excel
    	 * @return
    	 * @throws IOException
    	 */
    	@SuppressWarnings("deprecation")
    	private String reassigntask(File excel) throws IOException,SQLException{
    		FileInputStream stream=null;
    		Connection con=null;
    		String remsg="";
    		try{
    			stream=new FileInputStream(excel);
    			HSSFWorkbook workbook = new HSSFWorkbook(stream);
    			HSSFSheet sheet = workbook.getSheetAt(0); // 第一张工作表
    			HashMap<String,Long> userMap=userList();
    			con=Globals.getInstance().getCon();
    			//第一行为表头 不用解析
    			for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
    				HSSFRow row = sheet.getRow(i); // 表中每一行
    				if (row != null) {
    					//第一列为催收任务ID，第二列为催收员，不允许为空
    					if(cellVal(row.getCell((short)0))!=null&&cellVal(row.getCell((short)1))!=null){
    						try{
    							long creditcardid=Long.parseLong(cellVal(row.getCell((short)0)));
    							Long userid=userMap.get(cellVal(row.getCell((short)1)).trim());
    							if(userid!=null){
    								reassigntask(con, creditcardid, userid);
    							}else{
    								remsg+="<br/>第"+i+"行【"+cellVal(row.getCell((short)1)).trim()+"】催收员不存在";
    							}
    						}catch(Exception e){
    							LOG.error(e);
    						}
    					}
    				}//end if judge row
    			}//end for row
    		}finally{
    			if(stream!=null) stream.close();
    			if(con!=null) con.close();
    		}	
    		return remsg;
    }
}

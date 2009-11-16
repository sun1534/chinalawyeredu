/**
 * 
 */
package com.sxit.useraction.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sxit.common.PaginationSupport;
import com.sxit.netquality.models.TopCell;
import com.sxit.useraction.models.ErrorCodeStat;
import com.sxit.useraction.models.ExceptionUsers;
import com.sxit.useraction.models.MobilesTop;
import com.sxit.useraction.models.StatApnErrors;
import com.sxit.useraction.models.UserPdpErrorTop;

/**
 * @author 华锋 Oct 23, 2009-2:34:40 PM
 * 
 */
public class UseractionService {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfsec = new java.text.SimpleDateFormat("yyyyMM-dd HH:mm:ss");
	private static final DateFormat dfhour = new java.text.SimpleDateFormat("yyyyMM-dd HH:00");
	private int getDfSec(String date) {
	try {
		Date d = dfsec.parse(date);
		return (int) (d.getTime() / 1000);
	} catch (Exception e) {
		return (int) (System.currentTimeMillis() / 1000);
	}
}
	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
     * 表3、PDP异常用户排名（按失败次数取前1000个用户），其实主要是33号错误的
	 * @return
	 */
	public List getPdpErrorTopList(Date date){
		long start=com.sxit.stat.util.StatUtil.getDateTime(date);
		long end=com.sxit.stat.util.StatUtil.getOneDayAfter(start);
		String sql="select * from (select imsi,reqapnni,count(*) as cnt from cdr_mistake where opentime between "+start/1000+" and "+end/1000+" group by imsi,reqapnni) where rownum<=1000 order by cnt desc";
			
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					UserPdpErrorTop eusers = new UserPdpErrorTop();
					eusers.setApn(rs.getString("reqapnni"));
					eusers.setErrorcount(rs.getInt("cnt"));
					eusers.setImsi(rs.getString("imsi"));
					list.add(eusers);
				}
				return list;
			}
		});
		List list = (List) object;
		return list;
		
	}
	
	/**
	 * 得到异常行业用户 （实际上把错误码为33的，APN为*.cq的异常行业用户号码单列）
	 * 
	 * @param date
	 * @return
	 */
	public PaginationSupport getExceptionUsers(String apnni,Date date, int pageNo, int pageSize) {
		final long start = com.sxit.stat.util.StatUtil.getDateTime(date);
		final long end = com.sxit.stat.util.StatUtil.getOneDayAfter(start);

		String where="";
		if(apnni!=null&&!apnni.equals(""))
		where =" and reqapnni='"+apnni+"'";
		String cntsql = "select count(*) as cnt from (select count(reqapnni) from cdr_mistake where reqapnni like '%.cq' and errcode=33 and opentime between "
				+ start / 1000 + " and " + end / 1000 + " ${where} group by reqapnni )";
	
		cntsql=cntsql.replace("${where}", where);
		
		int totalCount = jdbcTemplate.queryForInt(cntsql);

		int startIndex = (pageNo - 1) * pageSize;

		String sql = "select * from(select a.*,rownum rn from(select reqapnni ,count(distinct(imsi)) as usercount from cdr_mistake where reqapnni like '%.cq' and errcode=33 and opentime between "
				+ start
				/ 1000
				+ " and "
				+ end
				/ 1000
				+ " ${where} group by reqapnni ) a where rownum<="
				+ (startIndex + pageSize) + ") where rn>" + startIndex;

		sql=sql.replace("${where}", where);
		
		// String sql="select reqapnni ,count(distinct(imsi)) from cdr_mistake
		// where reqapnni like '%.cq' and errcode=33 and opentime between
		// "+start/1000+" and "+end/1000+" group by reqapnni ";

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ExceptionUsers eusers = new ExceptionUsers();
					eusers.setApnni(rs.getString("reqapnni"));
					eusers.setUsercount(rs.getInt("usercount"));

					eusers.setImsilist(getExcepionUserDetailByApn(eusers, eusers.getApnni(), start, end));
					list.add(eusers);
				}
				return list;
			}
		});
		List list = (List) object;
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	public List getExcepionUserDetailByApn(final ExceptionUsers eusers, String apn, long start, long end) {
		String sql = "select imsi,count(imsi) as errorcount from cdr_mistake where reqapnni='" + apn
				+ "' and opentime between " + start / 1000 + " and " + end / 1000 +"group by imsi";
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();

				while (rs.next()) {
					ExceptionUsers.DetailImsi detailimsi = eusers.new DetailImsi();
					detailimsi.setErrcount(rs.getInt("errorcount"));
					detailimsi.setImsi(rs.getString("imsi"));
					list.add(detailimsi);
				}
				return list;
			}
		});
		List list = (List) object;
		return list;
	}

	/**
	 * 统计高流量的用户排名,根据时间和所在的apn进行排名
	 * stat_apn_mobile
	 * 
	 * @param date
	 * @param apn
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List getHightStreamDayUser(final Date date,String standard, String condition) {
		
		String sql="";
		String table=com.sxit.stat.util.StatUtil.getMobileApnTable(date);
		if(standard.equals("1")){
//			 sql="select cellid,sum(allvolume) as allv from stat_cellid where dayflag=1 and (stattime="+_date+") group by cellid having sum(allvolume)>="+condition+") order by allv desc";
			 sql="select mobile,apnni,upvolume,downvolume,allvolume,periodlen from "+table+" where dayflag=1 and allvolume>="+condition+" order by allvolume desc";

		}else{
			 sql="select mobile,apnni,upvolume,downvolume,allvolume,periodlen from  "+table+"  where dayflag=1 and rownum<="+condition+" order by allvolume desc";
        }
		System.out.println(sql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					MobilesTop model = new MobilesTop();
				model.setMobile(rs.getString("mobile"));
				model.setApnni(rs.getString("apnni"));
				model.setUpvolume(rs.getFloat("upvolume"));
				model.setDownvolume(rs.getFloat("downvolume"));
				model.setPeriodlen(rs.getInt("periodlen"));
				model.setAllvolume(rs.getFloat("allvolume"));
				model.setDate(df.format(date));
				model.setDatehour(dfsec.format(date));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
        return list;
	}

	
	public List getHightStreamHourUser(String standard,String condition, Date date,final String hour){
		final String _date=df.format(date);
		String start = _date + " " + hour + ":00:00";
		String end = _date + " " + hour + ":59:59";

		int _start = getDfSec(start);
		int _end = getDfSec(end);
		String table=com.sxit.stat.util.StatUtil.getMobileApnTable(date);

		
		String sql="";
		if(standard.equals("1")){
			 sql="select mobile,apnni,sum(upvolume) as upvolume,sum(downvolume) as downvolume,sum(allvolume) as allvolume,sum(periodlen) as periodlen from "+table+" where dayflag=0 and (stattime>="+_start+" and stattime<="+_end+") group by mobile,apnni having sum(allvolume)>="+condition+") order by allvolume desc";
		}else{
			 sql="select mobile,apnni,sum(upvolume) as upvolume,sum(downvolume) as downvolume,sum(allvolume) as allvolume,sum(periodlen) as periodlen from "+table+" where dayflag=0 and (stattime>="+_start+" and stattime<="+_end+") group by mobile,apnni ) where rownum<="+condition+" order by allvolume desc";
        }
		
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					MobilesTop model = new MobilesTop();
				model.setMobile(rs.getString("mobile"));
				model.setApnni(rs.getString("apnni"));
				model.setUpvolume(rs.getFloat("upvolume"));
				model.setDownvolume(rs.getFloat("downvolume"));
				model.setPeriodlen(rs.getInt("periodlen"));
				model.setAllvolume(rs.getFloat("allvolume"));
				model.setDate(_date);
				
				model.setDatehour(_date+" "+hour+":00");
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
        return list;
	}
	
	/**
	 * 统计pdp的失败用户排名
	 * 
	 * @param date
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PaginationSupport getTopPdpErrors(Date date, int pageNo, int pageSize) {

		// PaginationSupport ps = new PaginationSupport(list, totalCount,
		// pageSize, startIndex);
		// return ps;

		return null;
	}

	/**
	 * 得到某天的错误情况统计
	 * 
	 * @param date
	 * @return
	 */
	public ErrorCodeStat getErrcodeList(Date date, String errorcode) {
		// 直接到cdr_mistake表中取数据就是了

		long start = com.sxit.stat.util.StatUtil.getDateTime(date);
		long end = com.sxit.stat.util.StatUtil.getOneDayAfter(start);

		String countsql = "select count(distinct(imsi)) as usercount,count(*) as errorcount from  cdr_mistake where errcode="
				+ errorcode + " and opentime between " + start / 1000 + " and " + end / 1000;

		System.out.println(countsql);

		final ErrorCodeStat stat = new ErrorCodeStat();
		jdbcTemplate.query(countsql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

				if (rs.next()) {
					stat.setErrorcount(rs.getInt("errorcount"));
					stat.setUsercount(rs.getInt("usercount"));

				}
				return null;
			}
		});

		String sql = "select reqapnni,imsi,count(*) as errorcount from  cdr_mistake where errcode='" + errorcode
				+ "' and opentime between " + start / 1000 + " and " + end / 1000 + " group by reqapnni,imsi";

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ErrorCodeStat.Details details = stat.new Details();

					details.setApn(rs.getString("reqapnni"));
					details.setImsi(rs.getString("imsi"));
					details.setPdperrorcnt(rs.getInt("errorcount"));

					list.add(details);
				}
				return list;
			}
		});
		List list = (List) object;
		stat.setDetailist(list);

		return stat;

	}

	/**
	 * 得到pdp失败情况总表，主要是当天累加值以及本时间段增加值以及上个时间段增加值
	 * 
	 * 按天和小时统计来
	 * 
	 * @param date
	 *            统计时间
	 * @param dayflag
	 *            统计某天的还是统计某个时间点的
	 * @return
	 */
	public List getApnErrors(String date, String hour, String dayflag) {

		String sql = "";
		// if (dayflag.equals("1")) {
		String stattime = date.replace("-", "");
		sql = "select * from stat_apn_error where dayflag=1 and stattime=" + stattime;
		// }
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					StatApnErrors model = new StatApnErrors();
					int usercount = rs.getInt("USERCOUNT");
					int errorcount = rs.getInt("ERRORCOUNT");
					long stattime = rs.getLong("stattime");
					String apnni = rs.getString("apnni");

					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setApnni(apnni);
					model.setDayflag(rs.getString("dayflag"));
					model.setErrorcount(errorcount);
					model.setStattime(stattime);
					model.setUsercount(usercount);
					model.setErrcode(rs.getInt("errcode"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		return list;
	}

}
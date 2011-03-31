package com.xjdzjc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.xjdzjc.common.DbHelper;
import com.xjdzjc.model.MemberMtStatus;
import com.xjdzjc.model.Members;

/**
 * 地州短信模板业务处理类
 * 
 * @author xyl
 * @date 2011.01.09
 */
public class MemberFlagService {
	public static Logger log = Logger.getLogger(MemberFlagService.class);

	public HashMap<String, MemberMtStatus> getMemberFlagHashMap(Connection con,
			String city) {
		HashMap<String, MemberMtStatus> list = new HashMap<String, MemberMtStatus>();
		String sql = "select * from " + city + ".member_mt_status ";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				MemberMtStatus ctempc = new MemberMtStatus();
				ctempc.setId(rs.getInt("id"));
				ctempc.setMobile(rs.getString("tel_number"));
				ctempc.setChepai(rs.getString("chepai"));
				ctempc.setSendCount(rs.getInt("send_count"));
				ctempc.setSendDate(rs.getTimestamp("send_date"));
				ctempc.setSendFlag(rs.getInt("send_flag"));
				ctempc.setMt_flag(rs.getInt("mt_flag"));
				ctempc.setLast_mt_date(rs.getTimestamp("last_mt_date"));
				ctempc.setDzjcdate(rs.getTimestamp("dzjc_date"));
				ctempc.setChepai_type(rs.getInt("chepai_type"));
				list.put(ctempc.getMobile() + ctempc.getChepai()
						+ ctempc.getChepai_type(), ctempc);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("数据错误！" + e.getMessage());
		} finally {
			DbHelper.closeRsorStmt(rs, stmt);
		}
		return list;
	}

	public MemberMtStatus getMembermtstatus(Connection con, String city,
			Members member) {
		HashMap<String, MemberMtStatus> list = new HashMap<String, MemberMtStatus>();
		String sql = "select * from " + city
				+ ".member_mt_status where tel_number='"
				+ member.getTel_number() + "' and chepai='"
				+ member.getChepai() + "' and chepai_type="
				+ member.getChepai_type();
		// log.info("sql==="+sql);

		MemberMtStatus ctempc = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ctempc = new MemberMtStatus();
				ctempc.setId(rs.getInt("id"));
				ctempc.setMobile(rs.getString("tel_number"));
				ctempc.setChepai(rs.getString("chepai"));
				ctempc.setSendCount(rs.getInt("send_count"));
				ctempc.setSendDate(rs.getTimestamp("send_date"));
				ctempc.setSendFlag(rs.getInt("send_flag"));
				ctempc.setMt_flag(rs.getInt("mt_flag"));
				ctempc.setLast_mt_date(rs.getTimestamp("last_mt_date"));
				ctempc.setDzjcdate(rs.getTimestamp("dzjc_date"));
				ctempc.setChepai_type(rs.getInt("chepai_type"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("数据错误！" + e.getMessage());
		} finally {
			DbHelper.closeRsorStmt(rs, stmt);
		}
		return ctempc;
	}

	public HashMap<String, Members> getMemberMap(Connection con, String city) {
		HashMap<String, Members> list = new HashMap<String, Members>();
		String sql = "select m.id,m.tel_number,m.service,m.jiashizheng,m.chepai,m.chepai_type,m.active,m.score,m.banner,m.remark,m.mt_flag ,o.id as isChepai,o.chepai as chepai1,o.chepai_type as chepai_type1 from "
				+ city
				+ ".members m left join "
				+ city
				+ ".other_chepai o  on m.id=o.member_id where m.active=1 ";
		// log.info("sql=====" + sql);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Members member = new Members();
				member.setActive(rs.getInt("active"));
				member.setBanner(rs.getString("banner"));
				if (rs.getString("isChepai") == null)
					member.setChepaiflag(false);
				else
					member.setChepaiflag(true);

				member.setChepai(rs.getString("chepai"));
				member.setChepai_type(rs.getInt("chepai_type"));
				member.setMt_flag(rs.getInt("mt_flag"));
				member.setRemark(rs.getString("remark"));
				member.setScore(rs.getInt("score"));
				member.setService(rs.getString("m.service"));
				member.setTel_number(rs.getString("tel_number"));
				// log.info(member.getBanner());
				list.put(member.getTel_number() + member.getChepai()
						+ member.getChepai_type(), member);
				if (member.isChepaiflag()) {
					Members membertemp = new Members();
					membertemp.setChepai(rs.getString("chepai1"));
					membertemp.setChepai_type(rs.getInt("chepai_type1"));
					membertemp.setActive(rs.getInt("active"));
					membertemp.setBanner(rs.getString("banner"));
					membertemp.setChepaiflag(true);
					membertemp.setMt_flag(rs.getInt("mt_flag"));
					membertemp.setRemark(rs.getString("remark"));
					membertemp.setScore(rs.getInt("score"));
					membertemp.setService(rs.getString("m.service"));
					membertemp.setTel_number(rs.getString("tel_number"));
					list.put(member.getTel_number() + membertemp.getChepai()
							+ membertemp.getChepai_type(), membertemp);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("取会员信息出错！" + e.getMessage());
			return list;
		} finally {
			DbHelper.closeRsorStmt(rs, stmt);
		}
		return list;
	}
}

package com.xjdzjc.service;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import com.xjdzjc.common.DbHelper;
import com.xjdzjc.common.DzjcUtil;
import com.xjdzjc.common.StringTemplateLoader;
import com.xjdzjc.model.City_Tpl_content;
import com.xjdzjc.model.Dzjc;
import com.xjdzjc.model.MemberMtStatus;
import com.xjdzjc.model.Members;
import com.xjdzjc.model.MtBean;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 用户违章服务类
 * 
 * @author Administrator
 * 
 */
public class SendService {
	public static Logger log = Logger.getLogger(SendService.class);
	private int details_count;
	private MemberFlagService mfService = new MemberFlagService();

	/**
	 * 处理一个地州的所有用户
	 * 
	 * @param cityTemplateContent
	 * @param dbh
	 * @return
	 */
	public void handle_Sms(City_Tpl_content ctpcontent, Connection con)
			throws IOException, SQLException, TemplateException {
		// 对已经处理的用户进行批量删除
		delete_dzjc(con, ctpcontent.getCity());
		// 取会员
		log.info("取会员信息");
		// map 主键都是车牌+车牌类型
		HashMap<String, Members> memberMap = mfService.getMemberMap(con,
				ctpcontent.getCity());
		log.info("取黑名单");
		HashMap<String, String> balckmobile = getBlackList(con, ctpcontent
				.getCity());
		log.info("取地州提示信息");
		String city_prompt = readCityFile(con, ctpcontent.getCity());
		log.info("取免费用户");
		HashMap<String, String> freemobile = GetFreeMobile(con, ctpcontent
				.getCity());
		int count = proessSms(memberMap, ctpcontent, con, city_prompt,
				balckmobile, freemobile);
		// log.info("总共发送条数为：" + count);
	}

	/**
	 * 组装mt并更新状态
	 * 
	 * @param dzjclist
	 *            会员违章记录
	 * @param ctpcontent
	 *            地州模板
	 * @param dbh
	 *            数据库连接
	 * @param list
	 *            取会员map
	 * @param blackmobile
	 *            黑名单
	 * @param freemobile
	 *            免费用户
	 * @param city_prompt
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws TemplateException
	 */
	public int proessSms(HashMap<String, Members> members,
			City_Tpl_content ctpcontent, Connection con, String city_prompt,
			HashMap<String, String> blackmobile,
			HashMap<String, String> freemobile) {
		// 这个是根据手机号码存一个mtbean List;
		HashMap<String, List<MtBean>> hmtbean = new HashMap<String, List<MtBean>>();
		int count = 0;
		String content = "";
		details_count = 0;
		boolean b = false;
		PreparedStatement stmt = null;
		PreparedStatement stmt_update_sql = null;
		PreparedStatement stmt_insert_sql = null;
		try {
			con.setAutoCommit(false);
			/**
			 * 设置两个模板 一个详细 一个简单
			 * 
			 */
			Template template_details = getTemplate(ctpcontent
					.getTemplate_con_details());
			Template template_simple = getTemplate(ctpcontent
					.getTemplate_con_simple());

			Template template_null = getTemplate(ctpcontent
					.getTemplate_con_null());

			// 插入mt表
			stmt = getPreparedStatement(con, ctpcontent.getCity(), 0);
			// 更新用户发送状态
			boolean c = false;
			stmt_update_sql = getPreparedStatement(con, ctpcontent.getCity(), 1);
			// 插入用户发送状态

			stmt_insert_sql = getPreparedStatement(con, ctpcontent.getCity(), 2);
			// 循环违章记录
			MemberMtStatus mf = null;
			long longtime = System.currentTimeMillis();
			if (members != null || members.size() > 0) {
				// 对发统计违章信息
				Iterator iterator = members.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, Members> entry = (Entry<String, Members>) iterator
							.next();
					Members member = entry.getValue();
					String key = member.getTel_number() + member.getChepai()
							+ member.getChepai_type();
					// 如果手机号在黑名单中，则不发短信
					if (blackmobile.containsKey(member.getTel_number()))
						continue;
					// 设置免费用户
					if (freemobile.containsKey(member.getTel_number()))
						member.setService("free");
					// 根据车牌号与车牌类型取违章记录

					List<Dzjc> dzjcList = getweizhanglist(con, ctpcontent
							.getSql_content(), ctpcontent.getCity(), member);
					mf = mfService.getMembermtstatus(con, ctpcontent.getCity(),
							member);

					// 按手机号存放下发记录
					List<MtBean> mtbeanList = hmtbean.get(member
							.getTel_number());
					if (mtbeanList == null) {
						// 这个手机号还没有发送的短信
						// 新增一个mtbean 放到list 并放到hmtbean里
						mtbeanList = new ArrayList<MtBean>();
					}
					if (mf == null) {
						if (dzjcList == null || dzjcList.size() == 0) { // 无违章
							MtBean mtBean = new MtBean();
							mtBean.setChepai(member.getChepai());
							mtBean.setChepai_type(member.getChepai_type() + "");
							mtBean.setCity(ctpcontent.getCity());
							mtBean.setSrc_id(member.getBanner());
							mtBean.setMt_flag(member.getMt_flag());
							mtBean.setDst_id(member.getTel_number());
							mtBean.setService_id(member.getService());
							mtBean.setBanner(member.getBanner());
							content = getContent(template_null, member);
							content = content + city_prompt;
							mtBean.setContent(content);
							mtBean.setSendFlag(2);
							mtbeanList.add(mtBean);
							b = insertBath(stmt_insert_sql, mf, member,
									DzjcUtil.SEND_PROMPT, null);
						} else {
							// 有违章
							Dzjc dzjc = dzjcList.get(0);
							addBath_mt_details(stmt, ctpcontent.getCity(),
									dzjcList, template_details, member,
									hmtbean, mtbeanList);
							b = insertBath(stmt_insert_sql, mf, member,
									DzjcUtil.SEND_DETAIL, new Timestamp(dzjc
											.getDate().getTime()));
						}
						hmtbean.put(member.getTel_number(), mtbeanList);
						// 做插入用户状态
					} else {
						if (dzjcList == null || dzjcList.size() == 0) {
							// 无违章
							MtBean mtBean = new MtBean();
							mtBean.setChepai(member.getChepai());
							mtBean.setChepai_type(member.getChepai_type() + "");
							mtBean.setCity(ctpcontent.getCity());
							mtBean.setSrc_id(member.getBanner());
							mtBean.setDst_id(member.getTel_number());
							mtBean.setService_id(member.getService());
							mtBean.setBanner(member.getBanner());
							content = getContent(template_null, member);
							content = content + city_prompt;
							mtBean.setContent(content);
							mtBean.setSendFlag(DzjcUtil.SEND_PROMPT);
							mtBean.setMt_flag(member.getMt_flag());
							mtbeanList.add(mtBean);
							hmtbean.put(member.getTel_number(), mtbeanList);
							c = updateBath(stmt_update_sql, mf, member,
									DzjcUtil.SEND_PROMPT, mf.getMt_flag(), null);
						} else {
							/**
							 * 有违章并且用户状态表中已经存在。看一下他的mt_flag 标志 如果是9 不干预，如果非9，则干预
							 * 干预只干预一次
							 */
							Dzjc dzjc = dzjcList.get(0);
							if (mf.getMt_flag() == 0) {
								// 如果有违章，干预发详细
								c = updateBath(stmt_update_sql, mf, member, mf
										.getSendFlag(), DzjcUtil.MT_FLAG,
										new Timestamp(dzjc.getDate().getTime()));
								// 发送详细
								addBath_mt_details(stmt, ctpcontent.getCity(),
										dzjcList, template_details, member,
										hmtbean, mtbeanList);
								details_count += dzjcList.size();

							} else if (mf.getMt_flag() == 1) {
								// 如果有违章，，干预发统计
								details_count++;
								c = updateBath(stmt_update_sql, mf, member, mf
										.getSendFlag(), DzjcUtil.MT_FLAG,
										new Timestamp(dzjc.getDate().getTime()));
								dzjc.setCount(dzjcList.size());
								addBath_mt_simple(stmt, ctpcontent.getCity(),
										dzjc, template_simple, member, hmtbean,
										mtbeanList, city_prompt);
							} else {

								/**
								 * 不干预 按正常流程执行
								 * 这里判断用户状态表中的最后一条违章记录时间和现在违章记录表中的最新一条违章时间对比
								 * 如果状态表中的时间大于或等于最新一条违章时间
								 * ，则表示没有新的违章记录，在这个条件下再是发详细还是统计
								 * 如果状态表中的时间小于违章表中的违章时间，则有新的违章，发详细
								 **/
								if (dzjc.getDate().equals(
										mf.getDzjcdate() == null ? new Date()
												: mf.getDzjcdate())
										|| dzjc
												.getDate()
												.before(
														mf.getDzjcdate() == null ? new Date()
																: mf
																		.getDzjcdate())) {
									// 再根据记录发详细的时间，跟地州设
									// vkm置的天数对比一下，如果是小于这个时间，发详细
									if (mf.getSendFlag() == DzjcUtil.SEND_DETAIL) {
										// 这里要考虑一下，是发详细还是发统计
										if (mf.getMobile()
												.equals("13999821410")) {
											log
													.info(longtime
															- (mf.getSendDate() == null ? longtime
																	: mf
																			.getSendDate()
																			.getTime()));
											log.info((DzjcUtil.DAY * ctpcontent
													.getSendday()));
											log
													.info((longtime - (mf
															.getSendDate() == null ? longtime
															: mf.getSendDate()
																	.getTime())) < (DzjcUtil.DAY * ctpcontent
															.getSendday()));
										}
										if ((longtime - (mf.getSendDate() == null ? longtime
												: mf.getSendDate().getTime())) < (DzjcUtil.DAY * ctpcontent
												.getSendday())) {
											// 还在群发时间范围内 还群发
											c = updateBath(stmt_update_sql, mf,
													member,
													DzjcUtil.SEND_DETAIL, mf
															.getMt_flag(),
													new Timestamp(dzjc
															.getDate()
															.getTime()));
											// 发送详细
											addBath_mt_details(stmt, ctpcontent
													.getCity(), dzjcList,
													template_details, member,
													hmtbean, mtbeanList);
											details_count += dzjcList.size();
										} else {
											// 不在群发时间范围内 发统计+普通提示
											details_count++;
											c = updateBath(stmt_update_sql, mf,
													member,
													DzjcUtil.SEND_SIMPLE, mf
															.getMt_flag(),
													new Timestamp(dzjc
															.getDate()
															.getTime()));
											dzjc.setCount(dzjcList.size());
											addBath_mt_simple(stmt, ctpcontent
													.getCity(), dzjc,
													template_simple, member,
													hmtbean, mtbeanList,
													city_prompt);
										}
									} else {
										// 这里不要考虑，发统计+提示
										// 不在群发时间范围内 发普通提示 再发普通提示
										c = updateBath(stmt_update_sql, mf,
												member, DzjcUtil.SEND_SIMPLE,
												mf.getMt_flag(), new Timestamp(
														dzjc.getDate()
																.getTime()));
										dzjc.setCount(dzjcList.size());
										addBath_mt_simple(stmt, ctpcontent
												.getCity(), dzjc,
												template_simple, member,
												hmtbean, mtbeanList,
												city_prompt);
										details_count++;

									}
								} else {
									// 来了新违章，所有的都发详细 设置这个用户发送状态，这个用户在整个地州都要发详细了
									addBath_mt_details(stmt, ctpcontent
											.getCity(), dzjcList,
											template_details, member, hmtbean,
											mtbeanList);
									details_count += dzjcList.size();
									c = updateBath(stmt_update_sql, mf, member,
											DzjcUtil.SEND_DETAIL, mf
													.getMt_flag(),
											new Timestamp(dzjc.getDate()
													.getTime()));
								}

							}

						}
						// 做插入更新操作用户状态
					}
				}
				log.info("批量处理开始" + new Date().toLocaleString());
				if (b) {
					stmt_insert_sql.executeBatch();
					stmt_insert_sql.close();
				}
				if (c) {
					stmt_update_sql.executeBatch();
					stmt_update_sql.close();
				}
				// 写一下批量下发短信的方法，要判断是否合并短信
				// 一个手机号码对应的mtbeanlist;
				count = sendsms(stmt, hmtbean, ctpcontent);
				con.commit();
				log.info("批量处理结束" + new Date().toLocaleString());
			} else {
				log.info("本地州没有用户！！！！！！");
			}
		} catch (Exception e) {
			try {
				con.rollback();
				e.printStackTrace();
				log.error("处理：" + ctpcontent.getCity() + "地州异常"
						+ e.getMessage());
				return 0;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (stmt_update_sql != null)
					stmt_update_sql.close();
				if (stmt_insert_sql != null)
					stmt_insert_sql.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info("发送总条数为：" + count);
		return count;
	}

	private int sendsms(PreparedStatement stmt,
			HashMap<String, List<MtBean>> hmtbean, City_Tpl_content ctpcontent)
			throws SQLException {
		// 对发统计违章信息
		Iterator iterator = hmtbean.entrySet().iterator();
		int i = 0;
		while (iterator.hasNext()) {
			Entry<String, List<MtBean>> entry = (Entry<String, List<MtBean>>) iterator
					.next();
			List<MtBean> mtBeans = entry.getValue();
			MtBean mtBean1 = null;
			for (MtBean mtBean : mtBeans) {
				// log.info("手机号为：" + mtBean.getDst_id() + ",车牌为："
				// + mtBean.getChepai() + ",内容为+" + mtBean.getContent());
				if (mtBean.getSendFlag() == 0 || mtBean.getSendFlag() == 1) {
					i++;
					if (ctpcontent.getGateway_id() != null
							&& !ctpcontent.getGateway_id().equals("")) {
						stmt.setString(1, ctpcontent.getGateway_id());
						stmt.setString(3, "");
						stmt.setString(5, "MXJ0019901");
						stmt.setString(6, "1");
						stmt.setString(7, "0");
					} else {
						stmt.setString(1, "SMS3");
						stmt.setString(3, "909");
						stmt.setString(5, mtBean.getService_id());
						stmt.setString(6, "3");
						stmt.setString(7, "500");
					}
					stmt.setString(2, mtBean.getCity());
					stmt.setString(4, mtBean.getDst_id());

					stmt.setString(8, mtBean.getContent());
					stmt.addBatch();
				} else {
					// 如果是那十个手动无城市，则不存在mt_flag为1的，全部都是要下发的
					String temp = DzjcUtil.AUTO_MT_FLAG.get(ctpcontent
							.getCity());
					if (temp != null) {
						mtBean.setMt_flag(0);
					}

					if (mtBean.getMt_flag() == 1)
						mtBean1 = null;
					else
						mtBean1 = mtBean;
				}
			}
			if (mtBean1 != null) {
				if (ctpcontent.getGateway_id() != null
						&& !ctpcontent.getGateway_id().equals("")) {
					stmt.setString(1, ctpcontent.getGateway_id());
					stmt.setString(3, "");
					stmt.setString(5, "MXJ0019901");
					stmt.setString(6, "1");
					stmt.setString(7, "0");
				} else {
					stmt.setString(1, "SMS3");
					stmt.setString(3, "909");
					stmt.setString(5, mtBean1.getService_id());
					stmt.setString(6, "3");
					stmt.setString(7, "500");
				}
				stmt.setString(2, mtBean1.getCity());
				stmt.setString(4, mtBean1.getDst_id());
				stmt.setString(8, mtBean1.getContent());
				stmt.addBatch();
				i++;
			}
		}
		stmt.executeBatch();
		stmt.close();
		return i;
	}

	/**
	 * 读取地州文件内容 发送提示信息
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public String readCityFile(Connection con, String city) {
		StringBuffer sb = new StringBuffer();
		String sql = "select * from base.city_prompt where city='"
				+ city.trim() + "'";
		// log.info(sql);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				sb.append(rs.getString("sql_content"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("取地州提示信息数据错误！" + e.getMessage());
			return sb.toString();
		} finally {
			DbHelper.closeRsorStmt(rs, stmt);
		}
		return sb.toString();
	}

	/**
	 * 读取地州文件内容 发送提示信息
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public HashMap<String, String> GetFreeMobile(Connection con, String city)
			throws IOException {
		HashMap<String, String> freemobile = new HashMap<String, String>();
		String sql = "select * from " + city + ".free_numbers ";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String mobile = rs.getString("tel_number");
				freemobile.put(mobile, rs.getString("service"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("取免费用户数据错误！" + e.getMessage());
			return freemobile;
		} finally {
			DbHelper.closeRsorStmt(rs, stmt);
		}
		return freemobile;
	}

	/**
	 * 返回调用模板后的内容
	 * 
	 * @param template
	 * @param dzjc
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public String getContent(Template template, Dzjc dzjc)
			throws TemplateException, IOException {
		String content = "";
		StringWriter writer = new StringWriter();
		// log.info("11111111111"+dzjc.getBanner());
		template.process(dzjc, writer);
		content = writer.toString();
		// log.info(writer.toString());
		writer.close();
		return content;
	}

	/**
	 * 返回调用模板后的内容
	 * 
	 * @param template
	 * @param dzjc
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public String getContent(Template template, Members member)
			throws TemplateException, IOException {
		String content = "";
		StringWriter writer = new StringWriter();
		// log.info("11111111111"+dzjc.getBanner());
		template.process(member, writer);
		content = writer.toString();
		// log.info(writer.toString());
		writer.close();
		return content;
	}

	/**
	 * 详细批量插入
	 * 
	 * @param stmt
	 * @param dzjcList
	 * @param template_details
	 * @param member
	 * @throws SQLException
	 * @throws TemplateException
	 * @throws IOException
	 */

	public HashMap<String, List<MtBean>> addBath_mt_details(
			PreparedStatement stmt, String city, List<Dzjc> dzjcList,
			Template template_details, Members member,
			HashMap<String, List<MtBean>> hmtbean, List<MtBean> mtbeanList)
			throws SQLException, TemplateException, IOException {
		String content = "";
		int i = 0;
		int count = dzjcList.size();
		for (Dzjc dzjc2 : dzjcList) {
			MtBean mtBean = new MtBean();
			dzjc2.setBanner(member.getBanner());
			content = getContent(template_details, dzjc2);
			mtBean.setDst_id(dzjc2.getMobile());
			mtBean.setService_id(member.getService());
			mtBean.setCity(city);
			mtBean.setSrc_id(member.getBanner());
			mtBean.setContent(content);
			mtBean.setChepai(dzjc2.getChepai());
			mtBean.setChepai_type(dzjc2.getChepai_type() + "");
			mtBean.setMt_flag(member.getMt_flag());
			mtBean.setSendFlag(0);
			if (count < 8) {
				mtbeanList.add(mtBean);
			} else {
				if (i < 4)
					mtbeanList.add(mtBean);
			}
			i++;
		}
		hmtbean.put(member.getTel_number(), mtbeanList);
		return hmtbean;
	}

	/**
	 * 详细批量插入
	 * 
	 * @param stmt
	 * @param dzjcList
	 * @param template_details
	 * @param member
	 * @throws SQLException
	 * @throws TemplateException
	 * @throws IOException
	 */
	public HashMap<String, List<MtBean>> addBath_mt_simple(
			PreparedStatement stmt, String city, Dzjc dzjc,
			Template template_simple, Members member,
			HashMap<String, List<MtBean>> hmtbean, List<MtBean> mtbeanList,
			String city_prompt) throws SQLException, TemplateException,
			IOException {
		String content = "";
		dzjc.setBanner(member.getBanner());
		content = getContent(template_simple, dzjc);
		content = content + city_prompt;
		MtBean mtBean = new MtBean();
		mtBean.setDst_id(dzjc.getMobile());
		mtBean.setService_id(member.getService());
		mtBean.setCity(city);
		mtBean.setSendFlag(1);
		mtBean.setSrc_id(member.getBanner());
		mtBean.setContent(content);
		mtbeanList.add(mtBean);
		hmtbean.put(member.getTel_number(), mtbeanList);
		return hmtbean;
	}

	/**
	 * 返回模板类
	 * 
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public Template getTemplate(String content) throws IOException {

		Configuration cfg_simple = new Configuration();
		cfg_simple.setTemplateLoader(new StringTemplateLoader(content));
		Template template_simple = cfg_simple.getTemplate("");
		return template_simple;
	}

	public PreparedStatement getPreparedStatement(Connection con, String city,
			int type) throws SQLException {
		String sendsms_sql = "insert into base.mt_test(gateway_id,handler,src_id,dst_id,service_id,fee_user_type,fee_type,fee_code,msg_content,msg_fmt,report_flag,log_time)values (?,?,?,?,?,0,?,?,?,15,1,now())";
		String member_update_sql = "update "
				+ city
				+ ".member_mt_status set send_date=?,send_flag=?,send_count=?,mt_flag=?,last_mt_date=?,dzjc_date=? where chepai=? and chepai_type=? and tel_number=?";
		String member_insert_sql = "insert into "
				+ city
				+ ".member_mt_status(chepai,chepai_type,tel_number,send_count,send_date,send_flag,mt_flag,last_mt_date,dzjc_date) values (?,?,?,?,?,?,?,?,?)";

		if (type == 1)
			sendsms_sql = member_update_sql;
		else if (type == 2) {
			sendsms_sql = member_insert_sql;
		}
		PreparedStatement stmt = con.prepareStatement(sendsms_sql);
		return stmt;
	}

	/**
	 * 批量插入
	 * 
	 * @param stmt_insert_sql
	 * @param mf
	 * @param dzjc
	 * @param sendflag
	 *            0表示发详细 1表示发统计 2 表示无违章
	 * @return
	 * @throws SQLException
	 */
	public boolean insertBath(PreparedStatement stmt_insert_sql,
			MemberMtStatus mf, Members member, int sendflag, Timestamp d1)
			throws SQLException {
		stmt_insert_sql.setString(1, member.getChepai());
		stmt_insert_sql.setInt(2, member.getChepai_type());
		stmt_insert_sql.setString(3, member.getTel_number());
		// 发送条数
		stmt_insert_sql.setInt(4, 1);
		// 发送时间
		stmt_insert_sql.setTimestamp(5, new Timestamp(System
				.currentTimeMillis()));
		// 判断有无违章
		stmt_insert_sql.setInt(6, sendflag);
		stmt_insert_sql.setInt(7, DzjcUtil.MT_FLAG);
		stmt_insert_sql.setTimestamp(8, new Timestamp(System
				.currentTimeMillis()));
		stmt_insert_sql.setTimestamp(9, d1);
		stmt_insert_sql.addBatch();
		return true;
	}

	public void delete_dzjc(Connection con, String city) {
		log.info("删除已处理的违章记录开始" + new Date().toLocaleString());
		int i=0;
		String delete_sql = "delete from " + city
				+ ".dzjc where hphm=? and hpzl=? and dzjcsj=?  and  trim(replace(dzjcdd,'\r',''))= ? ";
		String sql = "select * from " + city + ".dzjc_dealed";
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(delete_sql);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				pstmt.setString(1, rs.getString("hphm"));
				pstmt.setInt(2, rs.getInt("hpzl"));
				pstmt.setTimestamp(3, rs.getTimestamp("dzjcsj"));
				pstmt.setString(4, rs.getString("dzjcdd"));
				pstmt.addBatch();
			}
			int a[] = pstmt.executeBatch();
			if(a.length>0)
			{
				for (int j = 0; j < a.length; j++) {
//					log.info("删除状态为=="+a[j]);
					if(a[j]==1)
						i++;
				}
			}
			log.info("删除 "+i+" 条违章记录");
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("删除已处理的违章记录异常" + e.getMessage());
		} finally {
			DbHelper.closeRsorStmt(rs, pstmt);
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		log.info("删除已处理的违章记录结束" + new Date().toLocaleString());
	}

	/**
	 * 批量更新
	 * 
	 * @param stmt_update_sql
	 * @param mf
	 * @param dzjc
	 * @param d1
	 * @param sendflag
	 * @param sendcount
	 * @param mt_flag
	 * @param d2
	 * @param mobile
	 * @return
	 * @throws SQLException
	 */
	public boolean updateBath(PreparedStatement stmt_update_sql,
			MemberMtStatus mf, Members member, int sendflag, int mt_flag,
			Timestamp d3) throws SQLException {
		if (d3 != null) {
			if (mf.getDzjcdate() != null
					&& (mf.getDzjcdate().equals(d3) || mf.getDzjcdate().after(
							d3))) {
				d3 = new Timestamp(mf.getDzjcdate().getTime());
			}
		}
		/**
		 * 判断此条是发什么 如果是发详细 则取到上一条，看是发什么 String member_update_sql = "update "+
		 * city + ".member_mt_status set
		 * send_date=?,send_flag=?,send_count=?,mt_flag=?,
		 * last_mt_date=?,dzjc_date=? where chepai=? and chepai_type=? and
		 * tel_number=?";
		 */
		if (sendflag == DzjcUtil.SEND_DETAIL) {
			if (mf.getSendFlag() == DzjcUtil.SEND_DETAIL) {
				stmt_update_sql.setTimestamp(1, new Timestamp(mf.getSendDate()
						.getTime()));
				stmt_update_sql.setInt(3, mf.getSendCount() + 1);
			} else {
				stmt_update_sql.setTimestamp(1, new Timestamp(System
						.currentTimeMillis()));
				stmt_update_sql.setInt(3, 1);
			}
		} else if (sendflag == DzjcUtil.SEND_SIMPLE) {
			if (mf.getSendFlag() == DzjcUtil.SEND_SIMPLE) {
				stmt_update_sql.setTimestamp(1, new Timestamp(mf.getSendDate()
						.getTime()));
				stmt_update_sql.setInt(3, mf.getSendCount() + 1);
			} else {
				stmt_update_sql.setTimestamp(1, new Timestamp(System
						.currentTimeMillis()));
				stmt_update_sql.setInt(3, 1);
			}
		} else {
			if (mf.getSendFlag() == DzjcUtil.SEND_PROMPT) {
				stmt_update_sql.setTimestamp(1, new Timestamp(mf.getSendDate()
						.getTime()));
				stmt_update_sql.setInt(3, mf.getSendCount() + 1);
			} else {
				stmt_update_sql.setTimestamp(1, new Timestamp(System
						.currentTimeMillis()));
				stmt_update_sql.setInt(3, 1);
			}
		}

		stmt_update_sql.setInt(2, sendflag);
		stmt_update_sql.setInt(4, mt_flag);
		stmt_update_sql.setTimestamp(5, new Timestamp(System
				.currentTimeMillis()));
		stmt_update_sql.setTimestamp(6, d3);
		stmt_update_sql.setString(7, member.getChepai());
		stmt_update_sql.setInt(8, member.getChepai_type());
		stmt_update_sql.setString(9, member.getTel_number());
		stmt_update_sql.addBatch();
		return true;
	}

	/**
	 * 根据模板变量与用户组装数据
	 * 
	 * @param mtBean
	 * @param conn
	 * @throws SQLException
	 * @throws Exception
	 * @throws SQLException
	 */
	public List<Dzjc> getweizhanglist(Connection con, String sql, String city,
			Members members) throws SQLException {
		List Dzjclist = new ArrayList<Dzjc>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, members.getChepai());
			stmt.setInt(2, members.getChepai_type());
			rs = stmt.executeQuery();
			while (rs.next()) {
				Dzjc dzjc = new Dzjc();
				dzjc.setId(rs.getInt("id"));
				dzjc.setAddress(rs.getString("address"));
				dzjc.setCause(rs.getString("cause"));
				dzjc.setDate(rs.getTimestamp("date"));
				dzjc.setChepai(rs.getString("chepai"));
				dzjc.setChepai_type(rs.getInt("chepai_type"));
				dzjc.setMobile(members.getTel_number());
				dzjc.setService_id(members.getService());
				if (city.equals("jwt_db")) {
					dzjc.setScore(rs.getString("score"));
					dzjc.setMoney(rs.getString("money"));
				}
				dzjc.setCount(1);
				Dzjclist.add(dzjc);
			}

		} finally {
			DbHelper.closeRsorStmt(rs, stmt);
		}
		return Dzjclist;
	}

	/**
	 * 取黑名单
	 * 
	 * @param dbh
	 * @return
	 */
	public HashMap<String, String> getBlackList(Connection con, String city) {
		HashMap<String, String> black_mobile = new HashMap<String, String>();
		String sql = "select *  from " + city + ".black_user";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String mobile = rs.getString("tel_number");
				black_mobile.put(mobile, mobile);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("取黑名单数据有误！" + e.getMessage());
			return black_mobile;
		} finally {
			DbHelper.closeRsorStmt(rs, stmt);
		}
		return black_mobile;
	}

}

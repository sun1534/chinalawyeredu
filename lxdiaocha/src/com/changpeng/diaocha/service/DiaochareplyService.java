package com.changpeng.diaocha.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.*;
import com.changpeng.models.system.*;

public class DiaochareplyService extends BasicService {
	private BasicDAO basicDAO;
	private PlatformTransactionManager transactionManager;

	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * 回复调查
	 * 
	 * @param wentilist
	 *            问题列表
	 * @param replys
	 *            回复答案
	 * @param sysUser
	 *            回复用户
	 * @return
	 * @throws ServiceException
	 */
	/*
	 * public int saveReply(final List<Diaochawenti> wentilist,final String[]
	 * replys,final SysUser sysUser) throws ServiceException { try {
	 * TransactionTemplate transactionTemplate = new TransactionTemplate(
	 * this.transactionManager); Object object = transactionTemplate
	 * .execute(new TransactionCallback() { public Object
	 * doInTransaction(TransactionStatus status) { Date now = new Date();
	 * Diaochareply reply = null;
	 * 
	 * int i = 0; int count = 0; // 回答题目数
	 * 
	 * for (; i < replys.length; i++) { String anwser = replys[i];
	 *  // 这里应该改成批量提交的 如果问题较多的话 if (anwser != null && !anwser.trim().equals("")) {
	 * count++;
	 * 
	 * Diaochawenti wenti = (Diaochawenti) wentilist.get(i);
	 *  // 保存回复 reply = new Diaochareply(); reply.setDiaochawenti(wenti);
	 * reply.setReplycontent(anwser); //
	 * reply.setReplyuser(getLoginUser().getUsername());
	 *  // 保持回复用户的唯一性 在这用登录名代替 reply.setReplyuser(sysUser.getLoginname());
	 * reply.setReplytime(now); basicDAO.save(reply);
	 * 
	 * int leixing = wenti.getWentileixing(); Set<Diaochaoption> options =
	 * wenti.getDiaochaoptions(); // 如果是选择题的话 匹配答案 为每个选项增加回复记录值 if (leixing ==
	 * 1) { // 单选题 for (Diaochaoption option : options) { if
	 * (option.getOptions().equals(anwser)) {
	 * option.setReplycount(option.getReplycount() + 1);
	 * 
	 * basicDAO.update(option); }
	 *  } } if (leixing == 2) { // 多选题 for (Diaochaoption option : options) {
	 * String rep[] = anwser.split("\\W"); for (String str : rep) { if
	 * (option.getOptions().equals(str)) {
	 * 
	 * option.setReplycount(option.getReplycount() + 1);
	 * basicDAO.update(option); } } } }
	 *  } } return count; } }); return (Integer)object; }catch (Exception e) {
	 * throw new ServiceException(e); } }
	 */

	/**
	 * 回复调查
	 * 
	 * @param wentiids
	 *            问题ID列表
	 * @param replys
	 *            回复答案
	 * @param sysUser
	 *            回复用户
	 * @return
	 * @throws ServiceException
	 */
	public int saveReply(final int[] wentiids, final String[] replys, final String[] others, final SysUser sysUser,
			final String ip, final String cookie, final String username, final String lawyerno, final int diaochaid,
			final String batchid) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					Date now = new Date();
					Diaochareply reply = null;

					int i = 0;
					int count = 0; // 回答题目数

					for (; i < replys.length; i++) {
						String anwser = replys[i];

						// 这里应该改成批量提交的 如果问题较多的话
						if (anwser != null && !anwser.trim().equals("")) {
							count++;

							Diaochawenti wenti = (Diaochawenti) basicDAO.get(Diaochawenti.class, wentiids[i]);

							// 保存回复
							reply = new Diaochareply();
							reply.setDiaochawenti(wenti);
							reply.setReplycontent(anwser);
							// reply.setReplyuser(getLoginUser().getUsername());

							// 保持回复用户的唯一性 在这用登录名代替
							if (sysUser != null){
								reply.setReplyuser(sysUser.getUsername());
								reply.setLawyerno(sysUser.getLawerno());
							}
							else if (username != null && !username.equals("")){
								reply.setReplyuser(username);
								reply.setLawyerno(lawyerno);
							}
							else{
								reply.setReplyuser("匿名用户");
								reply.setLawyerno("匿名用户");
							}
							reply.setReplytime(now);
							reply.setIp(ip);
							reply.setCookie(cookie);
							reply.setDiaochaid(diaochaid);
							reply.setBatchid(batchid);

							// 保存所回复的其他内容
							reply.setOthers(others[i]);

							basicDAO.save(reply);

							int leixing = wenti.getWentileixing();
							Set<Diaochaoption> options = wenti.getDiaochaoptions();
							// 如果是选择题的话 匹配答案 为每个选项增加回复记录值
							if (leixing == 1) { // 单选题
								for (Diaochaoption option : options) {
									if (option.getOptions().equals(anwser)) {
										option.setReplycount(option.getReplycount() + 1);

										basicDAO.update(option);
									}

								}
							}
							if (leixing == 2) { // 多选题
								for (Diaochaoption option : options) {
									String rep[] = anwser.split("\\W");
									for (String str : rep) {
										if (option.getOptions().equals(str)) {

											option.setReplycount(option.getReplycount() + 1);
											basicDAO.update(option);
										}
									}
								}
							}

						}
					}
					return count;
				}
			});
			return (Integer) object;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}

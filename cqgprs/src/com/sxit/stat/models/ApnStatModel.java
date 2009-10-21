/**
 * 
 */
package com.sxit.stat.models;

import com.sxit.system.util.NumberUtil;

/**
 * @author 华锋
 * Oct 19, 2009-10:37:32 PM
 *
 */
public class ApnStatModel {
	private String apnid;
	
	
		private long totalStream;
		private int totalUser;
		
	
		/**
		 * 某天的数据
		 */
		private String date;
		/**
		 * 某天-时的数据
		 */
		private String datetime;
		
		/**
		 * 以兆为单位
		 * @return
		 */
		public float getTotalStreamStr(){
			double d = ((double) totalStream) / (1024 * 1024);
			String totalStreamStr= NumberUtil.toMoney(d);
			return Float.parseFloat(totalStreamStr);
		}
		
		/**
		 * 得到平均流量,单位为k
		 * @return the averageStream
		 */
		public float getAverageStreamStr() {
			float f= ((float) totalStream) / (1024 * totalUser);
			String averageStreamStr= NumberUtil.toMoney(f);
			return Float.parseFloat(averageStreamStr);
		}
		
		/**
		 * 得到平均流量,单位为k
		 * @return the averageStream
		 */
		public float getAverageStream() {
			float f= ((float) totalStream) / (1024 * totalUser);
			String averageStreamStr= NumberUtil.toMoney(f);
			return Float.parseFloat(averageStreamStr);
		}

		/**
		 * @return the totalStream
		 */
		public long getTotalStream() {
			return totalStream;
		}

		/**
		 * @param totalStream the totalStream to set
		 */
		public void setTotalStream(long totalStream) {
			this.totalStream = totalStream;
		}

		/**
		 * @return the totalUser
		 */
		public int getTotalUser() {
			return totalUser;
		}

		/**
		 * @param totalUser the totalUser to set
		 */
		public void setTotalUser(int totalUser) {
			this.totalUser = totalUser;
		}

	
		/**
		 * @return the date
		 */
		public String getDate() {
			return date;
		}

		/**
		 * @param date the date to set
		 */
		public void setDate(String date) {
			this.date = date;
		}

		/**
		 * @return the datetime
		 */
		public String getDatetime() {
			return datetime;
		}

		/**
		 * @param datetime the datetime to set
		 */
		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}

		/**
		 * @return the apnid
		 */
		public String getApnid() {
			return apnid;
		}

		/**
		 * @param apnid the apnid to set
		 */
		public void setApnid(String apnid) {
			this.apnid = apnid;
		}
}

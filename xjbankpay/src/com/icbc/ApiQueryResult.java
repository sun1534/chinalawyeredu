/**
 * ApiQueryResp.java
 */
package com.icbc;

/**
 * @author 华锋 May 31, 20105:05:38 PM
 * 
 */
public class ApiQueryResult {
/**
 * 接口查询的返回码,如果respId=0，则下面的queryResp非空.
 * 其他非0情况的定义如下
	 *         -1,接口返回空值，respMsg的值为"接口返回空值"
	 *         -2,解析返回的xml数据有错，对应错误见respMsg
	 *         -3,发送查询请求失败，对应错误见respMsg
	 *         >0,查看APIQueryStat的QUERY_RESULT的key定义
 * 
 */
	private int respId;
	
	private String respMsg;
	private ApiQueryResp queryResp;
	/**
	 * @return the respId
	 */
	public int getRespId() {
		return respId;
	}
	/**
	 * @param respId the respId to set
	 */
	public void setRespId(int respId) {
		this.respId = respId;
	}
	/**
	 * @return the queryResp
	 */
	public ApiQueryResp getQueryResp() {
		return queryResp;
	}
	/**
	 * @param queryResp the queryResp to set
	 */
	public void setQueryResp(ApiQueryResp queryResp) {
		this.queryResp = queryResp;
	}
	/**
	 * @return the respMsg
	 */
	public String getRespMsg() {
		return respMsg;
	}
	/**
	 * @param respMsg the respMsg to set
	 */
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
}

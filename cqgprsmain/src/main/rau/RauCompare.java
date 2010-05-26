/**
 * RauCompare.java
 */
package main.rau;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 华锋 Apr 2, 201010:19:25 PM
 * 
 */
public class RauCompare {

	public List<String> printlnContent() {
		List<String> list = new ArrayList<String>();
		list.add("SGSNCQ" + sgsnid + "共"+all+"条数据,本次删除" + delete + "条数据,新增" + add + "条数据");

		for (int i = 0; i < deleteraus.size(); i++) {
			list.add("\t\t删除:" + deleteraus.get(i));
		}
		for (int i = 0; i < addraus.size(); i++) {
			list.add("\t\t新增:" + addraus.get(i));
		}
		return list;
	}

	private int all;
	private int sgsnid;
	private int add;
	private int delete;
	private List<String> addraus = new ArrayList<String>();
	private List<String> deleteraus = new ArrayList<String>();

	/**
	 * @return the sgsnid
	 */
	public int getSgsnid() {
		return sgsnid;
	}

	/**
	 * @param sgsnid
	 *            the sgsnid to set
	 */
	public void setSgsnid(int sgsnid) {
		this.sgsnid = sgsnid;
	}

	/**
	 * @return the add
	 */
	public int getAdd() {
		return add;
	}

	/**
	 * @param add
	 *            the add to set
	 */
	public void setAdd(int add) {
		this.add = add;
	}

	/**
	 * @return the delete
	 */
	public int getDelete() {
		return delete;
	}

	/**
	 * @param delete
	 *            the delete to set
	 */
	public void setDelete(int delete) {
		this.delete = delete;
	}

	/**
	 * @return the addraus
	 */
	public List<String> getAddraus() {
		return addraus;
	}

	/**
	 * @param addraus
	 *            the addraus to set
	 */
	public void addAddraus(String e) {
		this.addraus.add(e);
	}

	/**
	 * @return the deleteraus
	 */
	public List<String> getDeleteraus() {
		return deleteraus;
	}

	/**
	 * @param deleteraus
	 *            the deleteraus to set
	 */
	public void addDeleteraus(String e) {
		this.deleteraus.add(e);
	}

	/**
	 * @return the all
	 */
	public int getAll() {
		return all;
	}

	/**
	 * @param all the all to set
	 */
	public void setAll(int all) {
		this.all = all;
	}
}

/**
 * <pre>
 *                          
 * </pre>
 */

package com.uu800.webbase.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 华锋 2009-1-6 上午09:11:01
 * 
 */
public class TreeNode {

	/**
	 * 存储child-parent关系的键－值队列,都必须是Object型
	 */
	private Map<Object, Object> parentChild;
	/**
	 * 需要找父和找子的键值
	 */
	private Object key;

	/**
	 * 存储和返回对象对应的level值,一一对应
	 */
	private List<Integer> childrenLevel = new ArrayList<Integer>();

	/**
	 * 存储根据key找到的所有子对象
	 */
	private List<Object> childrenList = new ArrayList<Object>();

	/**
	 * 存储根据key找到的所有父对象
	 */
	private List<Object> parentList = new ArrayList<Object>();

	/**
	 * 存储对应父键和其对应的level值
	 */
	private Map<Object, Integer> parentlevelMap = new HashMap<Object, Integer>();
	/**
	 * 存储所有的键对象,即对应表中的关键字段
	 */
	private Object[] keyset;
	/**
	 * 键对象的长度
	 */
	private int length;

	/**
	 * 
	 * @param parentChild
	 *            存储子键和复键的键－值队列
	 * @param parent
	 *            键对象
	 */
	// public TreeNode(Map<Object, Object> parentChild, Object key) {
	// this.parentChild = parentChild;
	// this.key = key;
	// this.keyset = parentChild.keySet().toArray();
	// this.length = keyset.length;
	//
	// }
	/**
	 * 
	 * @param parentChild
	 */
	public TreeNode(Map<Object, Object> parentChild) {
		this.parentChild = parentChild;
		this.keyset = parentChild.keySet().toArray();
		this.length = keyset.length;
	}

	/**
	 * 返回和子对象对应的level值
	 * 
	 * @return
	 */
	public List<Integer> getChildrenLevel() {
		return this.childrenLevel;
	}

	/**
	 * 返回输入键的所有子对象
	 * 
	 * @return
	 */
	private List<Object> getChildrenList() {
		// if (!_getchild) {
		parentlevelMap.put(key, 0);
		if (key == null)
			throw new IllegalArgumentException("key can't be null");
		getChildren(key);
		// }
		_getchild = true;
		return this.childrenList;
	}

	public List<Object> getChildrenList(Object key) {
		if (this.key != null && this.key.equals(key) && _getchild) {// 如果前后2个的key是相同的
			return this.childrenList;
		} else {
			this.key = key;
			this.childrenList.clear();
			return getChildrenList();
		}
	}

	// /**
	// * 得到直接下属
	// * @param key
	// * @return
	// */
	// public List<Object> getDirectChildrenLit(Object key){
	//		
	// }

	/**
	 * 返回输入键的所有父对象
	 * 
	 * @return
	 */
	private List<Object> getParentList() {
		// if (!_getparent) {
		if (this.key == null)
			throw new IllegalArgumentException("key can't be null");
		getParent(this.key);
		// }
		_getparent = true;
		return this.parentList;
	}

	public List<Object> getParentList(Object key) {
		if (this.key != null && this.key.equals(key) && _getparent) {// 如果前后2个的key是相同的
			return this.parentList;
		} else {
			this.key = key;
			this.parentList.clear();
			return getParentList();
		}

	}

	/**
	 * 根据parent找到下面所有的children
	 * 
	 * @param parent
	 */
	private void getChildren(Object parent) {
		for (int i = 0; i < length; i++) {
			Object obj = keyset[i];
			if (parentChild.get(obj).equals(parent)) {
				childrenList.add(obj);// 加入到返回对象中
				childrenLevel.add(parentlevelMap.get(parent).intValue() + 1);
				parentlevelMap.put(obj, parentlevelMap.get(parent).intValue() + 1);
				getChildren(obj);
			}
		}
	}

	/**
	 * 根据child得到所有的父亲
	 * 
	 * @param child
	 */
	private void getParent(Object child) {
		Object obj = parentChild.get(child);
		if (obj != null) {
			parentList.add(obj);
			getParent(obj);
		}
	}

	/**
	 * 是否已经获取了子对象
	 */
	private boolean _getchild = false;
	/**
	 * 是否已经获取了父对象
	 */
	private boolean _getparent = false;

	public static void main(String args[]) {
		HashMap<Object, Object> parentChild = new HashMap<Object, Object>();

		long now = System.currentTimeMillis();

		for (int i = 0; i < 10000; i++) {
			parentChild.put(i, i + 1);
		}

		System.out.println(System.currentTimeMillis() - now);

		// parentChild.put(2, -1);
		// parentChild.put(0,-1);
		// parentChild.put(3,1);
		// parentChild.put(4,1);
		// parentChild.put(15,5);
		// parentChild.put(5,4);
		// parentChild.put(16,5);
		// parentChild.put(17,5);
		// parentChild.put(18,5);
		// parentChild.put(19,5);
		// parentChild.put(20,5);
		// parentChild.put(21,5);
		// parentChild.put(22,5);
		// parentChild.put(23,5);
		// parentChild.put(24,5);
		// parentChild.put(6,5);
		// parentChild.put(7,3);
		// parentChild.put(8,3);
		// parentChild.put(9,2);
		// parentChild.put(10,0);
		// parentChild.put(11,0);
		// parentChild.put(13,5);
		// parentChild.put(14,5);
		// parentChild.put(25,5);
		// parentChild.put(26,5);
		// parentChild.put(1,-1);

		TreeNode node = new TreeNode(parentChild);
		now = System.currentTimeMillis();
		System.out.println(node.getChildrenList(10));

		System.out.println(node.childrenLevel);

		System.out.println(node.getParentList(9991));
		System.out.println(System.currentTimeMillis() - now);
	}
}

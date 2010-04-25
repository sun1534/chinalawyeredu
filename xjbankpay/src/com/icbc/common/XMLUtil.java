package com.icbc.common;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.util.*;
public class XMLUtil {
	private List<Leaf> elemList = new ArrayList<Leaf>();
	   
    /** 
     * 获取根元素 
     * 
     * @return 
     * @throws DocumentException 
     */ 
    public Element getRootElement(String srcXml) throws DocumentException { 
        Document srcdoc = DocumentHelper.parseText(srcXml); 
        Element elem = srcdoc.getRootElement(); 
        return elem; 
    } 

    /** 
     * 递归遍历方法 
     * 
     * @param element 
     */ 
    public void getElementList(Element element) { 
    	elemList.clear();//每次填充数据的时候清空里面的数据
        List elements = element.elements(); 
       
        if (elements.size() == 0) { 
            //没有子元素 
            String xpath = element.getPath(); 
            String value = element.getTextTrim(); 
            elemList.add(new Leaf(xpath, value)); 
        } else { 
            //有子元素 
            for (Iterator it = elements.iterator(); it.hasNext();) { 
                Element elem = (Element) it.next(); 
                //递归遍历 
                getElementList(elem); 
            } 
        } 
    } 
    public String getValue(String xpath){
    	for(Leaf leaf:elemList){
    		if(leaf.getXpath().equals(xpath))
    			return leaf.getValue();
    	}
    	return null;
    }
    public String getListString() { 
        StringBuffer sb = new StringBuffer(); 
        for (Iterator<Leaf> it = elemList.iterator(); it.hasNext();) { 
            Leaf leaf = it.next(); 
            sb.append(leaf.getXpath()).append(" = ").append(leaf.getValue()).append("\n"); 
        } 
        return sb.toString(); 
    } 
	class Leaf { 
	    private String xpath;         // 
	    private String value; 
	    public Leaf(String xpath, String value) { 
	        this.xpath = xpath; 
	        this.value = value; 
	    } 
	    public String getXpath() { 
	        return xpath; 
	    } 

	    public void setXpath(String xpath) { 
	        this.xpath = xpath; 
	    } 

	    public String getValue() { 
	        return value; 
	    } 

	    public void setValue(String value) { 
	        this.value = value; 
	    } 
	}
}

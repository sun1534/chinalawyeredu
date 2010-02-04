/**
 * 
 */
package main.nsvcalarm;

import java.io.File;
import java.sql.Timestamp;

/**
 * @author 华锋
 * Nov 15, 2009-7:45:29 PM
 *
 */
public class NsvcAlarmFile {
	private int id;
	private String sgsnid;
	private String srcfilename;
	private File destfile;
	
	private String destdir;
	
	private Timestamp modified;
	/**
	 * 这个文件上次已经处理过的错误个数
	 */
	private int handleLines;
	

	/**
	 * @return the handleLines
	 */
	public int getHandleLines() {
		return handleLines;
	}

	/**
	 * @param handleLines the handleLines to set
	 */
	public void setHandleLines(int handleLines) {
		this.handleLines = handleLines;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the sgsnid
	 */
	public String getSgsnid() {
		return sgsnid;
	}

	/**
	 * @param sgsnid the sgsnid to set
	 */
	public void setSgsnid(String sgsnid) {
		this.sgsnid = sgsnid;
	}


	/**
	 * @return the destdir
	 */
	public String getDestdir() {
		return destdir;
	}

	/**
	 * @param destdir the destdir to set
	 */
	public void setDestdir(String destdir) {
		this.destdir = destdir;
	}

	/**
	 * @return the modified
	 */
	public Timestamp getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	/**
	 * @return the srcfile
	 */
	public String getSrcfilename() {
		return srcfilename;
	}

	/**
	 * @param srcfile the srcfile to set
	 */
	public void setSrcfilename(String srcfilename) {
		this.srcfilename = srcfilename;
	}

	/**
	 * @return the destfile
	 */
	public File getDestfile() {
		return destfile;
	}

	/**
	 * @param destfile the destfile to set
	 */
	public void setDestfile(File destfile) {
		this.destfile = destfile;
	}
	

	
}

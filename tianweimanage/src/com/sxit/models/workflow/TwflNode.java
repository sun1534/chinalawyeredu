package com.sxit.models.workflow;

import java.util.HashSet;
import java.util.Set;

/**
 * TwflNode entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TwflNode implements java.io.Serializable {

	// Fields

	private Integer nodeid;
	private TwflProcess twflProcess;
	private TwflPosition twflPosition;
	private String nodename;
	private String description;
	private String image;
	private short nodetype;
	private short nodedotype;
	private Integer xcoordinate;
	private Integer ycoordinate;
	private Integer width;
	private Integer height;
	private boolean sendemail;
	private boolean sendsms;
	private boolean jumpself;
	private Set<TwflDirection> fromNode = new HashSet<TwflDirection>(0);
	private Set<TwflDirection> toNode = new HashSet<TwflDirection>(0);

	// Constructors

	/** default constructor */
	public TwflNode() {
	}

	/** minimal constructor */
	public TwflNode(TwflPosition twflPosition, String nodename) {
		this.twflPosition = twflPosition;
		this.nodename = nodename;
	}


	// Property accessors

	public Integer getNodeid() {
		return this.nodeid;
	}

	public void setNodeid(Integer nodeid) {
		this.nodeid = nodeid;
	}

	public TwflProcess getTwflProcess() {
		return this.twflProcess;
	}

	public void setTwflProcess(TwflProcess twflProcess) {
		this.twflProcess = twflProcess;
	}

	public TwflPosition getTwflPosition() {
		return this.twflPosition;
	}

	public void setTwflPosition(TwflPosition twflPosition) {
		this.twflPosition = twflPosition;
	}

	public String getNodename() {
		return this.nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public short getNodetype() {
		return this.nodetype;
	}

	public void setNodetype(short nodetype) {
		this.nodetype = nodetype;
	}

	public Integer getXcoordinate() {
		return this.xcoordinate;
	}

	public void setXcoordinate(Integer xcoordinate) {
		this.xcoordinate = xcoordinate;
	}

	public Integer getYcoordinate() {
		return this.ycoordinate;
	}

	public void setYcoordinate(Integer ycoordinate) {
		this.ycoordinate = ycoordinate;
	}

	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}


	public boolean isSendemail() {
		return sendemail;
	}

	public void setSendemail(boolean sendemail) {
		this.sendemail = sendemail;
	}

	public boolean isSendsms() {
		return sendsms;
	}

	public void setSendsms(boolean sendsms) {
		this.sendsms = sendsms;
	}

	public boolean isJumpself() {
		return jumpself;
	}

	public void setJumpself(boolean jumpself) {
		this.jumpself = jumpself;
	}


	public Set<TwflDirection> getFromNode() {
		return fromNode;
	}

	public void setFromNode(Set<TwflDirection> fromNode) {
		this.fromNode = fromNode;
	}

	public Set<TwflDirection> getToNode() {
		return toNode;
	}

	public void setToNode(Set<TwflDirection> toNode) {
		this.toNode = toNode;
	}

	public short getNodedotype() {
		return nodedotype;
	}

	public void setNodedotype(short nodedotype) {
		this.nodedotype = nodedotype;
	}


}
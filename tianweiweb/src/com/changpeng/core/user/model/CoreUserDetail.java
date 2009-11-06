package com.changpeng.core.user.model;

/**
 * CoreUserDetail entity. @author MyEclipse Persistence Tools
 */

public class CoreUserDetail implements java.io.Serializable {

	// Fields

	private Integer userid;
	private String msn;
	private String qq;
	private String email;
	private String fetion;
	private String phone;
	private String postcode;
	private String userAddress;
	private String summary;
	private String interest;
	private String books;
	private String peoples;
	private String adages;
	private String musics;
	private String movies;
	private String games;
	private String animations;
	private String sports;
	private String question;
	private String answer;

	// Constructors

	/** default constructor */
	public CoreUserDetail() {
	}

	/** minimal constructor */
	public CoreUserDetail(Integer userid) {
		this.userid = userid;
	}

	/** full constructor */
	public CoreUserDetail(Integer userid, String msn, String qq, String email,
			String fetion, String phone, String postcode, String userAddress,
			String summary, String interest, String books, String peoples,
			String adages, String musics, String movies, String games,
			String animations, String sports, String question,
			String answer) {
		this.userid = userid;
		this.msn = msn;
		this.qq = qq;
		this.email = email;
		this.fetion = fetion;
		this.phone = phone;
		this.postcode = postcode;
		this.userAddress = userAddress;
		this.summary = summary;
		this.interest = interest;
		this.books = books;
		this.peoples = peoples;
		this.adages = adages;
		this.musics = musics;
		this.movies = movies;
		this.games = games;
		this.animations = animations;
		this.sports = sports;
		this.question = question;
		this.answer = answer;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFetion() {
		return this.fetion;
	}

	public void setFetion(String fetion) {
		this.fetion = fetion;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getInterest() {
		return this.interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getBooks() {
		return this.books;
	}

	public void setBooks(String books) {
		this.books = books;
	}

	public String getPeoples() {
		return this.peoples;
	}

	public void setPeoples(String peoples) {
		this.peoples = peoples;
	}

	public String getAdages() {
		return this.adages;
	}

	public void setAdages(String adages) {
		this.adages = adages;
	}

	public String getMusics() {
		return this.musics;
	}

	public void setMusics(String musics) {
		this.musics = musics;
	}

	public String getMovies() {
		return this.movies;
	}

	public void setMovies(String movies) {
		this.movies = movies;
	}

	public String getGames() {
		return this.games;
	}

	public void setGames(String games) {
		this.games = games;
	}

	public String getAnimations() {
		return this.animations;
	}

	public void setAnimations(String animations) {
		this.animations = animations;
	}

	public String getSports() {
		return this.sports;
	}

	public void setSports(String sports) {
		this.sports = sports;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
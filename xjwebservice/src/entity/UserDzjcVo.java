package entity;


/**
 * 下发构造短信的vo
 * 和违章信息记录一起
 * UserOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class UserDzjcVo implements java.io.Serializable {
	
	public static java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	${(banner)?default("")}${chepai}于${date}在${address}因${cause}违章。
	private String mobile;
	private String chepai;
	private String areacode;
	private String chepaileixing;
	private String date; //对应dzjcsj;
	private String cause;//对应wzxx
	private String address;//对应dzjcdd
	private int count;//违章次数
	
	//尊敬的xxxxx
	private String banner="";
	//罚款多少
	private String money="0";
	//分数多少
	private String score="0";
	
	public UserDzjcVo(){
		this.mobile="13510073023";
		this.chepai="新12345";
		this.areacode="乌鲁木齐";
		this.banner="测试";
		this.cause="压黄线";
		this.chepaileixing="1";
		this.count=5;
		this.date="2011-03-12 23:12:12";
		this.address="交叉路口";
	}
	public UserDzjcVo(UserOrder order,DzjcAllHistory history){
		this.mobile=order.getMobile();
		this.chepai=order.getChepai();
		this.areacode=order.getAreacode();
		this.chepaileixing=order.getChepaileixing();
		this.date=df.format(history.getDzjcsj());
		this.cause=history.getWzxx();
		this.address=history.getDzjcdd();
		
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the chepai
	 */
	public String getChepai() {
		return chepai;
	}

	/**
	 * @param chepai the chepai to set
	 */
	public void setChepai(String chepai) {
		this.chepai = chepai;
	}

	/**
	 * @return the areacode
	 */
	public String getAreacode() {
		return areacode;
	}

	/**
	 * @param areacode the areacode to set
	 */
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	/**
	 * @return the chepaileixing
	 */
	public String getChepaileixing() {
		return chepaileixing;
	}

	/**
	 * @param chepaileixing the chepaileixing to set
	 */
	public void setChepaileixing(String chepaileixing) {
		this.chepaileixing = chepaileixing;
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
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * @param cause the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the banner
	 */
	public String getBanner() {
		return banner;
	}
	/**
	 * @param banner the banner to set
	 */
	public void setBanner(String banner) {
		this.banner = banner;
	}
	/**
	 * @return the money
	 */
	public String getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(String money) {
		this.money = money;
	}
	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}
	

	
}
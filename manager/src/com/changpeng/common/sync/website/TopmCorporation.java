/**
 * 
 */
package com.changpeng.common.sync.website;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author 华锋 Jul 8, 2009-4:54:08 PM
 * 
 */
public class TopmCorporation {

	long corporationid;
	long parentid;
	int typeid;
	String corporationname;
	String corpnameshort;
	String corpnameen;
	String corpman;
	String corpmanemail;
	String corpmanmobiletel;
	String corpmantel;
	String corpmanfax;
	String postcode;
	String corpaddr;
	String corpurl;
	String corpship;
	String corptype;
	int corpgrade;
	String exchangetel;
	String contactermobiletel;
	String custservertel;
	String contacter;
	String contacteremail;
	String contactertel;
	String contacterfax;
	String province;
	String city;
	String county;
	String district;
	String community;
	String openbank;
	String bankaccount;
	String disciption;
	String dealin;
	int credit;
	long regfund;
	String businessyear;
	String licenceno;
	Timestamp registerdate;
	String variation;
	String constitution;
	String agreement;
	String assetprove;
	String officeprove;
	String copartnerprove;
	String memo;
	Timestamp opendate;
	Timestamp confirmdate;
	String confirmman;
	String confirmadvise;
	String ip;
	int port;
	String loginname;
	String loginpassword;
	int isexcellent;
	int isrecommended;
	int statusid;
	Timestamp createdate;
	String description;
	String typename;
	private int level;
	int quantity;

	public void setCorporationid(long corporationid) {
		this.corporationid = corporationid;
	}

	public long getCorporationid() {
		return corporationid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}

	public long getParentid() {
		return parentid;
	}

	public void setCorporationname(String corporationname) {
		this.corporationname = corporationname;
	}

	public String getCorporationname() {
		return corporationname;
	}

	public void setCorpnameshort(String corpnameshort) {
		this.corpnameshort = corpnameshort;
	}

	public String getCorpnameshort() {
		return corpnameshort;
	}

	public void setCorpnameen(String corpnameen) {
		this.corpnameen = corpnameen;
	}

	public String getCorpnameen() {
		return corpnameen;
	}

	public void setCorpman(String corpman) {
		this.corpman = corpman;
	}

	public String getCorpman() {
		return corpman;
	}

	public void setCorpmanemail(String corpmanemail) {
		this.corpmanemail = corpmanemail;
	}

	public String getCorpmanemail() {
		return corpmanemail;
	}

	public void setCorpmanmobiletel(String corpmanmobiletel) {
		this.corpmanmobiletel = corpmanmobiletel;
	}

	public String getCorpmanmobiletel() {
		return corpmanmobiletel;
	}

	public void setCorpmantel(String corpmantel) {
		this.corpmantel = corpmantel;
	}

	public String getCorpmantel() {
		return corpmantel;
	}

	public void setCorpmanfax(String corpmanfax) {
		this.corpmanfax = corpmanfax;
	}

	public String getCorpmanfax() {
		return corpmanfax;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setCorpaddr(String corpaddr) {
		this.corpaddr = corpaddr;
	}

	public String getCorpaddr() {
		return corpaddr;
	}

	public void setCorpurl(String corpurl) {
		this.corpurl = corpurl;
	}

	public String getCorpurl() {
		return corpurl;
	}

	public void setCorpship(String corpship) {
		this.corpship = corpship;
	}

	public String getCorpship() {
		return corpship;
	}

	public void setCorptype(String corptype) {
		this.corptype = corptype;
	}

	public String getCorptype() {
		return corptype;
	}

	public void setCorpgrade(int corpgrade) {
		this.corpgrade = corpgrade;
	}

	public int getCorpgrade() {
		return corpgrade;
	}

	public void setExchangetel(String exchangetel) {
		this.exchangetel = exchangetel;
	}

	public String getExchangetel() {
		return exchangetel;
	}

	public void setContactermobiletel(String contactermobiletel) {
		this.contactermobiletel = contactermobiletel;
	}

	public String getContactermobiletel() {
		return contactermobiletel;
	}

	public void setCustservertel(String custservertel) {
		this.custservertel = custservertel;
	}

	public String getCustservertel() {
		return custservertel;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getContacter() {
		return contacter;
	}

	public void setContacteremail(String contacteremail) {
		this.contacteremail = contacteremail;
	}

	public String getContacteremail() {
		return contacteremail;
	}

	public void setContactertel(String contactertel) {
		this.contactertel = contactertel;
	}

	public String getContactertel() {
		return contactertel;
	}

	public void setContacterfax(String contacterfax) {
		this.contacterfax = contacterfax;
	}

	public String getContacterfax() {
		return contacterfax;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {
		return province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCounty() {
		return county;
	}

	public void setOpenbank(String openbank) {
		this.openbank = openbank;
	}

	public String getOpenbank() {
		return openbank;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setDisciption(String disciption) {
		this.disciption = disciption;
	}

	public String getDisciption() {
		return disciption;
	}

	public void setDealin(String dealin) {
		this.dealin = dealin;
	}

	public String getDealin() {
		return dealin;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getCredit() {
		return credit;
	}

	public void setRegfund(long regfund) {
		this.regfund = regfund;
	}

	public long getRegfund() {
		return regfund;
	}

	public void setBusinessyear(String businessyear) {
		this.businessyear = businessyear;
	}

	public String getBusinessyear() {
		return businessyear;
	}

	public void setLicenceno(String licenceno) {
		this.licenceno = licenceno;
	}

	public String getLicenceno() {
		return licenceno;
	}

	public void setRegisterdate(Timestamp registerdate) {
		this.registerdate = registerdate;
	}

	public Timestamp getRegisterdate() {
		return registerdate;
	}

	public void setVariation(String variation) {
		this.variation = variation;
	}

	public String getVariation() {
		return variation;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public String getConstitution() {
		return constitution;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAssetprove(String assetprove) {
		this.assetprove = assetprove;
	}

	public String getAssetprove() {
		return assetprove;
	}

	public void setOfficeprove(String officeprove) {
		this.officeprove = officeprove;
	}

	public String getOfficeprove() {
		return officeprove;
	}

	public void setCopartnerprove(String copartnerprove) {
		this.copartnerprove = copartnerprove;
	}

	public String getCopartnerprove() {
		return copartnerprove;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMemo() {
		return memo;
	}

	public void setOpendate(Timestamp opendate) {
		this.opendate = opendate;
	}

	public Timestamp getOpendate() {
		return opendate;
	}

	public void setConfirmdate(Timestamp confirmdate) {
		this.confirmdate = confirmdate;
	}

	public Timestamp getConfirmdate() {
		return confirmdate;
	}

	public void setConfirmman(String confirmman) {
		this.confirmman = confirmman;
	}

	public String getConfirmman() {
		return confirmman;
	}

	public void setConfirmadvise(String confirmadvise) {
		this.confirmadvise = confirmadvise;
	}

	public String getConfirmadvise() {
		return confirmadvise;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}

	public String getLoginpassword() {
		return loginpassword;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

	public int getStatusid() {
		return statusid;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	private boolean isExist;

	public boolean getIsExist() {
		return this.isExist;
	}

	public void create(Connection con, String orgId) throws SQLException {
		PreparedStatement stmt;
		ResultSet rs;
		stmt = null;
		rs = null;

		String sql = "select corporationid,parentid,typeid,corporationname,corpnameshort,corpnameen,co"
				+ "rpman,corpmanemail,corpmanmobiletel,corpmantel,corpmanfax,postcode,corpaddr,corp"
				+ "url,corpship,corptype,corpgrade,exchangetel,contactermobiletel,custservertel,con"
				+ "tacter,contacteremail,contactertel,contacterfax,province,city,county,district,co"
				+ "mmunity,openbank,bankaccount,disciption,dealin,credit,regfund,businessyear,licen"
				+ "ceno,registerdate,variation,constitution,agreement,assetprove,officeprove,copart"
				+ "nerprove,memo,opendate,confirmdate,confirmman,confirmadvise,ip,port,loginname,lo"
				+ "ginpassword,isexcellent,isrecommended,statusid,createdate,description from topmc"
				+ "orporation where description=?";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, orgId);
		rs = stmt.executeQuery();
		if (rs.next()) {
			isExist = true;
			this.corporationid = rs.getLong("corporationid");
			parentid = rs.getLong("parentid");
			typeid = rs.getInt("typeid");
			corporationname = rs.getString("corporationname");
			corpnameshort = rs.getString("corpnameshort");
			corpnameen = rs.getString("corpnameen");
			corpman = rs.getString("corpman");
			corpmanemail = rs.getString("corpmanemail");
			corpmanmobiletel = rs.getString("corpmanmobiletel");
			corpmantel = rs.getString("corpmantel");
			corpmanfax = rs.getString("corpmanfax");
			postcode = rs.getString("postcode");
			corpaddr = rs.getString("corpaddr");
			corpurl = rs.getString("corpurl");
			corpship = rs.getString("corpship");
			corptype = rs.getString("corptype");
			corpgrade = rs.getInt("corpgrade");
			exchangetel = rs.getString("exchangetel");
			contactermobiletel = rs.getString("contactermobiletel");
			custservertel = rs.getString("custservertel");
			contacter = rs.getString("contacter");
			contacteremail = rs.getString("contacteremail");
			contactertel = rs.getString("contactertel");
			contacterfax = rs.getString("contacterfax");
			province = rs.getString("province");
			city = rs.getString("city");
			county = rs.getString("county");
			district = rs.getString("district");
			community = rs.getString("community");
			openbank = rs.getString("openbank");
			bankaccount = rs.getString("bankaccount");
			disciption = rs.getString("disciption");
			dealin = rs.getString("dealin");
			credit = rs.getInt("credit");
			regfund = rs.getLong("regfund");
			businessyear = rs.getString("businessyear");
			licenceno = rs.getString("licenceno");
			registerdate = rs.getTimestamp("registerdate");
			variation = rs.getString("variation");
			constitution = rs.getString("constitution");
			agreement = rs.getString("agreement");
			assetprove = rs.getString("assetprove");
			officeprove = rs.getString("officeprove");
			copartnerprove = rs.getString("copartnerprove");
			memo = rs.getString("memo");
			opendate = rs.getTimestamp("opendate");
			confirmdate = rs.getTimestamp("confirmdate");
			confirmman = rs.getString("confirmman");
			confirmadvise = rs.getString("confirmadvise");
			ip = rs.getString("ip");
			port = rs.getInt("port");
			loginname = rs.getString("loginname");
			loginpassword = rs.getString("loginpassword");
			isexcellent = rs.getInt("isexcellent");
			isrecommended = rs.getInt("isrecommended");
			statusid = rs.getInt("statusid");
			createdate = rs.getTimestamp("createdate");
			description = rs.getString("description");
		} else {
			isExist = false;
		}
		if (stmt != null) {
			stmt.close();
		}
		if (rs != null) {
			rs.close();
		}
	}

	public void create(Connection con, long corporationid) throws SQLException {
		PreparedStatement stmt;
		ResultSet rs;
		stmt = null;
		rs = null;

		String sql = "select corporationid,parentid,typeid,corporationname,corpnameshort,corpnameen,co"
				+ "rpman,corpmanemail,corpmanmobiletel,corpmantel,corpmanfax,postcode,corpaddr,corp"
				+ "url,corpship,corptype,corpgrade,exchangetel,contactermobiletel,custservertel,con"
				+ "tacter,contacteremail,contactertel,contacterfax,province,city,county,district,co"
				+ "mmunity,openbank,bankaccount,disciption,dealin,credit,regfund,businessyear,licen"
				+ "ceno,registerdate,variation,constitution,agreement,assetprove,officeprove,copart"
				+ "nerprove,memo,opendate,confirmdate,confirmman,confirmadvise,ip,port,loginname,lo"
				+ "ginpassword,isexcellent,isrecommended,statusid,createdate,description from topmc"
				+ "orporation where corporationid=?";
		stmt = con.prepareStatement(sql);
		stmt.setLong(1, corporationid);
		rs = stmt.executeQuery();
		if (rs.next()) {
			isExist = true;
			this.corporationid = rs.getLong("corporationid");
			parentid = rs.getLong("parentid");
			typeid = rs.getInt("typeid");
			corporationname = rs.getString("corporationname");
			corpnameshort = rs.getString("corpnameshort");
			corpnameen = rs.getString("corpnameen");
			corpman = rs.getString("corpman");
			corpmanemail = rs.getString("corpmanemail");
			corpmanmobiletel = rs.getString("corpmanmobiletel");
			corpmantel = rs.getString("corpmantel");
			corpmanfax = rs.getString("corpmanfax");
			postcode = rs.getString("postcode");
			corpaddr = rs.getString("corpaddr");
			corpurl = rs.getString("corpurl");
			corpship = rs.getString("corpship");
			corptype = rs.getString("corptype");
			corpgrade = rs.getInt("corpgrade");
			exchangetel = rs.getString("exchangetel");
			contactermobiletel = rs.getString("contactermobiletel");
			custservertel = rs.getString("custservertel");
			contacter = rs.getString("contacter");
			contacteremail = rs.getString("contacteremail");
			contactertel = rs.getString("contactertel");
			contacterfax = rs.getString("contacterfax");
			province = rs.getString("province");
			city = rs.getString("city");
			county = rs.getString("county");
			district = rs.getString("district");
			community = rs.getString("community");
			openbank = rs.getString("openbank");
			bankaccount = rs.getString("bankaccount");
			disciption = rs.getString("disciption");
			dealin = rs.getString("dealin");
			credit = rs.getInt("credit");
			regfund = rs.getLong("regfund");
			businessyear = rs.getString("businessyear");
			licenceno = rs.getString("licenceno");
			registerdate = rs.getTimestamp("registerdate");
			variation = rs.getString("variation");
			constitution = rs.getString("constitution");
			agreement = rs.getString("agreement");
			assetprove = rs.getString("assetprove");
			officeprove = rs.getString("officeprove");
			copartnerprove = rs.getString("copartnerprove");
			memo = rs.getString("memo");
			opendate = rs.getTimestamp("opendate");
			confirmdate = rs.getTimestamp("confirmdate");
			confirmman = rs.getString("confirmman");
			confirmadvise = rs.getString("confirmadvise");
			ip = rs.getString("ip");
			port = rs.getInt("port");
			loginname = rs.getString("loginname");
			loginpassword = rs.getString("loginpassword");
			isexcellent = rs.getInt("isexcellent");
			isrecommended = rs.getInt("isrecommended");
			statusid = rs.getInt("statusid");
			createdate = rs.getTimestamp("createdate");
			description = rs.getString("description");
		} else {
			isExist = false;
		}

		if (stmt != null) {
			stmt.close();
		}
		if (rs != null) {
			rs.close();
		}

	}

	public static int insert(Connection con, long corporationid, long parentid, int typeid, String corporationname,
			String corpnameshort, String corpnameen, String corpman, String corpmanemail, String corpmanmobiletel,
			String corpmantel, String corpmanfax, String postcode, String corpaddr, String corpurl, String corpship,
			String corptype, int corpgrade, String exchangetel, String contactermobiletel, String custservertel,
			String contacter, String contacteremail, String contactertel, String contacterfax, String province,
			String city, String county, String district, String community, String openbank, String bankaccount,
			String disciption, String dealin, int credit, long regfund, String businessyear, String licenceno,
			Timestamp registerdate, String variation, String constitution, String agreement, String assetprove,
			String officeprove, String copartnerprove, String memo, Timestamp opendate, Timestamp confirmdate,
			String confirmman, String confirmadvise, String ip, int port, String loginname, String loginpassword,
			int isexcellent, int isrecommended, int statusid, Timestamp createdate, String description)
			throws SQLException {
		PreparedStatement stmt = null;
		String sql = "";
		int cnt = 0;
		try {
			sql = "insert into topmcorporation (corporationid,parentid,typeid,corporationname,corpn"
					+ "ameshort,corpnameen,corpman,corpmanemail,corpmanmobiletel,corpmantel,corpmanfax,"
					+ "postcode,corpaddr,corpurl,corpship,corptype,corpgrade,exchangetel,contactermobil"
					+ "etel,custservertel,contacter,contacteremail,contactertel,contacterfax,province,c"
					+ "ity,county,district,community,openbank,bankaccount,disciption,dealin,credit,regf"
					+ "und,businessyear,licenceno,registerdate,variation,constitution,agreement,assetpr"
					+ "ove,officeprove,copartnerprove,memo,opendate,confirmdate,confirmman,confirmadvis"
					+ "e,ip,port,loginname,loginpassword,isexcellent,isrecommended,statusid,createdate,"
					+ "description) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
					+ ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, corporationid);
			stmt.setLong(2, parentid);
			stmt.setInt(3, typeid);
			stmt.setString(4, corporationname);
			stmt.setString(5, corpnameshort);
			stmt.setString(6, corpnameen);
			stmt.setString(7, corpman);
			stmt.setString(8, corpmanemail);
			stmt.setString(9, corpmanmobiletel);
			stmt.setString(10, corpmantel);
			stmt.setString(11, corpmanfax);
			stmt.setString(12, postcode);
			stmt.setString(13, corpaddr);
			stmt.setString(14, corpurl);
			stmt.setString(15, corpship);
			stmt.setString(16, corptype);
			stmt.setInt(17, corpgrade);
			stmt.setString(18, exchangetel);
			stmt.setString(19, contactermobiletel);
			stmt.setString(20, custservertel);
			stmt.setString(21, contacter);
			stmt.setString(22, contacteremail);
			stmt.setString(23, contactertel);
			stmt.setString(24, contacterfax);
			stmt.setString(25, province);
			stmt.setString(26, city);
			stmt.setString(27, county);
			stmt.setString(28, district);
			stmt.setString(29, community);
			stmt.setString(30, openbank);
			stmt.setString(31, bankaccount);
			stmt.setString(32, disciption);
			stmt.setString(33, dealin);
			stmt.setInt(34, credit);
			stmt.setLong(35, regfund);
			stmt.setString(36, businessyear);
			stmt.setString(37, licenceno);
			stmt.setTimestamp(38, registerdate);
			stmt.setString(39, variation);
			stmt.setString(40, constitution);
			stmt.setString(41, agreement);
			stmt.setString(42, assetprove);
			stmt.setString(43, officeprove);
			stmt.setString(44, copartnerprove);
			stmt.setString(45, memo);
			stmt.setTimestamp(46, opendate);
			stmt.setTimestamp(47, confirmdate);
			stmt.setString(48, confirmman);
			stmt.setString(49, confirmadvise);
			stmt.setString(50, ip);
			stmt.setInt(51, port);
			stmt.setString(52, loginname);
			stmt.setString(53, loginpassword);
			stmt.setInt(54, isexcellent);
			stmt.setInt(55, isrecommended);
			stmt.setInt(56, statusid);
			stmt.setTimestamp(57, createdate);
			stmt.setString(58, description);
			cnt = stmt.executeUpdate();
			stmt.close();
		} finally {
			if (stmt != null) {
				stmt = null;
			}
		}
		return cnt;
	}

	public int insert(Connection con) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "";
		int cnt = 0;
		try {
			sql = "insert into topmcorporation (corporationid,parentid,typeid,corporationname,corpn"
					+ "ameshort,corpnameen,corpman,corpmanemail,corpmanmobiletel,corpmantel,corpmanfax,"
					+ "postcode,corpaddr,corpurl,corpship,corptype,corpgrade,exchangetel,contactermobil"
					+ "etel,custservertel,contacter,contacteremail,contactertel,contacterfax,province,c"
					+ "ity,county,district,community,openbank,bankaccount,disciption,dealin,credit,regf"
					+ "und,businessyear,licenceno,registerdate,variation,constitution,agreement,assetpr"
					+ "ove,officeprove,copartnerprove,memo,opendate,confirmdate,confirmman,confirmadvis"
					+ "e,ip,port,loginname,loginpassword,isexcellent,isrecommended,statusid,createdate,"
					+ "description) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
					+ ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, corporationid);
			stmt.setLong(2, parentid);
			stmt.setInt(3, typeid);
			stmt.setString(4, corporationname);
			stmt.setString(5, corpnameshort);
			stmt.setString(6, corpnameen);
			stmt.setString(7, corpman);
			stmt.setString(8, corpmanemail);
			stmt.setString(9, corpmanmobiletel);
			stmt.setString(10, corpmantel);
			stmt.setString(11, corpmanfax);
			stmt.setString(12, postcode);
			stmt.setString(13, corpaddr);
			stmt.setString(14, corpurl);
			stmt.setString(15, corpship);
			stmt.setString(16, corptype);
			stmt.setInt(17, corpgrade);
			stmt.setString(18, exchangetel);
			stmt.setString(19, contactermobiletel);
			stmt.setString(20, custservertel);
			stmt.setString(21, contacter);
			stmt.setString(22, contacteremail);
			stmt.setString(23, contactertel);
			stmt.setString(24, contacterfax);
			stmt.setString(25, province);
			stmt.setString(26, city);
			stmt.setString(27, county);
			stmt.setString(28, district);
			stmt.setString(29, community);
			stmt.setString(30, openbank);
			stmt.setString(31, bankaccount);
			stmt.setString(32, disciption);
			stmt.setString(33, dealin);
			stmt.setInt(34, credit);
			stmt.setLong(35, regfund);
			stmt.setString(36, businessyear);
			stmt.setString(37, licenceno);
			stmt.setTimestamp(38, registerdate);
			stmt.setString(39, variation);
			stmt.setString(40, constitution);
			stmt.setString(41, agreement);
			stmt.setString(42, assetprove);
			stmt.setString(43, officeprove);
			stmt.setString(44, copartnerprove);
			stmt.setString(45, memo);
			stmt.setTimestamp(46, opendate);
			stmt.setTimestamp(47, confirmdate);
			stmt.setString(48, confirmman);
			stmt.setString(49, confirmadvise);
			stmt.setString(50, ip);
			stmt.setInt(51, port);
			stmt.setString(52, loginname);
			stmt.setString(53, loginpassword);
			stmt.setInt(54, isexcellent);
			stmt.setInt(55, isrecommended);
			stmt.setInt(56, statusid);
			stmt.setTimestamp(57, createdate);
			stmt.setString(58, description);
			cnt = stmt.executeUpdate();
			stmt.close();
		} finally {
			if (stmt != null) {
				stmt = null;
			}
		}
		return cnt;
	}

	// public static int update(Connection con, long corporationid, long
	// parentid, int typeid, String corporationname, String corpnameshort,
	// String corpnameen, String corpman, String corpmanemail, String
	// corpmanmobiletel, String corpmantel, String corpmanfax, String postcode,
	// String corpaddr, String corpurl, String corpship, String corptype, int
	// corpgrade, String exchangetel, String contactermobiletel,
	// String custservertel, String contacter, String contacteremail, String
	// contactertel, String contacterfax, String province, String city,
	// String county, String district, String community, String openbank, String
	// bankaccount, String disciption, String dealin,
	// int credit, long regfund, String businessyear, String licenceno,
	// Timestamp registerdate, String variation,
	// String constitution, String agreement, String assetprove, String
	// officeprove, String copartnerprove, String memo, Timestamp opendate,
	// Timestamp confirmdate, String confirmman, String confirmadvise, String
	// ip, int port, String loginname, String loginpassword,
	// int isexcellent, int isrecommended, int statusid, Timestamp createdate,
	// String description)
	// throws SQLException
	// {
	// PreparedStatement stmt = null;
	// String sql = "";
	// int cnt = 0;
	// try
	// {
	// sql = "update topmcorporation set
	// parentid=?,typeid=?,corporationname=?,corpnameshort=?" +
	// ",corpnameen=?,corpman=?,corpmanemail=?,corpmanmobiletel=?,corpmantel=?,corpmanfa"
	// +
	// "x=?,postcode=?,corpaddr=?,corpurl=?,corpship=?,corptype=?,corpgrade=?,exchangete"
	// +
	// "l=?,contactermobiletel=?,custservertel=?,contacter=?,contacteremail=?,contactert"
	// +
	// "el=?,contacterfax=?,province=?,city=?,county=?,district=?,community=?,openbank=?"
	// +
	// ",bankaccount=?,disciption=?,dealin=?,credit=?,regfund=?,businessyear=?,licenceno"
	// +
	// "=?,registerdate=?,variation=?,constitution=?,agreement=?,assetprove=?,officeprov"
	// +
	// "e=?,copartnerprove=?,memo=?,opendate=?,confirmdate=?,confirmman=?,confirmadvise="
	// +
	// "?,ip=?,port=?,loginname=?,loginpassword=?,isexcellent=?,isrecommended=?,statusid"
	// +
	// "=?,createdate=?,description=? where corporationid=?"
	// ;
	// stmt = con.prepareStatement(sql);
	// stmt.setLong(1, parentid);
	// stmt.setInt(2, typeid);
	// stmt.setString(3, corporationname);
	// stmt.setString(4, corpnameshort);
	// stmt.setString(5, corpnameen);
	// stmt.setString(6, corpman);
	// stmt.setString(7, corpmanemail);
	// stmt.setString(8, corpmanmobiletel);
	// stmt.setString(9, corpmantel);
	// stmt.setString(10, corpmanfax);
	// stmt.setString(11, postcode);
	// stmt.setString(12, corpaddr);
	// stmt.setString(13, corpurl);
	// stmt.setString(14, corpship);
	// stmt.setString(15, corptype);
	// stmt.setInt(16, corpgrade);
	// stmt.setString(17, exchangetel);
	// stmt.setString(18, contactermobiletel);
	// stmt.setString(19, custservertel);
	// stmt.setString(20, contacter);
	// stmt.setString(21, contacteremail);
	// stmt.setString(22, contactertel);
	// stmt.setString(23, contacterfax);
	// stmt.setString(24, province);
	// stmt.setString(25, city);
	// stmt.setString(26, county);
	// stmt.setString(27, district);
	// stmt.setString(28, community);
	// stmt.setString(29, openbank);
	// stmt.setString(30, bankaccount);
	// stmt.setString(31, disciption);
	// stmt.setString(32, dealin);
	// stmt.setInt(33, credit);
	// stmt.setLong(34, regfund);
	// stmt.setString(35, businessyear);
	// stmt.setString(36, licenceno);
	// stmt.setTimestamp(37, registerdate);
	// stmt.setString(38, variation);
	// stmt.setString(39, constitution);
	// stmt.setString(40, agreement);
	// stmt.setString(41, assetprove);
	// stmt.setString(42, officeprove);
	// stmt.setString(43, copartnerprove);
	// stmt.setString(44, memo);
	// stmt.setTimestamp(45, opendate);
	// stmt.setTimestamp(46, confirmdate);
	// stmt.setString(47, confirmman);
	// stmt.setString(48, confirmadvise);
	// stmt.setString(49, ip);
	// stmt.setInt(50, port);
	// stmt.setString(51, loginname);
	// stmt.setString(52, loginpassword);
	// stmt.setInt(53, isexcellent);
	// stmt.setInt(54, isrecommended);
	// stmt.setInt(55, statusid);
	// stmt.setTimestamp(56, createdate);
	// stmt.setString(57, description);
	// stmt.setLong(58, corporationid);
	// cnt = stmt.executeUpdate();
	// stmt.close();
	// }
	// finally
	// {
	// if(stmt != null)
	// {
	// stmt = null;
	// }
	// }
	// return cnt;
	// }

	public int update(Connection con) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "";
		int cnt = 0;
		try {
//			sql = "update topmcorporation set parentid=?,typeid=?,corporationname=?,corpnameshort=?"
//					+ ",corpnameen=?,corpman=?,corpmanemail=?,corpmanmobiletel=?,corpmantel=?,corpmanfa"
//					+ "x=?,postcode=?,corpaddr=?,corpurl=?,corpship=?,corptype=?,corpgrade=?,exchangete"
//					+ "l=?,contactermobiletel=?,custservertel=?,contacter=?,contacteremail=?,contactert"
//					+ "el=?,contacterfax=?,province=?,city=?,county=?,district=?,community=?,openbank=?"
//					+ ",bankaccount=?,disciption=?,dealin=?,credit=?,regfund=?,businessyear=?,licenceno"
//					+ "=?,registerdate=?,variation=?,constitution=?,agreement=?,assetprove=?,officeprov"
//					+ "e=?,copartnerprove=?,memo=?,opendate=?,confirmdate=?,confirmman=?,confirmadvise="
//					+ "?,ip=?,port=?,loginname=?,loginpassword=?,isexcellent=?,isrecommended=?,statusid"
//					+ "=?,createdate=?,description=? where corporationid=?";
			
			sql = "update topmcorporation set parentid=?,typeid=?,corporationname=?,corpnameshort=?"
				+ ",corpnameen=?,corpman=?,corpmanemail=?,corpmanmobiletel=?,corpmantel=?,corpmanfa"
				+ "x=?,postcode=?,corpaddr=?,corpurl=?,corpship=?,corptype=?,corpgrade=?,exchangete"
				+ "l=?,contactermobiletel=?,custservertel=?,contacter=?,contacteremail=?,contactert"
				+ "el=?,contacterfax=?,province=?,city=?,county=?,district=?,community=?,openbank=?"
				+ ",bankaccount=?,disciption=?,dealin=?,credit=?,regfund=?,businessyear=?,licenceno"
				+ "=?,registerdate=?,variation=?,constitution=?,agreement=?,assetprove=?,officeprov"
				+ "e=?,copartnerprove=?,memo=?,opendate=?,confirmdate=?,confirmman=?,confirmadvise="
				+ "?,ip=?,port=?,loginname=?,loginpassword=?,statusid"
				+ "=?,description=? where corporationid=?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, parentid);
			stmt.setInt(2, typeid);
			stmt.setString(3, corporationname);
			stmt.setString(4, corpnameshort);
			stmt.setString(5, corpnameen);
			stmt.setString(6, corpman);
			stmt.setString(7, corpmanemail);
			stmt.setString(8, corpmanmobiletel);
			stmt.setString(9, corpmantel);
			stmt.setString(10, corpmanfax);
			stmt.setString(11, postcode);
			stmt.setString(12, corpaddr);
			stmt.setString(13, corpurl);
			stmt.setString(14, corpship);
			stmt.setString(15, corptype);
			stmt.setInt(16, corpgrade);
			stmt.setString(17, exchangetel);
			stmt.setString(18, contactermobiletel);
			stmt.setString(19, custservertel);
			stmt.setString(20, contacter);
			stmt.setString(21, contacteremail);
			stmt.setString(22, contactertel);
			stmt.setString(23, contacterfax);
			stmt.setString(24, province);
			stmt.setString(25, city);
			stmt.setString(26, county);
			stmt.setString(27, district);
			stmt.setString(28, community);
			stmt.setString(29, openbank);
			stmt.setString(30, bankaccount);
			stmt.setString(31, disciption);
			stmt.setString(32, dealin);
			stmt.setInt(33, credit);
			stmt.setLong(34, regfund);
			stmt.setString(35, businessyear);
			stmt.setString(36, licenceno);
			stmt.setTimestamp(37, registerdate);
			stmt.setString(38, variation);
			stmt.setString(39, constitution);
			stmt.setString(40, agreement);
			stmt.setString(41, assetprove);
			stmt.setString(42, officeprove);
			stmt.setString(43, copartnerprove);
			stmt.setString(44, memo);
			stmt.setTimestamp(45, opendate);
			stmt.setTimestamp(46, confirmdate);
			stmt.setString(47, confirmman);
			stmt.setString(48, confirmadvise);
			stmt.setString(49, ip);
			stmt.setInt(50, port);
			stmt.setString(51, loginname);
			stmt.setString(52, loginpassword);
//			stmt.setInt(53, isexcellent);
//			stmt.setInt(54, isrecommended);
			stmt.setInt(53, statusid);
//			stmt.setTimestamp(56, createdate);
			stmt.setString(54, description);
			stmt.setLong(55, corporationid);
			cnt = stmt.executeUpdate();
			stmt.close();
		} finally {
			if (stmt != null) {
				stmt = null;
			}
		}
		return cnt;
	}

	public static int delete(Connection con, long corporationid) throws SQLException {
		java.sql.Statement stmt = null;
		
		
		try {
			String	sql = "delete from topmcorporation where corporationid="+corporationid;
			String sql1="delete from topmdepartment where departmentid="+corporationid;
			String sql2="delete from topmquarters  where quartersid="+corporationid;
			stmt = con.createStatement();
			stmt.addBatch(sql);
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			int cnt[] = stmt.executeBatch();
			
			stmt.close();
			if(cnt!=null){
				return cnt.length;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return 0;
	}

	// public static int delete(Connection con, String corporationid[])
	// throws SQLException
	// {
	// PreparedStatement stmt = null;
	// String sql = "";
	// int cnt = 0;
	// int i = 0;
	// try
	// {
	// sql = "delete from topmcorporation where corporationid in (";
	// for(i = 0; i < corporationid.length; i++)
	// {
	// if(i == 0)
	// {
	// sql = sql + corporationid[i];
	// } else
	// {
	// sql = sql + "," + corporationid[i];
	// }
	// }
	//
	// sql = sql + ")";
	// stmt = con.prepareStatement(sql);
	// cnt = stmt.executeUpdate();
	// stmt.close();
	// }
	// finally
	// {
	// if(stmt != null)
	// {
	// stmt.close();
	// }
	// }
	// return cnt;
	// }

	// public static String getCorporationid(Connection con)
	// throws SQLException
	// {
	// PreparedStatement stmt = null;
	// ResultSet rs = null;
	// String sql = "";
	// String str = "";
	// String str2 = "";
	// int i = 0;
	// int level = 0;
	// try
	// {
	// sql = "select corporationid,corporationname,parentid from topmcorporation
	// where statusi" +
	// "d=2 and parentid=0"
	// ;
	// stmt = con.prepareStatement(sql);
	// rs = stmt.executeQuery();
	// for(str = "<option value=\"0\">\u8BF7\u9009\u62E9</option>"; rs.next();
	// str = str + createChildTree1(con, level + 1,
	// rs.getLong("corporationid")))
	// {
	// level = 1;
	// str2 = "\u2520\uFF0D";
	// for(i = 0; i < level; i++)
	// {
	// str2 = "&nbsp;&nbsp;" + str2;
	// }
	//
	// str = str + "<option value=\"" + rs.getLong("corporationid") + "\">" +
	// str2 + rs.getString("corporationname") + "</option>";
	// }
	//
	// stmt.close();
	// }
	// finally
	// {
	// if(stmt != null)
	// {
	// stmt.close();
	// }
	// }
	// return str;
	// }

	// public static String createChildTree1(Connection con, int level, long
	// tmpcorporationid)
	// throws SQLException
	// {
	// PreparedStatement stmt = null;
	// ResultSet rs = null;
	// String sql = "";
	// String str = "";
	// String str2 = "";
	// int i = 0;
	// try
	// {
	// sql = "select corporationid,corporationname,parentid from topmcorporation
	// where statusi" +
	// "d=2 and parentid=?"
	// ;
	// stmt = con.prepareStatement(sql);
	// stmt.setLong(1, tmpcorporationid);
	// for(rs = stmt.executeQuery(); rs.next();)
	// {
	// str2 = "\u2520\uFF0D";
	// for(i = 0; i < level; i++)
	// {
	// str2 = "&nbsp;&nbsp;" + str2;
	// }
	//
	// str = str + "<option value=\"" + rs.getLong("corporationid") + "\">" +
	// str2 + rs.getString("corporationname") + "</option>";
	// str = str + createChildTree1(con, level + 1,
	// rs.getLong("corporationid"));
	// }
	//
	// }
	// finally
	// {
	// if(stmt != null)
	// {
	// stmt.close();
	// }
	// }
	// return str;
	// }
	//
	// public static String getCorporationid(Connection con, long corporationid)
	// throws SQLException
	// {
	// PreparedStatement stmt = null;
	// ResultSet rs = null;
	// String sql = "";
	// String str = "";
	// String str2 = "";
	// int i = 0;
	// int level = 0;
	// try
	// {
	// sql = "select corporationid,corporationname,parentid from topmcorporation
	// where statusi" +
	// "d=2 and parentid=0"
	// ;
	// stmt = con.prepareStatement(sql);
	// rs = stmt.executeQuery();
	// for(str = "<option value=\"0\">\u8BF7\u9009\u62E9</option>"; rs.next();
	// str = str + createChildTree2(con, level + 1, rs.getLong("corporationid"),
	// corporationid))
	// {
	// level = 1;
	// str2 = "\u2520\uFF0D";
	// for(i = 0; i < level; i++)
	// {
	// str2 = "&nbsp;&nbsp;" + str2;
	// }
	//
	// if(rs.getLong("corporationid") == corporationid)
	// {
	// str = str + "<option selected value=\"" + rs.getLong("corporationid") +
	// "\">" + str2 + rs.getString("corporationname") + "</option>";
	// } else
	// {
	// str = str + "<option value=\"" + rs.getLong("corporationid") + "\">" +
	// str2 + rs.getString("corporationname") + "</option>";
	// }
	// }
	//
	// stmt.close();
	// }
	// finally
	// {
	// if(stmt != null)
	// {
	// stmt.close();
	// }
	// }
	// return str;
	// }
	//
	// public static String createChildTree2(Connection con, int level, long
	// tmpcorporationid, long corporationid)
	// throws SQLException
	// {
	// PreparedStatement stmt = null;
	// ResultSet rs = null;
	// String sql = "";
	// String str = "";
	// String str2 = "";
	// int i = 0;
	// try
	// {
	// sql = "select corporationid,corporationname,parentid from topmcorporation
	// where statusi" +
	// "d=2 and parentid=?"
	// ;
	// stmt = con.prepareStatement(sql);
	// stmt.setLong(1, tmpcorporationid);
	// for(rs = stmt.executeQuery(); rs.next();)
	// {
	// str2 = "\u2520\uFF0D";
	// for(i = 0; i < level; i++)
	// {
	// str2 = "&nbsp;&nbsp;" + str2;
	// }
	//
	// if(rs.getLong("corporationid") == corporationid)
	// {
	// str = str + "<option selected value=\"" + rs.getLong("corporationid") +
	// "\">" + str2 + rs.getString("corporationname") + "</option>";
	// } else
	// {
	// str = str + "<option value=\"" + rs.getLong("corporationid") + "\">" +
	// str2 + rs.getString("corporationname") + "</option>";
	// }
	// str = str + createChildTree2(con, level + 1, rs.getLong("corporationid"),
	// corporationid);
	// }
	//
	// }
	// finally
	// {
	// if(stmt != null)
	// {
	// stmt.close();
	// }
	// }
	// return str;
	// }

	// public static String getCorporationname(Connection con, long
	// corporationid)
	// throws SQLException
	// {
	// PreparedStatement stmt = null;
	// ResultSet rs = null;
	// String sql = "";
	// String str = "";
	// int i = 0;
	// try
	// {
	// sql = "select corporationid,corporationname from topmcorporation where
	// statusid=2 and c" +
	// "orporationid=?"
	// ;
	// stmt = con.prepareStatement(sql);
	// stmt.setLong(1, corporationid);
	// rs = stmt.executeQuery();
	// str = "";
	// if(rs.next())
	// {
	// str = rs.getString("corporationname");
	// }
	// stmt.close();
	// }
	// finally
	// {
	// if(stmt != null)
	// {
	// stmt.close();
	// }
	// }
	// return str;
	// }

	// public static String getParentid(Connection con)
	// throws SQLException
	// {
	// PreparedStatement stmt = null;
	// ResultSet rs = null;
	// String sql = "";
	// String str = "";
	// String str2 = "";
	// int i = 0;
	// int level = 0;
	// try
	// {
	// sql = "select corporationid,corporationname,parentid from topmcorporation
	// where statusi" +
	// "d=2 and parentid=0"
	// ;
	// stmt = con.prepareStatement(sql);
	// rs = stmt.executeQuery();
	// for(str = "<option value=\"-1\">\u8BF7\u9009\u62E9</option>"; rs.next();
	// str = str + createChildTree3(con, level + 1,
	// rs.getLong("corporationid")))
	// {
	// level = 1;
	// str2 = "\u2520\uFF0D";
	// for(i = 0; i < level; i++)
	// {
	// str2 = "&nbsp;&nbsp;" + str2;
	// }
	//
	// str = str + "<option value=\"" + rs.getLong("corporationid") + "\">" +
	// str2 + rs.getString("corporationname") + "</option>";
	// }
	//
	// stmt.close();
	// }
	// finally
	// {
	// if(stmt != null)
	// {
	// stmt.close();
	// }
	// }
	// return str;
	// }

	// public static String createChildTree3(Connection con, int level, long
	// tmpcorporationid)
	// throws SQLException
	// {
	// PreparedStatement stmt = null;
	// ResultSet rs = null;
	// String sql = "";
	// String str = "";
	// String str2 = "";
	// int i = 0;
	// try
	// {
	// sql = "select corporationid,corporationname,parentid from topmcorporation
	// where statusi" +
	// "d=2 and parentid=?"
	// ;
	// stmt = con.prepareStatement(sql);
	// stmt.setLong(1, tmpcorporationid);
	// for(rs = stmt.executeQuery(); rs.next();)
	// {
	// str2 = "\u2520\uFF0D";
	// for(i = 0; i < level; i++)
	// {
	// str2 = "&nbsp;&nbsp;" + str2;
	// }
	//
	// str = str + "<option value=\"" + rs.getLong("corporationid") + "\">" +
	// str2 + rs.getString("corporationname") + "</option>";
	// str = str + createChildTree3(con, level + 1,
	// rs.getLong("corporationid"));
	// }
	//
	// }
	// finally
	// {
	// if(stmt != null)
	// {
	// stmt.close();
	// }
	// }
	// return str;
	// }

	// public static String getParentid(Connection con, long parentid, long
	// corporationid)
	// throws SQLException
	// {
	// PreparedStatement stmt = null;
	// ResultSet rs = null;
	// String sql = "";
	// String str = "";
	// String str2 = "";
	// int i = 0;
	// int level = 0;
	// try
	// {
	// sql = "select corporationid,corporationname,parentid from topmcorporation
	// where statusi" +
	// "d=2 and parentid=0"
	// ;
	// stmt = con.prepareStatement(sql);
	// rs = stmt.executeQuery();
	// str = "<option value=\"-1\">\u8BF7\u9009\u62E9</option>";
	// while(rs.next())
	// {
	// level = 1;
	// str2 = "\u2520\uFF0D";
	// for(i = 0; i < level; i++)
	// {
	// str2 = "&nbsp;&nbsp;" + str2;
	// }
	//
	// if(rs.getLong("corporationid") == parentid)
	// {
	// str = str + "<option selected value=\"" + rs.getLong("corporationid") +
	// "\">" + str2 + rs.getString("corporationname") + "</option>";
	// } else
	// if(rs.getLong("corporationid") != corporationid)
	// {
	// str = str + "<option value=\"" + rs.getLong("corporationid") + "\">" +
	// str2 + rs.getString("corporationname") + "</option>";
	// }
	// if(rs.getLong("corporationid") != corporationid)
	// {
	// str = str + createChildTree4(con, level + 1, rs.getLong("corporationid"),
	// parentid, corporationid);
	// }
	// }
	// stmt.close();
	// }
	// finally
	// {
	// if(stmt != null)
	// {
	// stmt.close();
	// }
	// }
	// return str;
	// }

	// public static String createChildTree4(Connection con, int level, long
	// tmpcorporationid, long parentid, long corporationid)
	// throws SQLException
	// {
	// PreparedStatement stmt = null;
	// ResultSet rs = null;
	// String sql = "";
	// String str = "";
	// String str2 = "";
	// int i = 0;
	// try
	// {
	// sql = "select corporationid,corporationname,parentid from topmcorporation
	// where statusi" +
	// "d=2 and parentid=?"
	// ;
	// stmt = con.prepareStatement(sql);
	// stmt.setLong(1, tmpcorporationid);
	// for(rs = stmt.executeQuery(); rs.next();)
	// {
	// str2 = "\u2520\uFF0D";
	// for(i = 0; i < level; i++)
	// {
	// str2 = "&nbsp;&nbsp;" + str2;
	// }
	//
	// if(rs.getLong("corporationid") == parentid)
	// {
	// str = str + "<option selected value=\"" + rs.getLong("corporationid") +
	// "\">" + str2 + rs.getString("corporationname") + "</option>";
	// } else
	// if(rs.getLong("corporationid") != corporationid)
	// {
	// str = str + "<option value=\"" + rs.getLong("corporationid") + "\">" +
	// str2 + rs.getString("corporationname") + "</option>";
	// }
	// if(rs.getLong("corporationid") != corporationid)
	// {
	// str = str + createChildTree4(con, level + 1, rs.getLong("corporationid"),
	// parentid, corporationid);
	// }
	// }
	//
	// }
	// finally
	// {
	// if(stmt != null)
	// {
	// stmt.close();
	// }
	// }
	// return str;
	// }

	public int getIsexcellent() {
		return isexcellent;
	}

	public void setIsexcellent(int isexcellent) {
		this.isexcellent = isexcellent;
	}

	public int getIsrecommended() {
		return isrecommended;
	}

	public void setIsrecommended(int isrecommended) {
		this.isrecommended = isrecommended;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

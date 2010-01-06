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
 * @author 华锋
 * Jul 8, 2009-4:53:57 PM
 *
 */
public class Trususer {
	 long userid;
	    long roleid=5;
	    long rankid=0;
	    long logoid=0;
	    long certificateid=0;
	    long provinceid=7;
	    long industryid=12;
	    long jobid=0;
	    long educationid=5;
	    long incomeid=0;
	    String rolename="后台添加操作员";
	    String rankname="请选择";
	    String logoname="请选择";
	    String provincename="广西";
	    String industryname="法律,财务,咨询";
	    String jobname="请选择";
	    String educationname="本科";
	    String incomename="请选择";
	    String corporationname;
	    String staffname;
	    String certificatename="请选择";
	    
	    String loginname;
	    String password;
	    String username;
	    int isregister=1;
	    String licenceno;
	    String licenceno2;
	    String filename;
	    String country="中国";
	    String nation;
	    String homeplace;
	    String nativeplace;
	    String residentplace;
	    String stature;
	    String avoirdupois;
	    String health;
	    int sex;
	    Timestamp birthday;
	    int isbirthday=1;
	    int married;
	    String certificatecode;
	    String email;
	    String mobileno;
	    int ismobileno=1;
	    String phoneno;
	    String city;
	    String county;
	    String district;
	    String community;
	    String postalcode;
	    String address;
	    int isaddress=1;
	    String qq;
	    String icq;
	    String msn;
	    String homepage;
	    String blog;
	    String bbs;
	    String companyname;
	    String quarters;
	    String responsibility;
	    int isunits=1;
	    String contacter;
	    String contacteremail;
	    String contacterfax;
	    String contactertel;
	    long servicedomain;
	    String domain;
	    String servicecontent;
	    String content;
	    long servicehour;
	    String hour;
	    long servicesite;
	    String site;
	    int isexperience;
	    String experience;
	    String jobyear;
	    String university;
	    String profession;
	    int politicsid;
	    int faithid;
	    String interest;
	    String speciality;
	    String memo;
	    Timestamp createdate;
	    int statusid;
	    int issecrecy;
	    long staffid;
	    long corporationid;
	    String message;
	    String description;
	    String nickname;
	    long award;
	    String signature;
	  
	  
	    private boolean isexist;
	    public boolean getIsexist(){
	    	return this.isexist;
	    }
	    
	    public void setUserid(long userid)
	    {
	        this.userid = userid;
	    }

	    public long getUserid()
	    {
	        return userid;
	    }

	    public void setRoleid(long roleid)
	    {
	        this.roleid = roleid;
	    }

	    public long getRoleid()
	    {
	        return roleid;
	    }

	    public void setRankid(long rankid)
	    {
	        this.rankid = rankid;
	    }

	    public long getRankid()
	    {
	        return rankid;
	    }

	    public void setLogoid(long logoid)
	    {
	        this.logoid = logoid;
	    }

	    public long getLogoid()
	    {
	        return logoid;
	    }

	    public void setCertificateid(long certificateid)
	    {
	        this.certificateid = certificateid;
	    }

	    public long getCertificateid()
	    {
	        return certificateid;
	    }

	    public void setProvinceid(long provinceid)
	    {
	        this.provinceid = provinceid;
	    }

	    public long getProvinceid()
	    {
	        return provinceid;
	    }

	    public void setIndustryid(long industryid)
	    {
	        this.industryid = industryid;
	    }

	    public long getIndustryid()
	    {
	        return industryid;
	    }

	    public void setJobid(long jobid)
	    {
	        this.jobid = jobid;
	    }

	    public long getJobid()
	    {
	        return jobid;
	    }

	    public void setEducationid(long educationid)
	    {
	        this.educationid = educationid;
	    }

	    public long getEducationid()
	    {
	        return educationid;
	    }

	    public void setIncomeid(long incomeid)
	    {
	        this.incomeid = incomeid;
	    }

	    public long getIncomeid()
	    {
	        return incomeid;
	    }

	    public void setLoginname(String loginname)
	    {
	        this.loginname = loginname;
	    }

	    public String getLoginname()
	    {
	        return loginname;
	    }

	    public void setPassword(String password)
	    {
	        this.password = password;
	    }

	    public String getPassword()
	    {
	        return password;
	    }

	    public void setUsername(String username)
	    {
	        this.username = username;
	    }

	    public String getUsername()
	    {
	        return username;
	    }

	    public void setIsregister(int isregister)
	    {
	        this.isregister = isregister;
	    }

	    public int getIsregister()
	    {
	        return isregister;
	    }

	    public void setLicenceno(String licenceno)
	    {
	        this.licenceno = licenceno;
	    }

	    public String getLicenceno()
	    {
	        return licenceno;
	    }

	    public void setLicenceno2(String licenceno2)
	    {
	        this.licenceno2 = licenceno2;
	    }

	    public String getLicenceno2()
	    {
	        return licenceno2;
	    }

	    public void setFilename(String filename)
	    {
	        this.filename = filename;
	    }

	    public String getFilename()
	    {
	        return filename;
	    }

	    public void setCountry(String country)
	    {
	        this.country = country;
	    }

	    public String getCountry()
	    {
	        return country;
	    }

	    public void setNation(String nation)
	    {
	        this.nation = nation;
	    }

	    public String getNation()
	    {
	        return nation;
	    }

	    public void setHomeplace(String homeplace)
	    {
	        this.homeplace = homeplace;
	    }

	    public String getHomeplace()
	    {
	        return homeplace;
	    }

	    public void setNativeplace(String nativeplace)
	    {
	        this.nativeplace = nativeplace;
	    }

	    public String getNativeplace()
	    {
	        return nativeplace;
	    }

	    public void setResidentplace(String residentplace)
	    {
	        this.residentplace = residentplace;
	    }

	    public String getResidentplace()
	    {
	        return residentplace;
	    }

	    public void setStature(String stature)
	    {
	        this.stature = stature;
	    }

	    public String getStature()
	    {
	        return stature;
	    }

	    public void setAvoirdupois(String avoirdupois)
	    {
	        this.avoirdupois = avoirdupois;
	    }

	    public String getAvoirdupois()
	    {
	        return avoirdupois;
	    }

	    public void setHealth(String health)
	    {
	        this.health = health;
	    }

	    public String getHealth()
	    {
	        return health;
	    }

	    public void setSex(int sex)
	    {
	        this.sex = sex;
	    }

	    public int getSex()
	    {
	        return sex;
	    }

	    public void setBirthday(Timestamp birthday)
	    {
	        this.birthday = birthday;
	    }

	    public Timestamp getBirthday()
	    {
	        return birthday;
	    }

	    public void setIsbirthday(int isbirthday)
	    {
	        this.isbirthday = isbirthday;
	    }

	    public int getIsbirthday()
	    {
	        return isbirthday;
	    }

	    public void setMarried(int married)
	    {
	        this.married = married;
	    }

	    public int getMarried()
	    {
	        return married;
	    }

	    public void setCertificatecode(String certificatecode)
	    {
	        this.certificatecode = certificatecode;
	    }

	    public String getCertificatecode()
	    {
	        return certificatecode;
	    }

	    public void setEmail(String email)
	    {
	        this.email = email;
	    }

	    public String getEmail()
	    {
	        return email;
	    }

	    public void setMobileno(String mobileno)
	    {
	        this.mobileno = mobileno;
	    }

	    public String getMobileno()
	    {
	        return mobileno;
	    }

	    public void setIsmobileno(int ismobileno)
	    {
	        this.ismobileno = ismobileno;
	    }

	    public int getIsmobileno()
	    {
	        return ismobileno;
	    }

	    public void setPhoneno(String phoneno)
	    {
	        this.phoneno = phoneno;
	    }

	    public String getPhoneno()
	    {
	        return phoneno;
	    }

	    public void setCity(String city)
	    {
	        this.city = city;
	    }

	    public String getCity()
	    {
	        return city;
	    }

	    public void setPostalcode(String postalcode)
	    {
	        this.postalcode = postalcode;
	    }

	    public String getPostalcode()
	    {
	        return postalcode;
	    }

	    public void setAddress(String address)
	    {
	        this.address = address;
	    }

	    public String getAddress()
	    {
	        return address;
	    }

	    public void setIsaddress(int isaddress)
	    {
	        this.isaddress = isaddress;
	    }

	    public int getIsaddress()
	    {
	        return isaddress;
	    }

	    public void setQq(String qq)
	    {
	        this.qq = qq;
	    }

	    public String getQq()
	    {
	        return qq;
	    }

	    public void setIcq(String icq)
	    {
	        this.icq = icq;
	    }

	    public String getIcq()
	    {
	        return icq;
	    }

	    public void setMsn(String msn)
	    {
	        this.msn = msn;
	    }

	    public String getMsn()
	    {
	        return msn;
	    }

	    public void setHomepage(String homepage)
	    {
	        this.homepage = homepage;
	    }

	    public String getHomepage()
	    {
	        return homepage;
	    }

	    public void setBlog(String blog)
	    {
	        this.blog = blog;
	    }

	    public String getBlog()
	    {
	        return blog;
	    }

	    public void setBbs(String bbs)
	    {
	        this.bbs = bbs;
	    }

	    public String getBbs()
	    {
	        return bbs;
	    }

	    public void setCompanyname(String companyname)
	    {
	        this.companyname = companyname;
	    }

	    public String getCompanyname()
	    {
	        return companyname;
	    }

	    public void setQuarters(String quarters)
	    {
	        this.quarters = quarters;
	    }

	    public String getQuarters()
	    {
	        return quarters;
	    }

	    public void setResponsibility(String responsibility)
	    {
	        this.responsibility = responsibility;
	    }

	    public String getResponsibility()
	    {
	        return responsibility;
	    }

	    public void setIsunits(int isunits)
	    {
	        this.isunits = isunits;
	    }

	    public int getIsunits()
	    {
	        return isunits;
	    }

	    public void setContacter(String contacter)
	    {
	        this.contacter = contacter;
	    }

	    public String getContacter()
	    {
	        return contacter;
	    }

	    public void setContacteremail(String contacteremail)
	    {
	        this.contacteremail = contacteremail;
	    }

	    public String getContacteremail()
	    {
	        return contacteremail;
	    }

	    public void setContacterfax(String contacterfax)
	    {
	        this.contacterfax = contacterfax;
	    }

	    public String getContacterfax()
	    {
	        return contacterfax;
	    }

	    public void setContactertel(String contactertel)
	    {
	        this.contactertel = contactertel;
	    }

	    public String getContactertel()
	    {
	        return contactertel;
	    }

	    public void setServicedomain(long servicedomain)
	    {
	        this.servicedomain = servicedomain;
	    }

	    public long getServicedomain()
	    {
	        return servicedomain;
	    }

	    public void setDomain(String domain)
	    {
	        this.domain = domain;
	    }

	    public String getDomain()
	    {
	        return domain;
	    }

	    public void setServicecontent(String servicecontent)
	    {
	        this.servicecontent = servicecontent;
	    }

	    public String getServicecontent()
	    {
	        return servicecontent;
	    }

	    public void setContent(String content)
	    {
	        this.content = content;
	    }

	    public String getContent()
	    {
	        return content;
	    }

	    public void setServicehour(long servicehour)
	    {
	        this.servicehour = servicehour;
	    }

	    public long getServicehour()
	    {
	        return servicehour;
	    }

	    public void setHour(String hour)
	    {
	        this.hour = hour;
	    }

	    public String getHour()
	    {
	        return hour;
	    }

	    public void setServicesite(long servicesite)
	    {
	        this.servicesite = servicesite;
	    }

	    public long getServicesite()
	    {
	        return servicesite;
	    }

	    public void setSite(String site)
	    {
	        this.site = site;
	    }

	    public String getSite()
	    {
	        return site;
	    }

	    public void setIsexperience(int isexperience)
	    {
	        this.isexperience = isexperience;
	    }

	    public int getIsexperience()
	    {
	        return isexperience;
	    }

	    public void setExperience(String experience)
	    {
	        this.experience = experience;
	    }

	    public String getExperience()
	    {
	        return experience;
	    }

	    public void setJobyear(String jobyear)
	    {
	        this.jobyear = jobyear;
	    }

	    public String getJobyear()
	    {
	        return jobyear;
	    }

	    public String getUniversity()
	    {
	        return university;
	    }

	    public void setUniversity(String university)
	    {
	        this.university = university;
	    }

	    public String getProfession()
	    {
	        return profession;
	    }

	    public void setProfession(String profession)
	    {
	        this.profession = profession;
	    }

	    public void setPoliticsid(int politicsid)
	    {
	        this.politicsid = politicsid;
	    }

	    public int getPoliticsid()
	    {
	        return politicsid;
	    }

	    public void setFaithid(int faithid)
	    {
	        this.faithid = faithid;
	    }

	    public int getFaithid()
	    {
	        return faithid;
	    }

	    public void setInterest(String interest)
	    {
	        this.interest = interest;
	    }

	    public String getInterest()
	    {
	        return interest;
	    }

	    public void setSpeciality(String speciality)
	    {
	        this.speciality = speciality;
	    }

	    public String getSpeciality()
	    {
	        return speciality;
	    }

	    public void setMemo(String memo)
	    {
	        this.memo = memo;
	    }

	    public String getMemo()
	    {
	        return memo;
	    }

	    public void setCreatedate(Timestamp createdate)
	    {
	        this.createdate = createdate;
	    }

	    public Timestamp getCreatedate()
	    {
	        return createdate;
	    }

	    public void setStatusid(int statusid)
	    {
	        this.statusid = statusid;
	    }

	    public int getStatusid()
	    {
	        return statusid;
	    }

	    public int getIssecrecy()
	    {
	        return issecrecy;
	    }

	    public void setIssecrecy(int issecrecy)
	    {
	        this.issecrecy = issecrecy;
	    }

	    public void setStaffid(long staffid)
	    {
	        this.staffid = staffid;
	    }

	    public long getStaffid()
	    {
	        return staffid;
	    }

	    public void setCorporationid(long corporationid)
	    {
	        this.corporationid = corporationid;
	    }

	    public long getCorporationid()
	    {
	        return corporationid;
	    }

	    public void setMessage(String message)
	    {
	        this.message = message;
	    }

	    public String getMessage()
	    {
	        return message;
	    }

	    public void setDescription(String description)
	    {
	        this.description = description;
	    }

	    public String getDescription()
	    {
	        return description;
	    }

	    public void setNickname(String nickname)
	    {
	        this.nickname = nickname;
	    }

	    public String getNickname()
	    {
	        return nickname;
	    }

	    public void setAward(long award)
	    {
	        this.award = award;
	    }

	    public long getAward()
	    {
	        return award;
	    }

	    public void setSignature(String signature)
	    {
	        this.signature = signature;
	    }

	    public String getSignature()
	    {
	        return signature;
	    }

	    public void setRolename(String rolename)
	    {
	        this.rolename = rolename;
	    }

	    public String getRolename()
	    {
	        return rolename;
	    }

	    public void setRankname(String rankname)
	    {
	        this.rankname = rankname;
	    }

	    public String getRankname()
	    {
	        return rankname;
	    }

	    public void setLogoname(String logoname)
	    {
	        this.logoname = logoname;
	    }

	    public String getLogoname()
	    {
	        return logoname;
	    }

	    public void setCertificatename(String certificatename)
	    {
	        this.certificatename = certificatename;
	    }

	    public String getCertificatename()
	    {
	        return certificatename;
	    }

	    public void setProvincename(String provincename)
	    {
	        this.provincename = provincename;
	    }

	    public String getProvincename()
	    {
	        return provincename;
	    }

	    public void setIndustryname(String industryname)
	    {
	        this.industryname = industryname;
	    }

	    public String getIndustryname()
	    {
	        return industryname;
	    }

	    public void setJobname(String jobname)
	    {
	        this.jobname = jobname;
	    }

	    public String getJobname()
	    {
	        return jobname;
	    }

	    public void setEducationname(String educationname)
	    {
	        this.educationname = educationname;
	    }

	    public String getEducationname()
	    {
	        return educationname;
	    }

	    public void setIncomename(String incomename)
	    {
	        this.incomename = incomename;
	    }

	    public String getIncomename()
	    {
	        return incomename;
	    }

	    public void create(Connection con, long userid)
	        throws SQLException
	    {
	    	
	        PreparedStatement stmt = null;
	        ResultSet   rs = null;
	       
	      
	    try{
	        String sql = "select userid,roleid,rankid,logoid,certificateid,provinceid,industryid,jobid,edu" +
	"cationid,incomeid,loginname,password,username,isregister,licenceno,licenceno2,fi" +
	"lename,country,nation,homeplace,nativeplace,residentplace,stature,avoirdupois,he" +
	"alth,sex,birthday,isbirthday,married,certificatecode,email,mobileno,ismobileno,p" +
	"honeno,city,county,district,community,postalcode,address,isaddress,qq,icq,msn,ho" +
	"mepage,blog,bbs,companyname,quarters,responsibility,isunits,contacter,contactere" +
	"mail,contacterfax,contactertel,servicedomain,domain,servicecontent,content,servi" +
	"cehour,hour,servicesite,site,isexperience,experience,jobyear,university,professi" +
	"on,politicsid,faithid,interest,speciality,memo,createdate,statusid,issecrecy,sta" +
	"ffid,corporationid,message,description,nickname,award,signature from trususer wh" +
	"ere userid=?"
	;
	        stmt = con.prepareStatement(sql);
	        stmt.setLong(1, userid);
	        rs = stmt.executeQuery();
	        if(rs.next())
	        {
	        	isexist=true;
	        
	            this.userid = rs.getLong("userid");
	            roleid = rs.getLong("roleid");
	            rankid = rs.getLong("rankid");
	            logoid = rs.getLong("logoid");
	            certificateid = rs.getLong("certificateid");
	            provinceid = rs.getLong("provinceid");
	            industryid = rs.getLong("industryid");
	            jobid = rs.getLong("jobid");
	            educationid = rs.getLong("educationid");
	            incomeid = rs.getLong("incomeid");
	            loginname = rs.getString("loginname");
	            password = rs.getString("password");
	            username = rs.getString("username");
	            isregister = rs.getInt("isregister");
	            licenceno = rs.getString("licenceno");
	            licenceno2 = rs.getString("licenceno2");
	            filename = rs.getString("filename");
	            country = rs.getString("country");
	            nation = rs.getString("nation");
	            homeplace = rs.getString("homeplace");
	            nativeplace = rs.getString("nativeplace");
	            residentplace = rs.getString("residentplace");
	            stature = rs.getString("stature");
	            avoirdupois = rs.getString("avoirdupois");
	            health = rs.getString("health");
	            sex = rs.getInt("sex");
	            birthday = rs.getTimestamp("birthday");
	            isbirthday = rs.getInt("isbirthday");
	            married = rs.getInt("married");
	            certificatecode = rs.getString("certificatecode");
	            email = rs.getString("email");
	            mobileno = rs.getString("mobileno");
	            ismobileno = rs.getInt("ismobileno");
	            phoneno = rs.getString("phoneno");
	            city = rs.getString("city");
	            county = rs.getString("county");
	            district = rs.getString("district");
	            community = rs.getString("community");
	            postalcode = rs.getString("postalcode");
	            address = rs.getString("address");
	            isaddress = rs.getInt("isaddress");
	            qq = rs.getString("qq");
	            icq = rs.getString("icq");
	            msn = rs.getString("msn");
	            homepage = rs.getString("homepage");
	            blog = rs.getString("blog");
	            bbs = rs.getString("bbs");
	            companyname = rs.getString("companyname");
	            quarters = rs.getString("quarters");
	            responsibility = rs.getString("responsibility");
	            isunits = rs.getInt("isunits");
	            contacter = rs.getString("contacter");
	            contacteremail = rs.getString("contacteremail");
	            contacterfax = rs.getString("contacterfax");
	            contactertel = rs.getString("contactertel");
	            servicedomain = rs.getLong("servicedomain");
	            domain = rs.getString("domain");
	            servicecontent = rs.getString("servicecontent");
	            content = rs.getString("content");
	            servicehour = rs.getLong("servicehour");
	            hour = rs.getString("hour");
	            servicesite = rs.getLong("servicesite");
	            site = rs.getString("site");
	            isexperience = rs.getInt("isexperience");
	            experience = rs.getString("experience");
	            jobyear = rs.getString("jobyear");
	            university = rs.getString("university");
	            profession = rs.getString("profession");
	            politicsid = rs.getInt("politicsid");
	            faithid = rs.getInt("faithid");
	            interest = rs.getString("interest");
	            speciality = rs.getString("speciality");
	            memo = rs.getString("memo");
	            createdate = rs.getTimestamp("createdate");
	            statusid = rs.getInt("statusid");
	            issecrecy = rs.getInt("issecrecy");
	            staffid = rs.getLong("staffid");
	            corporationid = rs.getLong("corporationid");
	            message = rs.getString("message");
	            description = rs.getString("description");
	            nickname = rs.getString("nickname");
	            award = rs.getLong("award");
	            signature = rs.getString("signature");
	        }else{
	        	isexist=false;
	        }
	    }
	      finally{
	        if(stmt != null)
	        {
	            stmt.close();
	        }
	        if(rs != null)
	        {
	            rs.close();
	        }
	      }
	     
	    }

	    public void create(Connection con, String description)
        throws SQLException
    {
    	
        PreparedStatement stmt = null;
        ResultSet   rs = null;
       
      
    try{
        String sql = "select userid,roleid,rankid,logoid,certificateid,provinceid,industryid,jobid,edu" +
"cationid,incomeid,loginname,password,username,isregister,licenceno,licenceno2,fi" +
"lename,country,nation,homeplace,nativeplace,residentplace,stature,avoirdupois,he" +
"alth,sex,birthday,isbirthday,married,certificatecode,email,mobileno,ismobileno,p" +
"honeno,city,county,district,community,postalcode,address,isaddress,qq,icq,msn,ho" +
"mepage,blog,bbs,companyname,quarters,responsibility,isunits,contacter,contactere" +
"mail,contacterfax,contactertel,servicedomain,domain,servicecontent,content,servi" +
"cehour,hour,servicesite,site,isexperience,experience,jobyear,university,professi" +
"on,politicsid,faithid,interest,speciality,memo,createdate,statusid,issecrecy,sta" +
"ffid,corporationid,message,description,nickname,award,signature from trususer wh" +
"ere description=?"
;
        stmt = con.prepareStatement(sql);
        stmt.setString(1, description);
        rs = stmt.executeQuery();
        if(rs.next())
        {
        	isexist=true;
        
            this.userid = rs.getLong("userid");
            roleid = rs.getLong("roleid");
            rankid = rs.getLong("rankid");
            logoid = rs.getLong("logoid");
            certificateid = rs.getLong("certificateid");
            provinceid = rs.getLong("provinceid");
            industryid = rs.getLong("industryid");
            jobid = rs.getLong("jobid");
            educationid = rs.getLong("educationid");
            incomeid = rs.getLong("incomeid");
            loginname = rs.getString("loginname");
            password = rs.getString("password");
            username = rs.getString("username");
            isregister = rs.getInt("isregister");
            licenceno = rs.getString("licenceno");
            licenceno2 = rs.getString("licenceno2");
            filename = rs.getString("filename");
            country = rs.getString("country");
            nation = rs.getString("nation");
            homeplace = rs.getString("homeplace");
            nativeplace = rs.getString("nativeplace");
            residentplace = rs.getString("residentplace");
            stature = rs.getString("stature");
            avoirdupois = rs.getString("avoirdupois");
            health = rs.getString("health");
            sex = rs.getInt("sex");
            birthday = rs.getTimestamp("birthday");
            isbirthday = rs.getInt("isbirthday");
            married = rs.getInt("married");
            certificatecode = rs.getString("certificatecode");
            email = rs.getString("email");
            mobileno = rs.getString("mobileno");
            ismobileno = rs.getInt("ismobileno");
            phoneno = rs.getString("phoneno");
            city = rs.getString("city");
            county = rs.getString("county");
            district = rs.getString("district");
            community = rs.getString("community");
            postalcode = rs.getString("postalcode");
            address = rs.getString("address");
            isaddress = rs.getInt("isaddress");
            qq = rs.getString("qq");
            icq = rs.getString("icq");
            msn = rs.getString("msn");
            homepage = rs.getString("homepage");
            blog = rs.getString("blog");
            bbs = rs.getString("bbs");
            companyname = rs.getString("companyname");
            quarters = rs.getString("quarters");
            responsibility = rs.getString("responsibility");
            isunits = rs.getInt("isunits");
            contacter = rs.getString("contacter");
            contacteremail = rs.getString("contacteremail");
            contacterfax = rs.getString("contacterfax");
            contactertel = rs.getString("contactertel");
            servicedomain = rs.getLong("servicedomain");
            domain = rs.getString("domain");
            servicecontent = rs.getString("servicecontent");
            content = rs.getString("content");
            servicehour = rs.getLong("servicehour");
            hour = rs.getString("hour");
            servicesite = rs.getLong("servicesite");
            site = rs.getString("site");
            isexperience = rs.getInt("isexperience");
            experience = rs.getString("experience");
            jobyear = rs.getString("jobyear");
            university = rs.getString("university");
            profession = rs.getString("profession");
            politicsid = rs.getInt("politicsid");
            faithid = rs.getInt("faithid");
            interest = rs.getString("interest");
            speciality = rs.getString("speciality");
            memo = rs.getString("memo");
            createdate = rs.getTimestamp("createdate");
            statusid = rs.getInt("statusid");
            issecrecy = rs.getInt("issecrecy");
            staffid = rs.getLong("staffid");
            corporationid = rs.getLong("corporationid");
            message = rs.getString("message");
            description = rs.getString("description");
            nickname = rs.getString("nickname");
            award = rs.getLong("award");
            signature = rs.getString("signature");
        }else{
        	isexist=false;
        }
    }
      finally{
        if(stmt != null)
        {
            stmt.close();
        }
        if(rs != null)
        {
            rs.close();
        }
      }
     
    }


	    public int insert(Connection con)
	        throws SQLException
	    {
	        PreparedStatement stmt = null;
	        String sql = "";
	        int cnt = 0;
	        try
	        {
	            sql = "insert into trususer (userid,roleid,rankid,logoid,certificateid,provinceid,indus" +
	"tryid,jobid,educationid,incomeid,loginname,password,username,isregister,licencen" +
	"o,licenceno2,filename,country,nation,homeplace,nativeplace,residentplace,stature" +
	",avoirdupois,health,sex,birthday,isbirthday,married,certificatecode,email,mobile" +
	"no,ismobileno,phoneno,city,county,district,community,postalcode,address,isaddres" +
	"s,qq,icq,msn,homepage,blog,bbs,companyname,quarters,responsibility,isunits,conta" +
	"cter,contacteremail,contacterfax,contactertel,servicedomain,domain,serviceconten" +
	"t,content,servicehour,hour,servicesite,site,isexperience,experience,jobyear,univ" +
	"ersity,profession,politicsid,faithid,interest,speciality,memo,createdate,statusi" +
	"d,issecrecy,staffid,corporationid,message,description,nickname,award,signature) " +
	"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
	"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
	"?,?,?,?,?,?,?)"
	;
	            stmt = con.prepareStatement(sql);
	            stmt.setLong(1, userid);
	            stmt.setLong(2, roleid);
	            stmt.setLong(3, rankid);
	            stmt.setLong(4, logoid);
	            stmt.setLong(5, certificateid);
	            stmt.setLong(6, provinceid);
	            stmt.setLong(7, industryid);
	            stmt.setLong(8, jobid);
	            stmt.setLong(9, educationid);
	            stmt.setLong(10, incomeid);
	            stmt.setString(11, loginname);
	            stmt.setString(12, password);
	            stmt.setString(13, username);
	            stmt.setInt(14, isregister);
	            stmt.setString(15, licenceno);
	            stmt.setString(16, licenceno2);
	            stmt.setString(17, filename);
	            stmt.setString(18, country);
	            stmt.setString(19, nation);
	            stmt.setString(20, homeplace);
	            stmt.setString(21, nativeplace);
	            stmt.setString(22, residentplace);
	            stmt.setString(23, stature);
	            stmt.setString(24, avoirdupois);
	            stmt.setString(25, health);
	            stmt.setInt(26, sex);
	            stmt.setTimestamp(27, birthday);
	            stmt.setInt(28, isbirthday);
	            stmt.setInt(29, married);
	            stmt.setString(30, certificatecode);
	            stmt.setString(31, email);
	            stmt.setString(32, mobileno);
	            stmt.setInt(33, ismobileno);
	            stmt.setString(34, phoneno);
	            stmt.setString(35, city);
	            stmt.setString(36, county);
	            stmt.setString(37, district);
	            stmt.setString(38, community);
	            stmt.setString(39, postalcode);
	            stmt.setString(40, address);
	            stmt.setInt(41, isaddress);
	            stmt.setString(42, qq);
	            stmt.setString(43, icq);
	            stmt.setString(44, msn);
	            stmt.setString(45, homepage);
	            stmt.setString(46, blog);
	            stmt.setString(47, bbs);
	            stmt.setString(48, companyname);
	            stmt.setString(49, quarters);
	            stmt.setString(50, responsibility);
	            stmt.setInt(51, isunits);
	            stmt.setString(52, contacter);
	            stmt.setString(53, contacteremail);
	            stmt.setString(54, contacterfax);
	            stmt.setString(55, contactertel);
	            stmt.setLong(56, servicedomain);
	            stmt.setString(57, domain);
	            stmt.setString(58, servicecontent);
	            stmt.setString(59, content);
	            stmt.setLong(60, servicehour);
	            stmt.setString(61, hour);
	            stmt.setLong(62, servicesite);
	            stmt.setString(63, site);
	            stmt.setInt(64, isexperience);
	            stmt.setString(65, experience);
	            stmt.setString(66, jobyear);
	            stmt.setString(67, university);
	            stmt.setString(68, profession);
	            stmt.setInt(69, politicsid);
	            stmt.setInt(70, faithid);
	            stmt.setString(71, interest);
	            stmt.setString(72, speciality);
	            stmt.setString(73, memo);
	            stmt.setTimestamp(74, createdate);
	            stmt.setInt(75, statusid);
	            stmt.setInt(76, issecrecy);
	            stmt.setLong(77, staffid);
	            stmt.setLong(78, corporationid);
	            stmt.setString(79, message);
	            stmt.setString(80, description);
	            stmt.setString(81, nickname);
	            stmt.setLong(82, award);
	            stmt.setString(83, signature);
	            cnt = stmt.executeUpdate();
	            stmt.close();
	        }
//	        catch(Exception e)
//	        {
//	            throw new SQLException(e);
//	        }
	        finally
	        {
	            if(stmt != null)
	            {
	                stmt = null;
	            }
	        }
	        return cnt;
	    }

	  

	    public int update(Connection con)
	        throws SQLException
	    {
	        PreparedStatement stmt = null;
	        String sql = "";
	        int cnt = 0;
	        try
	        {
//	            sql = "update trususer set roleid=?,rankid=?,logoid=?,certificateid=?,provinceid=?,indu" +
//	"stryid=?,jobid=?,educationid=?,incomeid=?,loginname=?,password=?,username=?,isre" +
//	"gister=?,licenceno=?,licenceno2=?,filename=?,country=?,nation=?,homeplace=?,nati" +
//	"veplace=?,residentplace=?,stature=?,avoirdupois=?,health=?,sex=?,birthday=?,isbi" +
//	"rthday=?,married=?,certificatecode=?,email=?,mobileno=?,ismobileno=?,phoneno=?,c" +
//	"ity=?,county=?,district=?,community=?,postalcode=?,address=?,isaddress=?,qq=?,ic" +
//	"q=?,msn=?,homepage=?,blog=?,bbs=?,companyname=?,quarters=?,responsibility=?,isun" +
//	"its=?,contacter=?,contacteremail=?,contacterfax=?,contactertel=?,servicedomain=?" +
//	",domain=?,servicecontent=?,content=?,servicehour=?,hour=?,servicesite=?,site=?,i" +
//	"sexperience=?,experience=?,jobyear=?,university=?,profession=?,politicsid=?,fait" +
//	"hid=?,interest=?,speciality=?,memo=?,createdate=?,statusid=?,issecrecy=?,staffid" +
//	"=?,corporationid=?,message=?,description=?,nickname=?,award=?,signature=? where " +
//	"userid=?";
	            sql = "update trususer set roleid=?,rankid=?,logoid=?,certificateid=?,provinceid=?,indu" +
	        	"stryid=?,jobid=?,educationid=?,incomeid=?,loginname=?,password=?,username=?,isre" +
	        	"gister=?,licenceno=?,licenceno2=?,filename=?,country=?,nation=?,homeplace=?,nati" +
	        	"veplace=?,residentplace=?,stature=?,avoirdupois=?,health=?,sex=?,birthday=?,isbi" +
	        	"rthday=?,married=?,certificatecode=?,email=?,mobileno=?,ismobileno=?,phoneno=?,c" +
	        	"ity=?,county=?,district=?,community=?,postalcode=?,address=?,isaddress=?,qq=?,ic" +
	        	"q=?,msn=?,homepage=?,blog=?,bbs=?,companyname=?,quarters=?,responsibility=?,isun" +
	        	"its=?,contacter=?,contacteremail=?,contacterfax=?,contactertel=?,servicedomain=?" +
	        	",domain=?,servicecontent=?,content=?,servicehour=?,hour=?,servicesite=?,site=?,i" +
	        	"sexperience=?,experience=?,jobyear=?,university=?,profession=?,politicsid=?,fait" +
	        	"hid=?,interest=?,speciality=?,memo=?,statusid=?,issecrecy=?,staffid" +
	        	"=?,corporationid=?,message=?,description=?,nickname=?,signature=? where " +
	        	"userid=?";
	            stmt = con.prepareStatement(sql);
	            stmt.setLong(1, roleid);
	            stmt.setLong(2, rankid);
	            stmt.setLong(3, logoid);
	            stmt.setLong(4, certificateid);
	            stmt.setLong(5, provinceid);
	            stmt.setLong(6, industryid);
	            stmt.setLong(7, jobid);
	            stmt.setLong(8, educationid);
	            stmt.setLong(9, incomeid);
	            stmt.setString(10, loginname);
	            stmt.setString(11, password);
	            stmt.setString(12, username);
	            stmt.setInt(13, isregister);
	            stmt.setString(14, licenceno);
	            stmt.setString(15, licenceno2);
	            stmt.setString(16, filename);
	            stmt.setString(17, country);
	            stmt.setString(18, nation);
	            stmt.setString(19, homeplace);
	            stmt.setString(20, nativeplace);
	            stmt.setString(21, residentplace);
	            stmt.setString(22, stature);
	            stmt.setString(23, avoirdupois);
	            stmt.setString(24, health);
	            stmt.setInt(25, sex);
	            stmt.setTimestamp(26, birthday);
	            stmt.setInt(27, isbirthday);
	            stmt.setInt(28, married);
	            stmt.setString(29, certificatecode);
	            stmt.setString(30, email);
	            stmt.setString(31, mobileno);
	            stmt.setInt(32, ismobileno);
	            stmt.setString(33, phoneno);
	            stmt.setString(34, city);
	            stmt.setString(35, county);
	            stmt.setString(36, district);
	            stmt.setString(37, community);
	            stmt.setString(38, postalcode);
	            stmt.setString(39, address);
	            stmt.setInt(40, isaddress);
	            stmt.setString(41, qq);
	            stmt.setString(42, icq);
	            stmt.setString(43, msn);
	            stmt.setString(44, homepage);
	            stmt.setString(45, blog);
	            stmt.setString(46, bbs);
	            stmt.setString(47, companyname);
	            stmt.setString(48, quarters);
	            stmt.setString(49, responsibility);
	            stmt.setInt(50, isunits);
	            stmt.setString(51, contacter);
	            stmt.setString(52, contacteremail);
	            stmt.setString(53, contacterfax);
	            stmt.setString(54, contactertel);
	            stmt.setLong(55, servicedomain);
	            stmt.setString(56, domain);
	            stmt.setString(57, servicecontent);
	            stmt.setString(58, content);
	            stmt.setLong(59, servicehour);
	            stmt.setString(60, hour);
	            stmt.setLong(61, servicesite);
	            stmt.setString(62, site);
	            stmt.setInt(63, isexperience);
	            stmt.setString(64, experience);
	            stmt.setString(65, jobyear);
	            stmt.setString(66, university);
	            stmt.setString(67, profession);
	            stmt.setInt(68, politicsid);
	            stmt.setInt(69, faithid);
	            stmt.setString(70, interest);
	            stmt.setString(71, speciality);
	            stmt.setString(72, memo);
//	            stmt.setTimestamp(73, createdate);
	            stmt.setInt(73, statusid);
	            stmt.setInt(74, issecrecy);
	            stmt.setLong(75, staffid);
	            stmt.setLong(76, corporationid);
	            stmt.setString(77, message);
	            stmt.setString(78, description);
	            stmt.setString(79, nickname);
//	            stmt.setLong(81, award);
	            stmt.setString(80, signature);
	            stmt.setLong(81, userid);
	            cnt = stmt.executeUpdate();
	            stmt.close();
	        }
	        finally
	        {
	            if(stmt != null)
	            {
	                stmt = null;
	            }
	        }
	        return cnt;
	    }

	    public static int delete(Connection con, long userid)
	        throws SQLException
	    {
	        PreparedStatement stmt = null;
	        String sql = "";
	        int cnt = 0;
	        try
	        {
	            sql = "delete from trususer where userid=?";
	            stmt = con.prepareStatement(sql);
	            stmt.setLong(1, userid);
	            cnt = stmt.executeUpdate();
	            stmt.close();
	        }
	        finally
	        {
	            if(stmt != null)
	            {
	                stmt.close();
	            }
	        }
	        return cnt;
	    }
	  

	    public String getCorporationname()
	    {
	        return corporationname;
	    }

	    public void setCorporationname(String corporationname)
	    {
	        this.corporationname = corporationname;
	    }

	    public String getStaffname()
	    {
	        return staffname;
	    }

	    public void setStaffname(String staffname)
	    {
	        this.staffname = staffname;
	    }

	    public String getCommunity()
	    {
	        return community;
	    }

	    public void setCommunity(String community)
	    {
	        this.community = community;
	    }

	    public String getCounty()
	    {
	        return county;
	    }

	    public void setCounty(String county)
	    {
	        this.county = county;
	    }

	    public String getDistrict()
	    {
	        return district;
	    }

	    public void setDistrict(String district)
	    {
	        this.district = district;
	    }
}

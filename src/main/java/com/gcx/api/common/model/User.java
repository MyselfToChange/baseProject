package com.gcx.api.common.model;

import com.gcx.api.common.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

public class User extends BaseEntity {
    private BigDecimal userId;//用户编号

    private String userName;//用户名(融云的Userid)

    private String password;//密码

    private String mobileNumber;//用户手机号码

    private String email;//邮箱

    private Short status;//用户状态 //0：正常  

    private Short accCate;//帐号类别 0 个人 1 企业  

    private BigDecimal relatedId;//关联id 根据acc_cate帐号类别关联个人/企业/员工表//0 个人id 1 企业id 2 员工id

    private Date registTime;//注册时间

    private Date lastLoginTime;//上次登录时间

    private Date modifyPwdTime;//最后修改密码时间

    private Integer loginNum;//登录次数

    private String source;//用户来源

    private String headPortraits;//头像图片地址

    private String registType;//1.手机 2.邮箱

    private Integer deleteFlag;//逻辑删除：0.有效1.删除

    private String userGroup;//成员类型
    
    private String realName;//昵称(融云的Username)

    private String z1;//单位名称

    private String z2;//成员级别

    private String z3;//实名认证1.未认证2.已认证

    private String z4; //用户真实姓名、公司名、协会名等

    private String z5;//职务

    private String z6;//常驻地址

    private String z7;//擅长领域

    private String z8;//关注重点

    private String z9;//现有项目数量

    private String z10;//电子名片 1.未生成2.已生成

    private String other;//融云TOKEN
    
    private String token; //融云TOKEN
    
    private String telName; //真实姓名
    
    
    
    /**
	 * @return the telName
	 */
	public String getTelName() {
		return telName;
	}

	/**
	 * @param telName the telName to set
	 */
	public void setTelName(String telName) {
		this.telName = telName;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	private static final long serialVersionUID = 1L;

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getAccCate() {
        return accCate;
    }

    public void setAccCate(Short accCate) {
        this.accCate = accCate;
    }

    public BigDecimal getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(BigDecimal relatedId) {
        this.relatedId = relatedId;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getModifyPwdTime() {
        return modifyPwdTime;
    }

    public void setModifyPwdTime(Date modifyPwdTime) {
        this.modifyPwdTime = modifyPwdTime;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getHeadPortraits() {
        return headPortraits;
    }

    public void setHeadPortraits(String headPortraits) {
        this.headPortraits = headPortraits == null ? null : headPortraits.trim();
    }

    public String getRegistType() {
        return registType;
    }

    public void setRegistType(String registType) {
        this.registType = registType == null ? null : registType.trim();
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup == null ? null : userGroup.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getZ1() {
        return z1;
    }

    public void setZ1(String z1) {
        this.z1 = z1 == null ? null : z1.trim();
    }

    public String getZ2() {
        return z2;
    }

    public void setZ2(String z2) {
        this.z2 = z2 == null ? null : z2.trim();
    }

    public String getZ3() {
        return z3;
    }

    public void setZ3(String z3) {
        this.z3 = z3 == null ? null : z3.trim();
    }

    public String getZ4() {
        return z4;
    }

    public void setZ4(String z4) {
        this.z4 = z4 == null ? null : z4.trim();
    }

    public String getZ5() {
        return z5;
    }

    public void setZ5(String z5) {
        this.z5 = z5 == null ? null : z5.trim();
    }

    public String getZ6() {
        return z6;
    }

    public void setZ6(String z6) {
        this.z6 = z6 == null ? null : z6.trim();
    }

    public String getZ7() {
        return z7;
    }

    public void setZ7(String z7) {
        this.z7 = z7 == null ? null : z7.trim();
    }

    public String getZ8() {
        return z8;
    }

    public void setZ8(String z8) {
        this.z8 = z8 == null ? null : z8.trim();
    }

    public String getZ9() {
        return z9;
    }

    public void setZ9(String z9) {
        this.z9 = z9 == null ? null : z9.trim();
    }

    public String getZ10() {
        return z10;
    }

    public void setZ10(String z10) {
        this.z10 = z10 == null ? null : z10.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }
}
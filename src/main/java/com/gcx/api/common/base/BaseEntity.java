package com.gcx.api.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;






import org.hibernate.validator.constraints.NotBlank;

import com.gcx.api.common.util.Page;
import com.gcx.api.common.validator.groups.MajorKey;
/**
 *<p>Title:BaseEntity</p>
 *<p>Description:公共Entity</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2017年9月28日</p>
 */
@ApiModel(value="BaseEntity对象",description="公共对象")
public class BaseEntity extends Page implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message="主键不能为空",groups=MajorKey.class)
	@ApiModelProperty(value="主键")
	private String tid;//主键
	@ApiModelProperty(value="创建时间")
    private String createTime;//创建时间
	@ApiModelProperty(value="创建人")
    private String createUser;//创建人
	@ApiModelProperty(value="更新时间")
    private String updateTime;//更新时间
	@ApiModelProperty(value="更新人")
    private String updateUser;//更新人
	@ApiModelProperty(value="审核状态(1未提交2审核中3通过4驳回)")
    private Integer auditStatus;//审核状态(1未提交2审核中3通过4驳回)
	@ApiModelProperty(value="驳回原因")
    private String rejectReson;//驳回原因
	@ApiModelProperty(value="备用1")
    private String t1;//备用1
	@ApiModelProperty(value="备用2")
    private String t2;//备用2
	@ApiModelProperty(value="备用3")
    private String t3;//备用3
	@ApiModelProperty(value="备用4")
    private String t4;//备用4
	@ApiModelProperty(value="备用5")
    private String t5;//备用5
	@ApiModelProperty(value="备用6")
    private Integer t6;//备用6
	@ApiModelProperty(value="备用7")
    private Integer t7;//备用7

	/**
	 * @return the tid
	 */
	public String getTid() {
		return tid;
	}

	/**
	 * @param tid the tid to set
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @return the t1
	 */
	public String getT1() {
		return t1;
	}

	/**
	 * @param t1 the t1 to set
	 */
	public void setT1(String t1) {
		this.t1 = t1;
	}

	/**
	 * @return the t2
	 */
	public String getT2() {
		return t2;
	}

	/**
	 * @param t2 the t2 to set
	 */
	public void setT2(String t2) {
		this.t2 = t2;
	}

	/**
	 * @return the t3
	 */
	public String getT3() {
		return t3;
	}

	/**
	 * @param t3 the t3 to set
	 */
	public void setT3(String t3) {
		this.t3 = t3;
	}

	/**
	 * @return the t4
	 */
	public String getT4() {
		return t4;
	}

	/**
	 * @param t4 the t4 to set
	 */
	public void setT4(String t4) {
		this.t4 = t4;
	}

	/**
	 * @return the t5
	 */
	public String getT5() {
		return t5;
	}

	/**
	 * @param t5 the t5 to set
	 */
	public void setT5(String t5) {
		this.t5 = t5;
	}

	/**
	 * @return the t6
	 */
	public Integer getT6() {
		return t6;
	}

	/**
	 * @param t6 the t6 to set
	 */
	public void setT6(Integer t6) {
		this.t6 = t6;
	}

	/**
	 * @return the t7
	 */
	public Integer getT7() {
		return t7;
	}

	/**
	 * @param t7 the t7 to set
	 */
	public void setT7(Integer t7) {
		this.t7 = t7;
	}

	/**
	 * @return the auditStatus
	 */
	public Integer getAuditStatus() {
		return auditStatus;
	}

	/**
	 * @param auditStatus the auditStatus to set
	 */
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * @return the rejectReson
	 */
	public String getRejectReson() {
		return rejectReson;
	}

	/**
	 * @param rejectReson the rejectReson to set
	 */
	public void setRejectReson(String rejectReson) {
		this.rejectReson = rejectReson;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    

    
    
    
    
    
}

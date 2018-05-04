package com.gcx.api.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.gcx.api.common.base.BaseEntity;
@ApiModel
public class UserGroup extends BaseEntity {
	@ApiModelProperty(value="群组ID")
    private String groupId;//群组ID
	@ApiModelProperty(value="用户ID")
    private String userId;//用户ID
	@ApiModelProperty(value="群名称")
    private String groupName;//群名称
    private String standbyOne;//备用1

    private String standbyTwo;//备用2

    private Integer standbyThere;//备用3

    private Integer standbyFour;//备用4

    private static final long serialVersionUID = 1L;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getStandbyOne() {
        return standbyOne;
    }

    public void setStandbyOne(String standbyOne) {
        this.standbyOne = standbyOne == null ? null : standbyOne.trim();
    }

    public String getStandbyTwo() {
        return standbyTwo;
    }

    public void setStandbyTwo(String standbyTwo) {
        this.standbyTwo = standbyTwo == null ? null : standbyTwo.trim();
    }

    public Integer getStandbyThere() {
        return standbyThere;
    }

    public void setStandbyThere(Integer standbyThere) {
        this.standbyThere = standbyThere;
    }

    public Integer getStandbyFour() {
        return standbyFour;
    }

    public void setStandbyFour(Integer standbyFour) {
        this.standbyFour = standbyFour;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupId=").append(groupId);
        sb.append(", userId=").append(userId);
        sb.append(", groupName=").append(groupName);
        sb.append(", standbyOne=").append(standbyOne);
        sb.append(", standbyTwo=").append(standbyTwo);
        sb.append(", standbyThere=").append(standbyThere);
        sb.append(", standbyFour=").append(standbyFour);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
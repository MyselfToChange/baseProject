package com.gcx.api.model;

import com.gcx.api.common.base.BaseEntity;

public class Flicker extends BaseEntity {
    private Integer classify;//分类(1表扬2激励)

    private String county;//区县

    private String villages;//乡镇

    private String organizationName;//机构名称

    private String departmentName;//部门名称

    private String incident;//事件

    private String cityCode;//城市类型 1济南

    private String createT;//创建时间

    private String createUid;//创建人ID

    private String updateT;//更新时间

    private String updateUid;//更新人ID

    private String publishUid;//发布人ID

    private String publishT;//发布时间

    private Integer publishStatus;//发布状态(1未发布2已发布)

    private Integer t6;

    private static final long serialVersionUID = 1L;

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getVillages() {
        return villages;
    }

    public void setVillages(String villages) {
        this.villages = villages == null ? null : villages.trim();
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName == null ? null : organizationName.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident == null ? null : incident.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCreateT() {
        return createT;
    }

    public void setCreateT(String createT) {
        this.createT = createT == null ? null : createT.trim();
    }

    public String getCreateUid() {
        return createUid;
    }

    public void setCreateUid(String createUid) {
        this.createUid = createUid == null ? null : createUid.trim();
    }

    public String getUpdateT() {
        return updateT;
    }

    public void setUpdateT(String updateT) {
        this.updateT = updateT == null ? null : updateT.trim();
    }

    public String getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(String updateUid) {
        this.updateUid = updateUid == null ? null : updateUid.trim();
    }

    public String getPublishUid() {
        return publishUid;
    }

    public void setPublishUid(String publishUid) {
        this.publishUid = publishUid == null ? null : publishUid.trim();
    }

    public String getPublishT() {
        return publishT;
    }

    public void setPublishT(String publishT) {
        this.publishT = publishT == null ? null : publishT.trim();
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getT6() {
        return t6;
    }

    public void setT6(Integer t6) {
        this.t6 = t6;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", classify=").append(classify);
        sb.append(", county=").append(county);
        sb.append(", villages=").append(villages);
        sb.append(", organizationName=").append(organizationName);
        sb.append(", departmentName=").append(departmentName);
        sb.append(", incident=").append(incident);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", createT=").append(createT);
        sb.append(", createUid=").append(createUid);
        sb.append(", updateT=").append(updateT);
        sb.append(", updateUid=").append(updateUid);
        sb.append(", publishUid=").append(publishUid);
        sb.append(", publishT=").append(publishT);
        sb.append(", publishStatus=").append(publishStatus);
        sb.append(", t6=").append(t6);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
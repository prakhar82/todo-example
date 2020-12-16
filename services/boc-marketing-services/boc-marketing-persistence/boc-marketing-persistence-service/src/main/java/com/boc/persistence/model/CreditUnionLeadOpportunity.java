package com.boc.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "BOC_CU_LEADS_OPPORTUNITIES")
public class CreditUnionLeadOpportunity {
    @Id
    @JsonProperty("Id")
    @Column(name = "LEADS_OPPORTUNITIES_ID")
    private String leadsOpportunitiesId;

    @Column(name = "CU_ID")
    private String creditUnionId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TYPE")
    private String type;

    @JsonProperty("bocforgeMemberNo__c")
    @Column(name = "BOCFORGE_MEMBER_NO")
    private String bocforgeMemberNumber;

    @JsonProperty("bocforgeFinancialAccountType__c")
    @Column(name = "BOCFORGE_FINANCIAL_ACCOUNT_TYPE")
    private String bocforgeFinancialAccountType;

    @JsonProperty("bocforgeLoHeaderText__c")
    @Column(name = "BOCFORGE_LO_HEADER_TEXT")
    private String bocforgeHeaderText;

    @JsonProperty("bocforgeMemberResponse__c")
    @Column(name = "BOCFORGE_MEMBER_RESPONSE")
    private String bocforgeMemberResponse;

    @JsonProperty("bocforgeMemberChoiceText__c")
    @Column(name = "BOCFORGE_MEMBER_CHOICE_TEXT")
    private String bocforgeMemberChoiceText;

    @JsonProperty("bocforgeMemberResponseDate__c")
    @Column(name = "BOCFORGE_MEMBER_RESPONSE_DATE")
    private Date bocforgeMemberResponseDate;

    @JsonProperty("bocforgeMemberResponseTime__c")
    @Column(name = "BOCFORGE_MEMBER_RESPONSE_TIME")
    private Date getBocforgeMemberResponseTime;

    @JsonProperty("bocforgePromotionDescription1__c")
    @Column(name = "BOCFORGE_PROMOTION_DESCRIPTION1")
    private String bocforgePromotionDescription1;

    @JsonProperty("bocforgePromotionDescription2__c")
    @Column(name = "BOCFORGE_PROMOTION_DESCRIPTION2")
    private String bocforgePromotionDescription2;

    @JsonProperty("bocforgePromotionDescription3__c")
    @Column(name = "BOCFORGE_PROMOTION_DESCRIPTION3")
    private String bocforgePromotionDescription3;

    @JsonProperty("bocforgePromotionDescription4__c")
    @Column(name = "BOCFORGE_PROMOTION_DESCRIPTION4")
    private String bocforgePromotionDescription4;

    @JsonProperty("bocforgePromotionDescription5__c")
    @Column(name = "BOCFORGE_PROMOTION_DESCRIPTION5")
    private String bocforgePromotionDescription5;

    @JsonProperty("bocforgePromotionDescription6__c")
    @Column(name = "BOCFORGE_PROMOTION_DESCRIPTION6")
    private String bocforgePromotionDescription6;

    @JsonProperty("bocforgeButtonCode__c")
    @Column(name = "BOCFORGE_BUTTON_CODE")
    private Integer bocforgeButtonCode;

    @JsonProperty("bocforgeChoiceCode__c")
    @Column(name = "BOCFORGE_CHOICE_CODE")
    private Integer bocforgeChoiceCode;

    @JsonProperty("bocforgeCRMPriority__c")
    @Column(name = "BOCFORGE_CRM_PRIORITY")
    private Integer bocforgeCRMPriority;

    @JsonProperty("bocforgePromotion1StartDate__c")
    @Column(name = "BOCFORGE_PROMOTION1_START_DATE")
    private Date bocforgePromotion1StartDate;

    @JsonProperty("bocforgePromotion2StartDate__c")
    @Column(name = "BOCFORGE_PROMOTION2_START_DATE")
    private Date bocforgePromotion2StartDate;

    @JsonProperty("bocforgePromotion3StartDate__c")
    @Column(name = "BOCFORGE_PROMOTION3_START_DATE")
    private Date bocforgePromotion3StartDate;

    @JsonProperty("bocforgePromotion4StartDate__c")
    @Column(name = "BOCFORGE_PROMOTION4_START_DATE")
    private Date bocforgePromotion4StartDate;

    @JsonProperty("bocforgePromotion5StartDate__c")
    @Column(name = "BOCFORGE_PROMOTION5_START_DATE")
    private Date bocforgePromotion5StartDate;

    @JsonProperty("bocforgePromotion6StartDate__c")
    @Column(name = "BOCFORGE_PROMOTION6_START_DATE")
    private Date bocforgePromotion6StartDate;

    @JsonProperty("bocforgePromotion1EndDate__c")
    @Column(name = "BOCFORGE_PROMOTION1_END_DATE")
    private Date bocforgePromotion1EndDate;

    @JsonProperty("bocforgePromotion2EndDate__c")
    @Column(name = "BOCFORGE_PROMOTION2_END_DATE")
    private Date bocforgePromotion2EndDate;

    @JsonProperty("bocforgePromotion3EndDate__c")
    @Column(name = "BOCFORGE_PROMOTION3_END_DATE")
    private Date bocforgePromotion3EndDate;

    @JsonProperty("bocforgePromotion4EndDate__c")
    @Column(name = "BOCFORGE_PROMOTION4_END_DATE")
    private Date bocforgePromotion4EndDate;

    @JsonProperty("bocforgePromotion5EndDate__c")
    @Column(name = "BOCFORGE_PROMOTION5_END_DATE")
    private Date bocforgePromotion5EndDate;

    @JsonProperty("bocforgePromotion6EndDate__c")
    @Column(name = "BOCFORGE_PROMOTION6_END_DATE")
    private Date bocforgePromotion6EndDate;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    public String getLeadsOpportunitiesId() {
        return leadsOpportunitiesId;
    }

    public void setLeadsOpportunitiesId(String leadsOpportunitiesId) {
        this.leadsOpportunitiesId = leadsOpportunitiesId;
    }

    public String getCreditUnionId() {
        return creditUnionId;
    }

    public void setCreditUnionId(String creditUnionId) {
        this.creditUnionId = creditUnionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBocforgeMemberNumber() {
        return bocforgeMemberNumber;
    }

    public void setBocforgeMemberNumber(String bocforgeMemberNumber) {
        this.bocforgeMemberNumber = bocforgeMemberNumber;
    }

    public String getBocforgeFinancialAccountType() {
        return bocforgeFinancialAccountType;
    }

    public void setBocforgeFinancialAccountType(String bocforgeFinancialAccountType) {
        this.bocforgeFinancialAccountType = bocforgeFinancialAccountType;
    }

    public String getBocforgeHeaderText() {
        return bocforgeHeaderText;
    }

    public void setBocforgeHeaderText(String bocforgeHeaderText) {
        this.bocforgeHeaderText = bocforgeHeaderText;
    }

    public String getBocforgeMemberResponse() {
        return bocforgeMemberResponse;
    }

    public void setBocforgeMemberResponse(String bocforgeMemberResponse) {
        this.bocforgeMemberResponse = bocforgeMemberResponse;
    }

    public String getBocforgeMemberChoiceText() {
        return bocforgeMemberChoiceText;
    }

    public void setBocforgeMemberChoiceText(String bocforgeMemberChoiceText) {
        this.bocforgeMemberChoiceText = bocforgeMemberChoiceText;
    }

    public Date getBocforgeMemberResponseDate() {
        return bocforgeMemberResponseDate;
    }

    public void setBocforgeMemberResponseDate(Date bocforgeMemberResponseDate) {
        this.bocforgeMemberResponseDate = bocforgeMemberResponseDate;
    }

    public Date getGetBocforgeMemberResponseTime() {
        return getBocforgeMemberResponseTime;
    }

    public void setGetBocforgeMemberResponseTime(Date getBocforgeMemberResponseTime) {
        this.getBocforgeMemberResponseTime = getBocforgeMemberResponseTime;
    }

    public String getBocforgePromotionDescription1() {
        return bocforgePromotionDescription1;
    }

    public void setBocforgePromotionDescription1(String bocforgePromotionDescription1) {
        this.bocforgePromotionDescription1 = bocforgePromotionDescription1;
    }

    public String getBocforgePromotionDescription2() {
        return bocforgePromotionDescription2;
    }

    public void setBocforgePromotionDescription2(String bocforgePromotionDescription2) {
        this.bocforgePromotionDescription2 = bocforgePromotionDescription2;
    }

    public String getBocforgePromotionDescription3() {
        return bocforgePromotionDescription3;
    }

    public void setBocforgePromotionDescription3(String bocforgePromotionDescription3) {
        this.bocforgePromotionDescription3 = bocforgePromotionDescription3;
    }

    public String getBocforgePromotionDescription4() {
        return bocforgePromotionDescription4;
    }

    public void setBocforgePromotionDescription4(String bocforgePromotionDescription4) {
        this.bocforgePromotionDescription4 = bocforgePromotionDescription4;
    }

    public String getBocforgePromotionDescription5() {
        return bocforgePromotionDescription5;
    }

    public void setBocforgePromotionDescription5(String bocforgePromotionDescription5) {
        this.bocforgePromotionDescription5 = bocforgePromotionDescription5;
    }

    public String getBocforgePromotionDescription6() {
        return bocforgePromotionDescription6;
    }

    public void setBocforgePromotionDescription6(String bocforgePromotionDescription6) {
        this.bocforgePromotionDescription6 = bocforgePromotionDescription6;
    }

    public Integer getBocforgeButtonCode() {
        return bocforgeButtonCode;
    }

    public void setBocforgeButtonCode(Integer bocforgeButtonCode) {
        this.bocforgeButtonCode = bocforgeButtonCode;
    }

    public Integer getBocforgeChoiceCode() {
        return bocforgeChoiceCode;
    }

    public void setBocforgeChoiceCode(Integer bocforgeChoiceCode) {
        this.bocforgeChoiceCode = bocforgeChoiceCode;
    }

    public Integer getBocforgeCRMPriority() {
        return bocforgeCRMPriority;
    }

    public void setBocforgeCRMPriority(Integer bocforgeCRMPriority) {
        this.bocforgeCRMPriority = bocforgeCRMPriority;
    }

    public Date getBocforgePromotion1StartDate() {
        return bocforgePromotion1StartDate;
    }

    public void setBocforgePromotion1StartDate(Date bocforgePromotion1StartDate) {
        this.bocforgePromotion1StartDate = bocforgePromotion1StartDate;
    }

    public Date getBocforgePromotion2StartDate() {
        return bocforgePromotion2StartDate;
    }

    public void setBocforgePromotion2StartDate(Date bocforgePromotion2StartDate) {
        this.bocforgePromotion2StartDate = bocforgePromotion2StartDate;
    }

    public Date getBocforgePromotion3StartDate() {
        return bocforgePromotion3StartDate;
    }

    public void setBocforgePromotion3StartDate(Date bocforgePromotion3StartDate) {
        this.bocforgePromotion3StartDate = bocforgePromotion3StartDate;
    }

    public Date getBocforgePromotion4StartDate() {
        return bocforgePromotion4StartDate;
    }

    public void setBocforgePromotion4StartDate(Date bocforgePromotion4StartDate) {
        this.bocforgePromotion4StartDate = bocforgePromotion4StartDate;
    }

    public Date getBocforgePromotion5StartDate() {
        return bocforgePromotion5StartDate;
    }

    public void setBocforgePromotion5StartDate(Date bocforgePromotion5StartDate) {
        this.bocforgePromotion5StartDate = bocforgePromotion5StartDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditUnionLeadOpportunity that = (CreditUnionLeadOpportunity) o;
        return leadsOpportunitiesId.equals(that.leadsOpportunitiesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leadsOpportunitiesId);
    }

    public Date getBocforgePromotion6StartDate() {
        return bocforgePromotion6StartDate;
    }

    public void setBocforgePromotion6StartDate(Date bocforgePromotion6StartDate) {
        this.bocforgePromotion6StartDate = bocforgePromotion6StartDate;
    }

    public Date getBocforgePromotion1EndDate() {
        return bocforgePromotion1EndDate;
    }

    public void setBocforgePromotion1EndDate(Date bocforgePromotion1EndDate) {
        this.bocforgePromotion1EndDate = bocforgePromotion1EndDate;
    }

    public Date getBocforgePromotion2EndDate() {
        return bocforgePromotion2EndDate;
    }

    public void setBocforgePromotion2EndDate(Date bocforgePromotion2EndDate) {
        this.bocforgePromotion2EndDate = bocforgePromotion2EndDate;
    }

    public Date getBocforgePromotion3EndDate() {
        return bocforgePromotion3EndDate;
    }

    public void setBocforgePromotion3EndDate(Date bocforgePromotion3EndDate) {
        this.bocforgePromotion3EndDate = bocforgePromotion3EndDate;
    }

    public Date getBocforgePromotion4EndDate() {
        return bocforgePromotion4EndDate;
    }

    public void setBocforgePromotion4EndDate(Date bocforgePromotion4EndDate) {
        this.bocforgePromotion4EndDate = bocforgePromotion4EndDate;
    }

    public Date getBocforgePromotion5EndDate() {
        return bocforgePromotion5EndDate;
    }

    public void setBocforgePromotion5EndDate(Date bocforgePromotion5EndDate) {
        this.bocforgePromotion5EndDate = bocforgePromotion5EndDate;
    }

    public Date getBocforgePromotion6EndDate() {
        return bocforgePromotion6EndDate;
    }

    public void setBocforgePromotion6EndDate(Date bocforgePromotion6EndDate) {
        this.bocforgePromotion6EndDate = bocforgePromotion6EndDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}

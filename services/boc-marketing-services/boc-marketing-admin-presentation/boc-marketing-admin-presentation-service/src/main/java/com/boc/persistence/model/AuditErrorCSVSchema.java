package com.boc.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "memberId",
        "notificationId",
        "operationResult",
        "createDate",
        "buttonCode",
        "buttonText",
        "choiceCode",
        "choiceText",
        "responseDate",
        "responseTime"
})
public class AuditErrorCSVSchema {

    @JsonProperty("memberId")
    private String memberId;

    @JsonProperty("notificationId")
    private String notificationId;

    @JsonProperty("operationResult")
    private String operationResult;

    @JsonProperty("createDate")
    private String createDate;

    @JsonProperty("buttonCode")
    private Integer buttonCode;

    @JsonProperty("buttonText")
    private String buttonText;

    @JsonProperty("choiceCode")
    private Integer choiceCode;

    @JsonProperty("choiceText")
    private String choiceText;

    @JsonProperty("responseDate")
    private String responseDate;

    @JsonProperty("responseTime")
    private String responseTime;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getButtonCode() {
        return buttonCode;
    }

    public void setButtonCode(Integer buttonCode) {
        this.buttonCode = buttonCode;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public Integer getChoiceCode() {
        return choiceCode;
    }

    public void setChoiceCode(Integer choiceCode) {
        this.choiceCode = choiceCode;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }
}

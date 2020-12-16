package com.boc.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BOC_CU_AUDIT_INFO")
public class CreditUnionAuditInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TXN_ID")
    private String txnId;

    @Column(name = "CU_ID")
    private String creditUnionId;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "NOTIFICATION_ID")
    private String notificationId;

    @Column(name = "CHANNEL")
    private String channel;

    @Column(name = "OPERATION")
    private String operation;

    @Column(name = "OPERATION_RESULT")
    private String operationResult;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "REQUEST")
    private String request;

    @Column(name = "BUTTON_CODE")
    private Integer buttonCode;

    @Column(name = "BUTTON_TEXT")
    private String buttonText;

    @Column(name = "CHOICE_CODE")
    private Integer choiceCode;

    @Column(name = "CHOICE_TEXT")
    private String choiceText;

    @Column(name = "RESPONSE_DATE")
    private String responseDate;

    @Column(name = "RESPONSE_TIME")
    private String responseTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getCreditUnionId() {
        return creditUnionId;
    }

    public void setCreditUnionId(String creditUnionId) {
        this.creditUnionId = creditUnionId;
    }

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
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

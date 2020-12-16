package com.boc.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonPropertyOrder({
        "id",
        "txnId",
        "memberId",
        "notificationId",
        "channel",
        "operation",
        "operationResult",
        "createDate",
        "request",
        "buttonCode",
        "buttonText",
        "choiceCode",
        "choiceText"
})
public class AuditAllCSVSchema extends AuditErrorCSVSchema{
    @JsonProperty("id")
    private String id;

    @JsonProperty("txnId")
    private String txnId;

    @JsonProperty("channel")
    private Date channel;

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("request")
    private String request;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public Date getChannel() {
        return channel;
    }

    public void setChannel(Date channel) {
        this.channel = channel;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}

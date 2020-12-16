package com.boc.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "BOC_CU_INFO")
public class CreditUnionInfo {
    @Id
    @Column(name = "CU_ID")
    private String creditUnionId;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    public String getSource() {
        return source;
    }

    public String getCreditUnionId() {
        return creditUnionId;
    }

    public void setCreditUnionId(String creditUnionId) {
        this.creditUnionId = creditUnionId;
    }

    public void setSource(String source) {
        this.source = source;
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

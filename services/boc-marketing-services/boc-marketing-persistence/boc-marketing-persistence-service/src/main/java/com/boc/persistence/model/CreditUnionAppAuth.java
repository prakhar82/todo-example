package com.boc.persistence.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "BOC_CU_APP_AUTH")
public class CreditUnionAppAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTH_ID")
    private int id;

    @Column(name = "CU_ID")
    private String creditUnionId;

    @Column(name = "HEADER")
    private String header;

    @Column(name = "KEY_STORE_PASS")
    private String keystorePass;

    @Column(name = "KEY_ALIAS")
    private String keyAlias;

    @Column(name = "KEY_PASS")
    private String keyPass;

    @Column(name = "CLIENT_ID")
    private String clientId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DOMAIN")
    private String domain;

    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    @Column(name = "TOKEN_EXPIRY_ON")
    private Date tokenExpiryOn;

    @Column(name = "URL")
    private String url;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "DATABASE_TYPE")
    private String databaseType;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreditUnionId() {
        return creditUnionId;
    }

    public void setCreditUnionId(String creditUnionId) {
        this.creditUnionId = creditUnionId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getKeystorePass() {
        return keystorePass;
    }

    public void setKeystorePass(String keyStorePass) {
        this.keystorePass = keyStorePass;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public String getKeyPass() {
        return keyPass;
    }

    public void setKeyPass(String keyPass) {
        this.keyPass = keyPass;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public Date getTokenExpiryOn() {
        return tokenExpiryOn;
    }

    public void setTokenExpiryOn(Date tokenExpiryOn) {
        this.tokenExpiryOn = tokenExpiryOn;
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

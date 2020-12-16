package com.boc.auth.constant;

public class Constants {

    private Constants(){}

    public static final String SUNSHINE_CLAIM_TEMPLATE = "'{'\"iss\": \"{0}\", \"sub\": \"{1}\", \"aud\": \"{2}\", \"exp\": \"{3}\"'}'";
    public static final String GNF_CLAIM_TEMPLATE = "'{'\"iss\": \"{0}\", \"sub\": \"{1}\", \"aud\": \"{2}\", \"exp\": \"{3}\"'}'";
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String KEY_STORE_TYPE = "JKS";
    public static final String GRANT_TYPE_KEY = "grant_type";
    public static final String GRANT_TYPE_VALUE = "urn:ietf:params:oauth:grant-type:jwt-bearer";
    public static final String ASSERTION = "assertion";
    public static final String SUNSHINE_CU_ID = "1";
    public static final String GNF_CU_ID = "2";
    public static final String DELIMITER = "/";
}

package com.boc.auth.token.helper;

import com.backbase.authentication.rest.spec.v1.authentication.BocCustomErrorException;
import com.boc.auth.constant.Constants;
import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;

public class TokenManagerHelper {

    private TokenManagerHelper(){}

    public static StringBuilder encodeAsBS6(StringBuilder token, String data) {

        if (data.isEmpty()){
            throw getCustomExceptionInstance();
        }
        token.append(Base64.encodeBase64URLSafeString(data.getBytes(StandardCharsets.UTF_8)));
        return token;
    }

    public static PrivateKey getPrivateKeyFromJKS(String pathToKeyStore, String keystorePass, String keyAlias, String keyPass) throws KeyStoreException, IOException,
            UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException {

        KeyStore keystore = KeyStore.getInstance(Constants.KEY_STORE_TYPE);
        keystore.load(new FileInputStream(pathToKeyStore), keystorePass.toCharArray());
        return (PrivateKey) keystore.getKey(keyAlias, keyPass.toCharArray());
    }

    public static String signHeaderDotClaims(PrivateKey privateKey, StringBuilder token) throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException {

        Signature signature = Signature.getInstance(Constants.SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(token.toString().getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64URLSafeString(signature.sign());
    }

    private static BocCustomErrorException getCustomExceptionInstance() {
        BocCustomErrorException bocCustomErrorException = new BocCustomErrorException();
        bocCustomErrorException.setMessage("Sorry something went wrong. Please try after some time");
        bocCustomErrorException.setCode("301");
        return bocCustomErrorException;
    }
}

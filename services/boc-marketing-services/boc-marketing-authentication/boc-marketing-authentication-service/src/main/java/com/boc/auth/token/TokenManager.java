package com.boc.auth.token;

import com.backbase.authentication.rest.spec.v1.authentication.AuthenticationPostRequestBody;
import com.boc.auth.constant.Constants;
import com.boc.auth.token.helper.TokenManagerHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateException;
import java.text.MessageFormat;

@Service
public class TokenManager {
    @Value("${jksFileBasePath}")
    String jksFileBasePath;
    @Value("${jksFileName}")
    String jksFileName;

    public String getSignedJWTToken(AuthenticationPostRequestBody authenticationPostRequestBody) throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, SignatureException, InvalidKeyException {
        StringBuilder token = new StringBuilder();
        String header = authenticationPostRequestBody.getHeader();
        String claimTemplate = Constants.SUNSHINE_CLAIM_TEMPLATE;
        String keystorePass = authenticationPostRequestBody.getKeystorePass();
        String keyAlias = authenticationPostRequestBody.getKeyAlias();
        String keyPass = authenticationPostRequestBody.getKeyPass();
        String creditUnionId = authenticationPostRequestBody.getCreditUnionId();

        String[] claimArray = new String[4];
        // Client ID
        claimArray[0] = authenticationPostRequestBody.getClientId();
        //
        claimArray[1] = authenticationPostRequestBody.getEmail();
        claimArray[2] = authenticationPostRequestBody.getDomain();
        claimArray[3] = Long.toString( ( System.currentTimeMillis()/1000 ) + 300);

        // Encode header with BS6
        token = TokenManagerHelper.encodeAsBS6(token, header);
        token.append(".");
        MessageFormat claim = new MessageFormat(claimTemplate);
        String payload = claim.format(claimArray);
        // Encode claims with BS6
        token = TokenManagerHelper.encodeAsBS6(token, payload);
        String pathToKeyStore =  Paths.get(jksFileBasePath,creditUnionId, jksFileName).toString();

        PrivateKey privateKey = TokenManagerHelper.getPrivateKeyFromJKS(pathToKeyStore, keystorePass, keyAlias, keyPass);
        String signedToken = TokenManagerHelper.signHeaderDotClaims(privateKey, token);
        token.append(".");
        token.append(signedToken);

        return token.toString();
    }
}

package com.boc.deposits;

import com.backbase.deposits.rest.spec.v1.deposits.Deposit;
import com.backbase.deposits.rest.spec.v1.deposits.DepositsApi;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.deposits.rest.spec.v1.deposits.DepositsGetResponseBody;
import com.boc.api.model.InlineResponse200DepositDetails;
import com.boc.api.spec.DepositDetailsApi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// making class as a RestController Spring Bean
@RequestMapping("/v1/deposits")
@RestController
public class DepositRestController implements DepositsApi {

    private final Logger logger = LoggerFactory.getLogger(DepositRestController.class);

    @Autowired
    DepositService accountService;
    DepositsGetResponseBody accountsGetResponseBody;
    @Autowired
    private final DepositDetailsApi accountDetailsApi;
    @Autowired
    public DepositRestController(DepositDetailsApi accountDetailsApi) {
        this.accountDetailsApi = accountDetailsApi;
    }

    public DepositsGetResponseBody getDeposits(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userName=userDetails.getUsername();
            return new DepositsGetResponseBody().withDeposits(parseDepositJson(userName));
        } catch (Exception e) {
            logger.info("Error trying to get the locations", e);
        }
        return null;
    }

    private List<Deposit> parseDepositJson(String userid) throws IOException {
        String json = executeRequest(userid);
        ArrayList<InlineResponse200DepositDetails> deposits = new ObjectMapper().readValue(json, new TypeReference<ArrayList<InlineResponse200DepositDetails>>() {});
        return transformJsonToDeposit(deposits);
    }

    private String executeRequest(String userid) throws IOException {
        try {

            String url = accountDetailsApi.getApiClient().getBasePath() + "/" + userid;
            return new RestTemplate().getForEntity(url, String.class).getBody();
        } catch (Exception e) {
            logger.info("Looks like the server is down, getting the JSON from the file");
            return new String(Files.readAllBytes(Paths.get("../extras/atms.json")));
        }
    }

    private List<Deposit> transformJsonToDeposit(List<InlineResponse200DepositDetails> deposits) {
        return deposits.stream().map(DepositTransformer::transformDepositDetailToDeposit).collect(Collectors.toList());
    }
}
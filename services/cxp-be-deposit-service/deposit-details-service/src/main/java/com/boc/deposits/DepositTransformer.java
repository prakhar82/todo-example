package com.boc.deposits;

import com.backbase.deposits.rest.spec.v1.deposits.Deposit;
import com.backbase.deposits.rest.spec.v1.deposits.DepositAmt;
import com.backbase.deposits.rest.spec.v1.deposits.MaturityAmt;
import com.backbase.deposits.rest.spec.v1.deposits.InterestRate;
import com.backbase.deposits.rest.spec.v1.deposits.NewMaturityAmt;
import com.backbase.deposits.rest.spec.v1.deposits.AccountRouting;
import com.backbase.deposits.rest.spec.v1.deposits.Details;
import com.boc.api.model.InlineResponse200DepositDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DepositTransformer {

    /**
     * Transform an ATM object into a Location object
     * @param deposits the deposit object from the  Bank API
     * @return the transformed object
     */
    public static Deposit transformDepositDetailToDeposit(InlineResponse200DepositDetails deposits) {

        Deposit deposit = new Deposit();

        deposit.setAccountNumber(deposits.getAccountNumber());
        deposit.setTypeOfDeposit(deposits.getTypeOfDeposit());
        deposit.setCategoryId(deposits.getCategoryId());
        deposit.setCategoryName(deposits.getCategoryName());
        deposit.setHolder(deposits.getHolder());

        if (deposits.getDepositAmt() != null) {
            DepositAmt depositAmt = new DepositAmt();
            depositAmt.setAmount(deposits.getDepositAmt().getAmount());
            depositAmt.setCurrency(deposits.getDepositAmt().getCurrency());
            deposit.setDepositAmt(depositAmt);
        }

        if (deposits.getMaturityAmt() != null) {
            MaturityAmt maturityAmt = new MaturityAmt();
            maturityAmt.setAmount(deposits.getMaturityAmt().getAmount());
            maturityAmt.setCurrency(deposits.getMaturityAmt().getCurrency());
            deposit.setMaturityAmt(maturityAmt);
        }

        if (deposits.getInterestRate() != null) {
            InterestRate interestRate = new InterestRate();
            interestRate.setRate(deposits.getInterestRate().getRate());
            deposit.setInterestRate(interestRate);
        }

        if (deposits.getNewMaturityAmt() != null) {
            NewMaturityAmt newmaturityAmt = new NewMaturityAmt();
            newmaturityAmt.setAmount(deposits.getNewMaturityAmt().getAmount());
            newmaturityAmt.setCurrency(deposits.getNewMaturityAmt().getCurrency());
            deposit.setNewMaturityAmt(newmaturityAmt);
        }

        deposit.setDepositPeriod(deposits.getDepositPeriod());
        deposit.setRenewalDate(deposits.getRenewalDate());
        deposit.setOpenedDate(deposits.getOpenedDate());
        deposit.setProductId(deposits.getProductId());

        if (deposits.getAccountRouting() != null) {
            AccountRouting accountRouting = new AccountRouting();
            accountRouting.setScheme(deposits.getAccountRouting().getScheme());
            deposit.setAccountRouting(accountRouting);
        }

        if (deposits.getDetails() != null) {
            Details details = new Details();
            details.setValue(deposits.getDetails().getValue());
            details.setLabel(deposits.getDetails().getLabel());
            deposit.setDetails(details);
        }

        deposit.setDepositRenewEnable(deposits.getDepositRenewEnable());

        deposit.setDepositSavedFlag(checkSavedDeposit(deposits.getAccountNumber()));

        return deposit;
    }

    public static String checkSavedDeposit(String accountNumber) {

        //StringBuffer result = new StringBuffer();
        String result = "";
        try {
            String url = "http://localhost:6516/boc/deposits/checkSavedDeposit?accountNumber=" + accountNumber;
            result = new RestTemplate().getForEntity(url, String.class).getBody();

            System.out.println("result.toString():::::::::::::::::: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
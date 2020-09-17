package com.boc.deposits;

import com.backbase.deposits.rest.spec.v1.deposits.Deposit;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.*;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepositService {

    private List<Deposit> readFromFile(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new ClassPathResource(fileName).getFile(), new TypeReference<ArrayList<Deposit>>() { });
    }

    public List<Deposit> readFromBody(String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body,new TypeReference<ArrayList<Deposit>>() { });
    }

    public List<Deposit> getDeposit()throws IOException{
        return readFromFile("Deposit.JSON");
    }
}

package com.econetwireless.epay.business.utils;

import com.econetwireless.epay.api.config.EpayApiWebConfig;
import com.econetwireless.epay.business.config.RootConfig;
import com.econetwireless.in.webservice.BalanceResponse;
import com.econetwireless.in.webservice.CreditRequest;
import com.econetwireless.in.webservice.CreditResponse;
import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.messages.AirtimeTopupRequest;
import com.econetwireless.utils.pojo.INBalanceResponse;
import com.econetwireless.utils.pojo.INCreditRequest;
import com.econetwireless.utils.pojo.INCreditResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {EpayApiWebConfig.class})
})
public class MessageConvertersTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    public String partnerCode;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        partnerCode = "hot-recharge";
    }

    @Test
    public void testConvert() {


            INCreditRequest inCreditRequest = new INCreditRequest(
                    partnerCode,
                    "773303584",
                    2.73,
                    "TOPUP-REF-0123\""
            );

            assertEquals(inCreditRequest.getMsisdn(), MessageConverters.convert(inCreditRequest).getMsisdn());
            assertEquals(inCreditRequest.getAmount(), MessageConverters.convert(inCreditRequest).getAmount());
            assertEquals(inCreditRequest.getPartnerCode(), MessageConverters.convert(inCreditRequest).getPartnerCode());
            assertEquals(inCreditRequest.getReferenceNumber(), MessageConverters.convert(inCreditRequest).getReferenceNumber());

        /******************************/
        CreditRequest creditRequest = new CreditRequest();

        creditRequest.setPartnerCode(partnerCode);
        creditRequest.setMsisdn("773303584");
        creditRequest.setAmount(2.73);
        creditRequest.setReferenceNumber("TOPUP-REF-0123\"");


        assertEquals(creditRequest.getMsisdn(), MessageConverters.convert(creditRequest).getMsisdn());
        assertEquals(creditRequest.getAmount(), MessageConverters.convert(creditRequest).getAmount());
        assertEquals(creditRequest.getPartnerCode(), MessageConverters.convert(creditRequest).getPartnerCode());
        assertEquals(creditRequest.getReferenceNumber(), MessageConverters.convert(creditRequest).getReferenceNumber());

        /******************************/

        INCreditResponse inCreditResponse = new INCreditResponse();

        inCreditResponse.setResponseCode("200");
        inCreditResponse.setNarrative("No Narative");
        inCreditResponse.setMsisdn("773303584");
        inCreditResponse.setBalance(5);

        assertEquals(inCreditResponse.getMsisdn(), MessageConverters.convert(inCreditResponse).getMsisdn());
        assertEquals(inCreditResponse.getResponseCode(), MessageConverters.convert(inCreditResponse).getResponseCode());
        assertEquals(inCreditResponse.getMsisdn(), MessageConverters.convert(inCreditResponse).getMsisdn());
        assertEquals(inCreditResponse.getBalance(), MessageConverters.convert(inCreditResponse).getBalance());

        /******************************/

        CreditResponse creditResponse = new CreditResponse();
        creditResponse.setResponseCode("200");
        creditResponse.setNarrative("No Narative");
        creditResponse.setMsisdn("773303584");
        creditResponse.setBalance(5);

        assertEquals(creditResponse.getMsisdn(), MessageConverters.convert(creditResponse).getMsisdn());
        assertEquals(creditResponse.getResponseCode(), MessageConverters.convert(creditResponse).getResponseCode());
        assertEquals(creditResponse.getMsisdn(), MessageConverters.convert(creditResponse).getMsisdn());
        assertEquals(creditResponse.getBalance(), MessageConverters.convert(creditResponse).getBalance());

        /******************************/


        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setResponseCode("200");
        balanceResponse.setNarrative("No Narative");
        balanceResponse.setMsisdn("773303584");
        balanceResponse.setAmount(5);

        assertEquals(balanceResponse.getMsisdn(), MessageConverters.convert(balanceResponse).getMsisdn());
        assertEquals(balanceResponse.getResponseCode(), MessageConverters.convert(balanceResponse).getResponseCode());
        assertEquals(balanceResponse.getMsisdn(), MessageConverters.convert(balanceResponse).getMsisdn());
        assertEquals(balanceResponse.getAmount(), MessageConverters.convert(balanceResponse).getAmount());


        /******************************/


        INBalanceResponse inBalanceResponse = new INBalanceResponse();
        inBalanceResponse.setResponseCode("200");
        inBalanceResponse.setNarrative("No Narative");
        inBalanceResponse.setMsisdn("773303584");
        inBalanceResponse.setAmount(5);

        assertEquals(inBalanceResponse.getMsisdn(), MessageConverters.convert(inBalanceResponse).getMsisdn());
        assertEquals(inBalanceResponse.getResponseCode(), MessageConverters.convert(inBalanceResponse).getResponseCode());
        assertEquals(inBalanceResponse.getMsisdn(), MessageConverters.convert(inBalanceResponse).getMsisdn());
        assertEquals(inBalanceResponse.getAmount(), MessageConverters.convert(inBalanceResponse).getAmount());







    }

/*
    @Test
    public void testConvert() {


        INCreditRequest inCreditRequest = new INCreditRequest(
                partnerCode,
                "773303584",
                2.73,
                "TOPUP-REF-0123\""
        );

        assertEquals(inCreditRequest.getMsisdn(), MessageConverters.convert(inCreditRequest).getMsisdn());
        assertEquals(inCreditRequest.getAmount(), MessageConverters.convert(inCreditRequest).getAmount());
        assertEquals(inCreditRequest.getPartnerCode(), MessageConverters.convert(inCreditRequest).getPartnerCode());
        assertEquals(inCreditRequest.getReferenceNumber(), MessageConverters.convert(inCreditRequest).getReferenceNumber());


    }


    @Test
    public  void testConvert1() {

        CreditRequest creditRequest = new CreditRequest();

        creditRequest.setPartnerCode(partnerCode);
        creditRequest.setMsisdn("773303584");
        creditRequest.setAmount(2.73);
        creditRequest.setReferenceNumber("TOPUP-REF-0123\"");


        assertEquals(creditRequest.getMsisdn(), MessageConverters.convert(creditRequest).getMsisdn());
        assertEquals(creditRequest.getAmount(), MessageConverters.convert(creditRequest).getAmount());
        assertEquals(creditRequest.getPartnerCode(), MessageConverters.convert(creditRequest).getPartnerCode());
        assertEquals(creditRequest.getReferenceNumber(), MessageConverters.convert(creditRequest).getReferenceNumber());




    }


    @Test
    public   void testConvert2() {
        INCreditResponse inCreditResponse = new INCreditResponse();

        inCreditResponse.setResponseCode("200");
        inCreditResponse.setNarrative("No Narative");
        inCreditResponse.setMsisdn("773303584");
        inCreditResponse.setBalance(5);

        assertEquals(inCreditResponse.getMsisdn(), MessageConverters.convert(inCreditResponse).getMsisdn());
        assertEquals(inCreditResponse.getResponseCode(), MessageConverters.convert(inCreditResponse).getResponseCode());
        assertEquals(inCreditResponse.getMsisdn(), MessageConverters.convert(inCreditResponse).getMsisdn());
        assertEquals(inCreditResponse.getBalance(), MessageConverters.convert(inCreditResponse).getBalance());
    }

    @Test
    public  void testConvert3() {
        CreditResponse creditResponse = new CreditResponse();
        creditResponse.setResponseCode("200");
        creditResponse.setNarrative("No Narative");
        creditResponse.setMsisdn("773303584");
        creditResponse.setBalance(5);

        assertEquals(creditResponse.getMsisdn(), MessageConverters.convert(creditResponse).getMsisdn());
        assertEquals(creditResponse.getResponseCode(), MessageConverters.convert(creditResponse).getResponseCode());
        assertEquals(creditResponse.getMsisdn(), MessageConverters.convert(creditResponse).getMsisdn());
        assertEquals(creditResponse.getBalance(), MessageConverters.convert(creditResponse).getBalance());
    }

    @Test
    public  void testConvert4() {
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setResponseCode("200");
        balanceResponse.setNarrative("No Narative");
        balanceResponse.setMsisdn("773303584");
        balanceResponse.setAmount(5);

        assertEquals(balanceResponse.getMsisdn(), MessageConverters.convert(balanceResponse).getMsisdn());
        assertEquals(balanceResponse.getResponseCode(), MessageConverters.convert(balanceResponse).getResponseCode());
        assertEquals(balanceResponse.getMsisdn(), MessageConverters.convert(balanceResponse).getMsisdn());
        assertEquals(balanceResponse.getAmount(), MessageConverters.convert(balanceResponse).getAmount());



    }

    @Test
    public void testConvert5() {

        INBalanceResponse inBalanceResponse = new INBalanceResponse();
        inBalanceResponse.setResponseCode("200");
        inBalanceResponse.setNarrative("No Narative");
        inBalanceResponse.setMsisdn("773303584");
        inBalanceResponse.setAmount(5);

        assertEquals(inBalanceResponse.getMsisdn(), MessageConverters.convert(inBalanceResponse).getMsisdn());
        assertEquals(inBalanceResponse.getResponseCode(), MessageConverters.convert(inBalanceResponse).getResponseCode());
        assertEquals(inBalanceResponse.getMsisdn(), MessageConverters.convert(inBalanceResponse).getMsisdn());
        assertEquals(inBalanceResponse.getAmount(), MessageConverters.convert(inBalanceResponse).getAmount());


    }
*/


}
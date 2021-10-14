package com.econetwireless.epay.business.utils;

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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = {RootConfig.class})
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


    }


    @Test
    public void testConvert1() {

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
    public void testConvert2() {
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
    public void testConvert3() {
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
    public void testConvert4() {
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

    @org.junit.jupiter.api.Test
    void testConvert6() {
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setMsisdn("42");
        balanceResponse.setAmount(10.0);
        balanceResponse.setNarrative("42");
        balanceResponse.setResponseCode("42");
        INBalanceResponse actualConvertResult = MessageConverters.convert(balanceResponse);
        assertEquals(10.0, actualConvertResult.getAmount());
        assertEquals("42", actualConvertResult.getResponseCode());
        assertEquals("42", actualConvertResult.getNarrative());
        assertEquals("42", actualConvertResult.getMsisdn());
    }

    @org.junit.jupiter.api.Test
    void testConvert7() {
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setMsisdn("42");
        creditRequest.setAmount(10.0);
        creditRequest.setPartnerCode("42");
        creditRequest.setReferenceNumber("42");
        INCreditRequest actualConvertResult = MessageConverters.convert(creditRequest);
        assertEquals(10.0, actualConvertResult.getAmount());
        assertEquals("42", actualConvertResult.getReferenceNumber());
        assertEquals("42", actualConvertResult.getPartnerCode());
        assertEquals("42", actualConvertResult.getMsisdn());
    }

    @org.junit.jupiter.api.Test
    void testConvert8() {
        CreditResponse creditResponse = new CreditResponse();
        creditResponse.setMsisdn("42");
        creditResponse.setBalance(10.0);
        creditResponse.setNarrative("42");
        creditResponse.setResponseCode("42");
        INCreditResponse actualConvertResult = MessageConverters.convert(creditResponse);
        assertEquals(10.0, actualConvertResult.getBalance());
        assertEquals("42", actualConvertResult.getResponseCode());
        assertEquals("42", actualConvertResult.getNarrative());
        assertEquals("42", actualConvertResult.getMsisdn());
    }

    @org.junit.jupiter.api.Test
    void testConvert9() {
        INBalanceResponse inBalanceResponse = new INBalanceResponse();
        inBalanceResponse.setMsisdn("Msisdn");
        inBalanceResponse.setAmount(10.0);
        inBalanceResponse.setNarrative("Narrative");
        inBalanceResponse.setResponseCode("Response Code");
        BalanceResponse actualConvertResult = MessageConverters.convert(inBalanceResponse);
        assertEquals(10.0, actualConvertResult.getAmount());
        assertEquals("Response Code", actualConvertResult.getResponseCode());
        assertEquals("Narrative", actualConvertResult.getNarrative());
        assertEquals("Msisdn", actualConvertResult.getMsisdn());
    }

    @org.junit.jupiter.api.Test
    void testConvert10() {
        CreditRequest actualConvertResult = MessageConverters
                .convert(new INCreditRequest("Partner Code", "Msisdn", 10.0, "42"));
        assertEquals(10.0, actualConvertResult.getAmount());
        assertEquals("42", actualConvertResult.getReferenceNumber());
        assertEquals("Partner Code", actualConvertResult.getPartnerCode());
        assertEquals("Msisdn", actualConvertResult.getMsisdn());
    }

    @org.junit.jupiter.api.Test
    void testConvert11() {
        assertNull(MessageConverters.convert((INCreditRequest) null));
    }

    @org.junit.jupiter.api.Test
    void testConvert12() {
        INCreditRequest inCreditRequest = mock(INCreditRequest.class);
        when(inCreditRequest.getReferenceNumber()).thenReturn("42");
        when(inCreditRequest.getPartnerCode()).thenReturn("Partner Code");
        when(inCreditRequest.getAmount()).thenReturn(10.0);
        when(inCreditRequest.getMsisdn()).thenReturn("Msisdn");
        CreditRequest actualConvertResult = MessageConverters.convert(inCreditRequest);
        assertEquals(10.0, actualConvertResult.getAmount());
        assertEquals("42", actualConvertResult.getReferenceNumber());
        assertEquals("Partner Code", actualConvertResult.getPartnerCode());
        assertEquals("Msisdn", actualConvertResult.getMsisdn());
        verify(inCreditRequest).getAmount();
        verify(inCreditRequest).getMsisdn();
        verify(inCreditRequest).getPartnerCode();
        verify(inCreditRequest).getReferenceNumber();
    }

    @org.junit.jupiter.api.Test
    void testConvert13() {
        INCreditResponse inCreditResponse = new INCreditResponse();
        inCreditResponse.setMsisdn("Msisdn");
        inCreditResponse.setBalance(10.0);
        inCreditResponse.setNarrative("Narrative");
        inCreditResponse.setResponseCode("Response Code");
        CreditResponse actualConvertResult = MessageConverters.convert(inCreditResponse);
        assertEquals(10.0, actualConvertResult.getBalance());
        assertEquals("Response Code", actualConvertResult.getResponseCode());
        assertEquals("Narrative", actualConvertResult.getNarrative());
        assertEquals("Msisdn", actualConvertResult.getMsisdn());
    }


}
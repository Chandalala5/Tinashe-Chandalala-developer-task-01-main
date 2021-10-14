package com.econetwireless.epay.business.integrations.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.econetwireless.in.webservice.BalanceResponse;
import com.econetwireless.in.webservice.CreditResponse;
import com.econetwireless.in.webservice.IntelligentNetworkService;
import com.econetwireless.utils.pojo.INBalanceResponse;
import com.econetwireless.utils.pojo.INCreditRequest;
import com.econetwireless.utils.pojo.INCreditResponse;
import org.junit.jupiter.api.Test;

class ChargingPlatformImplTest {
    @Test
    void testConstructor() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     ChargingPlatformImpl.intelligentNetworkService

        new ChargingPlatformImpl(null);
    }

    @Test
    void testEnquireBalance() {
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setMsisdn("42");
        balanceResponse.setAmount(10.0);
        balanceResponse.setNarrative("42");
        balanceResponse.setResponseCode("42");
        IntelligentNetworkService intelligentNetworkService = mock(IntelligentNetworkService.class);
        when(intelligentNetworkService.enquireBalance((String) any(), (String) any())).thenReturn(balanceResponse);
        INBalanceResponse actualEnquireBalanceResult = (new ChargingPlatformImpl(intelligentNetworkService))
                .enquireBalance("Partner Code", "Msisdn");
        assertEquals(10.0, actualEnquireBalanceResult.getAmount());
        assertEquals("42", actualEnquireBalanceResult.getResponseCode());
        assertEquals("42", actualEnquireBalanceResult.getNarrative());
        assertEquals("42", actualEnquireBalanceResult.getMsisdn());
        verify(intelligentNetworkService).enquireBalance((String) any(), (String) any());
    }

    @Test
    void testCreditSubscriberAccount() {
        CreditResponse creditResponse = new CreditResponse();
        creditResponse.setMsisdn("42");
        creditResponse.setBalance(10.0);
        creditResponse.setNarrative("42");
        creditResponse.setResponseCode("42");
        IntelligentNetworkService intelligentNetworkService = mock(IntelligentNetworkService.class);
        when(intelligentNetworkService.creditSubscriberAccount((com.econetwireless.in.webservice.CreditRequest) any()))
                .thenReturn(creditResponse);
        ChargingPlatformImpl chargingPlatformImpl = new ChargingPlatformImpl(intelligentNetworkService);
        INCreditRequest inCreditRequest = mock(INCreditRequest.class);
        when(inCreditRequest.getReferenceNumber()).thenReturn("42");
        when(inCreditRequest.getPartnerCode()).thenReturn("Partner Code");
        when(inCreditRequest.getAmount()).thenReturn(10.0);
        when(inCreditRequest.getMsisdn()).thenReturn("Msisdn");
        INCreditResponse actualCreditSubscriberAccountResult = chargingPlatformImpl
                .creditSubscriberAccount(inCreditRequest);
        assertEquals(10.0, actualCreditSubscriberAccountResult.getBalance());
        assertEquals("42", actualCreditSubscriberAccountResult.getResponseCode());
        assertEquals("42", actualCreditSubscriberAccountResult.getNarrative());
        assertEquals("42", actualCreditSubscriberAccountResult.getMsisdn());
        verify(intelligentNetworkService).creditSubscriberAccount((com.econetwireless.in.webservice.CreditRequest) any());
        verify(inCreditRequest).getAmount();
        verify(inCreditRequest).getMsisdn();
        verify(inCreditRequest).getPartnerCode();
        verify(inCreditRequest).getReferenceNumber();
    }
}


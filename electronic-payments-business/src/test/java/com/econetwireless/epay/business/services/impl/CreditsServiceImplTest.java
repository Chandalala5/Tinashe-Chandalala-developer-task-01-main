package com.econetwireless.epay.business.services.impl;

import static org.mockito.Mockito.mock;

import com.econetwireless.epay.business.integrations.impl.ChargingPlatformImpl;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import org.junit.jupiter.api.Test;

class CreditsServiceImplTest {
    @Test
    void testConstructor() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CreditsServiceImpl.chargingPlatform
        //     CreditsServiceImpl.subscriberRequestDao

        new CreditsServiceImpl();
    }

    @Test
    void testConstructor2() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CreditsServiceImpl.chargingPlatform
        //     CreditsServiceImpl.subscriberRequestDao

        new CreditsServiceImpl(new ChargingPlatformImpl(null), mock(SubscriberRequestDao.class));

    }
}


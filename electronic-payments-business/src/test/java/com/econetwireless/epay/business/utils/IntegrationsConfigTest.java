/*
package com.econetwireless.epay.business.config;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = {RootConfig.class})
})
public class IntegrationsConfigTest {
    @Test
    public void testChargingPlatform() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     IntegrationsConfig.env
        //     ChargingPlatformImpl.intelligentNetworkService

        IntegrationsConfig integrationsConfig = new IntegrationsConfig();
        ReflectionTestUtils.setField(integrationsConfig, "env", mock(Environment.class));
        integrationsConfig.chargingPlatform(null);
    }
}

*/

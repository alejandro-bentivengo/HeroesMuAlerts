package org.benti.process;

import org.junit.jupiter.api.Test;

/**
 * These are not real tests, I used them to test the output of some methods and alerts
 */
public class AlertHandlerTest {

    @Test
    public void alertHandlerTest() {
        AlertHandler alertHandler = AlertHandlerFactory.getAlertHandler(UserType.VIP, ResetType.MR, "");
        System.out.println(alertHandler.hasAlert());
    }
}

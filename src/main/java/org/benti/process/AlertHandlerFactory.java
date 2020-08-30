package org.benti.process;

import java.util.Map;

public class AlertHandlerFactory {

    private static final Map<UserType, Map<ResetType, Integer>> LEVEL_VALUES = instantiateLevels();
    private static final Map<ResetType, String> REGEX_VALUES = instantiateRegex();


    private static Map<UserType, Map<ResetType, Integer>> instantiateLevels() {
        return Map.of(
                UserType.FREE,
                Map.of(
                        ResetType.RR,
                        400,
                        ResetType.MR,
                        400,
                        ResetType.C4,
                        520
                ),
                UserType.VIP,
                Map.of(
                        ResetType.RR,
                        380,
                        ResetType.MR,
                        380,
                        ResetType.C4,
                        500
                )
        );
    }

    private static Map<ResetType, String> instantiateRegex() {
        return Map.of(
                ResetType.RR,
                "\\] Level: \\[(\\d*)\\]",
                ResetType.MR,
                "Master Level: \\[(\\d*)\\]",
                ResetType.C4,
                "Master Level: \\[(\\d*)\\]"
        );
    }


    public static AlertHandler getAlertHandler(UserType userType, ResetType resetType, String character) {
        return new AlertHandler(LEVEL_VALUES.get(userType).get(resetType), REGEX_VALUES.get(resetType), character);
    }

}

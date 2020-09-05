package org.benti.process;

import org.benti.util.MapBuilder;

import java.util.Map;

public class AlertHandlerFactory {

    private static final Map<UserType, Map<ResetType, Integer>> LEVEL_VALUES = instantiateLevels();
    private static final Map<ResetType, String> REGEX_VALUES = instantiateRegex();


    private static Map<UserType, Map<ResetType, Integer>> instantiateLevels() {
        return new MapBuilder<UserType, Map<ResetType, Integer>>()
                .addRecord(
                        UserType.FREE,
                        new MapBuilder<ResetType, Integer>()
                                .addRecord(
                                        ResetType.RR,
                                        400)
                                .addRecord(
                                        ResetType.MR,
                                        400)
                                .addRecord(
                                        ResetType.C4,
                                        520)
                                .build()
                ).addRecord(
                        UserType.VIP,
                        new MapBuilder<ResetType, Integer>()
                                .addRecord(
                                        ResetType.RR,
                                        380)
                                .addRecord(
                                        ResetType.MR,
                                        380)
                                .addRecord(
                                        ResetType.C4,
                                        500)
                                .build()
                ).build();

    }

    private static Map<ResetType, String> instantiateRegex() {
        return new MapBuilder<ResetType, String>()
                .addRecord(
                        ResetType.RR,
                        "\\] Level: \\[(\\d*)\\]")
                .addRecord(
                        ResetType.MR,
                        "Master Level: \\[(\\d*)\\]")
                .addRecord(
                        ResetType.C4,
                        "Master Level: \\[(\\d*)\\]")
                .build();

    }


    public static AlertHandler getAlertHandler(UserType userType, ResetType resetType, String character) {
        return new AlertHandler(LEVEL_VALUES.get(userType).get(resetType), REGEX_VALUES.get(resetType), character);
    }

}

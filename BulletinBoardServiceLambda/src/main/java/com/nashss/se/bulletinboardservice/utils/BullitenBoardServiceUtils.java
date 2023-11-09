package com.nashss.se.bulletinboardservice.utils;

import org.apache.commons.lang3.RandomStringUtils;
public final class BullitenBoardServiceUtils {
    static final int GENERATED_ID_LENGTH = 5;

    private BullitenBoardServiceUtils() {
    }

    /**
     * Provides a generated user ID.
     * @return String of a generated ID.
     */
    public static String generateUserId() {
        return RandomStringUtils.randomAlphanumeric(GENERATED_ID_LENGTH);
    }

//    /**
//     * Provides a generated Ad ID.
//     * @return String of a generated ID.
//     */
//    public static String generateAdId() {
//        return RandomStringUtils.randomAlphanumeric(GENERATED_ID_LENGTH);
//    }
}

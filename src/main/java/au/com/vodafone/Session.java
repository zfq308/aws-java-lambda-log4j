package au.com.vodafone;

import org.apache.commons.lang3.RandomStringUtils;

public class Session {

    public static String generateSessionId() {
        return RandomStringUtils.randomAlphabetic(8).toUpperCase();
    }
}

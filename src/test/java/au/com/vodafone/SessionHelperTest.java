package au.com.vodafone;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SessionHelperTest {

    @Test
    public void should_generate_8_upcase_alphabets_session_id() throws Exception {
        assertTrue(SessionHelper.generateSessionId().matches("[A-Z]{8}"));
    }
}
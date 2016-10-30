package au.com.vodafone;

public class APIResponse {
    private String sessionId;
    private String rawCookie;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getRawCookie() {
        return rawCookie;
    }

    public void setRawCookie(String rawCookie) {
        this.rawCookie = rawCookie;
    }
}

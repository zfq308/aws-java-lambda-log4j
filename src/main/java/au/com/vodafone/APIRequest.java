package au.com.vodafone;

public class APIRequest {
    private String apiId;
    private String resourceId;
    private String stage;
    private String sessionId;
    private String rawCookie;

    public APIRequest() {
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

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

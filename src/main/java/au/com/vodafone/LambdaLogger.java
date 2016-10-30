package au.com.vodafone;

import com.amazonaws.services.lambda.runtime.Context;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class LambdaLogger {
    private APIRequest apiRequest;
    private Context context;
    private Logger logger = Logger.getLogger(LambdaLogger.class);

    final static String NULL_STRING = "Null";
    final static String EMPTY_STRING = "";

    public LambdaLogger(Logger logger) {
        this.logger = logger;
    }

    public APIRequest getApiRequest() {
        return apiRequest;
    }

    public void setApiRequest(APIRequest apiRequest) {
        this.apiRequest = apiRequest;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void updateMDC(APIRequest apiRequest, Context context) {
        if (apiRequest != null) {
            MDC.put("ApiId", checkRequestApiId(apiRequest.getApiId()));
            MDC.put("ResourceId", checkRequestResourceId(apiRequest.getResourceId()));
            MDC.put("Stage", checkRequestStage(apiRequest.getStage()));
            MDC.put("SessionId", checkSessionId(apiRequest.getSessionId()));
            MDC.put("FunctionName", checkFunctionName(context.getFunctionName()));
            MDC.put("FunctionVersion", checkFunctionVersion(context.getFunctionVersion()));
        } else {
            logger.error("apiRequest is null");
        }
    }

    private String checkRequestApiId(String apiId) {
        if(apiId != null && !apiId.equals(EMPTY_STRING)) {
            return apiId;
        } else {
            logger.error("ApiId is null");
            return NULL_STRING;
        }
    }

    private String checkRequestResourceId(String resourceId) {
        if(resourceId != null && !resourceId.equals(EMPTY_STRING)) {
            return resourceId;
        } else {
            logger.error("ResourceId is null");
            return NULL_STRING;
        }
    }

    private String checkRequestStage(String stage) {
        if(stage != null && !stage.equals(EMPTY_STRING)) {
            return stage;
        } else {
            logger.error("Stage is null");
            return NULL_STRING;
        }
    }

    private String checkSessionId(String sessionId) {
        if(sessionId == null || sessionId.equals(EMPTY_STRING)) {
            sessionId = SessionHelper.generateSessionId();
        } else if(!sessionId.matches("[A-Z]{8}")){
            logger.error("SessionId " + sessionId + " is invalid");
            throw new IllegalArgumentException("SessionId is invalid");
        }
        return sessionId;
    }

    private String checkFunctionName(String functionName) {
        if(functionName != null && !functionName.equals(EMPTY_STRING)) {
            return functionName;
        } else {
            logger.error("FunctionName is null");
            return NULL_STRING;
        }
    }

    private String checkFunctionVersion(String functionVersion) {
        if(functionVersion != null && !functionVersion.equals(EMPTY_STRING)) {
            return functionVersion;
        } else {
            logger.error("FunctionName is null");
            return NULL_STRING;
        }
    }
}

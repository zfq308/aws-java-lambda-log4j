package au.com.vodafone;

import com.amazonaws.services.lambda.runtime.Context;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class LambdaLogger {
    private APIRequest apiRequest;
    private Context context;
    private Logger logger = Logger.getLogger(LambdaLogger.class);

    final static String NULL_STRING = "Null";

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
        if(apiId != null) {
            return apiId;
        } else {
            logger.error("ApiId is null");
            return NULL_STRING;
        }
    }

    private String checkRequestResourceId(String resourceId) {
        if(resourceId != null) {
            return resourceId;
        } else {
            logger.error("ResourceId is null");
            return NULL_STRING;
        }
    }

    private String checkRequestStage(String stage) {
        if(stage != null) {
            return stage;
        } else {
            logger.error("Stage is null");
            return NULL_STRING;
        }
    }

    private String checkSessionId(String sessionId) {
        if(sessionId == null || sessionId.equals("")) {
            sessionId = generateSessionId();
        } else if(sessionId.length() != 8){
            logger.error("SessionId " + sessionId + " is invalid");
            throw new IllegalArgumentException("SessionId is invalid");
        }
        return sessionId;
    }

    private String generateSessionId() {
        return RandomStringUtils.randomAlphabetic(8).toUpperCase();
    }

    private String checkFunctionName(String functionName) {
        if(functionName != null) {
            return functionName;
        } else {
            logger.error("FunctionName is null");
            return NULL_STRING;
        }
    }

    private String checkFunctionVersion(String functionVersion) {
        if(functionVersion != null) {
            return functionVersion;
        } else {
            logger.error("FunctionName is null");
            return NULL_STRING;
        }
    }


}


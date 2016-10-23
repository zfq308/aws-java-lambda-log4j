package au.com.vodafone;

import com.amazonaws.services.lambda.runtime.Context;
import org.apache.log4j.MDC;

public class LambdaLogger {
    private APIRequest apiRequest;
    private Context context;

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
            MDC.put("ApiId", apiRequest.getApiId() == null ? " " : apiRequest.getApiId());
            MDC.put("ResourceId", apiRequest.getResourceId() == null ? " " : apiRequest.getResourceId());
            MDC.put("Stage", apiRequest.getStage() == null ? " " : apiRequest.getStage());
            MDC.put("SessionId", apiRequest.getSessionId() == null ? " " : apiRequest.getSessionId());
            MDC.put("FunctionName", context.getFunctionName() == null ? " " : context.getFunctionName());
            MDC.put("FunctionVersion", context.getFunctionVersion() == null ? " " : context.getFunctionVersion());
        }
    }
}


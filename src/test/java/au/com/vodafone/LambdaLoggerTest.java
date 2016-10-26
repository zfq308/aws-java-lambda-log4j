package au.com.vodafone;

import com.amazonaws.services.lambda.runtime.Context;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LambdaLoggerTest {
    private APIRequest apiRequest;
    private Context context;
    private LambdaLogger lambdaLogger;
    private Logger logger;

    @Before
    public void setUp() throws Exception {
        logger = mock(Logger.class);
        lambdaLogger = new LambdaLogger(logger);
        apiRequest = new APIRequest();
        context = mock(Context.class);
    }

    @Test
    public void should_not_update_MDC_when_api_request_is_null() throws Exception {
        apiRequest = null;
        lambdaLogger.updateMDC(null, context);
        verify(logger, times(1)).error("apiRequest is null");
        assertEquals(null, MDC.get("ApiId"));
        assertEquals(null, MDC.get("ResourceId"));
        assertEquals(null, MDC.get("Stage"));
        assertEquals(null, MDC.get("SessionId"));
        assertEquals(null, MDC.get("FunctionName"));
        assertEquals(null, MDC.get("FunctionVersion"));
    }

    @Test
    public void should_update_api_id_when_api_id_is_not_null() throws Exception {
        apiRequest.setApiId("TEST_API_ID");
        lambdaLogger.updateMDC(apiRequest, context);
        assertEquals("TEST_API_ID", MDC.get("ApiId"));
    }

    @Test
    public void should_update_resource_id_when_resource_id_is_not_null() throws Exception {
        apiRequest.setResourceId("TEST_RESOURCE_ID");
        lambdaLogger.updateMDC(apiRequest, context);
        assertEquals("TEST_RESOURCE_ID", MDC.get("ResourceId"));
    }

    @Test
    public void should_update_stage_when_stage_is_not_null() throws Exception {
        apiRequest.setStage("TEST_STAGE");
        lambdaLogger.updateMDC(apiRequest, context);
        assertEquals("TEST_STAGE", MDC.get("Stage"));
    }

    @Test
    public void should_update_session_id_when_session_id_is_not_null() throws Exception {
        apiRequest.setSessionId("ABCDEFGH");
        lambdaLogger.updateMDC(apiRequest, context);
        assertEquals("ABCDEFGH", MDC.get("SessionId"));
    }

    @Test
    public void should_update_function_name_when_function_name_is_not_null() throws Exception {
        when(context.getFunctionName()).thenReturn("TEST_FUNCTION_NAME");
        lambdaLogger.updateMDC(apiRequest, context);
        assertEquals("TEST_FUNCTION_NAME", MDC.get("FunctionName"));
    }

    @Test
    public void should_update_function_version_when_function_version_is_not_null() throws Exception {
        when(context.getFunctionVersion()).thenReturn("TEST_FUNCTION_VERSION");
        lambdaLogger.updateMDC(apiRequest, context);
        assertEquals("TEST_FUNCTION_VERSION", MDC.get("FunctionVersion"));
    }

    @After
    public void tearDown() throws Exception {
        MDC.clear();
    }
}
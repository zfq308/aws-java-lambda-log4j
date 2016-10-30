package au.com.vodafone;

import org.simpleframework.http.Cookie;
import org.simpleframework.http.parse.CookieParser;

import java.util.Iterator;

public class CookieHelper {
    public static Boolean hasCookie(String rawCookie){
        return !(rawCookie == null || rawCookie.equals(""));
    }

    public static Iterator<Cookie> getCookies(String rawCookie){
        CookieParser cookieParser = new CookieParser(rawCookie);
        return cookieParser.iterator();
    }

    public static Cookie getCookiebyName(String name, String rawCookie){
        CookieParser cookieParser = new CookieParser(rawCookie);
        Iterator<Cookie> cookies = cookieParser.iterator();
        final Cookie[] cookieList = new Cookie[1];
        cookies.forEachRemaining(cookie -> {
            if (cookie.getName().equals(name)) {
                cookieList[0] = cookie;
            }
        });
        return cookieList[0];
    }
}

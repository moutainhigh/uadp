package com.myzh.session.session;

import com.myzh.session.SessionUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by chensg on 2017/4/14.
 */
public interface SessionProvider {
    /**
     * 生成Session信息
     * @param request
     * @param user
     * @param attrs
     * @return
     */
    Session generateSession(HttpServletRequest request, String secretKey, SessionUser user, Map<String, Object> attrs);

    /**
     * 生成SessionID
     * @param user
     * @return
     */
    String generateId(SessionUser user, String secretKey);
    /**
     * 验证SessionId
     * @return
     */
    boolean validateId(String tokenId, String secretKey);
}

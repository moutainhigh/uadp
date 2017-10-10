/*******************************************************************************
 * @(#)TokenFactory.java 2017年04月14日 14:58 
 * Copyright 2017 明医众禾科技（北京）有限责任公司. All rights reserved.
 *******************************************************************************/
package com.upbos.session.session;

import com.upbos.session.SessionUser;
import com.upbos.util.IpUtils;
import com.upbos.util.RandomUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <b>Application name：</b> SessionProvider.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2017 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2017年04月14日 14:58 <br>
 * <b>author：</b> <a href="mailto:chensg@miyzh.com"> chensg </a>
 * <b>version：</b>V2.0.0
 */
@Service
public class WebSessionProvider implements SessionProvider {

    /**
     * 生成Session信息
     * @param request
     * @param user
     * @param attrs
     * @return
     */
    public Session generateSession(HttpServletRequest request, String secretKey, SessionUser user, Map<String, Object> attrs) {
        Session token = new Session();
        token.setId(generateId(user, secretKey));
        token.setUid(user.getUid());
        token.setTime(System.currentTimeMillis());
        token.setIp(IpUtils.getIpAddr(request));
        if(user != null) {
            token.setUser(user);
        }
        if(attrs != null) {
            token.putAtrr(attrs);
        }
        return token;
    }

    /**
     * 生成TokenID
     * @param user
     * @return
     */
    public String generateId(SessionUser user, String secretKey) {
        return user.getUid() + "_" + RandomUtils.get32UUID();
    }
    /**
     * 验证tokenId
     * @return
     */
    public boolean validateId(String tokenId, String secretKey) {
        return true;
    }
}
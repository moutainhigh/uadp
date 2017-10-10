/*******************************************************************************
 * @(#)AppTokenService.java 2017年04月14日 20:14 
 * Copyright 2017 明医众禾科技（北京）有限责任公司. All rights reserved.
 *******************************************************************************/
package com.myzh.session.session;

import com.myzh.session.SessionUser;
import com.myzh.util.encrypt.MD5;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <b>Application name：</b> AppTokenProvider.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2017 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2017年04月14日 20:14 <br>
 * <b>author：</b> <a href="mailto:chensg@miyzh.com"> chensg </a>
 * <b>version：</b>V2.0.0
 */
public class AppTokenProvider implements SessionProvider {

    /**
     * 生成Token信息
     *
     * @param request
     * @param user
     * @param attrs
     * @return
     */
    @Override
    public Session generateSession(HttpServletRequest request, String appSecretKey, SessionUser user, Map<String, Object> attrs) {
        Session token = new Session();
        token.setId(generateId(user, appSecretKey));
        return token;
    }

    /**
     * 生成TokenID
     *
     * @param user
     * @return
     */
    @Override
    public String generateId(SessionUser user, String appSecretKey) {
        return user.getUid() + "_" + MD5.getSignature(user.getUid(), appSecretKey);
    }

    /**
     * 验证tokenId
     *
     * @param tokenId
     * @return
     */
    @Override
    public boolean validateId(String tokenId, String appSecretKey) {
        String checkTokenId = null;
        if (StringUtils.isNotBlank(tokenId) && tokenId.indexOf("_") > -1) {
            String uid = tokenId.substring(0, tokenId.indexOf("_"));
            checkTokenId = uid + "_" + MD5.getSignature(uid, appSecretKey);
        }

        return tokenId == null?false:tokenId.equals(checkTokenId);
    }
}
package com.myzh.session;

import com.myzh.session.session.Session;

public interface SessionStorageManager {
	void saveSession(Session token);
	void removeSession(String tokenId);
	void removeSession(String key, boolean isPrecise);
	Session getSession(String tokenId);
	void setOnLocalCache(Boolean on);
	Boolean getOnLocalCache();
}
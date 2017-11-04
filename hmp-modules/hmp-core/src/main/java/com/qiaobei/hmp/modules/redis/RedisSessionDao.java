package com.qiaobei.hmp.modules.redis;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;

public class RedisSessionDao extends AbstractSessionDAO {

    private static final Logger log = LoggerFactory.getLogger(RedisSessionDao.class);

    private RedisManager<String, Serializable, Session> redisManager;
    private String key;

    private void saveSession(final Session session) {
        if (session == null || session.getId() == null) {
            log.error("session or session id is null");
            return;
        }
        this.redisManager.put(this.key, session.getId(), session);
    }

    @Override
    protected Serializable doCreate(Session session) {
        final Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            log.error("session id is null");
            return null;
        }
        return redisManager.get(this.key, sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            log.error("session or session id is null");
            return;
        }
        this.redisManager.remove(this.key, session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return this.redisManager.values(this.key);
    }

    public RedisManager<String, Serializable, Session> getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager<String, Serializable, Session> redisManager) {
        this.redisManager = redisManager;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

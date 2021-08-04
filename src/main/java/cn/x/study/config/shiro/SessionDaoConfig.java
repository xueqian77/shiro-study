package cn.x.study.config.shiro;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 重写shiro的保存会话类，使用redis来保存会话（默认使用session）。
 */
@Component
public class SessionDaoConfig extends EnterpriseCacheSessionDAO {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        SimpleSession session1 = (SimpleSession) session;
        session1.setId(sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session o = (Session) redisTemplate.opsForValue().get(sessionId);
        return o;
    }

    @Override
    protected void doUpdate(Session session) {
        if (session instanceof ValidatingSession) {
            System.out.println(session.getTimeout());
            System.out.println(session.getId());
            if (((ValidatingSession) session).isValid()) {
                redisTemplate.opsForValue().set(session.getId(), session);
            } else {
                redisTemplate.delete(session.getId());
            }
        } else {
            redisTemplate.opsForValue().set(session.getId(), session);
        }
    }

    @Override
    protected void doDelete(Session session) {
        redisTemplate.delete(session.getId());
    }
}
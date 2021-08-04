package cn.x.study.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类。
 */
@Configuration
public class ShiroConfig {

    //1.创建realm
    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }

    //2.创建安全管理器
    @Bean
    public SecurityManager securityManager(SessionDaoConfig sessionDaoConfig) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(customRealm());

        // 自定义session管理 使用redis
        SessionConfig sessionConfig = new SessionConfig();
        sessionConfig.setSessionDAO(sessionDaoConfig);
        securityManager.setSessionManager(sessionConfig);
        // 自定义缓存实现 使用redis
        //securityManager.setCacheManager();


        return securityManager;
    }

    //3.配置shiro的过滤器工厂
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        //1.创建过滤器工厂
        ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();
        //2.设置安全管理器
        filterFactory.setSecurityManager(securityManager);
        //3.通用配置（跳转登录页面，为授权跳转的页面）
        //3.1 登录
        filterFactory.setLoginUrl("/login");
        //3.2 首页
        filterFactory.setSuccessUrl("/index");
        //3.3 错误页面，认证不通过跳转
        filterFactory.setUnauthorizedUrl("/error");

        //4.设置过滤器集合（key = 拦截的url地址，value = 过滤器类型（anon(匿名访问)、perms(指定权限)、roles(指定角色)、authc(必须认证)、logout(退出登录)））
        Map<String,String> filterMap = new LinkedHashMap<>();
        //4.1.所有请求地址必须认证之后可以访问
        filterMap.put("/**","authc");

        filterFactory.setFilterChainDefinitionMap(filterMap);

        return filterFactory;
    }

    //4.开启对shiro注解的支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    //5.解决shiro与Spring产生的二次代理的问题
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }







}

package com.marlowe.rbac.config.shiro;

import com.marlowe.rbac.config.shiro.jwt.JWTFilter;
import com.marlowe.rbac.config.shiro.realms.UserRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @auther yincaiTA
 * @date 2021/3/17 10:25
 * @description shiro配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * SecurityManager
     *
     * @param userRealm
     * @return
     */
    @Bean
    public DefaultSecurityManager securityManager(UserRealm userRealm) {
        // 创建securityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 使用自定义Realm
        securityManager.setRealm(userRealm);

        // 关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        // 禁止session
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);

        securityManager.setSubjectDAO(subjectDAO);

        // UnavailableSecurityManagerException: No SecurityManager accessible to the calling code,
        //     either bound to the org.apache.shiro.util.ThreadContext or as a vm static singleton.  This is an invalid application configuration.
        //     加上其中一个即可 但是加上了就会出现 /login 也会被拦截的情况, 但是不加又会出现 JWTFilter中注入不了JWTUtils的情况
//        SecurityUtils.setSecurityManager(securityManager);
//        ThreadContext.bind(securityManager);  // 就是导包的问题！！！！  shiro-spring-boot-starter -> 换成 shiro-spring-boot-web-starter
        return securityManager;
    }

    // ShiroFilter 注入 JWTFilter, 并设置为主过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, JWTFilter jwtFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 添加我们自定义的过滤器并取名为jwt  ***很重要***
        Map<String, Filter> newFilters = new HashMap<>();
        newFilters.put("jwt", jwtFilter);
        shiroFilterFactoryBean.setFilters(newFilters);

        // 注入securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 定义规则
        Map<String, String> map = new LinkedHashMap<>();
        // anon(写在authc的前面): 不进行认证都能访问
        map.put("/login", "anon");
        map.put("/register", "anon");
//        map.put("/logout", "anon");

        // 放行swagger
        map.put("/swagger/**", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("/swagger-ui.html", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/webjars/**", "anon");
        map.put("/favicon.ico", "anon");
        map.put("/captcha.jpg", "anon");
        map.put("/csrf", "anon");

        // 其他所有请求都由JWTFilter处理
        map.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    /**
     * 不向 Spring容器中注册 JWTFilter Bean，防止 Spring 将 JWTFilter 注册为全局过滤器
     * 全局过滤器会对所有请求进行拦截，而本例中只需要拦截除 /login 和 /logout 外的请求
     * 另一种简单做法是：不将 JWTFilter 放入 Spring 容器
     * <p>
     * 不添加下面这个 Bean 的话, 会出现 /** jwt 会拦截所有请求的情况
     */
    @Bean
    public FilterRegistrationBean<Filter> registration(JWTFilter jwtFilter) {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>(jwtFilter);
        registration.setEnabled(false);
        return registration;
    }
//
//    /**
//     * 下面的代码是添加注解支持
//     */
//    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        // 强制使用cglib，防止重复代理和可能引起代理出错的问题，https://zhuanlan.zhihu.com/p/29161098
//        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//        return defaultAdvisorAutoProxyCreator;
//    }
//
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}

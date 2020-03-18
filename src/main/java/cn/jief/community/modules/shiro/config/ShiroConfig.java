package cn.jief.community.modules.shiro.config;

import cn.jief.community.modules.shiro.realm.UserRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro配置类
 * @author jfeng
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建安全主体 ShiroFilterFactoryBean
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);

        //添加过滤器，实现权限拦截
        Map<String,String> filterMap = new HashMap<>();
        filterMap.put("/user/**", "authc");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        //过滤器end
        //设置拦截跳转页
        factoryBean.setLoginUrl("/login.html");

        return factoryBean;
    }
    /**
     * 创建安全管理器 DefaultWebSecurityManager
     * @param realm
     * @return
     */
    @Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") Realm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }
    /**
     * 创建realm
     * @return Realm
     */
    @Bean("userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

}

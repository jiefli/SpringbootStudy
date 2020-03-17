package cn.jief.community.modules.shiro.config;

import cn.jief.community.modules.shiro.realm.UserRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Bean
    public AuthorizingRealm getRealm(){
        return new UserRealm();
    }

}

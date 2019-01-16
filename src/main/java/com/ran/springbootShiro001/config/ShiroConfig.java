package com.ran.springbootShiro001.config;

import com.ran.springbootShiro001.shiro.realm.UserRealm;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
//      设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        //添加shiro内置过滤器，实现权限相关的url拦截
        /**
         * 常见过滤器：
         * anon：无需认证（登录）可以访问
         * authc：必须认证才可以访问
         * user:如果使用Remember Me的功能，可以直接访问
         * perms:该资源必须得到资源权限才可以访问
         * role:该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap=new LinkedHashMap<String, String>();
        filterMap.put("/add", "authc");
        filterMap.put("/update", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //修改跳转的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getdefaultDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */

    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

}

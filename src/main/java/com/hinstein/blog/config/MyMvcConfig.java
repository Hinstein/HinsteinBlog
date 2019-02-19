package com.hinstein.blog.config;

import com.hinstein.blog.component.LoginHandlerInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.config
 * @Author: Hinstein
 * @CreateTime: 2018-11-17 22:14
 * @Description:
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("admin/login");
        registry.addViewController("/admin/index").setViewName("admin/login");
        registry.addViewController("/admin/login.html").setViewName("admin/login");
        registry.addViewController("/admin/index.html").setViewName("admin/login");
        registry.addViewController("/admin/exit.html").setViewName("admin/login");
        registry.addViewController("/admin/main.html").setViewName("admin/dashboard");

        registry.addViewController("/").setViewName("/blog/index");
        registry.addViewController("/blog").setViewName("/blog/index");
        registry.addViewController("/blog/index.html").setViewName("/blog/index");
        registry.addViewController("/blog/contact.html").setViewName("blog/contact");
    }


    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        //静态资源也会被拦截，所以需要加"/assets/**","/webjars/**"
        String[] excludeUrl = new String[]{"/cadmin/login","/admin/login.html","/admin/index","/admin/login/submit", "/", "/admin", "/admin/index.html", "/assets/**", "/webjars/**", "/blog/**"};

//        String[] excludeUrl = new String[]{"/admin/login", "/assets/**", "/webjars/**", "/blog/**"};
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/admin/**").excludePathPatterns(excludeUrl);

    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/webjars/**").excludePathPatterns("/index.html").excludePathPatterns("/asserts/**");
//    }

}

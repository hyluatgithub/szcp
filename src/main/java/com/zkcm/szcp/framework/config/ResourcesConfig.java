package com.zkcm.szcp.framework.config;

import com.zkcm.szcp.framework.security.filter.MobileAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.zkcm.szcp.common.constant.Constants;

/**
 * 通用配置
 *
 * @author hylu
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer
{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** 本地文件上传路径 */
        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**").addResourceLocations("file:" + SystemConfig.getProfile() + "/");

        /** swagger配置 */
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，
        registry.addInterceptor(MobileAuthenticationFilter()).addPathPatterns("/mobile/**");
    }


    @Bean
    public MobileAuthenticationFilter MobileAuthenticationFilter() {
        return new MobileAuthenticationFilter();
    }

}

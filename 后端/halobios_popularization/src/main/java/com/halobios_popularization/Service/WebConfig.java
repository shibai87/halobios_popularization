package com.halobios_popularization.Service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
//    图片
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //映射static路径的请求到static目录下
    // 静态资源访问路径和存放路径配置
    //registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    // swagger访问配置
    registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/");
    //通过image访问本地的图片
    registry.addResourceHandler("/knowledge/**")
            .addResourceLocations("file:C:/my/code/海洋生物科普Web App/项目/后端/halobios_popularization/src/main/resources/static/knowledge/");
    registry.addResourceHandler("/activity/**")
            .addResourceLocations("file:C:/my/code/海洋生物科普Web App/项目/后端/halobios_popularization/src/main/resources/static/activity/");
    registry.addResourceHandler("/video/**")
            .addResourceLocations("file:C:/my/code/海洋生物科普Web App/项目/后端/halobios_popularization/src/main/resources/static/video/");
//
    }

}

package ynu.myblog.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import ynu.myblog.interceptor.LogInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

  public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
    }

}

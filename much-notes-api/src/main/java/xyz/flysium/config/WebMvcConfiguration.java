package xyz.flysium.config;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;
import xyz.flysium.web.interceptor.UserInfoInterceptor;

/**
 * Web MVC 配置
 *
 * @author zeno
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  @Autowired
  private UserInfoInterceptor userInfoInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new MappedInterceptor(new String[]{"/mp/**"},
      new String[]{
        "/mp/wx/validate",
        "/mp/wx/login"
      },
      userInfoInterceptor));
//    registry.addInterceptor(new MappedInterceptor(new String[]{"/mp/**"},
//      new String[]{
//        "/swagger*/**", "/doc.html",
//        "/v2/**", "/webjars/**",
//        "/",
//        "/favicon.ico",
//        "/error",
//        "/index.html"
//      },
//      userInfoInterceptor));
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/*")
      .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("swagger-ui.html")
      .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
      .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Bean
  public MultipartResolver multipartResolver() throws IOException {
    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
    commonsMultipartResolver.setDefaultEncoding("UTF-8");
    commonsMultipartResolver.setMaxUploadSize(10485760000L);
    commonsMultipartResolver.setMaxInMemorySize(40960);
    return commonsMultipartResolver;
  }

  @Bean
  public FilterRegistrationBean<CharacterEncodingFilter> encodingFilter() {
    final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
    encodingFilter.setEncoding("UTF-8");
    encodingFilter.setForceEncoding(true);
    FilterRegistrationBean<CharacterEncodingFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(encodingFilter);
    registration.addUrlPatterns("/*");
    return registration;
  }

}

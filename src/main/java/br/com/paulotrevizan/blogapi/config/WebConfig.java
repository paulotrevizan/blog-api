package br.com.paulotrevizan.blogapi.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    List<String> all = Arrays.asList("*");
    corsConfiguration.setAllowedOriginPatterns(all);
    corsConfiguration.setAllowedHeaders(all);
    corsConfiguration.setAllowedMethods(all);
    corsConfiguration.setAllowCredentials(false);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);

    CorsFilter corsFilter = new CorsFilter(source);
    FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(corsFilter);
    filter.setOrder(Ordered.HIGHEST_PRECEDENCE);

    return filter;
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true);
  }

}

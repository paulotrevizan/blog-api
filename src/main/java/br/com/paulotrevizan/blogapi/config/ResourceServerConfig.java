package br.com.paulotrevizan.blogapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  public static final String API_ALBUMS = "/api/albuns/**";
  public static final String API_OAUTH = "/oauth/token";
  public static final String API_POSTAGENS = "/api/postagens/**";
  public static final String API_USUARIOS = "/api/usuarios/**";

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, API_OAUTH).permitAll()
        .antMatchers(API_USUARIOS).permitAll()
        .antMatchers(HttpMethod.GET, API_POSTAGENS).permitAll()
        .antMatchers(HttpMethod.POST, API_POSTAGENS).authenticated()
        .antMatchers(HttpMethod.DELETE, API_POSTAGENS).authenticated()
        .antMatchers(HttpMethod.GET, API_ALBUMS).permitAll()
        .antMatchers(HttpMethod.POST, API_ALBUMS).authenticated()
        .antMatchers(HttpMethod.DELETE, API_ALBUMS).authenticated()
        .anyRequest().denyAll();
  }

}

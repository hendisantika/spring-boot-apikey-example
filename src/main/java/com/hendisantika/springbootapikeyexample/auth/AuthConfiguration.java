package com.hendisantika.springbootapikeyexample.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-apikey-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/30/23
 * Time: 09:25
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class AuthConfiguration extends WebSecurityConfigurerAdapter {
    private static final String API_KEY_AUTH_HEADER_NAME = "API_KEY";

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter(API_KEY_AUTH_HEADER_NAME);
        filter.setAuthenticationManager(new ApiKeyAuthManager(dataSource));

        http.antMatcher("/api/v1/secure").
                csrf().
                disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and()
                .addFilter(filter)
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}

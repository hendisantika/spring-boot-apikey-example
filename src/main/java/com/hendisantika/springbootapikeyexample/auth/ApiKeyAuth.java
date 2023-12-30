package com.hendisantika.springbootapikeyexample.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-apikey-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/30/23
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class ApiKeyAuth extends AbstractAuthenticationToken {

    private final String apiKey;

    public ApiKeyAuth(String apiKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiKey = apiKey;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return apiKey;
    }
}

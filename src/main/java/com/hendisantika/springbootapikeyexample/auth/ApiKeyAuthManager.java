package com.hendisantika.springbootapikeyexample.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-apikey-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/30/23
 * Time: 09:15
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class ApiKeyAuthManager implements AuthenticationManager {

    private final LoadingCache<String, Boolean> keys;

}

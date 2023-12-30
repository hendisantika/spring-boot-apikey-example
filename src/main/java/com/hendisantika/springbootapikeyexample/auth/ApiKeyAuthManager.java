package com.hendisantika.springbootapikeyexample.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

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

    public ApiKeyAuthManager(DataSource dataSource) {
        this.keys = CacheProperties.Caffeine.newBuilder()
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .build(new DatabaseCacheLoader(dataSource));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();

        if (!keys.get(principal)) {
            throw new BadCredentialsException("The API key was not found or not the expected value.");
        } else {
            authentication.setAuthenticated(true);
            return authentication;
        }
    }

    /**
     * Caffeine CacheLoader that checks the database for the api key if it not found in the cache.
     */
    private static class DatabaseCacheLoader implements CacheLoader<String, Boolean> {
        private final DataSource dataSource;

        DatabaseCacheLoader(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public Boolean load(String key) throws Exception {
            log.info("Loading api key from database: [key: {}]", key);

            try (Connection conn = dataSource.getConnection()) {
                try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM auth WHERE api_key = ?")) {
                    ps.setObject(1, UUIDUtil.fromHex(key));

                    try (ResultSet rs = ps.executeQuery()) {
                        // Valid API Key
                        // Invalid API Key
                        return rs.next();
                    }
                }
            } catch (Exception e) {
                log.error("An error occurred while retrieving api key from database", e);
                return false;
            }
        }
    }

}

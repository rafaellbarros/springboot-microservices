package br.com.rafaellbarros.backend.security.config;

import br.com.rafaellbarros.backend.core.property.JwtConfiguration;
import br.com.rafaellbarros.security.SecurityTokenConfig;
import br.com.rafaellbarros.security.filter.JwtTokenAuthorizationFilter;


import br.com.rafaellbarros.security.token.converter.TokenConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {

    private final TokenConverter tokenConverter;

    public SecurityCredentialsConfig(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
        super(jwtConfiguration);
        this.tokenConverter = tokenConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.addFilterAfter(new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
               UsernamePasswordAuthenticationFilter.class);
       super.configure(http);

    }
}

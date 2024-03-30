package br.com.rafaelbarros.gateway.security.config;

import br.com.rafaelbarros.gateway.security.filter.GatewayJwtTokenAuthorizationFilter;
import br.com.rafaellbarros.domain.core.property.JwtConfiguration;
import br.com.rafaellbarros.security.config.SecurityTokenConfig;
import br.com.rafaellbarros.security.token.converter.TokenConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends SecurityTokenConfig {
    private final TokenConverter tokenConverter;

    public SecurityConfig(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
        super(jwtConfiguration);
        this.tokenConverter = tokenConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new GatewayJwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
                UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }
}

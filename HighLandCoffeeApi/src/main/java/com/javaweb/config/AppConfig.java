package com.javaweb.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.javaweb.service.CustomerUserDetails;

@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

    private final CustomerUserDetails customerUserDetails;
    
    public AppConfig(CustomerUserDetails customerUserDetails) {
        this.customerUserDetails = customerUserDetails;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/admin/product/find/{productId}").hasAnyAuthority("CUSTOMER","STAFF","ADMIN")
            .antMatchers("/api/admin/order/{order_id}/find").hasAnyAuthority("CUSTOMER","STAFF","ADMIN")
            .antMatchers("/api/admin/order/status").hasAnyAuthority("ADMIN","STAFF","CUSTOMER")   
            .antMatchers("/api/admin/**").hasAnyAuthority("ADMIN","STAFF")
                .antMatchers("/api/users/find").hasAnyAuthority("STAFF","ADMIN","CUSTOMER")
                .antMatchers("/api/users/all").hasAnyAuthority("STAFF","ADMIN","CUSTOMER")
                .antMatchers("/api/**").hasAnyAuthority("CUSTOMER")
                .anyRequest().permitAll()
            .and()
            .addFilterBefore(new JwtTokenValidator(), UsernamePasswordAuthenticationFilter.class)
            .csrf().disable()
            .cors().configurationSource(corsConfigurationSource())
            .and().httpBasic()
            .and().formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetails);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000", // React
            "http://localhost:5173",
            "http://localhost:4200" // Angular
        ));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setMaxAge(3600L);

        return request -> configuration;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

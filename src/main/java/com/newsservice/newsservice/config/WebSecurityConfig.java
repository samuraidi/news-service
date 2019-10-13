package com.newsservice.newsservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/news/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/role").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/role/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/role/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/news").hasAnyRole("PUBLISHER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/news/**").hasAnyRole("PUBLISHER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/news/**").hasAnyRole("PUBLISHER", "ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}

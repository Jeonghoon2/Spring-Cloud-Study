package com.example.userserivce.security;

import com.example.userserivce.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment env;


    public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, Environment env) {
        this.env = env;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  //권환
        http.csrf().disable();
        /*http.authorizeHttpRequests().antMatchers("/users/**").permitAll();*/
        http.authorizeRequests().antMatchers("/actuator/**").permitAll();
//      연구실 아이피
        http.authorizeRequests().antMatchers("/**")
                .hasIpAddress("192.168.0.15")
                .and()
                .addFilter(getAuthenticationFilter());
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        //        authenticationFilter.setAuthenticationManager(authenticationManager());

        return new AuthenticationFilter(authenticationManager(), userService, env);
    }

    //select pwd from users where email = ?
    //db_pwd(encrypted) == input_pwd(encrypted)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //인증
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}

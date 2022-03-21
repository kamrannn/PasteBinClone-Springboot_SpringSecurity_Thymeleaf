package com.app.pastebinclone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userService")
    @Autowired
    UserDetailsService userDetailsService;

    /**
     * This method is getting used by the Spring security to decrypt the passwords that are stored in encrypted
     * form in our database.
     * The passwords are getting encrypted by this same method while registering a user.
     */
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * This method is getting used by the Spring security to authenticate the user
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    /**
     * This method is getting used by the Spring security to authorize the user
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/users/register/**", "/login-error/**", "/login/**", "/users/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/users/home")
                .failureUrl("/login-error")
                .and()
                .logout()
                .logoutSuccessUrl("/login");

        http.csrf().disable();
    }

    /**
     * This method is getting used by the Spring security to ignore applying security to the following paths
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2/**", "/resources/**", "/static/**"); // #3
    }
}

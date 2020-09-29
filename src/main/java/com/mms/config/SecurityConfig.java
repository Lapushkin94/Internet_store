package com.mms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import static org.hibernate.criterion.Restrictions.and;

@Configuration
@EnableWebSecurity
// @ComponentScan("com.mms.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private AuthProviderImpl authProvider;
//
//    @Autowired
//    public void setAuthProvider(AuthProviderImpl authProvider) {
//        this.authProvider = authProvider;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                    .antMatchers("/signIn", "/login").anonymous()
//                    .antMatchers("/order").authenticated()
//                .and()
//                    .csrf().disable()
//                    .formLogin()
//                    .loginPage("/login")
//                    .loginProcessingUrl("/login/process")
//                    .usernameParameter("email")
//                .and()
//                    .logout();
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/catalog").hasRole("MANAGER")
                    .antMatchers("/contacts").hasRole("ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/showMyLoginPage")
                    .loginProcessingUrl("/authenticateTheClient")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 //       auth.authenticationProvider(authProvider);
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("john").password("123").roles("EMPLOYEE"))
                .withUser(users.username("mary").password("123").roles("MANAGER"))
                .withUser(users.username("susan").password("123").roles("ADMIN"));
    }

}

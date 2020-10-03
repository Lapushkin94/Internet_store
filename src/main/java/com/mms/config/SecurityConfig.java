package com.mms.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/signIn", "/signUp", "/signUpPage").anonymous()
                    .antMatchers("/logout", "/myProfile/**", "/order/**").authenticated()
                    .antMatchers("/clientControl/**").access("hasRole('ADMIN')")
                    .antMatchers("/orderList", "/catalog/editProduct/**", "/catalog/addProduct", "/catalog/delete/**")
                        .access("hasRole('ADMIN') or hasRole('MANAGER')")
                .and()
                    .csrf().disable()
                    .formLogin()
                    .loginPage("/signIn")
                    .loginProcessingUrl("/signIn/process")
                    .usernameParameter("email")
                .and()
                    .logout()
                    .logoutSuccessUrl("/logoutSuccessPage")
                .and()
                    .exceptionHandling().accessDeniedPage("/accessDenied");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

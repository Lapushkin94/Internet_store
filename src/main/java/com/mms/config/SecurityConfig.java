package com.mms.config;
import com.mms.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.mms.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthProviderImpl authProvider;

    @Autowired
    public void setAuthProvider(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/signIn", "/signUp").anonymous()
                    .antMatchers("/").permitAll()
                .and()
                    .csrf().disable()
                    .formLogin()
                    .loginPage("/signIn")
                    .loginProcessingUrl("/signIn/process")
                    .usernameParameter("email")
                .and()
                    .logout()
                    .logoutSuccessUrl("/logout")
                .and()
                    .exceptionHandling().accessDeniedPage("/accessDenied");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
}

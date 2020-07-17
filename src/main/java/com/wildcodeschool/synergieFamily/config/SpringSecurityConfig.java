package com.wildcodeschool.synergieFamily.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/image/*").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/init").permitAll()
                .antMatchers("/autolog").permitAll()
                .antMatchers("/activity-leader-creation").hasAnyRole("ADMIN", "COORDINATEUR")
                .antMatchers("/activity-leader-email/*").hasAnyRole("ADMIN", "COORDINATEUR")
                .antMatchers("/activity-leader-management-email").hasAnyRole("ADMIN", "COORDINATEUR")
                .antMatchers("/filter-email").hasAnyRole("ADMIN","COORDINATEUR")
                .antMatchers("/activity-leader-modification/*").hasAnyRole("ADMIN", "COORDINATEUR")
                .antMatchers("/activity-leader-edit/**").permitAll()
                .antMatchers("/activity-leader-management").hasAnyRole("ADMIN", "COORDINATEUR")
                .antMatchers("/filter").hasAnyRole("ADMIN", "COORDINATEUR")
                .antMatchers("/profile").hasAnyRole("ADMIN", "COORDINATEUR")
                .antMatchers("/user-creation").hasRole("ADMIN")
                .antMatchers("/user-management").hasRole("ADMIN")
                .antMatchers("/activity-leader/disable").hasAnyRole("ADMIN", "COORDINATEUR")
                .antMatchers("/user/disable").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/profile", true)
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}

package com.example.springProject.springProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**/
/*OverWriting METHOD*/
/**/

@Configuration
@EnableWebSecurity
public class config extends WebSecurityConfigurerAdapter {

    /*Fetching getUserDetailsService from springSecurity DAO UserDetailsService interface*/
    @Bean
    public UserDetailsService getUserDetailsService() {
        return new userDetailsServiceImplement();
    }

    /*Fetching springSecurity BCryptPasswordEncoder for password encryption*/
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }


    /*Method Configuration*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider()); /*provides authenticationManagerBuilder*/
    }

    /*http spring security request handler*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")    /*set role as admin in springsecurity*/
                .antMatchers("/user/**").hasRole("USER")         /*set role as user in springsecurity*/
                .antMatchers("/**").permitAll().and().formLogin().loginPage("/Login")     /*other pages are permitted for all, set loginPage as "/Login" defined in HomeController*/
                .loginProcessingUrl("/Login").defaultSuccessUrl("/user/user_dashboard")
                .and().csrf().disable();
    }
}

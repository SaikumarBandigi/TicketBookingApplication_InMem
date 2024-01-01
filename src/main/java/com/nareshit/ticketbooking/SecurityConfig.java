package com.nareshit.ticketbooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
/*
 * @EnableWebSecurity ==> This Annotation will load SecurityConfig as default Security filter in place of WebSecurity
 * 							ConfigureAdapter
 *
 */
public class SecurityConfig {


    //Authentication Logic
    /*
     * Create two users and map these users to two roles
     *    1) username ==>  nareshitadmin , password ==> nareshitadmin and role ==> ADMIN
     *    2) username ==> nareshitguest , password ==> nareshitguest and role ==> GUEST
     *
     */


    @Autowired
    public void configure_global(AuthenticationManagerBuilder authObj) throws Exception {


        //By default spring boot security framework does not take plain text password due to security reasons
        //It should be encoded ==> converting into non readable format
        authObj.inMemoryAuthentication().withUser("nareshitadmin").password("{noop}nareshitadmin")
                .roles("ADMIN");


        authObj.inMemoryAuthentication().withUser("nareshitguest").password("{noop}nareshitguest")
                .roles("GUEST");


    }


    //Authorization Logic

    //ADMIN Role can access all tickets and individual ticket
    //Guest Role can access only individual ticket
    //SecurityFilterChain ==> Default interface for having authorization logic

    @Bean
    public SecurityFilterChain configure(HttpSecurity authorizationObj) throws Exception {

        //http://localhost:8080/ticket/all ==> All Tickets -> GET
        // or
        //http://localhost:8080/ticket/1 -> DELETE
        //formLogin ==> Redirect to Login Page

        /* // won't work for this
         authorizationObj.csrf().disable() //This line i will explain in tomarrow class
                .authorizeRequests().antMatchers("/ticket/**").hasAnyRole("ADMIN")
                .and().formLogin();

         */
        authorizationObj.csrf().disable() //This line i will explain in tomarrow class
                .authorizeRequests().antMatchers("/ticket/**").hasAnyRole("ADMIN")
                .and().formLogin();


        //http://localhost:8080/ticket/get/1 ==> Retrieving Individual ticket
        authorizationObj.csrf().disable()
                .authorizeRequests().antMatchers("/ticket/get/**").hasAnyRole( "GUEST")
                .and().formLogin();


        return authorizationObj.build();

    }


}

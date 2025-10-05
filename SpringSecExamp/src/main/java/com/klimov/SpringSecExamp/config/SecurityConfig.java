package com.klimov.SpringSecExamp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    //user and password with our AuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    /*//user and password without database
    @Bean
    public UserDetailsService userDetailsService (){

        UserDetails user1 = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username("user")
                .password("123")
                .roles("USER")
                .build();

        UserDetails user2 = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

    return  new InMemoryUserDetailsManager(user1,user2);
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

 return  httpSecurity
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request-> request.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session
                        ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    /*   the same as above

                  httpSecurity.csrf(customizer -> customizer.disable());
                          *//*     These rows are the same as above one row
                          Customizer<CsrfConfigurer<HttpSecurity>> custCsrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {
                                @Override
                                public void customize(CsrfConfigurer<HttpSecurity> customizer) {
                                    customizer.disable();
                                }
                            };
                            httpSecurity.csrf(custCsrf);
                            *//*
        //for authorization form
        httpSecurity.authorizeHttpRequests(request-> request.anyRequest().authenticated());
        //only for web form login
        //httpSecurity.formLogin(Customizer.withDefaults());
        //for basic authentication (postman)
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();*/

    }
}

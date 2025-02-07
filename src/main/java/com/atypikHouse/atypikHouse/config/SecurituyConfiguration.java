package com.atypikHouse.atypikHouse.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity



public class SecurituyConfiguration extends  WebSecurityConfigurerAdapter {


    @Autowired
private UserDetailsService userDetailsService;
@Autowired
 private  BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void  configure(AuthenticationManagerBuilder auth) throws  Exception {
        //auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
  protected void  configure(HttpSecurity http) throws  Exception{
     http.cors();
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
     // http.formLogin()
      and()
   .authorizeRequests().antMatchers("/login/**","/register/**").
             permitAll()
      .antMatchers(HttpMethod.POST,"/tasks").hasAnyAuthority("USER")
        .anyRequest().authenticated().
                and()
        .addFilter(new JWTAuthenticationFilter(authenticationManager()))
    .addFilterBefore(new JWTAuthorizationFilter(),
             UsernamePasswordAuthenticationFilter.class).csrf().disable();




    }
  }





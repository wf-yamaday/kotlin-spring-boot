package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfig: WebSecurityConfigurerAdapter(

) {


    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            "/**/favicon.ico",
            "/images/**",
            "/css/**",
            "/javascript/**",
            "/webjars/**"
        )
    }

    override fun configure(http: HttpSecurity) {

        http.authorizeRequests()
            .antMatchers("/auth").permitAll()
            .anyRequest().authenticated()
            .and().logout()
            .and().csrf().disable()
    }

    fun configureAuth(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService()
            .passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
package com.example.demo.config

import com.example.demo.helper.jwt.JWTAuthenticationFilter
import com.example.demo.helper.jwt.JWTAuthorizationFilter
import com.example.demo.helper.jwt.SIGN_UP_URL
import com.example.demo.service.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfig(val userDetailsService: UserDetailsServiceImpl) : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            "/**/favicon.ico",
            "/images/**",
            "/css/**",
            "/javascript/**",
            "/webjars/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs"
        )
    }

    override fun configure(http: HttpSecurity) {

        http.authorizeRequests()
            .antMatchers(SIGN_UP_URL, "/api/users", "/login").permitAll()
            .anyRequest().authenticated()
            .and().logout()
            .and().csrf().disable()
            .addFilter(JWTAuthenticationFilter(authenticationManager()))
            .addFilter(JWTAuthorizationFilter(authenticationManager()))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Throws(Exception::class)
    @Autowired
    fun configureAuth(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
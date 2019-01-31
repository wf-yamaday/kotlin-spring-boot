package com.example.demo.helper.jwt

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter
    constructor(
        authenticationManager,
        bCryptPasswordEncoder,
        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/auth", "POST"))

    ) : UsernamePasswordAuthenticationFilter {

    private val LOGGER : Logger = LoggerFactory.getLogger(JWTAuthenticationFilter::class.java),
    private val authenticationManager: AuthenticationManager,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        return super.attemptAuthentication(request, response)
    }

}
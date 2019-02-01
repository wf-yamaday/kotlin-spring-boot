package com.example.demo.helper.jwt

import com.example.demo.model.dto.user.UserResource
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {
    private val LOGGER : Logger = LoggerFactory.getLogger(JWTAuthenticationFilter::class.java)

    private var bCryptPasswordEncoder = BCryptPasswordEncoder()
    init{
        this.authenticationManager = authenticationManager
        this.bCryptPasswordEncoder = bCryptPasswordEncoder
        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher(SIGN_UP_URL, "POST"))
    }

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        println("attempt auth")
        val creds = ObjectMapper().readValue(request.inputStream, UserResource::class.java)
        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                creds.userId,
                creds.password,
                emptyList<GrantedAuthority>()
            )
        )
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        println("success handler")
        val JWT = Jwts.builder()
            .setSubject((authResult.principal as org.springframework.security.core.userdetails.User).username)
            .setExpiration(Date(System.currentTimeMillis() +  EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact()
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT)
    }
}
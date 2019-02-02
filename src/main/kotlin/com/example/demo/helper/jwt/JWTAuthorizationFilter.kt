package com.example.demo.helper.jwt

import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager) : BasicAuthenticationFilter(authenticationManager) {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader(HEADER_STRING)
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response)
            return
        }

        val authentication = getAuthenticaion(request)

        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    fun getAuthenticaion(request: HttpServletRequest): Authentication? {
        val token = request.getHeader(HEADER_STRING)
        if (token != null) {
            val user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .body
                        .subject
            return if (user != null)
                UsernamePasswordAuthenticationToken(user, null, emptyList<GrantedAuthority>())
            else
                null
        }
        return null
    }
}
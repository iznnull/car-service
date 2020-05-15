package com.example.carservice.jwt

import com.google.common.base.Strings
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenVerifier : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authorizationHeader: String = request.getHeader("Authorization")
        val token: String = authorizationHeader.replace("Bearer ", "")

        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        try {
            val key = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure"
            val claimsJws = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(key.toByteArray())).parseClaimsJws(token)
            val body = claimsJws.body
            val username: String = body.subject
            body.get("authorities")
            val authorities = body["authorities"] as List<Map<String?, String?>?>?
            val simpleGrantedAuthority = authorities!!.stream().map { m -> SimpleGrantedAuthority(m?.get("authority")) }.collect(Collectors.toSet())
            val auth: Authentication = UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthority)
            SecurityContextHolder.getContext().authentication = auth

        } catch (e: JwtException) {
            throw IllegalStateException(String.format("Token: %s cannot be trusted", token))
        }
        filterChain.doFilter(request, response)
    }

}
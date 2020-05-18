package com.example.carservice.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
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

class JwtTokenVerify : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token: String = request.getHeader("Authorization").toString().replace("Bearer ", "")
        val key = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure"

        val claimsJws: Jws<Claims> = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(key.toByteArray()))
                .parseClaimsJws(token)

        val authorities: (List<Map<String, String>>) = claimsJws.body["authorities"] as List<Map<String, String>>


        val simpleGrantedAuthorities: Set<SimpleGrantedAuthority> = authorities.stream()
                .map { m -> SimpleGrantedAuthority(m.get("authority")) }
                .collect(Collectors.toSet())

        val authentication: Authentication = UsernamePasswordAuthenticationToken(
                claimsJws.body.subject,
                null,
                simpleGrantedAuthorities
        )

        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }

}
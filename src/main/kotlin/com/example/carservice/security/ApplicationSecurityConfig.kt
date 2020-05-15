package com.example.carservice.security

import com.example.carservice.auth.UserService
import com.example.carservice.jwt.JwtTokenVerify
import com.example.carservice.jwt.JwtUsernameAndPasswordAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class ApplicationSecurityConfig(private val passwordEncoder: PasswordEncoder, private val userService: UserService) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(JwtTokenVerify(), JwtUsernameAndPasswordAuthenticationFilter::class.java)
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/cars/**").hasAnyRole(UserRole.ADMIN.name)
                .antMatchers(HttpMethod.POST, "/cars/**").hasAnyRole(UserRole.ADMIN.name)
                .antMatchers(HttpMethod.PUT, "/cars/**").hasAnyRole(UserRole.ADMIN.name)
                .antMatchers(HttpMethod.GET, "/cars/**").hasAnyRole(UserRole.ADMIN.name, UserRole.USER.name)
                .anyRequest()
                .authenticated()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(daoAuthProvider())
    }

    @Bean
    fun daoAuthProvider(): DaoAuthenticationProvider? {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(passwordEncoder)
        provider.setUserDetailsService(userService)
        return provider
    }
}
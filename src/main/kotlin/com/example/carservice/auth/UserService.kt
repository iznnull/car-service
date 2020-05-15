package com.example.carservice.auth

import com.example.carservice.model.User
import com.example.carservice.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user: User? = userRepository.findByUsername(username)

        return org.springframework.security.core.userdetails.User(
                user?.username,
                passwordEncoder.encode(user?.password),
                true,
                true,
                true,
                true,
                listOf(SimpleGrantedAuthority(user?.role)))
    }
}
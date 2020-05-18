package com.example.carservice.auth

import com.example.carservice.model.User
import com.example.carservice.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) {
            throw IllegalArgumentException("Username is null")
        }

        val user: Optional<User> = userRepository.findById(username)

        if (user.isPresent) {
            return org.springframework.security.core.userdetails.User(
                    user.get().username,
                    passwordEncoder.encode(user.get().password),
                    true,
                    true,
                    true,
                    true,
                    listOf(SimpleGrantedAuthority(user.get().role)))
        } else {
            throw UsernameNotFoundException("Username not found")
        }

    }
}
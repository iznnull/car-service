package com.example.carservice.security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

enum class UserRole(private val permissions: MutableSet<UserPermission>) {
    USER(mutableSetOf(UserPermission.CAR_READ)),
    ADMIN(mutableSetOf(UserPermission.CAR_READ, UserPermission.CAR_WRITE));

    open fun getGrantedAuthorities(): Set<SimpleGrantedAuthority>? {
        val permissions = permissions.stream().map { p: UserPermission -> SimpleGrantedAuthority(p.name) }.collect(Collectors.toSet())
        permissions.add(SimpleGrantedAuthority("ROLE_$name"))
        return permissions
    }
}
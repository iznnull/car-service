package com.example.carservice.security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

enum class UserRole(private val permissions: MutableSet<ApplicationUserPermission>) {
    USER(mutableSetOf(ApplicationUserPermission.CAR_READ)),
    ADMIN(mutableSetOf(ApplicationUserPermission.CAR_READ, ApplicationUserPermission.CAR_WRITE));

    open fun getGrantedAuthorities(): Set<SimpleGrantedAuthority>? {
        val permissions = permissions.stream().map { p: ApplicationUserPermission -> SimpleGrantedAuthority(p.name) }.collect(Collectors.toSet())
        permissions.add(SimpleGrantedAuthority("ROLE_$name"))
        return permissions
    }
}
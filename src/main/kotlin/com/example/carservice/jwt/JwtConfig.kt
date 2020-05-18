package com.example.carservice.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "application.jwt")
data class JwtConfig(
        var secretKey: String = "",
        var tokenPrefix: String = "",
        var tokenExpirationAfterDays: String = ""
)

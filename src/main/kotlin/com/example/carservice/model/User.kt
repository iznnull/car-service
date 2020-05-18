package com.example.carservice.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(
        @Id
        @Column(nullable = false)
        var username: String,

        @JsonIgnore
        @Column(nullable = false)
        var password: String,

        @Column(nullable = false)
        var role: String
)

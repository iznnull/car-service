package com.example.carservice.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@SequenceGenerator(name = "IdSequenceUser")
data class User(
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdSequenceUser")
        @Id
        var id: Long? = null,

        @Column(nullable = false)
        var username: String,

        @JsonIgnore
        @Column(nullable = false)
        var password: String,

        @Column(nullable = false)
        var role: String
)

package com.example.carservice.model

import javax.persistence.*

@Table(name = "CARS")
@Entity
@SequenceGenerator(name = "IdSequenceCar")
data class Car (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdSequenceCar")
    var id: Long? = null,

    @Column(nullable = false)
    var brand: String,

    @Column(nullable = false)
    var model: String,

    @Column(nullable = false)
    var color: String,

    @Column(nullable = false)
    var price: Double)

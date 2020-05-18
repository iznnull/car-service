package com.example.carservice.repository

import com.example.carservice.model.Car
import org.springframework.data.repository.CrudRepository

interface CarRepository : CrudRepository<Car, Long>

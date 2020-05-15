package com.example.carservice.controller

import com.example.carservice.dto.CarDto
import com.example.carservice.mappers.toCarModel
import com.example.carservice.model.Car
import com.example.carservice.repository.CarRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cars")
class CarController(private val carRepository: CarRepository) {

    @GetMapping()
    fun findAll(): Iterable<Car> = carRepository.findAll()

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long) = carRepository.findById(id)

    @PostMapping()
    fun addCar(@RequestBody carDto: CarDto) = carRepository.save(carDto.toCarModel())
}

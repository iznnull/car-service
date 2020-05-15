package com.example.carservice.controller

import com.example.carservice.dto.CarDto
import com.example.carservice.mappers.CarMapper
import com.example.carservice.model.Car
import com.example.carservice.repository.CarRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarController(private val carRepository: CarRepository, private val carMapper: CarMapper) {

    @GetMapping()
    fun findAll(): Iterable<Car> = carRepository.findAll()

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long) = carRepository.findById(id)

    @PostMapping()
    fun addCar(@RequestBody carDto: CarDto) = carRepository.save(carMapper.toCarModel(carDto))

//    @PutMapping(path = ["{carId}"])
//    fun updateCar(@PathVariable carId: Long, @RequestBody carDto: CarDto) = carRepository.save(carId, carMapper.toCarModel(carDto))

    @DeleteMapping(path = ["{carId}"])
    fun deleteCar(@PathVariable carId: Long) = carRepository.deleteById(carId)
}

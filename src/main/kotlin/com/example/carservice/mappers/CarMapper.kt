package com.example.carservice.mappers

import com.example.carservice.dto.CarDto
import com.example.carservice.model.Car
import org.springframework.stereotype.Component

@Component
class CarMapper {
    fun toCarModel(carDto: CarDto) = Car(
            brand = carDto.brand,
            model = carDto.model,
            color = carDto.color,
            price = carDto.price
    )

    fun toCarDto(car: Car) = CarDto(
            brand = car.brand,
            model = car.model,
            color = car.color,
            price = car.price
    )
}

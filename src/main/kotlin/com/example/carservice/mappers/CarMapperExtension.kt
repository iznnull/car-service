package com.example.carservice.mappers

import com.example.carservice.dto.CarDto
import com.example.carservice.model.Car

fun CarDto.toCarModel(): Car = Car(brand = brand, model = model, color = color, price = price)
fun Car.toCarDto(): CarDto = CarDto(brand = brand, model = model, color = color, price = price)


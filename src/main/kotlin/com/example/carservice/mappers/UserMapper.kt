package com.example.carservice.mappers

import com.example.carservice.dto.UserDto
import com.example.carservice.model.User
import org.springframework.stereotype.Component

@Component
class UserMapper {

    fun toUserModel(userDto: UserDto) = User(
            username = userDto.username,
            password = userDto.password,
            role = userDto.role
    )
}
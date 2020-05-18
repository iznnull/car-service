package com.example.carservice.repository

import com.example.carservice.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String>

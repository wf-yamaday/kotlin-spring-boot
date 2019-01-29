package com.example.demo.repository

import com.example.demo.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
    fun getByIdOrNull(id: String): User?
}
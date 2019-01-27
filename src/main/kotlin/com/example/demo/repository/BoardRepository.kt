package com.example.demo.repository

import com.example.demo.model.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Int> {
    fun getByIdOrIdNull(id: Int): Board?
}
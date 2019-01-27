package com.example.demo.repository

import com.example.demo.model.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Int> {
    fun findAllByBoardId(id: Int): List<Post>
    fun getByIdOrIdNull(id: Int): Post?
}
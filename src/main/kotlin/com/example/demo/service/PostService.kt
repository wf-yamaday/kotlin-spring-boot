package com.example.demo.service

import com.example.demo.entity.Post
import com.example.demo.repository.PostRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PostService(
    val postRepository: PostRepository
) {
    fun findAll(): List<Post> = postRepository.findAll()
    // Optional型はIDで見つけられなくてもnull処理できる
    fun findById(id: Int): Optional<Post> = postRepository.findById(id)
    fun save(post: Post) {
        postRepository.save(post)
    }
}
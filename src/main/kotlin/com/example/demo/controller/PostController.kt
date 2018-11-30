package com.example.demo.controller

import com.example.demo.entity.Post
import com.example.demo.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Optional

@RestController
@RequestMapping("/api/posts")
class PostController(
    val postService: PostService
) {
    @GetMapping("/", "")
    fun getPosts(): ResponseEntity<List<Post>> {
        return ResponseEntity.ok(postService.findAll())
    }
    @GetMapping("/{id}")
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    fun getPost(@PathVariable id: Int): ResponseEntity<Optional<Post>> {
        return if (postService.findById(id).isPresent) {
            ResponseEntity.ok().body(postService.findById(id))
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
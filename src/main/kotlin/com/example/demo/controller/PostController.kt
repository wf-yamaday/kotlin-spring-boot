package com.example.demo.controller

import com.example.demo.dto.NotFoundException
import com.example.demo.entity.Post
import com.example.demo.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
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
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun getPost(@PathVariable id: Int): ResponseEntity<Optional<Post>> {
        return if (postService.findById(id).isPresent) {
            ResponseEntity.ok().body(postService.findById(id))
        } else throw NotFoundException("not found")
    }
}
package com.example.demo.controller

import com.example.demo.dto.NotFoundException
import com.example.demo.dto.post.PostResource
import com.example.demo.model.entity.Post
import com.example.demo.service.PostService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/api/posts")
class PostController(
    val postService: PostService
) {
    @ApiOperation(value = "投稿全件取得", notes = "投稿を全て取得します．", consumes = "application/json")
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

    @PatchMapping("/{postId}")
    fun updatePost(@PathVariable postId: Int, @RequestBody postResource: PostResource): ResponseEntity<String> {
        postService.update(postId, postResource)
        return ResponseEntity.ok("success")
    }

    @DeleteMapping("/{postId}")
    fun delete(@PathVariable postId: Int): ResponseEntity<String> {
        postService.delete(postId)
        return ResponseEntity.ok("success")
    }
}
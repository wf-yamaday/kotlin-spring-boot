package com.example.demo.controller

import com.example.demo.dto.BadRequestException
import com.example.demo.dto.NotFoundException
import com.example.demo.dto.post.PostResource
import com.example.demo.entity.Board
import com.example.demo.entity.Post
import com.example.demo.service.BoardService
import com.example.demo.service.PostService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/api/posts")
class PostController(
    val postService: PostService,
    val boardService: BoardService
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
    @PostMapping("/", "")
    fun createPost(@Validated @RequestBody postResource: PostResource, errors: Errors): ResponseEntity<String> {
        if (errors.hasErrors()) throw BadRequestException("validation error")
        val board = boardService.findOne(postResource.boardId)
//        val post =
//        post.modelMapper(postResource, board)
//        postService.save()
        return ResponseEntity.ok("success")
    }
}
package com.example.demo.controller

import com.example.demo.dto.BadRequestException
import com.example.demo.dto.NotFoundException
import com.example.demo.dto.post.PostResource
import com.example.demo.model.dto.board.BoardResource
import com.example.demo.model.entity.Board
import com.example.demo.model.entity.Post
import com.example.demo.service.BoardService
import com.example.demo.service.PostService
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/boards")
class BoardController(
    val boardService: BoardService,
    val postService: PostService
) {

    @ApiOperation(value = "掲示板全件取得", notes = "掲示板を全て取得します．", consumes = "application/json")
    @GetMapping("/", "")
    fun getBoards(): ResponseEntity<List<Board>> {
        return ResponseEntity.ok(boardService.findAll())
    }

    @GetMapping("/{boardId}")
    fun getBoard(@PathVariable boardId: Int): ResponseEntity<Board> {
        val board: Board? = boardService.findOne(boardId)
        if (board === null) throw NotFoundException("Not found")
        return ResponseEntity.ok(board)
    }

    @PostMapping("")
    fun saveBoard(@Validated @RequestBody boardResource: BoardResource, error: Errors): ResponseEntity<String> {
        if (error.hasErrors()) throw BadRequestException("validation error")
        boardService.save(boardResource)
        return ResponseEntity.ok("success")
    }

    @PatchMapping("/{boardId}")
    fun updateBoard(@PathVariable boardId: Int, @RequestBody boardResource: BoardResource, error: Errors): ResponseEntity<String> {
        if (error.hasErrors()) throw BadRequestException("validation error")
        boardService.update(boardId, boardResource)
        return ResponseEntity.ok("success")
    }

    @PostMapping("/{boardId}/posts")
    fun savePost(@PathVariable boardId: Int, @RequestBody postResource: PostResource): ResponseEntity<String> {
        val board: Board? = boardService.findOne(boardId)
        if (board === null) throw NotFoundException("Not found")
        postService.save(postResource, board)
        return ResponseEntity.ok("success")
    }

    @GetMapping("/{boardId}/posts", "/{boardId}/posts/")
    fun getPosts(@PathVariable boardId: Int): ResponseEntity<List<Post>> {
        return ResponseEntity.ok(postService.findAllByBoardId(boardId))
    }
}
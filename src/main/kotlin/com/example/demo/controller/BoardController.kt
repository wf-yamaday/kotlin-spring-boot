package com.example.demo.controller


import com.example.demo.dto.BadRequestException
import com.example.demo.model.dto.board.BoardResource
import com.example.demo.model.entity.Board
import com.example.demo.service.BoardService
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/boards")
class BoaedController(
    val boardService: BoardService
) {
    @ApiOperation(value = "掲示板全件取得", notes = "掲示板を全て取得します．", consumes = "application/json")
    @GetMapping("/", "")
    fun getBoards(): ResponseEntity<List<Board>> {
        return ResponseEntity.ok(boardService.findAll())
    }
    fun getBoard(boardId: Int): ResponseEntity<Board> {
        return ResponseEntity.ok(boardService.findOne(boardId))
    }
    @PostMapping("/")
    fun saveBoard(@Validated @RequestBody boardResource: BoardResource, error: Errors) : ResponseEntity<String> {
        if (error.hasErrors()) throw BadRequestException("validation error")
        boardService.save(boardResource)
        return ResponseEntity.ok("success")
    }
}
package com.example.demo.controller

import com.example.demo.entity.Board
import com.example.demo.service.BoardService
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/boards")
class BoaedController(
    val boardService: BoardService
) {
    @ApiOperation(value = "掲示板全件取得", notes = "掲示板を全て取得します．", consumes = "application/json")
    @GetMapping("/","")
    fun getBoards(): ResponseEntity<List<Board>> {
        return ResponseEntity.ok(boardService.findAll())
    }
}
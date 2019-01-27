package com.example.demo.service

import com.example.demo.model.dto.board.BoardResource
import com.example.demo.model.entity.Board
import com.example.demo.repository.BoardRepository
import org.springframework.stereotype.Service

@Service
class BoardService(
    val boardsRepository: BoardRepository
) {
    fun findAll(): List<Board> = boardsRepository.findAll()
    fun findOne(boardId: Int): Board? = boardsRepository.getByIdOrIdNull(boardId)
    fun save(boardResource: BoardResource) {
        boardsRepository.save(boardResource.toBoard())
    }
    fun update(boardId: Int, boardResource: BoardResource) {
        var board = boardsRepository.getOne(boardId)
        board.title = boardResource.title
        board.description = boardResource.description
        println(board)
        boardsRepository.save(board)
    }
}
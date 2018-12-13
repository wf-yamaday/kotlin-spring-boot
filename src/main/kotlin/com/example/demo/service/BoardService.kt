package com.example.demo.service

import com.example.demo.entity.Board
import com.example.demo.repository.BoardRepository
import org.springframework.stereotype.Service

@Service
class BoardService(
    val boardsRepository: BoardRepository
) {
    fun findAll(): List<Board> = boardsRepository.findAll()
}
package com.example.demo.dto.post

import com.example.demo.entity.Board
import com.example.demo.service.BoardService
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Autowired
import java.io.Serializable
import javax.validation.constraints.Digits
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank

data class PostResource(
    @NotBlank
    @Max(128)
    @JsonProperty("title")
    var title: String,
    @NotBlank
    @JsonProperty("body")
    var body: String,
    @NotBlank
    @Digits(integer = 10, fraction = 0)
    @JsonProperty("board_id")
    var boardId: Int,

    var board: Board,
    @Autowired
    internal var boardService: BoardService
) : Serializable {
}

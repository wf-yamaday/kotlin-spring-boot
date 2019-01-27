package com.example.demo.model.dto.board

import com.example.demo.model.entity.Board
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import javax.validation.constraints.NotBlank

data class BoardResource(
    @NotNull
    @NotBlank
    var title: String,
    @NotNull
    @NotBlank
    var description: String
) : Serializable {
    fun toBoard(): Board {
        var board = Board()
        return board.copy(title = this.title, description = this.description)
    }
}
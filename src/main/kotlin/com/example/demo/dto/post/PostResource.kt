package com.example.demo.dto.post

import com.fasterxml.jackson.annotation.JsonProperty
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
    var boardId: Int
) : Serializable

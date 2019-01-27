package com.example.demo.dto.post

import com.example.demo.model.entity.Post
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank

data class PostResource(
    @NotBlank
    @Max(128)
    @JsonProperty("title")
    var title: String,
    @NotBlank
    @JsonProperty("body")
    var body: String
) : Serializable {
    fun toPost(): Post {
        var post = Post()
        return post.copy(title = this.title, body = this.body)
    }
}

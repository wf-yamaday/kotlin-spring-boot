package com.example.demo.entity

import com.example.demo.dto.post.PostResource
import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@Entity
@Table(name = "posts")
data class Post(
    @Id
    @GeneratedValue
    var id: Int = 0,
    @Column(name = "title", nullable = true)
    var title: String,
    @Column(name = "body", nullable = true)
    var body: String,
    @Column(name = "updated_at", nullable = false)
    var updated: LocalDateTime = LocalDateTime.now(),
    @Column(name = "created_at", nullable = false)
    var created: LocalDateTime = LocalDateTime.now(),
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, insertable = false, updatable = false, name = "boards_id")
    var board: Board
) : Serializable {
    @PrePersist
    fun prePersist() {
        created = LocalDateTime.now()
    }
    @PreUpdate
    fun preUpdate() {
        updated = LocalDateTime.now()
    }
    fun modelMapper(postResource: PostResource, board: Board) : Post {
        return Post(title = postResource.title, body = postResource.body, board = board)
    }
}
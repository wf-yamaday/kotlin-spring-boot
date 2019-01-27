package com.example.demo.model.entity

import com.example.demo.dto.post.PostResource
import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table

@Entity
@Table(name = "posts")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column(name = "title", nullable = true)
    var title: String = "",
    @Column(name = "body", nullable = true)
    var body: String = "",
    @Column(name = "updated_at", nullable = false)
    var updated: LocalDateTime = LocalDateTime.now(),
    @Column(name = "created_at", nullable = false)
    var created: LocalDateTime = LocalDateTime.now(),
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, insertable = true, updatable = false, name = "board_id")
    var board: Board? = null
) : Serializable {
    @PrePersist
    fun prePersist() {
        created = LocalDateTime.now()
    }
    @PreUpdate
    fun preUpdate() {
        updated = LocalDateTime.now()
    }
    fun modelMapper(postResource: PostResource, board: Board): Post {
        return Post(title = postResource.title, body = postResource.body, board = board)
    }
}
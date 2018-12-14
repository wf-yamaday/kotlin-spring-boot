package com.example.demo.entity

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table

@Entity
@Table(name = "boards")
data class Board(
    @Id
    @GeneratedValue
    var id: Int = 0,
    @Column(name = "title")
    var title: String,
    @Column(name = "description")
    var description: String,
    @Column(name = "created_at")
    var created: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at")
    var updated: LocalDateTime = LocalDateTime.now(),
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    var posts: List<Post>
) : Serializable {
    @PrePersist
    fun prePersist() {
        created = LocalDateTime.now()
    }
    @PreUpdate
    fun preUpdated() {
        updated = LocalDateTime.now()
    }
}
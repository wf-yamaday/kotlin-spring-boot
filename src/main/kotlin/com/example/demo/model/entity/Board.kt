package com.example.demo.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table

@Entity
@Table(name = "boards")
data class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column(name = "title")
    var title: String = "",
    @Column(name = "description")
    var description: String = "",
    @Column(name = "created_at")
    var created: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at")
    var updated: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    @JsonIgnore
    var posts: List<Post>? = null
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
package com.example.demo.entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
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
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    var posts: List<Post>
)
package com.example.demo.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id
    @Column(name = "user_id")
    var userId: String,
    @JsonIgnore
    @Column(name = "password")
    var password: String
) : Serializable {
}
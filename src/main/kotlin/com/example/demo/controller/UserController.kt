package com.example.demo.controller

import com.example.demo.model.dto.user.UserResource
import com.example.demo.service.UserDetailsServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController(
    val userService: UserDetailsServiceImpl,
    val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    @PostMapping("/auth")
    fun auth(@RequestBody userResource: UserResource) {

    }


    @PostMapping("/users")
    fun save(@RequestBody userResource: UserResource): ResponseEntity<String> {
        userResource.password = bCryptPasswordEncoder.encode(userResource.password)
        userService.save(userResource)
        return ResponseEntity.ok("success")
    }

}
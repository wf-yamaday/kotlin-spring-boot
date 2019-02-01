package com.example.demo.service

import com.example.demo.model.dto.user.UserResource
import com.example.demo.model.entity.User
import com.example.demo.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    val userRepository: UserRepository
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.getByUserId(username) ?: throw UsernameNotFoundException(username)
        return org.springframework.security.core.userdetails.User(user.userId, user.password, emptyList())
    }

    fun save(userResource: UserResource) {
        var user = User(userResource.userId, userResource.password)
        userRepository.save(user)
    }
}
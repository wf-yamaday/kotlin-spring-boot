package com.example.demo.service

import com.example.demo.model.entity.User
import com.example.demo.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImp(
    val userRepository: UserRepository
) : UserDetailsService{

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User? = userRepository.getByIdOrNull(username)?: throw UsernameNotFoundException(String.format("The username %s doesn't exist", username))
        return
    }
}
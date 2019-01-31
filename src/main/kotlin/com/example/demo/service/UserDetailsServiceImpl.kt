package com.example.demo.service

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
        val user: User? = userRepository.getByIdOrNull(username)
        if (user === null) throw UsernameNotFoundException(String.format("The username %s doesn't exist", username))
        return org.springframework.security.core.userdetails.User.withUsername(user.userId).password(user.password).authorities("ROLE_USER").build()
//        return org.springframework.security.core.userdetails.User(user.userId, user.password, AuthorityUtils.createAuthorityList("ROLE_USER"))
    }
}
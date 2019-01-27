package com.example.demo.service

import com.example.demo.dto.NotFoundException
import com.example.demo.dto.post.PostResource
import com.example.demo.model.entity.Board
import com.example.demo.model.entity.Post
import com.example.demo.repository.PostRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PostService(
    val postRepository: PostRepository
) {
    fun findAll(): List<Post> = postRepository.findAll()
    // Optional型はIDで見つけられなくてもnull処理できる
    fun findById(id: Int): Optional<Post> = postRepository.findById(id)
    fun findAllByBoardId(boardId: Int): List<Post> = postRepository.findAllByBoardId(boardId)
    fun save(post: Post) {
        postRepository.save(post)
    }
    fun save(postResource: PostResource, board: Board) {
        var post: Post = postResource.toPost()
        post.board = board
        postRepository.save(post)
    }

    fun update(postId: Int, postResource: PostResource) {
        var post: Post? = postRepository.getByIdOrIdNull(postId)
        post?.title = postResource.title
        post?.body = postResource.body
        if (post === null) throw NotFoundException("Not found")
        postRepository.save(post)
    }

    fun delete(postId: Int) {
        postRepository.deleteById(postId)
    }
}
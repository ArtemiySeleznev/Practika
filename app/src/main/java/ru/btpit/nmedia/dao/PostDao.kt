package ru.btpit.nmedia.dao

import ru.btpit.nmedia.activity.Post


interface PostDao {
    fun getAll(): List<Post>
    fun save(post:Post): Post
    fun likeById(id: Long)
    fun removeById(id: Long)
}

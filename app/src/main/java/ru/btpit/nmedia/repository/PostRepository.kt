package ru.btpit.nmedia.repository

import androidx.lifecycle.LiveData
import ru.btpit.nmedia.activity.Post

interface PostRepository {
   fun getAll(): LiveData<List<Post>>
   fun likeById(id: Long)
   fun shareById(id: Long)
   fun removeById(id: Long)
   fun save(post: Post)
}

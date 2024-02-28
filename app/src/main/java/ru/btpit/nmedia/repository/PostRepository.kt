package ru.btpit.nmedia.repository

import androidx.lifecycle.LiveData
import ru.btpit.nmedia.Post

interface PostRepository {
   fun getAll(): LiveData<List<Post>>
   fun likeById(id: Long)
   fun shareById(id: Long)
}

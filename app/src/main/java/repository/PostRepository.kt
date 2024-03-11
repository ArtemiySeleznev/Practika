package repository

import androidx.lifecycle.LiveData

import ru.btpit.nmedia.activity.Post

interface PostRepository {
   fun get(): LiveData<Post>
   fun like()
}
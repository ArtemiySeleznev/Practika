package ru.btpit.nmedia.repository

import androidx.lifecycle.LiveData
import ru.btpit.nmedia.MainActivity

interface PostRepository {
   fun get(): LiveData<MainActivity.Post>
   fun like()
}

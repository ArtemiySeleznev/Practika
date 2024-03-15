package ru.btpit.nmedia.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.btpit.nmedia.activity.Post
import ru.btpit.nmedia.db.AppDb
import ru.btpit.nmedia.repository.PostRepository
import ru.btpit.nmedia.repository.PostRepositoryInMemoryImpl
import ru.btpit.nmedia.repository.PostRepositorySQLiteImpl

private val empty = Post(
    id = 0,
    author = "",
    content = "",
    published = "",
    share = 0,
    likes = 0,
    prosmotr = 0,
    likedByMe = false,
)
class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositorySQLiteImpl(
        AppDb.getInstance(application).postDao
    )
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun save (){
        edited.value?.let{
            repository.save(it)
        }
        edited.value = empty
    }
    fun edit(post: Post) {
        edited.value = post
    }
    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }
}
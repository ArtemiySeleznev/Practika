package ru.btpit.nmedia

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.btpit.nmedia.databinding.ActivityMainBinding
import ru.btpit.nmedia.util.AndroidUtils
import ru.btpit.nmedia.viewModel.PostViewModel
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter


class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(object : OnInteractionListener{
            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }
            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }
            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
            }
        })



        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.save.setOnClickListener{
            with(binding.content){
                if (text.isNullOrBlank()){
                    Toast.makeText(
                        this@MainActivity,
                        "Не может быть пустым",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text.toString())
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
        viewModel.edited.observe(this){post ->
            if (post.id == 0L){
                return@observe
            }
            with(binding.content){
                requestFocus()
                setText(post.content)
            }

        }
    }



}



















    data class Post(
        val id: Long,
        val author: String,
        val content: String,
        val published: String,
        var share: Int,
        var likes: Int,
        val prosmotr: Int,
        var likedByMe: Boolean = false,
        val shareByMe: Boolean = false
    )






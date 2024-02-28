package ru.btpit.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.btpit.nmedia.databinding.ActivityMainBinding
import ru.btpit.nmedia.databinding.CardPostBinding
import ru.btpit.nmedia.viewModel.PostViewModel








class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) {posts ->
            binding.container.removeAllViews()
            posts.map { post ->
                CardPostBinding.inflate(layoutInflater, binding.container,true).apply {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                imageView4.setImageResource(
                    if (post.likedByMe) R.drawable.serd else R.drawable.hearth_svgrepo_com

                )
                textlike?.text = post.likes.toString()

                if (post.likes >= 1000) {
                    textlike.text = "${post.likes / 1000}.${post.likes % 1000 / 100}K"
                } else {
                    textlike.text
                }
                if (post.likes>= 10000){
                    textlike.text = "${post.likes / 1000}.${post.likes % 1000 / 100}K"
                }
                else  {
                    textlike.text
                }
                if (post.likes>= 1000000){
                    textlike.text = "${post.likes / 1000000}.${post.likes % 1000 / 100}М"
                }
                else  {
                    textlike.text
                }

                textshare.text = post.share.toString()
                if (post.share >= 1000) {
                    textshare.text = "${post.share / 1000}.${post.share % 1000 / 100}K"
                } else  {
                    textshare.text
                }
                if (post.share >= 10000){
                    textshare.text = "${post.share / 1000}.${post.share % 1000 / 100}K"
                }
                else  {
                    textshare.text
                }
                if (post.share>= 1000000){
                    textshare.text = "${post.share / 1000000}.${post.share % 1000 / 100}М"
                }
                else  {
                    textshare.text
                }
               imageView4.setOnClickListener{
                   viewModel.likeById(post.id)

               }
                    imageView5.setOnClickListener{
                        viewModel.shareById(post.id)

                    }
            }.root


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






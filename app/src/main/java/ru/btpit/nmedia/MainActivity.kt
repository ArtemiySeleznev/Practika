package ru.btpit.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.btpit.nmedia.databinding.ActivityMainBinding
import ru.btpit.nmedia.viewModel.PostViewModel







class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post: Post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                post.likedByMe = !post.likedByMe
                imageView4.setImageResource(
                    if (post.likedByMe) R.drawable.jjjj else R.drawable.hearth_svgrepo_com
                )

            }
        }
    val post = Post(
        id = 1,
        author = "Борисоглебский техникум...",
        content = "БПОУ ВО «БТПИТ» образовано в соответствии с  постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в  форме слияния государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский индустриальный техникум», государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский техникум информатики и вычислительной техники» и государственного образовательного бюджетного учреждения начального профессионального образования Воронежской области «Профессиональное училище № 34 г. Борисоглебска», зарегистрировано в качестве юридического лица 11 сентября 2015 г.",
        published = "21 мая в 18:36",
        share = 999 ,
        likes = 999999,
        prosmotr = 65,
        likedByMe = false)

        with(binding) {
            likes.text = post.likes.toString()
            share.text = post.likes.toString()

            imageView4?.setOnClickListener {
                post.likedByMe = !post.likedByMe
                imageView4.setImageResource(
                    if (post.likedByMe) R.drawable.jjjj else R.drawable.hearth_svgrepo_com
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likes?.text = post.likes.toString()
                val dada = if (post.likes >= 1000) {
                    likes?.text = "${post.likes / 1000}.${post.likes % 1000 / 100}K"
                } else {
                    likes.toString()
                }
                if (post.likes>= 10000){
                    likes?.text = "${post.likes / 1000}.${post.likes % 1000 / 100}K"
                }
                else  {
                    likes.toString()
                }
                if (post.likes>= 1000000){
                    likes?.text = "${post.likes / 1000000}.${post.likes % 1000 / 100}М"
                }
                else  {
                    likes.toString()
                }

            }
        }
        with(binding) {
            imageView5?.setOnClickListener {
                post.shareByMe = !post.shareByMe

                if (post.shareByMe) post.share++
                share?.text = post.share.toString()
                if (post.share >= 1000) {
                    share?.text = "${post.share / 1000}.${post.share % 1000 / 100}K"
                } else  {
                    share.toString()
                }
               if (post.share >= 10000){
                   share?.text = "${post.share / 1000}.${post.share % 1000 / 100}K"
               }
               else  {
                   share.toString()
               }
                if (post.share>= 1000000){
                    share?.text = "${post.share / 1000000}.${post.share % 1000 / 100}М"
                }
                else  {
                    share.toString()
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
        var shareByMe: Boolean = false
    )}




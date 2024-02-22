package ru.btpit.nmedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.LayoutRes
import org.jetbrains.annotations.Nullable
import ru.btpit.nmedia.databinding.ActivityMainBinding
import kotlin.coroutines.coroutineContext


class MainActivity : AppCompatActivity() {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val post = Post(
            id = 1,
            author = "Борисоглебский техникум...",
            content = "БПОУ ВО «БТПИТ» образовано в соответствии с  постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в  форме слияния государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский индустриальный техникум», государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский техникум информатики и вычислительной техники» и государственного образовательного бюджетного учреждения начального профессионального образования Воронежской области «Профессиональное училище № 34 г. Борисоглебска», зарегистрировано в качестве юридического лица 11 сентября 2015 г.",
            published = "21 мая в 18:36",
            share = 999 ,
            likes = 999,
            prosmotr = 65,
            likedByMe = false


        )
        with(binding) {
            textView2.text = post.author
            textView4.text = post.content
            likes.text = post.likes.toString()
            share.text = post.likes.toString()
            prosmtr.text = post.prosmotr.toString()
            if (post.likedByMe) {
                imageView4?.setImageResource(R.drawable.jjjj)
            }

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
        var share: Int ,
        var likes: Int ,
        var prosmotr: Int ,
        var likedByMe: Boolean = false,
        var shareByMe: Boolean = false
    )}



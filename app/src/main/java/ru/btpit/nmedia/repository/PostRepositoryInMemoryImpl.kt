package ru.btpit.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.btpit.nmedia.Post


class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = listOf(
        Post(
            id = 2,
            author = "Борисоглебский техникум...",
            content = "dadadad",
            published = "25 мая в 20:00",
            share = 9999,
            likes = 99999,
            prosmotr = 65,
            likedByMe = false,
            shareByMe = false
        ),
        Post(
        id = 1,
        author = "Борисоглебский техникум...",
        content = "БПОУ ВО «БТПИТ» образовано в соответствии с  постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в  форме слияния государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский индустриальный техникум», государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский техникум информатики и вычислительной техники» и государственного образовательного бюджетного учреждения начального профессионального образования Воронежской области «Профессиональное училище № 34 г. Борисоглебска», зарегистрировано в качестве юридического лица 11 сентября 2015 г.",
        published = "21 мая в 18:36",
        share = 999,
        likes = 999999,
        prosmotr = 65,
        likedByMe = false,
        shareByMe = false
    )
    )
    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {

      posts = posts.map {
          if (it.id == id) {
              val likedByMe = !it.likedByMe
              val newLikesCount = if (likedByMe) it.likes + 1 else it.likes - 1
              it.copy(likedByMe = likedByMe, likes = newLikesCount)
          } else {
              it
          }
      }


        data.value = posts

    }
    override fun shareById(id: Long){
        /* post = post.copy(shareByMe = !post.shareByMe)*/
         posts = posts.map {
             if (it.id == id) {
                 val shareByMe = !it.shareByMe
                 val newshareCount =   it.share + 1
                 it.copy(shareByMe = shareByMe, share = newshareCount)
             } else {
                 it
             }
      }

        data.value = posts
    }
}



package ru.btpit.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import repository.PostRepository

import ru.btpit.nmedia.activity.Post

class PostRepositoryInMemotyImpl :  PostRepository{
    private var post = Post(
        id = 1,
        author = "Борисоглебский техникум...",
        content = "БПОУ ВО «БТПИТ» образовано в соответствии с  постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в  форме слияния государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский индустриальный техникум», государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский техникум информатики и вычислительной техники» и государственного образовательного бюджетного учреждения начального профессионального образования Воронежской области «Профессиональное училище № 34 г. Борисоглебска», зарегистрировано в качестве юридического лица 11 сентября 2015 г.",
        published = "21 мая в 18:36",
        share = 999,
        likes = 999999,
        prosmotr = 65,
        likedByMe = false
    )
    private val data = MutableLiveData(post)
    override fun get(): LiveData<Post> = data
    override fun like(){
        post = post.copy(likedByMe = !post.likedByMe)
        data.value = post
    }



}

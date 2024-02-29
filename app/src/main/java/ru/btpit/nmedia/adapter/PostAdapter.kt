package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.btpit.nmedia.Post
import ru.btpit.nmedia.R
import ru.btpit.nmedia.databinding.CardPostBinding


typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit


class PostsAdapter(
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener

) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener

) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
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

            imageView4.setOnClickListener{
                onLikeListener(post)
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
            imageView5.setOnClickListener{
              onShareListener(post)
            }
        }
    }
}


class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}
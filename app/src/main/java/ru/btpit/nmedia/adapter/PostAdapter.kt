package ru.netology.nmedia.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.btpit.nmedia.activity.Post
import ru.btpit.nmedia.R
import ru.btpit.nmedia.databinding.CardPostBinding



interface OnInteractionListener {
    fun onLike(post: Post) {}
    fun onEdit(post: Post) {}
    fun onRemove(post: Post) {}
    fun onShare(post: Post) {}
}

class PostsAdapter(
    private val onInteractionListener: OnInteractionListener,

) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener,

) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("ResourceType")
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
           /* imageView4.setImageResource(
                if (post.likedByMe) R.drawable.serd else R.drawable.hearth_svgrepo_com

            )*/
            like.isChecked = post.likedByMe
            like.text = "${post.likes}"
            menu.setOnClickListener{
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId){
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }





            like.text = post.likes.toString()

            if (post.likes >= 1000) {
                like.text = "${post.likes / 1000}.${post.likes % 1000 / 100}K"
            } else {
                like.text
            }
            if (post.likes>= 10000){
                like.text = "${post.likes / 1000}.${post.likes % 1000 / 100}K"
            }
            else  {
                like.text
            }
            if (post.likes>= 1000000){
                like.text = "${post.likes / 1000000}.${post.likes % 1000 / 100}М"
            }
            else  {
                like.text
            }

            like.setOnClickListener{
               onInteractionListener.onLike(post)
            }


            share.text = post.share.toString()
            if (post.share >= 1000) {
                share.text = "${post.share / 1000}.${post.share % 1000 / 100}K"
            } else  {
                share.text
            }
            if (post.share >= 10000){
                share.text = "${post.share / 1000}.${post.share % 1000 / 100}K"
            }
            else  {
                share.text
            }
            if (post.share>= 1000000){
                share.text = "${post.share / 1000000}.${post.share % 1000 / 100}М"
            }
            else  {
                share.text
            }
            share.setOnClickListener{
              onInteractionListener.onShare(post)
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
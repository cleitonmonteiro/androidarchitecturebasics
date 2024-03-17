package io.github.cleitonmonteiro.androidarchitecturebasics.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.cleitonmonteiro.androidarchitecturebasics.data.model.Post
import io.github.cleitonmonteiro.androidarchitecturebasics.databinding.PostItemBinding

class PostsAdapter(
    private val onClick: (Post) -> Unit
) : ListAdapter<Post, PostsAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) = with(binding) {
            postTitle.text = post.title
            postTag.text = post.tag
            itemView.setOnClickListener {
                onClick(post)
            }
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {

        override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem

    }
}

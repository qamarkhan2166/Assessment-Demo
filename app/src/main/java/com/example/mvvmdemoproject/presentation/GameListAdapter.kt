package com.example.mvvmdemoproject.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmdemoproject.data.model.TransformResponse
import com.example.mvvmdemoproject.databinding.PostItemRowBinding

class GameListAdapter(
    private var context: Context,
    private val onItemCheckedCallback: (item: TransformResponse) -> Unit
) : ListAdapter<TransformResponse, GameListAdapter.ItemViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            PostItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemCheckedCallback,
            context
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ItemViewHolder(
        private val binding: PostItemRowBinding,
        private val onItemCheckedCallback: (item: TransformResponse) -> Unit,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TransformResponse) =
            with(binding) {
                tvSource.text = data.rating.toString()
                tvSource.text = """${ratingText}${data.rating}"""
                Glide.with(context)
                    .load(data.background_image)
                    .into(ivNewsCover)
                tvDate.text = data.released
                tvNewsHeadline.text = data.name
                tvNewsContent.text = data.description
                root.setOnClickListener {
                    onItemCheckedCallback(data)
                }
            }
    }

    private class DiffCallback : DiffUtil.ItemCallback<TransformResponse>() {
        override fun areItemsTheSame(oldItem: TransformResponse, newItem: TransformResponse) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TransformResponse, newItem: TransformResponse) =
            oldItem == newItem
    }
    companion object {
        private const val ratingText = "Rating: "
    }
}

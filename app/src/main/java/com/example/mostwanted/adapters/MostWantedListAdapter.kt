package com.example.mostwanted.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mostwanted.OnItemClickListener
import com.example.mostwanted.databinding.MostWantedItemBinding
import com.example.mostwanted.models.ImageUnionType
import com.example.mostwanted.models.MostWantedPerson

class MostWantedListAdapter(
    private val listener: OnItemClickListener<MostWantedPerson>
) :
    PagingDataAdapter<MostWantedPerson, MostWantedListAdapter.MostWantedViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostWantedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MostWantedItemBinding.inflate(inflater)
        return MostWantedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MostWantedViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class MostWantedViewHolder(private val binding: MostWantedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: MostWantedPerson) {
            binding.apply {
                root.setOnClickListener { listener.onClick(person) }
                Glide.with(itemView)
                    .load(
                        when (person.thumbnailImage) {
                            is ImageUnionType.Drawable -> person.thumbnailImage.resourceId
                            is ImageUnionType.Url -> person.thumbnailImage.url
                        }
                    )
                    .centerInside()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(thumbnail)

                textView.text = person.name
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MostWantedPerson>() {
            override fun areItemsTheSame(
                oldItem: MostWantedPerson,
                newItem: MostWantedPerson
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MostWantedPerson,
                newItem: MostWantedPerson
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
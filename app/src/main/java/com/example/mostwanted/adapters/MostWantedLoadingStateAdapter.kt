package com.example.mostwanted.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mostwanted.databinding.NetworkStateItemBinding

class MostWantedLoadingStateAdapter(
    private val adapter: MostWantedListAdapter
): LoadStateAdapter<MostWantedLoadingStateAdapter.NetworkStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NetworkStateItemBinding.inflate(inflater)
        return NetworkStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NetworkStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class NetworkStateViewHolder(
        private val binding: NetworkStateItemBinding,
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { adapter.retry() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error
                errorMsg.isVisible =
                    !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                errorMsg.text = (loadState as? LoadState.Error)?.error?.message
            }
        }
    }
}
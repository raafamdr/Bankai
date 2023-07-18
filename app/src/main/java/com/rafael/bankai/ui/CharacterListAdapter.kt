package com.rafael.bankai.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rafael.bankai.databinding.ListViewItemBinding

class CharacterListAdapter :
    ListAdapter<com.rafael.bankai.network.Character, CharacterListAdapter.CharacterViewHolder>(DiffCallback) {

    class CharacterViewHolder(
        private var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(boneco: com.rafael.bankai.network.Character) {
            binding.character = boneco
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<com.rafael.bankai.network.Character>() {
        override fun areItemsTheSame(oldItem: com.rafael.bankai.network.Character, newItem: com.rafael.bankai.network.Character): Boolean {
            return oldItem.character.mal_id == newItem.character.mal_id
        }

        override fun areContentsTheSame(oldItem: com.rafael.bankai.network.Character, newItem: com.rafael.bankai.network.Character): Boolean {
            return oldItem.character.images == newItem.character.images
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterListAdapter.CharacterViewHolder {
        return CharacterViewHolder(
            ListViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterListAdapter.CharacterViewHolder, position: Int) {
        val bonecao = getItem(position)
        holder.bind(bonecao)
    }
}

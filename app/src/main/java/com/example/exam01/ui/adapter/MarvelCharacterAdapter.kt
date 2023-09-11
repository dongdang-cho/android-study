package com.example.exam01.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.exam01.databinding.ItemMarvelCharacterBinding
import com.example.exam01.network.response.Result
import com.example.exam01.util.MediaUtil.Companion.saveToGallery


class MarvelCharacterAdapter(private val clickType: (ItemClickType) -> Unit)  : RecyclerView.Adapter<MarvelCharacterViewHolder>() {
    private val characterList = mutableListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        val marvelBinding =
            ItemMarvelCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarvelCharacterViewHolder(marvelBinding)
    }

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    fun addAll(list: List<Result>) {
        characterList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        characterList.clear()
        notifyDataSetChanged()
    }

}

class MarvelCharacterViewHolder(private val binding: ItemMarvelCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Result) {
        binding.item = item

        with(binding.ivCharacter) {
            setOnClickListener {
                drawToBitmap().saveToGallery(itemView.context)
            }
        }

    }
}
sealed interface ItemClickType {
    data class ItemClick(val item: Result) : ItemClickType
    data class AddBookmark(val item: Result) : ItemClickType
    data class DeleteBookmark(val item: Result) : ItemClickType
}




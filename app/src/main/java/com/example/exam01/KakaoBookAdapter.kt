package com.example.exam01

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exam01.databinding.ItemKakaoBookBinding
import com.example.exam01.network.response.Document

class KakaoBookAdapter : RecyclerView.Adapter<KakaoBookViewHolder>(){
        private val bookList = mutableListOf<Document>()
        override fun onBindViewHolder(holder: KakaoBookViewHolder, position: Int) {
                holder.bind(bookList[position])
        }

        override fun getItemCount(): Int = bookList.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KakaoBookViewHolder {
                val bookBinding =
                        ItemKakaoBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return KakaoBookViewHolder(bookBinding)
        }

        fun clear() {
                bookList.clear()
                notifyDataSetChanged()
        }

        fun addAll(list: List<Document>) {
                bookList.addAll(list)
                notifyDataSetChanged()
        }
}

class KakaoBookViewHolder(private val binding: ItemKakaoBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
                fun bind(item: Document) {
                        binding.item = item
                }
}
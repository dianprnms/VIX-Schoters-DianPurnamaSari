package com.example.vix_schoters_dianpurnamasari.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vix_schoters_dianpurnamasari.databinding.ItemSourceBinding
import com.example.vix_schoters_dianpurnamasari.model.Article

class SourceAdapter(private var listDataSource: List<Article>) : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {


    fun setData(dataSource: List<Article>) {
        listDataSource = dataSource
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataSource = listDataSource[position]
        holder.bind(dataSource)
        holder.binding.itemSource.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return listDataSource.size
    }

    inner class ViewHolder(val binding: ItemSourceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataSource: Article) {
            binding.nameSource.text = dataSource.title
            Glide.with(binding.imageView.context)
                .load(dataSource.urlToImage)
                .into(binding.imageView)

        }
    }
}

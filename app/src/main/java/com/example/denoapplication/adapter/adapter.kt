package com.example.denoapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.denoapplication.databinding.ItemPostBinding
import com.example.denoapplication.model.PostResponseItem

class PostAdapter(
    private val postList: ArrayList<PostResponseItem>,
    var onClick: (name: String) -> Unit
) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = postList[position]
        holder.binding.textTitle.text = item.title
        holder.binding.textBody.text = item.body

        holder.binding.root.setOnClickListener {
            onClick.invoke(postList[position].title)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<PostResponseItem>) {
        postList.clear()
        postList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = postList.size
}

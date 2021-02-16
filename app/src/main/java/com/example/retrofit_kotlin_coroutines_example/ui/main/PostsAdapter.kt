package com.example.retrofit_kotlin_coroutines_example.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_kotlin_coroutines_example.R
import com.example.retrofit_kotlin_coroutines_example.pojo.PostResponse
import kotlinx.android.synthetic.main.item_recycler.view.*

class PostsAdapter (private val users: ArrayList<PostResponse>) : RecyclerView.Adapter<PostsAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: PostResponse) {
            itemView.apply {
                body_txt.text = user.body
                title_txt.text = user.title
                position_txt.text = user.id.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: List<PostResponse>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}
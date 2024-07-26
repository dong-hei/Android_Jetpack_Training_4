package com.dk.pagingv1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : PagingDataAdapter<User, MyAdapter.UserViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val id = view.findViewById<TextView>(R.id.id)
        val userName = view.findViewById<TextView>(R.id.username)

        fun bind(item : User){
            id.text = item.id.toString()
            userName.text = item.userName
        }
    }


    override fun onBindViewHolder(holder: MyAdapter.UserViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return UserViewHolder(view)
    }

}
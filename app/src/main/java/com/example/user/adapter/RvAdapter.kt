package com.example.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.user.databinding.SingleViewBinding
import com.example.user.model.Users


class RvAdapter(var context: Context,var user:Users):RecyclerView.Adapter<RvAdapter.UserViewholder>() {

    inner class UserViewholder(var binding:SingleViewBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewholder {
        val inflater = LayoutInflater.from(context)
        val binding = SingleViewBinding.inflate(inflater,parent,false)
        return UserViewholder(binding)
    }

    override fun onBindViewHolder(holder: UserViewholder, position: Int) {
       val currentList = user[position]
        holder.binding.name.text = currentList.name
        holder.binding.email.text = currentList.email
    }

    override fun getItemCount(): Int {
          return user.size
    }
}
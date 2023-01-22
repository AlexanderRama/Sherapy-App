package com.example.sherapy.utilities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sherapy.databinding.ItemEditProfileLayoutBinding

class EditProfileAdapter: RecyclerView.Adapter<EditProfileAdapter.EditProfileViewHolder>() {
    private val list = arrayListOf<Pair<Int, String>>()
    private val listValue = arrayListOf<String>()

    fun submitList(data: List<Pair<Int, String>>, dataValue: List<String>) {
        list.apply {
            clear()
            addAll(data)
        }
        listValue.apply {
            clear()
            addAll(dataValue)
        }
        notifyDataSetChanged()
    }

    class EditProfileViewHolder(val view: ItemEditProfileLayoutBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(data: Pair<Int, String>, value: String) {
            view.apply {
                ivIcon.setImageResource(data.first)
                tvTitle.text = data.second
                tvValue.text = value
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditProfileViewHolder {
        val view = ItemEditProfileLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EditProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: EditProfileViewHolder, position: Int) {
        holder.bind(list[position], listValue[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
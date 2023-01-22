package com.example.sherapy.utilities

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sherapy.activities.EditProfileActivity
import com.example.sherapy.activities.SignInActivity
import com.example.sherapy.databinding.ItemProfileLayoutBinding
import com.example.sherapy.fragments.ProfileFragment

class ProfileAddOnsAdapter: RecyclerView.Adapter<ProfileAddOnsAdapter.ProfileAddOnsViewHolder>() {

    private val list = arrayListOf<Pair<Int, String>>()

    fun submitList(data: List<Pair<Int, String>>) {
        list.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    class ProfileAddOnsViewHolder(val view: ItemProfileLayoutBinding): RecyclerView.ViewHolder(view.root) {
        private lateinit var preferenceManager: PreferenceManager
        fun bind(data: Pair<Int, String>) {
            preferenceManager = PreferenceManager(itemView.context)
            view.apply {
                ivIcon.setImageResource(data.first)
                tvAction.text = data.second
            }
            itemView.setOnClickListener {
                if (data.second == "Edit Profile") {
                    val intent = Intent(itemView.context, EditProfileActivity::class.java)
                    itemView.context.startActivity(intent)
                }
            }
            itemView.setOnClickListener {
                if (data.second == "Log Out") {
                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, false)
                    val intent = Intent(itemView.context, SignInActivity::class.java)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAddOnsViewHolder {
        val view = ItemProfileLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileAddOnsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileAddOnsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
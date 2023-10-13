package kodecamp.android.na_my_work.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.ProfileItemBinding
import kodecamp.android.na_my_work.ui.model.User

class UserAdapter(private val dummyData: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: ProfileItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ProfileItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return dummyData.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = dummyData[position]
        holder.binding.apply {
            name.text = data.name
            job.text = data.occupation
            address.text = data.location
            description.text = data.description.toString()
            imageView.setImageResource(R.drawable.profile_image)
        }
    }
}
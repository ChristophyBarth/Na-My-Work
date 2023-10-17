package kodecamp.android.na_my_work.ui.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.ProfileItemBinding
import kodecamp.android.na_my_work.databinding.UserItemBinding
import kodecamp.android.na_my_work.ui.model.UserEntity
import kodecamp.android.na_my_work.ui.utils.GsonUtil

class UserAdapter(
    private val context: Context,
    private val dummyData: List<UserEntity>,
    private val viewType: Int,
    @IdRes private val navigationId: Int
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    companion object {
        const val VIEW_TYPE_TYPE1 = 1
        const val VIEW_TYPE_TYPE2 = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (viewType) {
            VIEW_TYPE_TYPE1 -> {
                VIEW_TYPE_TYPE1
            }

            else -> {
                VIEW_TYPE_TYPE2
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return when (viewType) {
            VIEW_TYPE_TYPE1 -> UserViewHolder(
                ProfileItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            VIEW_TYPE_TYPE2 -> UserViewHolder(
                UserItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> {
                throw IllegalArgumentException("Unknown ViewType in User Adapter")
            }
        }
    }

    override fun getItemCount(): Int {
        return dummyData.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = dummyData[position]
        holder.bind(user)
    }

    inner class UserViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            if (binding is ProfileItemBinding) {
                binding.apply {
                    name.text = user.fullName
                    job.text = user.title
                    address.text = context.getString(
                        R.string.user_adapter_address, user.cityOfResidence, user.countryOfResidence
                    )
                    description.text = user.bio
                    rating.text = context.getString(
                        R.string.user_adapter_rating,
                        user.rating.toString(),
                        user.ratingCount.toString()
                    )
                    imageView.setImageResource(R.drawable.profile_image)

                    viewProfile.setOnClickListener {
                        val userToString = GsonUtil().getGsonParser().toJson(user)
                        val bundle = Bundle()
                        bundle.putString("user", userToString)

                        it.findNavController().navigate(navigationId, bundle)
                    }
                }
            } else if (binding is UserItemBinding) {
                binding.apply {
                    name.text = user.fullName
                    job.text = user.title
                    address.text = context.getString(
                        R.string.user_adapter_address, user.cityOfResidence, user.countryOfResidence
                    )
                    description.text = user.bio
                    rating.text = context.getString(
                        R.string.user_adapter_rating,
                        user.rating.toString(),
                        user.ratingCount.toString()
                    )
                    imageView.setImageResource(R.drawable.profile_image)

                    viewProfile.setOnClickListener {
                        val userToString = GsonUtil().getGsonParser().toJson(user)
                        val bundle = Bundle()
                        bundle.putString("user", userToString)

                        it.findNavController().navigate(navigationId, bundle)
                    }
                }

                if (adapterPosition == dummyData.size - 1) {
                    binding.divider.visibility = View.GONE
                }
            }
        }
    }
}
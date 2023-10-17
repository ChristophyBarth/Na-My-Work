package kodecamp.android.na_my_work.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kodecamp.android.na_my_work.databinding.CategoryItemBinding
import kodecamp.android.na_my_work.databinding.PopularServiceItemBinding
import kodecamp.android.na_my_work.ui.model.Category

class CategoriesAdapter(
    private val dummyData: List<Category>,
    private val viewType: Int,
    @IdRes private val navigationId: Int
) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    companion object {
        const val VIEW_TYPE_TYPE1 = 1
        const val VIEW_TYPE_TYPE2 = 2
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CategoriesAdapter.CategoryViewHolder {
        return when (viewType) {
            VIEW_TYPE_TYPE1 -> CategoryViewHolder(
                PopularServiceItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            VIEW_TYPE_TYPE2 -> CategoryViewHolder(
                CategoryItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> {
                throw IllegalArgumentException("Unknown ViewType in Categories Adapter")
            }
        }
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

    override fun getItemCount(): Int {
        return dummyData.size
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.CategoryViewHolder, position: Int) {
        val category = dummyData[position]
        holder.bind(category)
    }

    inner class CategoryViewHolder(val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            when (binding) {
                is PopularServiceItemBinding -> {
                    binding.apply {
                        categoryImage.setImageResource(category.image)
                        categoryName.text = category.name

                        root.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("category", category.name)
                            it.findNavController().navigate(navigationId, bundle)
                        }
                    }
                }

                is CategoryItemBinding -> {
                    binding.apply {
                        categoryImage.setImageResource(category.image)
                        categoryName.text = category.name

                        root.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("category", category.name)
                            it.findNavController().navigate(navigationId, bundle)
                        }
                    }
                }
            }
        }
    }
}
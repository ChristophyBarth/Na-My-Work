package kodecamp.android.na_my_work.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kodecamp.android.na_my_work.databinding.CategoryItemBinding
import kodecamp.android.na_my_work.ui.model.Category

class CategoriesAdapter(private val dummyData: List<Category>) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding : CategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesAdapter.CategoryViewHolder {
        return CategoryViewHolder(CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.CategoryViewHolder, position: Int) {
        val data = dummyData[position]
        holder.binding.apply {
            categoryImage.setImageResource(data.image)
            categoryName.text = data.name
        }
    }

    override fun getItemCount(): Int {
        return dummyData.size
    }
}
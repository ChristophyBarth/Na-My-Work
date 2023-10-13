package kodecamp.android.na_my_work.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kodecamp.android.na_my_work.databinding.CategoryItemBinding
import kodecamp.android.na_my_work.databinding.ServiceItemBinding
import kodecamp.android.na_my_work.ui.model.Category

class ServiceAdapter(private val dummyData: List<Category>) : RecyclerView.Adapter<ServiceAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding : ServiceItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceAdapter.CategoryViewHolder {
        return CategoryViewHolder(
            ServiceItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ServiceAdapter.CategoryViewHolder, position: Int) {
        val data = dummyData[position]
        holder.binding.apply {
            imageView2.setImageResource(data.image)
            serviceTv.text = data.name
        }
    }

    override fun getItemCount(): Int {
        return dummyData.size
    }
}
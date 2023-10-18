package kodecamp.android.na_my_work.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kodecamp.android.na_my_work.R

class SkillAdapter(private val context: Context, private val skills: List<String>) :
    RecyclerView.Adapter<SkillAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(skill: String)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val skill = skills[position]
        holder.bind(skill)

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(skill)
        }
    }

    override fun getItemCount(): Int {
        return skills.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(skill: String) {
            itemView.findViewById<TextView>(R.id.tvChip).text = skill
        }
    }
}




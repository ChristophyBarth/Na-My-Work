package kodecamp.android.na_my_work.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.CurrencyItemBinding

class CurrencyAdapter(private val currencies: List<String>, private val context: Context) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    var selectedItemPosition = 0

    inner class CurrencyViewHolder(val binding: CurrencyItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(currency :String){
            binding.currency.text = currency

            if (adapterPosition == selectedItemPosition){
                binding.currency.setTextColor(ContextCompat.getColor(context, R.color.green))
                binding.currency.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.currency_tick_circle,0)
            } else{
                binding.currency.setTextColor(ContextCompat.getColor(context, R.color.black))
                binding.currency.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, 0,0)
            }

            binding.root.setOnClickListener {
                notifyItemChanged(selectedItemPosition)
                selectedItemPosition = adapterPosition
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CurrencyItemBinding.inflate(layoutInflater, parent, false)

        return CurrencyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencies[position])
    }
}
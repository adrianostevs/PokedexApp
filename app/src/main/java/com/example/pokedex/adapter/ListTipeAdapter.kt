package com.example.tipedex.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.databinding.ListTipeBinding
import com.example.pokedex.model.TypeResponse

class ListTipeAdapter: RecyclerView.Adapter<ListTipeAdapter.ListViewHolder>(){

    private val listTipe = ArrayList<TypeResponse>()

    fun setData(tipe: List<TypeResponse>){
        listTipe.clear()
        listTipe.addAll(tipe)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(var binding: ListTipeBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(tipes: TypeResponse){
            binding.apply{
                val tipe = tipes.type.name
                binding.tvTypes1.text = tipe

                if(tipe == "poison"){
                    binding.linear.setBackgroundResource(R.color.poison)
                } else if(tipe == "grass"){
                    binding.linear.setBackgroundResource(R.color.grass)
                } else if (tipe == "fire"){
                    binding.linear.setBackgroundResource(R.color.fire)
                } else if (tipe == "normal"){
                    binding.linear.setBackgroundResource(R.color.normal)
                } else if (tipe == "flying"){
                    binding.linear.setBackgroundResource(R.color.flying)
                } else if (tipe == "rock"){
                    binding.linear.setBackgroundResource(R.color.rock)
                } else if (tipe == "water"){
                    binding.linear.setBackgroundResource(R.color.water)
                } else if (tipe == "ice"){
                    binding.linear.setBackgroundResource(R.color.ice)
                } else if (tipe == "ghost"){
                    binding.linear.setBackgroundResource(R.color.ghost)
                } else if (tipe == "steel"){
                    binding.linear.setBackgroundResource(R.color.steel)
                } else if (tipe == "physic"){
                    binding.linear.setBackgroundResource(R.color.physic)
                } else if (tipe == "dark"){
                    binding.linear.setBackgroundResource(R.color.dark)
                } else if (tipe == "fairy"){
                    binding.linear.setBackgroundResource(R.color.fairy)
                } else if (tipe == "fighting"){
                    binding.linear.setBackgroundResource(R.color.fighting)
                } else if (tipe == "ground"){
                    binding.linear.setBackgroundResource(R.color.ground)
                } else if (tipe == "bug"){
                    binding.linear.setBackgroundResource(R.color.bug)
                } else if (tipe == "electric"){
                    binding.linear.setBackgroundResource(R.color.electric)
                } else if (tipe == "dragon"){
                    binding.linear.setBackgroundResource(R.color.dragon)
                }
                Log.d("tip", tipes.toString())
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding =
            ListTipeBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listTipe[position])
    }

    override fun getItemCount(): Int = listTipe.size

}

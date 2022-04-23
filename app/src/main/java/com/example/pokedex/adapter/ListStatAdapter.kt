package com.example.pokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.databinding.ListPokemonBinding
import com.example.pokedex.databinding.ListStatBinding
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.Stats

class ListStatAdapter : RecyclerView.Adapter<ListStatAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listStat = ArrayList<Stats>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(poke: List<Stats>){
        listStat.clear()
        listStat.addAll(poke)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(var binding: ListStatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokes: Stats){
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(pokes) }
            binding.apply{
                tvAttack.text = pokes.stat.name
                tvAttackStat.text = pokes.base_stats.toString()
                pbAttack.setProgress(pokes.base_stats)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding =
            ListStatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    interface OnItemClickCallback {
        fun onItemClicked(pokes: Stats)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listStat[position])
    }

    override fun getItemCount(): Int = listStat.size

}
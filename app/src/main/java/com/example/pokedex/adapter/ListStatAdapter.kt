package com.example.pokedex.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.ListStatBinding
import com.example.pokedex.model.Stats

class ListStatAdapter : RecyclerView.Adapter<ListStatAdapter.ListViewHolder>(){

    private val listStat = ArrayList<Stats>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(poke: List<Stats>){
        listStat.clear()
        listStat.addAll(poke)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(var binding: ListStatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokes: Stats){
            binding.apply{
                tvAttack.text = pokes.stat.name
                tvAttackStat.text = pokes.base_stats.toString()
                pbAttack.setProgress(pokes.base_stats)
                Log.d("adap", pokes.toString())
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding =
            ListStatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listStat[position])
    }

    override fun getItemCount(): Int = listStat.size

}
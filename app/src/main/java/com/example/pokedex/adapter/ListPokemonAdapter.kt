package com.example.pokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.databinding.ListPokemonBinding
import com.example.pokedex.model.MainModel

class ListPokemonAdapter() : RecyclerView.Adapter<ListPokemonAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listPokemon = ArrayList<MainModel>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(poke: MainModel){
        listPokemon.clear()
        listPokemon.addAll(listOf(poke))
        notifyDataSetChanged()
    }

    inner class ListViewHolder(var binding: ListPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokes: MainModel){
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(pokes) }
            binding.apply{
                Glide.with(itemView)
                    .load(pokes.getImageUrl())
                    .into(ivAvatar)
                tvNama.text = pokes.name
                tvId.text = pokes.getId().format("#%03d")
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding =
            ListPokemonBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    interface OnItemClickCallback {
        fun onItemClicked(pokes: MainModel)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listPokemon[position])
    }

    override fun getItemCount(): Int = listPokemon.size

}
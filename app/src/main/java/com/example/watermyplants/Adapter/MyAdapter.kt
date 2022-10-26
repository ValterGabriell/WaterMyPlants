package com.example.watermyplants.Adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.watermyplants.Model.PlantItem
import com.example.watermyplants.R
import com.squareup.picasso.Picasso

class MyAdapter(val itens: List<PlantItem>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(plantItem: PlantItem) {
            itemView.findViewById<CardView>(R.id.card_view_layout)
                .setCardBackgroundColor(plantItem.plantColor)
            itemView.findViewById<TextView>(R.id.txt_plant_name).text = plantItem.title
            itemView.findViewById<TextView>(R.id.txt_qtd_water).text =
                plantItem.ml.toString() + " ml"
            val imagem = itemView.findViewById<ImageView>(R.id.imageView)
            imagem.setImageBitmap(plantItem.photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_reycler_view, parent, false)
        return MyViewHolder(view)
    }


    var onItemClick: ((Int, Int) -> Unit)? = null

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itens[position])
        holder.itemView.findViewById<CardView>(R.id.card_view_layout).setOnClickListener {
            onItemClick?.invoke(itens[position].id, position)
        }

    }

    override fun getItemCount(): Int = itens.size


}
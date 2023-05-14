package com.example.srifin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val filteredList: ArrayList<Catogeries>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val tvTitle :TextView = itemView.findViewById(R.id.categoryname)
        val tvDescription :TextView= itemView.findViewById(R.id.categorydescription)

        fun bind(currentCategory: Catogeries) {
            tvTitle.text = currentCategory.categoryname
            tvDescription.text = currentCategory.categorydescription
            val collectionId = currentCategory.collectionid

            itemView.setOnClickListener {

                val intent = Intent(itemView.context, Update::class.java)

                intent.putExtra("categoryname", currentCategory.categoryname)
                intent.putExtra("categorydescription", currentCategory.categorydescription)
                intent.putExtra("collectionid", collectionId)

                itemView.context.startActivity(intent)


            }


        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.catogery_list_item,parent,false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentCategory = filteredList[position]
        holder.bind(currentCategory)

    }

    override fun getItemCount(): Int {
        return filteredList.size
    }
}
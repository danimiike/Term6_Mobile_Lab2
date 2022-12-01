package com.example.dmiike.lab2

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val context: Context, private val dataSet: ArrayList<Plant>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val btn: Button
        init {
            btn = view.findViewById(R.id.btnPlantName)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int): Unit {
        viewHolder.btn.text = dataSet[position].plantName
        viewHolder.btn.setOnClickListener { _ ->
            if (context is PlantListActivity) {
                context.showImage(dataSet[position].plantName)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


}
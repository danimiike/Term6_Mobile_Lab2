package com.example.dmiike.lab2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PlantListActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageButton
    private lateinit var plantNameList: ArrayList<String>

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerView.Adapter<*>
    private lateinit var recyclerViewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plant_list_layout)
        supportActionBar?.hide()

        btnBack = findViewById(R.id.btnFinishActivity)
        btnBack.setOnClickListener { finish() }

        recyclerView = findViewById(R.id.rvItems)
        recyclerViewManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = recyclerViewManager
        recyclerView.setHasFixedSize(true)

        val bundle = intent.extras
        plantNameList = bundle!!.getStringArrayList("plantList")!!
        val plantList: ArrayList<Plant> = ArrayList<Plant>()
        plantNameList.forEach { name -> plantList.add(Plant(name)) }
        recyclerAdapter = RecyclerAdapter(this, plantList)
        recyclerView.adapter = recyclerAdapter
    }

    fun showImage(name: String) {
        val bundle = Bundle()
        bundle.putString("plantName", name)
        val intent = Intent(this, PlantActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }


}
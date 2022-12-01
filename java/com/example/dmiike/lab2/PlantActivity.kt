package com.example.dmiike.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class PlantActivity : AppCompatActivity() {
    private lateinit var btnFinish: ImageButton
    private lateinit var tvPlantName: TextView
    private lateinit var imgPlant: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant)
        supportActionBar?.hide()

        btnFinish = findViewById(R.id.btnFinishActivity)
        btnFinish.setOnClickListener { finish() }

        tvPlantName = findViewById(R.id.tvPlantName)
        imgPlant = findViewById(R.id.imgPlant)

        val bundle = intent.extras
        tvPlantName.text = "Plant name: " + bundle!!.getString("plantName")
        when (tvPlantName.text.toString().lowercase()) {
            "plant name: apple" -> imgPlant.setImageResource(R.drawable.apple)
            "plant name: plum" -> imgPlant.setImageResource(R.drawable.plum)
            "plant name: orchid" -> imgPlant.setImageResource(R.drawable.orchid)
            "plant name: pear" -> imgPlant.setImageResource(R.drawable.pear)
            "plant name: rose" -> imgPlant.setImageResource(R.drawable.rose)
            else -> imgPlant.setImageResource(R.drawable.noimage)
        }


    }
}
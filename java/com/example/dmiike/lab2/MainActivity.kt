package com.example.dmiike.lab2

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var btn_readDB: Button
    private lateinit var btnBack: ImageButton

    private lateinit var plantList: ArrayList<Plant>

    private var db: DBAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        btnBack = findViewById(R.id.btnFinishActivity)
        btnBack.visibility = View.INVISIBLE;
    }

    fun onClick(view: View) {
        btn_readDB = findViewById(R.id.btn_readDB)
        populatePlantList()
        val bundle = Bundle()
        val plantNameList: ArrayList<String> = ArrayList()
        plantList.forEach { it -> plantNameList.add(it.plantName) }
        bundle.putStringArrayList("plantList", plantNameList)
        val intent = Intent(this, PlantListActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }


    private fun populatePlantList(): Unit {
        plantList = ArrayList<Plant>()
        db = DBAdapter(this)
        db!!.open()
        // read all the items from the database file
        val c: Cursor? = db!!.getAllPlants()
        if (c!!.moveToFirst()) {
            do {
                plantList.add(Plant(c.getString(0)))
            } while (c.moveToNext())
        }
        db!!.close()
    }
}

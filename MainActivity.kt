package com.example.restaurantapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.restaurantapp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = RestaurantDB.getDB(applicationContext)

        adapter = RestaurantAdapter()
        binding.rcView.layoutManager = GridLayoutManager(this, 2)
        binding.rcView.adapter = adapter

        lifecycleScope.launch {
            db.getDao().getAllItems().collect { items ->
                adapter.setItems(items)
            }
        }

        binding.newEntryBtn.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }
}

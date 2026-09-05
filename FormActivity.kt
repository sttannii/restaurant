package com.example.restaurantapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantapp.databinding.ActivityFormBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = RestaurantDB.getDB(applicationContext)

        binding.addBtn.setOnClickListener {
            val newItem = RestaurantItem(
                image = R.drawable.placeholder_dish,
                name = binding.etName.text.toString(),
                category = binding.etCategory.text.toString(),
                price = binding.etPrice.text.toString().toDoubleOrNull() ?: 0.0,
                rating = binding.etRating.text.toString().toDoubleOrNull() ?: 0.0
            )

            CoroutineScope(Dispatchers.IO).launch {
                db.getDao().insertItem(newItem)
                finish()
            }
        }
    }
}

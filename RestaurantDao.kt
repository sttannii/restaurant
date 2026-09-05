package com.example.restaurantapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Insert suspend fun insertItem(item: RestaurantItem)
    @Delete suspend fun deleteItem(item: RestaurantItem)
    @Update suspend fun updateItem(item: RestaurantItem)

    @Query("SELECT * FROM restaurant_items")
    fun getAllItems(): Flow<List<RestaurantItem>>

    @Query("DELETE FROM restaurant_items WHERE id = :id")
    suspend fun deleteById(id: Int)
}

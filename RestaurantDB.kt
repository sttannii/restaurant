package com.example.restaurantapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RestaurantItem::class], version = 1)
abstract class RestaurantDB : RoomDatabase() {
    abstract fun getDao(): RestaurantDao

    companion object {
        fun getDB(context: Context): RestaurantDB {
            return Room.databaseBuilder(
                context.applicationContext,
                RestaurantDB::class.java,
                "restaurant_db"
            ).build()
        }
    }
}

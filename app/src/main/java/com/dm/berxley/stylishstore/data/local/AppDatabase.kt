package com.dm.berxley.stylishstore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dm.berxley.stylishstore.domain.models.Category
import com.dm.berxley.stylishstore.domain.models.Product
import com.dm.berxley.stylishstore.domain.models.User

@Database(entities = [Product::class, Category::class], version = 2,)
@TypeConverters(ImageListConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val appDao: AppDao

    companion object{
        const val APP_DB_NAME = "stylish_room_db"
    }
}
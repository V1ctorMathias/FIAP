package com.vinheriaagnello.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Produto::class], version = 1, exportSchema = false)
abstract class VinheriaDatabase : RoomDatabase() {

    abstract fun produtoDao(): ProdutoDao

    companion object {
        @Volatile
        private var INSTANCE: VinheriaDatabase? = null

        fun getDatabase(context: Context): VinheriaDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    VinheriaDatabase::class.java,
                    "vinheria_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}

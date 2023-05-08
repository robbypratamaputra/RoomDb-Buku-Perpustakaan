package com.robby1.daftarbukuperpustakaan.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.robby1.daftarbukuperpustakaan.data.dao.BukuDao
import com.robby1.daftarbukuperpustakaan.data.entity.Buku

@Database(entities = [Buku::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun bukudao():BukuDao

    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(context: Context):AppDatabase{
            if(instance==null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}
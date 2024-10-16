package com.guntur.santripunya.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 1)
abstract class WiridDatabase: RoomDatabase(){
    abstract fun wiridDao(): Dao

    companion object{
        @Volatile
        private var INSTANCE: WiridDatabase? = null

        @JvmStatic
        fun getInstance(context : Context):WiridDatabase{
            if (INSTANCE == null){
                synchronized(WiridDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WiridDatabase::class.java,"wirid_database"
                    )
                        .build()
                }
            }
            return INSTANCE as WiridDatabase
        }
    }
}
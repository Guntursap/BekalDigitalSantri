package com.guntur.santripunya.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveWirid(wirid: Entity)

    @Delete
    fun deleteWirid(wirid: Entity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateWirid(wirid: Entity)

    @Query("SELECT * FROM wirid")
    fun getAllWirid(): LiveData<List<Entity>>

    @Query("SELECT * FROM wirid WHERE wiridId = :wiridId")
    fun getWirid(wiridId: Int): LiveData<Entity>

}
package com.idea3d.idea3d.data.network

import androidx.room.*
import com.idea3d.idea3d.data.model.ThingEntity

@Dao
interface ThingsDao {
    @Query("SELECT * FROM ThingEntity ")
    suspend fun getAllFavoriteThings(): List<ThingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteThings(thing: ThingEntity)

    @Delete
    suspend fun deleteFavoriteThing(thing: ThingEntity)
}
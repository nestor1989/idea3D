package com.idea3d.idea3d.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idea3d.idea3d.data.model.ThingEntity
import com.idea3d.idea3d.data.network.ThingsDao

@Database(entities = [ThingEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun thingsDao(): ThingsDao

}
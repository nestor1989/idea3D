package com.idea3d.idea3d.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.data.model.ThingEntity
import com.idea3d.idea3d.data.network.TasksDao
import com.idea3d.idea3d.data.network.ThingsDao

@Database(entities = [ThingEntity::class, Task::class], version = 2)
abstract class AppDataBase: RoomDatabase() {

    abstract fun thingsDao(): ThingsDao

    abstract fun tasksDao(): TasksDao

}
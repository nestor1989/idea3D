package com.idea3d.idea3d.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.data.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(private val repo: Repo): ViewModel(){

    fun addTask (task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            repo.addTask(task)
        }
    }

    fun deleteTask (task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            repo.deleteTask(task)
        }
    }

    fun getAllTask() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getAllTasks())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getByDate(date:String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getByDate(date))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getUrgent() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getUrgent())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getByStatus(id_status: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getByStatus(id_status))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun updateTask(task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            repo.updateTask(task)
        }
    }




}
package com.idea3d.idea3d.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.works.Task
import com.idea3d.idea3d.data.repository.work.WorkRepository
import com.idea3d.idea3d.domain.works.GetAllWoksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val workRepository: WorkRepository,
    private val getAllWoksUseCase: GetAllWoksUseCase): ViewModel(){

    fun addTask (task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            workRepository.addTask(task)
        }
    }

    fun deleteTask (task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            workRepository.deleteTask(task)
        }
    }

    fun getAllTask() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val result = getAllWoksUseCase()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getByDate(date:String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(workRepository.getByDate(date))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getUrgent() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(workRepository.getUrgent())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getByStatus(id_status: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(workRepository.getByStatus(id_status))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun updateTask(task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            workRepository.updateTask(task)
        }
    }

    fun getDateRange(today: String, dateInit:String) = liveData(Dispatchers.IO){
        try {
            emit(workRepository.getDateRange(today, dateInit))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }
}
package com.idea3d.idea3d.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.mapper.TaskMapper
import com.idea3d.idea3d.data.model.works.GetAllTaskMapper
import com.idea3d.idea3d.data.model.works.Task
import com.idea3d.idea3d.data.model.works.TaskDTO
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
    private val getAllWoksUseCase: GetAllWoksUseCase,
    private val taskMapper: TaskMapper,
    private val getAllTaskMapper: GetAllTaskMapper
    ): ViewModel(){

    fun addTask (task: TaskDTO) {
        CoroutineScope(Dispatchers.Main).launch {
            workRepository.addTask(taskMapper.mapToDomain(task))
        }
    }

    fun deleteTask (task: TaskDTO) {
        CoroutineScope(Dispatchers.Main).launch {
            workRepository.deleteTask(taskMapper.mapToDomain(task))
        }
    }

    fun getAllTask() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val result = getAllWoksUseCase()
            emit(Resource.Success(getAllTaskMapper.mapToUI(result)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getByDate(date:String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val result = getAllTaskMapper.mapToUI(workRepository.getByDate(date))
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getUrgent() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val result = getAllTaskMapper.mapToUI(workRepository.getUrgent())
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getByStatus(id_status: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val result = getAllTaskMapper.mapToUI(workRepository.getByStatus(id_status))
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun updateTask(task: TaskDTO) {
        CoroutineScope(Dispatchers.Main).launch {
            workRepository.updateTask(taskMapper.mapToDomain(task))
        }
    }

    fun getDateRange(today: String, dateInit:String) = liveData(Dispatchers.IO){
        try {
            val result = workRepository.getDateRange(today, dateInit)
            emit(Resource.Success(getAllTaskMapper.mapToUI(result)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }
}
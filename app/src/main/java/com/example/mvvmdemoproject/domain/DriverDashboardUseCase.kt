package com.example.mvvmdemoproject.domain

import com.example.mvvmdemoproject.data.model.TransformResponse
import com.example.mvvmdemoproject.data.model.games.toViewData
import com.example.mvvmdemoproject.data.model.pagination.toViewData
import com.example.mvvmdemoproject.data.remote.networkboundresource.Resource
import com.example.mvvmdemoproject.data.remote.networkboundresource.Status
import com.example.mvvmdemoproject.data.repository.LoginRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DriverDashboardUseCase @Inject constructor(
    private val dashboardRepository: LoginRepository
) {

    suspend fun getMasterData(firstPage: Int): Flow<Resource<List<TransformResponse>>> {
        return flow {
            this.emit(Resource.loading(null))
            dashboardRepository.getListOFPages(firstPage).collect {
                when (it.status) {
                    Status.LOADING -> {
                        emit(Resource.loading(null))
                    }

                    Status.SUCCESS -> {
                        val data: List<TransformResponse> = it.data!!.toViewData()
                        emit(Resource.success(data))
                    }
                    Status.ERROR -> emit(Resource.error(it.message.toString(), null))
                }
            }
            if (firstPage == 1) {
                dashboardRepository.getGames().collect {
                    when (it.status) {
                        Status.LOADING -> {
                            emit(Resource.loading(null))
                        }

                        Status.SUCCESS -> {
                            val data = listOf(it.data!!.toViewData())
                            emit(Resource.success(data))
                        }
                        Status.ERROR -> emit(Resource.error(it.message.toString(), null))
                    }
                }
            }
        }
    }
}

package com.example.mvvmdemoproject.presentation

import androidx.lifecycle.ViewModel
import com.example.mvvmdemoproject.data.model.TransformResponse
import com.example.mvvmdemoproject.data.remote.networkboundresource.Resource
import com.example.mvvmdemoproject.domain.DriverDashboardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val driverDashboardUseCase: DriverDashboardUseCase
) : ViewModel() {

    suspend fun getData(page: Int): Flow<Resource<List<TransformResponse>>> {
        return driverDashboardUseCase.getMasterData(page)
    }
}

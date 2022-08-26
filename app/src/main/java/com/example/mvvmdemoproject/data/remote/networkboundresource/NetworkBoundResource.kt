package com.example.mvvmdemoproject.data.remote.networkboundresource

import com.example.mvvmdemoproject.utils.isNetworkAvailable
import kotlinx.coroutines.flow.*
import retrofit2.Response

inline fun <ResultType> localFirstNetworkBound(
    crossinline query: suspend () -> Flow<ResultType>,
    crossinline fetch: suspend () -> Response<ResultType>,
    crossinline saveFetchResult: suspend (ResultType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean,
    crossinline onFetchFailed: (Throwable) -> Unit

) = flow<Resource<ResultType>> {
    emit(Resource.loading(null))
    val data = query().first()

    val flow = if (shouldFetch(data) && isNetworkAvailable()) {
        try {
            val response = fetch()
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    saveFetchResult(res)
                }

                query().map { Resource.success(data = it) }
            } else {
                query().map { Resource.error(msg = "" + response.errorBody(), data = it) }
            }
        } catch (throwable: Exception) {
            throwable.printStackTrace()
            onFetchFailed(throwable)
            query().map { Resource.error(msg = "" + throwable.message, it) }
        }
    } else {
        query().map { Resource.success(data = it) }
    }

    emitAll(flow)
}

inline fun <ResultType> networkOnly(
    crossinline fetch: suspend () -> Response<ResultType>,
    crossinline onFetchFailed: (Throwable) -> Unit

) = flow<Resource<ResultType>> {
    emit(Resource.loading(null))

    try {
        if (isNetworkAvailable()) {
            val response = fetch()
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    emit(Resource.success(data = res))
                }
            } else {
                emit(Resource.error(msg = "" + response.message(), data = null))
            }
        } else {
            emit(Resource.error(msg = "internet connectivity problems", data = null))
        }
    } catch (e: Exception) {
        onFetchFailed(e)
        emit(Resource.error(msg = "" + e.message, data = null))
    }
}

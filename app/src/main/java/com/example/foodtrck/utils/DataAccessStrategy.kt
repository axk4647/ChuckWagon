package com.example.foodtrck.utils

import com.example.foodtrck.data.model.Region
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> performGetFlowOperation(
    networkCall: suspend () -> Resource<T>,
    dataBaseQuery: () -> Resource<T>?,
    saveCallResult: suspend (T) -> Unit): Flow<Resource<T>?> {

    return flow {
        emit(dataBaseQuery())

        emit(Resource.loading())

        val result = networkCall.invoke()

        if(result.status == Resource.Status.SUCCESS) {
            result.data?.let { data ->
                saveCallResult(data)
            }
        }

        emit(result)

    }.flowOn(Dispatchers.IO)
}
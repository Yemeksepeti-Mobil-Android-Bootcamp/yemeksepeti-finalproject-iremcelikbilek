package com.iremcelikbilek.yemeksepetiapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.iremcelikbilek.yemeksepetiapp.data.entity.login.LoginResponse
import kotlinx.coroutines.Dispatchers

fun <T> performNetworkOperation(call: suspend () -> Resource<T>) : LiveData<Resource<T>> {
    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val networkCall = call.invoke()

        if(networkCall.status == Resource.Status.SUCCESS) {
            val data = networkCall.data!!
            emit(Resource.success(data))
        } else if(networkCall.status == Resource.Status.ERROR) {
            emit(Resource.error("Error: ${networkCall.message}"))
        }

    }
}

fun <T> performAuthTokenNetworkOperation(
    call: suspend () -> Resource<T>,
    save: (token: String) -> Unit
) : LiveData<Resource<T>> {
    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val networkCall = call.invoke()

        if(networkCall.status == Resource.Status.SUCCESS) {
            val data = networkCall.data!!
            if(data is LoginResponse) {
                save(data.data.token)
            }

            emit(Resource.success(data))
        } else if(networkCall.status == Resource.Status.ERROR) {
            emit(Resource.error("Error: ${networkCall.message}"))
        }

    }
}
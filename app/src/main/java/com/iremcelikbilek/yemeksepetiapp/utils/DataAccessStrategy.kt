package com.iremcelikbilek.yemeksepetiapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.iremcelikbilek.yemeksepetiapp.data.entity.login.LoginResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.register.RegisterResponse
import kotlinx.coroutines.Dispatchers

fun <T> performNetworkOperation(call: suspend () -> Resource<T>) : LiveData<Resource<T>> {
    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val networkCall = call.invoke()

        if(networkCall.status == Resource.Status.SUCCESS) {
            ifNotNull(networkCall.data) {
                emit(Resource.success(it))
            }
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
            ifNotNull(networkCall.data) {
                if(it is LoginResponse) {
                    ifNotNull(it.data?.token) { token ->
                        save(token)
                    }
                }else if(it is RegisterResponse) {
                    ifNotNull(it.data?.token) { token ->
                        save(token)
                    }
                }
                emit(Resource.success(it))
            }

        } else if(networkCall.status == Resource.Status.ERROR) {
            emit(Resource.error("Error: ${networkCall.message}"))
        }
    }
}
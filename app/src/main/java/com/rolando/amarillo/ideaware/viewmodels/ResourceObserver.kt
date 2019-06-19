package com.rolando.amarillo.ideaware.viewmodels

import androidx.lifecycle.Observer
import com.rolando.amarillo.ideaware.repositories.Resource
import com.rolando.amarillo.ideaware.repositories.Status

abstract class ResourceObserver<T> : Observer<Resource<T>> {

    override fun onChanged(resource: Resource<T>?) {
        when (resource?.status) {
            Status.SUCCESS -> {
                onSuccess(resource.data)
            }
            Status.LOADING -> {
                onLoading()
            }
            Status.ERROR -> {
                onError(resource.message)
            }
        }
    }

    abstract fun onSuccess(t: T?)

    abstract fun onError(errorMessage: String?)

    abstract fun onLoading()

}
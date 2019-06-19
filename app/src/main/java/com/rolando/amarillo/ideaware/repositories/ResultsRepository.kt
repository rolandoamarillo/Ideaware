package com.rolando.amarillo.ideaware.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rolando.amarillo.ideaware.api.ResultsApi
import com.rolando.amarillo.ideaware.api.responses.ResultResponse
import com.rolando.amarillo.ideaware.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultsRepository(private val resultsApi: ResultsApi) {

    fun getResults(): LiveData<Resource<List<Result>>> {
        val data = MutableLiveData<Resource<List<Result>>>()
        data.value = Resource.loading(null)
        resultsApi.getResults().enqueue(object : Callback<List<ResultResponse>> {
            override fun onFailure(call: Call<List<ResultResponse>>, t: Throwable) {
                data.value = Resource.error(t.localizedMessage, null)
            }

            override fun onResponse(call: Call<List<ResultResponse>>, response: Response<List<ResultResponse>>) {
                val resultsResponse = response.body()
                val results = mutableListOf<Result>()
                resultsResponse?.let {
                    for (resultResponse in resultsResponse) {
                        results.add(Result.parse(resultResponse))
                    }
                }
                data.value = Resource.success(results)
            }
        })
        return data
    }

}
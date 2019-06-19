package com.rolando.amarillo.ideaware.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rolando.amarillo.ideaware.api.FixturesApi
import com.rolando.amarillo.ideaware.api.responses.FixtureResponse
import com.rolando.amarillo.ideaware.model.Fixture
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FixturesRepository(private val fixturesApi: FixturesApi) {

    fun getFixtures(): LiveData<Resource<List<Fixture>>> {
        val data = MutableLiveData<Resource<List<Fixture>>>()
        data.value = Resource.loading(null)
        fixturesApi.getFixtures().enqueue(object : Callback<List<FixtureResponse>> {
            override fun onFailure(call: Call<List<FixtureResponse>>, t: Throwable) {
                data.value = Resource.error(t.localizedMessage, null)
            }

            override fun onResponse(call: Call<List<FixtureResponse>>, response: Response<List<FixtureResponse>>) {
                val fixturesResponse = response.body()
                val fixtures = mutableListOf<Fixture>()
                fixturesResponse?.let {
                    for (fixtureResponse in fixturesResponse) {
                        fixtures.add(Fixture.parse(fixtureResponse))
                    }
                }
                data.value = Resource.success(fixtures)
            }

        })
        return data
    }

}
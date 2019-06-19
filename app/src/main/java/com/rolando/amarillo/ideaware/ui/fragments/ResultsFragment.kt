package com.rolando.amarillo.ideaware.ui.fragments

import androidx.lifecycle.LiveData
import com.rolando.amarillo.ideaware.model.Fixture
import com.rolando.amarillo.ideaware.repositories.Resource
import com.rolando.amarillo.ideaware.viewmodels.ResultsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ResultsFragment : AbstractFixtureFragment() {

    private val resultsViewModel: ResultsViewModel by viewModel()

    override fun getLiveDataFixtures(): LiveData<Resource<List<Fixture>>> {
        return resultsViewModel.results as LiveData<Resource<List<Fixture>>>
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ResultsFragment()
    }
}

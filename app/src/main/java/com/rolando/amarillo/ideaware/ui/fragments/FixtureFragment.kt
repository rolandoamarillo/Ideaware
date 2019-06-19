package com.rolando.amarillo.ideaware.ui.fragments

import androidx.lifecycle.LiveData
import com.rolando.amarillo.ideaware.model.Fixture
import com.rolando.amarillo.ideaware.repositories.Resource
import com.rolando.amarillo.ideaware.viewmodels.FixturesViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class FixtureFragment : AbstractFixtureFragment() {

    private val fixturesViewModel: FixturesViewModel by viewModel()

    override fun getLiveDataFixtures(): LiveData<Resource<List<Fixture>>> {
        return fixturesViewModel.fixtures
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FixtureFragment()
    }
}

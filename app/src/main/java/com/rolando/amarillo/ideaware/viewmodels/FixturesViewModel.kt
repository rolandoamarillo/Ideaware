package com.rolando.amarillo.ideaware.viewmodels

import androidx.lifecycle.ViewModel
import com.rolando.amarillo.ideaware.repositories.FixturesRepository

class FixturesViewModel(fixturesRepository: FixturesRepository) : ViewModel() {

    val fixtures = fixturesRepository.getFixtures()

}
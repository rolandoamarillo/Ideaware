package com.rolando.amarillo.ideaware.viewmodels

import androidx.lifecycle.ViewModel
import com.rolando.amarillo.ideaware.repositories.ResultsRepository

class ResultsViewModel(resultsRepository: ResultsRepository) : ViewModel() {

    val results = resultsRepository.getResults()

}
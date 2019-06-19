package com.rolando.amarillo.ideaware.ui.adapters.filter

import android.widget.Filter
import com.rolando.amarillo.ideaware.model.Fixture
import java.util.*

abstract class FixtureFilter(private val values: List<Fixture>) : Filter() {

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val filterString = constraint.toString()

        val results = FilterResults()

        val list = values

        val count = list.size
        val nlist = ArrayList<Fixture>(count)

        var filterableString: Fixture

        for (i in 0 until count) {
            filterableString = list[i]
            if (filterableString.competitionStage.competition.name.contains(filterString, true)) {
                nlist.add(filterableString)
            }
        }

        results.values = nlist
        results.count = nlist.size

        return results
    }

}
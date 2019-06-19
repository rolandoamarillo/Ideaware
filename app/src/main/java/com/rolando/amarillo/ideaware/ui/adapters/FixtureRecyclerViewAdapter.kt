package com.rolando.amarillo.ideaware.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.rolando.amarillo.ideaware.R
import com.rolando.amarillo.ideaware.model.Fixture
import com.rolando.amarillo.ideaware.model.Result
import com.rolando.amarillo.ideaware.ui.adapters.filter.FixtureFilter
import kotlinx.android.synthetic.main.row_fixture.view.*
import java.text.SimpleDateFormat
import java.util.*

class FixtureRecyclerViewAdapter :
    RecyclerView.Adapter<FixtureRecyclerViewAdapter.ViewHolder>(), Filterable {

    private val values: MutableList<Fixture> = mutableListOf()
    private val filterValues: MutableList<Fixture> = mutableListOf()

    private val filter = object : FixtureFilter(values) {
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filterValues.clear()
            results?.let {
                filterValues.addAll(results.values as List<Fixture>)
            }
            notifyDataSetChanged()
        }
    }

    private val sdfInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    private val sdfOutput = SimpleDateFormat("MMM d, yy 'at' HH:mm:ss", Locale.US)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_fixture, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val context = holder.rootView.context

        val item = values[position]

        holder.competitionTextView.text = item.competitionStage.competition.name

        holder.postponedTextView.visibility = if (item.isPostponed()) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }

        holder.venueTextView.text = item.venue.name

        val date = sdfInput.parse(item.date)
        holder.dateTextView.text = sdfOutput.format(date)

        holder.homeTextView.text = item.homeTeam.name
        holder.awayTextView.text = item.awayTeam.name

        if (item is Result) {
            holder.homeScoreTextView.visibility = View.VISIBLE
            holder.homeScoreTextView.text = item.score.home.toString()
            holder.awayScoreTextView.visibility = View.VISIBLE
            holder.awayScoreTextView.text = item.score.away.toString()

            val highlightedColor = ResourcesCompat.getColor(context.resources, R.color.colorAccent, null)

            val normalColor = ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null)

            when {
                item.score.isHomeWinner() -> {
                    holder.homeScoreTextView.setTextColor(highlightedColor)
                    holder.awayScoreTextView.setTextColor(normalColor)
                }
                item.score.isAwayWinner() -> {
                    holder.awayScoreTextView.setTextColor(highlightedColor)
                    holder.homeScoreTextView.setTextColor(normalColor)
                }
                else -> {
                    holder.awayScoreTextView.setTextColor(normalColor)
                    holder.homeScoreTextView.setTextColor(normalColor)
                }
            }

        } else {
            holder.homeScoreTextView.visibility = View.INVISIBLE
            holder.awayScoreTextView.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int = filterValues.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rootView: View = view
        val competitionTextView: TextView = view.competitionTextView
        val postponedTextView: TextView = view.postponedTextView
        val venueTextView: TextView = view.venueTextView
        val dateTextView: TextView = view.dateTextView
        val homeTextView: TextView = view.homeTeamTextView
        val awayTextView: TextView = view.awayTeamTextView
        val homeScoreTextView: TextView = view.homeTeamScoreTextView
        val awayScoreTextView: TextView = view.awayTeamScoreTextView
    }

    fun setValues(listFixture: List<Fixture>) {
        values.clear()
        values.addAll(listFixture)
        filterValues.clear()
        filterValues.addAll(listFixture)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return filter
    }
}

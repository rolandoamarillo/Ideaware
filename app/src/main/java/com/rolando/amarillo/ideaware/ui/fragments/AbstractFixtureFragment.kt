package com.rolando.amarillo.ideaware.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rolando.amarillo.ideaware.R
import com.rolando.amarillo.ideaware.model.Fixture
import com.rolando.amarillo.ideaware.repositories.Resource
import com.rolando.amarillo.ideaware.ui.adapters.FixtureRecyclerViewAdapter
import com.rolando.amarillo.ideaware.viewmodels.ResourceObserver
import kotlinx.android.synthetic.main.fragment_fixture_list.*


abstract class AbstractFixtureFragment : Fragment() {

    private lateinit var adapter: FixtureRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fixture_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        adapter = FixtureRecyclerViewAdapter()
        list.layoutManager = layoutManager
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        getLiveDataFixtures().observe(this, object : ResourceObserver<List<Fixture>>() {

            override fun onSuccess(t: List<Fixture>?) {
                progressBar.visibility = View.INVISIBLE
                errorTextView.visibility = View.INVISIBLE
                list.visibility = View.VISIBLE
                t?.let {
                    adapter.setValues(t)
                }
            }

            override fun onError(errorMessage: String?) {
                list.visibility = View.INVISIBLE
                progressBar.visibility = View.INVISIBLE
                errorTextView.visibility = View.VISIBLE
            }

            override fun onLoading() {
                errorTextView.visibility = View.INVISIBLE
                list.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            }

        })
    }

    abstract fun getLiveDataFixtures(): LiveData<Resource<List<Fixture>>>
}

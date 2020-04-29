package com.davidups.skell.features.people.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidups.skell.R
import com.davidups.skell.core.platform.BaseFragment
import com.davidups.skell.databinding.FragmentPeopleBinding
import com.davidups.skell.features.people.models.view.PeopleView
import com.davidups.skell.features.people.view.adapters.PeopleAdapter
import com.davidups.skell.features.people.view.viewmodels.PeopleViewModel
import com.davidups.starwars.core.extensions.failure
import com.davidups.starwars.core.extensions.observe
import com.kotlinpermissions.notNull
import kotlinx.android.synthetic.main.fragment_people.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PeopleListFragment : BaseFragment<FragmentPeopleBinding>() {

    private val peopleViewmodel: PeopleViewModel by viewModel()
    private val peopleAdapter: PeopleAdapter by inject()

    private var _binding :FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(peopleViewmodel) {
            observe(showSpinner, ::handleShowSpinner)
            observe(people, ::handlePeople)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
        binding.tvName.text
    }

    override fun inflateBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPeopleBinding? {
        _binding = FragmentPeopleBinding.inflate(inflater!!,container!!, false)
        return binding
    }

    private fun initView() {
        peopleViewmodel.getPeople()

        binding.rvPeople.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = peopleAdapter
        }
    }

    private fun initListeners() {
        peopleAdapter.clickListener = {

        }
    }

    private fun handlePeople(peopleView: PeopleView?) {
        peopleView.notNull {
            peopleAdapter.collection = it.results.orEmpty()
        }
    }

    private fun handleShowSpinner(show: Boolean?) {
        showSpinner(show ?: false)
    }

    private fun handleFailure(failure: Throwable?) {
        Log.w("holi", "people ")
    }


}

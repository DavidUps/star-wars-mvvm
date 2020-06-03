package com.davidups.starwars.features.people.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davidups.skell.R
import com.davidups.starwars.core.extensions.Constants
import com.davidups.starwars.core.extensions.showInfoAlertDialog
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.skell.databinding.FragmentPeopleBinding
import com.davidups.starwars.features.people.models.view.PeopleView
import com.davidups.skell.features.people.view.adapters.PeopleAdapter
import com.davidups.starwars.features.people.view.viewmodels.PeopleViewModel
import com.davidups.starwars.core.extensions.failure
import com.davidups.starwars.core.extensions.observe
import com.davidups.starwars.core.platform.viewBinding.viewBinding
import com.kotlinpermissions.notNull
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PeopleListFragment : BaseFragment(R.layout.fragment_people) {

    private val peopleViewmodel: PeopleViewModel by viewModel()
    private val peopleAdapter: PeopleAdapter by inject()
    private val binding by viewBinding(FragmentPeopleBinding::bind)

    private var people = PeopleView.empty()

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
    }

    private fun initView() {
        peopleViewmodel.getPeople()

        binding.tvName.text
        binding.rvPeople.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = peopleAdapter
        }
    }

    private fun initListeners() {
        peopleAdapter.clickListener = {
            val bundle = bundleOf(Constants.People.PERSON to it)
            view?.findNavController()?.navigate(R.id.action_people_to_person, bundle)
        }

        binding.rvPeople.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = binding.rvPeople.layoutManager as GridLayoutManager
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == peopleAdapter.collection.size - 1) {
                    peopleViewmodel.loadMorePeople(people.toPeople().toPeopleEntity())
                }
            }
        })
    }

    private fun handlePeople(peopleView: PeopleView?) {
        peopleView.notNull { peopleView ->
            people.next = peopleView.next
            people.results!!.addAll(peopleView.results!!.toMutableList())
            peopleAdapter.collection = people.results.orEmpty()
        }
    }

    private fun handleShowSpinner(show: Boolean?) {
        showSpinner(show ?: false)
    }

    private fun handleFailure(failure: Throwable?) {
        showInfoAlertDialog {
            setTitle(getString(R.string.common_error))
        }.show()
    }
}

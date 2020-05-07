package com.davidups.starwars.features.people.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.davidups.skell.R
import com.davidups.starwars.core.extensions.Constants
import com.davidups.skell.core.extensions.loadFromUrl
import com.davidups.skell.core.extensions.randomImage
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.skell.databinding.FragmentPersonBinding
import com.davidups.skell.features.people.models.view.PersonView
import com.davidups.starwars.core.platform.viewBinding.viewBinding

class PersonDetailFragment: BaseFragment(R.layout.fragment_person) {

    val binding by viewBinding(FragmentPersonBinding::bind)

    private var people = PersonView.empty()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val argunments = arguments
        if (argunments != null) {
            people = argunments.getSerializable(Constants.People.PERSON) as PersonView
        }

        initView()
    }

    private fun initView() {
        binding.ivBanner.loadFromUrl(String.randomImage())
        binding.tvName.text = people.name
        binding.tvBithYear.text = "Birth year: ${people.birth_year}"
        binding.tvGender.text = "Gender: ${people.gender}"
        binding.tvHeight.text = "Height: ${people.height}"
    }
}
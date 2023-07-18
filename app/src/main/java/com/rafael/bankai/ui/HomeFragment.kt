package com.rafael.bankai.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rafael.bankai.R
import com.rafael.bankai.databinding.FragmentHomeBinding

/**
 * This Fragment is the Landing Page
 */
class HomeFragment : Fragment(), OnClickListener {

    private val viewModel: OverviewViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        // Eventos
        binding.buttonInfo.setOnClickListener(this)
        binding.buttonCharacters.setOnClickListener(this)
        binding.buttonQuotes.setOnClickListener(this)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_info -> {
                findNavController().navigate(R.id.action_homeFragment_to_infoFragment)
            }
            R.id.button_characters -> {
                findNavController().navigate(R.id.action_homeFragment_to_charactersListFragment)
            }
            R.id.button_quotes -> {
                findNavController().navigate(R.id.action_homeFragment_to_quotesFragment)
            }
        }
    }

}
package com.rafael.bankai.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rafael.bankai.R
import com.rafael.bankai.databinding.FragmentHomeBinding

/**
 * This Fragment is the Landing Page
 */
class HomeFragment : Fragment(), OnClickListener {

    // private val viewModel: AmphibianViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        // TODO set viewModel
        // binding.viewModel = viewModel

        // Eventos
        binding.buttonInfo.setOnClickListener(this)
        binding.buttonCharacters.setOnClickListener(this)
        binding.buttonQuotes.setOnClickListener(this)

        // return inflater.inflate(R.layout.fragment_home, container, false)

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
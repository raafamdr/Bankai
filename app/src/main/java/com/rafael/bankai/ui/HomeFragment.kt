package com.rafael.bankai.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafael.bankai.databinding.FragmentHomeBinding

/**
 * This Fragment is the Landing Page
 */
class HomeFragment : Fragment() {

    // private val viewModel: AmphibianViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        // TODO set viewModel
        // binding.viewModel = viewModel

        // return inflater.inflate(R.layout.fragment_home, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

}
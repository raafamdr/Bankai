package com.rafael.bankai.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rafael.bankai.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private val viewModel: OverviewViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInfoBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        // Inflate the layout for this fragment
        return binding.root
    }
}
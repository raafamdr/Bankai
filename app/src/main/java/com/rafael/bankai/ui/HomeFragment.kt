package com.rafael.bankai.ui

import android.os.Bundle
import android.view.*
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
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

        // Events
        binding.textDetails.setOnClickListener(this)
        binding.textCharacters.setOnClickListener(this)
        binding.textQuotes.setOnClickListener(this)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.text_details -> {
                findNavController().navigate(R.id.action_homeFragment_to_infoFragment)
            }
            R.id.text_characters -> {
                findNavController().navigate(R.id.action_homeFragment_to_charactersListFragment)
            }
            R.id.text_quotes -> {
                findNavController().navigate(R.id.action_homeFragment_to_quotesFragment)
            }
        }
    }

}
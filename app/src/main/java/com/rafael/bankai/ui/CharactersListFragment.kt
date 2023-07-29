package com.rafael.bankai.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rafael.bankai.R
import com.rafael.bankai.databinding.FragmentCharactersListBinding

class CharactersListFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: OverviewViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var adapter: CharacterListAdapter
    private var filteredList: List<com.rafael.bankai.network.Character> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCharactersListBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        adapter = CharacterListAdapter()
        binding.recyclerView.adapter = adapter

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        // The usage of an interface lets you inject your own implementation
        val menuHost: MenuHost = requireActivity()

        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.info_menu, menu)

                // Initialize the SearchView
                val searchItem = menu.findItem(R.id.menu_search)
                val searchView = searchItem.actionView as SearchView
                searchView.setOnQueryTextListener(this@CharactersListFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.homeFragment -> {
                        navController.navigateUp()
                        return true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        filterList(query)
        return true
    }

    private fun filterList(query: String?) {
        filteredList = if (query.isNullOrEmpty()) {
            viewModel.getAllCharacters()
        } else {
            viewModel.searchCharacters(query)
        }

        adapter.submitList(filteredList)
    }

}
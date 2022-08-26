package com.example.mvvmdemoproject.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.model.TransformResponse
import com.example.mvvmdemoproject.data.remote.networkboundresource.Status
import com.example.mvvmdemoproject.databinding.FragmentHomeBinding
import com.example.mvvmdemoproject.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()
    private val viewModel by viewModels<HomeFragmentViewModel>()
    var data: List<TransformResponse> = emptyList()
    var pageCount = 1

    private lateinit var chatsCallGroupContactAdapter: GameListAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var isLoading: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = emptyList()
        chatsCallGroupContactAdapter = GameListAdapter(requireContext(), ::navigateToProfile)
            .also {
                binding.recyclerView.adapter = it
            }
        layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (layoutManager.findLastCompletelyVisibleItemPosition() == data.size - 1) {
                    fetchMore()
                    isLoading = true
                }
            }
        })
        fetchGamesData()
    }

    private fun navigateToProfile(selectedItem: TransformResponse) {
        val args = Bundle().apply {
            putParcelable(TRANSFORM_ARGUMENT, selectedItem)
        }
        findNavController().navigate(R.id.action_nav_home_fragment_to_detailFragment, args)
    }

    private fun fetchGamesData() = lifecycleScope.launchWhenCreated {
        viewModel.getData(pageCount).collect {
            when (it.status) {
                Status.LOADING -> binding.progressbar.isVisible = true

                Status.SUCCESS -> {
                    binding.progressbar.isVisible = false
                    getMoreItems(it.data ?: emptyList())
                }
                Status.ERROR -> Unit
            }
        }
    }

    private fun fetchMore() = lifecycleScope.launchWhenCreated {
        pageCount += 1
        viewModel.getData(pageCount).collect {
            when (it.status) {
                Status.LOADING -> binding.progressbar.isVisible = true

                Status.SUCCESS -> {
                    binding.progressbar.isVisible = false
                    getMoreItems(it.data ?: emptyList())
                }
                Status.ERROR -> Unit
            }
        }
    }

    private fun getMoreItems(listItems: List<TransformResponse>) {
        val tempList = data.toMutableList()
        tempList.addAll(listItems)
        chatsCallGroupContactAdapter.submitList(sortByOldest(tempList))
        data = tempList
    }

    private fun sortByOldest(listItems: List<TransformResponse>) = listItems.sortedByDescending { it.getCreatedDate }
    companion object {
        const val TRANSFORM_ARGUMENT = "chatsOverview"
    }
}

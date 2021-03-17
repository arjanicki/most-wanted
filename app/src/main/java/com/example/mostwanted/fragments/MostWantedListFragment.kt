package com.example.mostwanted.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mostwanted.OnItemClickListener
import com.example.mostwanted.adapters.MostWantedListAdapter
import com.example.mostwanted.adapters.MostWantedLoadingStateAdapter
import com.example.mostwanted.databinding.FragmentMostWantedListBinding
import com.example.mostwanted.models.MostWantedPerson
import com.example.mostwanted.viewmodels.MostWantedListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MostWantedListFragment : Fragment(), AndroidScopeComponent,
    OnItemClickListener<MostWantedPerson> {
    override val scope by fragmentScope()
    private val mostWantedListAdapter: MostWantedListAdapter by inject { parametersOf(this) }
    private val loadingStateAdapter: MostWantedLoadingStateAdapter by inject()
    private val viewModel = viewModel<MostWantedListViewModel>()

    private var _binding: FragmentMostWantedListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMostWantedListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == mostWantedListAdapter.itemCount && loadingStateAdapter.itemCount > 0) {
                    2
                } else {
                    1
                }
            }
        }

        binding.recipeListRecyclerview.apply {
            adapter = mostWantedListAdapter
                .withLoadStateFooter(
                    loadingStateAdapter,
                )
            layoutManager = gridLayoutManager

        }
        binding.swipeRefreshLayout.setOnRefreshListener { mostWantedListAdapter.refresh() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.value.getMostWantedPeople().collect {
                binding.swipeRefreshLayout.isRefreshing = false
                mostWantedListAdapter.submitData(it)
            }
        }
    }

    override fun onClick(item: MostWantedPerson) {
        findNavController().navigate(
            MostWantedListFragmentDirections.actionMostWantedListFragmentToMostWantedDetailFragment(
                item,
                item.name
            )
        )
    }
}
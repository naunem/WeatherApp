package com.example.weatherapp.ui.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.extensions.hideKeyboard
import com.example.weatherapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModels()
    private val searchAdapter by lazy {
        SearchAdapter().apply {
            onItemFavoriteClick = {
                viewModel.handleFavoriteLocation(it)
            }
        }
    }

    override fun getViewBinding(): FragmentSearchBinding =
        FragmentSearchBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.run {
            getSearchResultLiveData().observe(viewLifecycleOwner, {
                searchAdapter.submitList(it)
            })
            getErrorApiLiveData().observe(viewLifecycleOwner, {
                showErrorDialog(it)
            })
            getLoadingLiveData().observe(viewLifecycleOwner, {
                showHideLoadingProgress(it)
            })
        }
    }

    private fun initViews() {
        binding.run {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                itemAnimator = null
                adapter = searchAdapter
            }
        }
    }

    private fun initListeners() {
        binding.run {
            edtSearch.setOnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handleSearch(v.text.toString())
                    v.hideKeyboard()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }
    }

    private fun handleSearch(query: String) {
        viewModel.searchLocation(query)
    }

    private fun showErrorDialog(message: String) {
        context?.run {
            AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_text_ok) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun showHideLoadingProgress(isShow: Boolean) {
        binding.progressBar.visibility = if (isShow) View.VISIBLE else View.GONE
    }
}

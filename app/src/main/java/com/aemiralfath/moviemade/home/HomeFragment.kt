package com.aemiralfath.moviemade.home

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aemiralfath.core.data.Resource
import com.aemiralfath.core.domain.model.Movie
import com.aemiralfath.core.ui.MovieAdapter
import com.aemiralfath.core.utils.SortUtils
import com.aemiralfath.moviemade.R
import com.aemiralfath.moviemade.databinding.FragmentHomeBinding
import com.aemiralfath.moviemade.detail.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var querySearch: String = ""
    private var sortType: String = SortUtils.NEWEST

    private lateinit var movieAdapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = {
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            val searchManager =
                requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager

            homeViewModel.setMovies(sortType, querySearch)
                .observe(viewLifecycleOwner, movieObserver)

            with(binding) {
                rvMovie.layoutManager = LinearLayoutManager(context)
                rvMovie.setHasFixedSize(true)
                rvMovie.adapter = movieAdapter

                svMovie.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
                svMovie.isActivated = true
                svMovie.queryHint = resources.getString(R.string.search_movie)
                svMovie.onActionViewExpanded()
                svMovie.isIconified = false
                svMovie.clearFocus()

                svMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            homeViewModel.setMovies(sortType, it)
                                .observe(viewLifecycleOwner, movieObserver)
                            querySearch = it
                        }
                        requireActivity().hideSoftKeyboard()
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        newText?.let {
                            homeViewModel.setMovies(sortType, it)
                                .observe(viewLifecycleOwner, movieObserver)
                        }
                        return true
                    }
                })

                val idBtn = svMovie.context.resources.getIdentifier(
                    "android:id/search_close_btn",
                    null,
                    null
                )
                val closeButton = svMovie.findViewById<ImageView>(idBtn)

                closeButton.setOnClickListener {
                    querySearch = ""
                    homeViewModel.setMovies(sortType, querySearch)
                        .observe(viewLifecycleOwner, movieObserver)
                    svMovie.setQuery("", false)
                    requireActivity().hideSoftKeyboard()
                }
            }

        }
    }

    private val movieObserver = Observer<Resource<List<Movie>>> {
        if (it != null) {
            when (it) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    movieAdapter.setData(it.data)
                    binding.viewEmpty.root.visibility =
                        if (it.data?.isNotEmpty() == true) View.GONE else View.VISIBLE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.viewError.root.visibility = View.VISIBLE
                    binding.viewError.tvError.text =
                        it.message ?: getString(R.string.something_wrong)
                }
            }
        }
    }

    private fun Activity.hideSoftKeyboard() {
        currentFocus?.let {
            val inputMethodManager =
                ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
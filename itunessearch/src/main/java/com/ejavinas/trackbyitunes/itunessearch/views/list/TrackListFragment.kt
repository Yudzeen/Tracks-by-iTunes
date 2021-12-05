package com.ejavinas.trackbyitunes.itunessearch.views.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ejavinas.trackbyitunes.common.lifecycle.EventObserver
import com.ejavinas.trackbyitunes.common.utils.Resource
import com.ejavinas.trackbyitunes.common.utils.network.NetworkException
import com.ejavinas.trackbyitunes.common.utils.network.NetworkUtil
import com.ejavinas.trackbyitunes.itunessearch.R
import com.ejavinas.trackbyitunes.itunessearch.databinding.FragmentTrackListBinding
import com.ejavinas.trackbyitunes.itunessearch.model.Track
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment for displaying list of [Track]
 */
@AndroidEntryPoint
class TrackListFragment : Fragment() {

    private val viewModel: TrackListViewModel by viewModels()
    private lateinit var binding: FragmentTrackListBinding
    private lateinit var adapter: TrackListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrackListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initGetTracksObserver()
        initRefreshEventObserver()
    }

    private fun initViews() {
        initAppBar()
        initAdapter()
        initRecyclerView()
    }

    private fun initAppBar() {
        binding.toolbar.apply {
            title = getString(R.string.app_name)
            inflateMenu(R.menu.menu_track_list)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.refresh -> {
                        handleRefreshAction()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun initAdapter() {
        adapter = TrackListAdapter(object : TrackListAdapter.Listener {
            override fun onItemClicked(track: Track) {
                navigateToTrackDetail(track)
            }
        })
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
    }

    private fun initGetTracksObserver() {
        viewModel.getTracksLiveData().observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> handleGetTracksLoading()
                is Resource.Success -> handleGetTracksSuccess(it.data)
                is Resource.Error -> handleGetTracksError(it.error)
            }
        })
    }

    private fun handleGetTracksLoading() {
        Log.i(TAG, "Get tracks loading...")
    }

    private fun handleGetTracksSuccess(tracks: List<Track>) {
        Log.i(TAG, "Tracks: $tracks")
        adapter.submitList(tracks)
        if (tracks.isEmpty()) {
            if (!NetworkUtil.hasNetworkConnection(requireActivity().application)) {
                Snackbar.make(binding.root, getString(R.string.msg_empty_tracks_and_no_network),
                    Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun handleGetTracksError(error: Throwable) {
        Log.e(TAG, "Get tracks error", error)
        Toast.makeText(requireContext(), getString(R.string.error_getting_tracks),
            Toast.LENGTH_SHORT).show()
    }

    private fun initRefreshEventObserver() {
        viewModel.getRefreshEventLiveData().observe(viewLifecycleOwner, EventObserver {
            when (it) {
                is Resource.Loading -> handleRefreshLoading()
                is Resource.Success -> handleRefreshSuccess()
                is Resource.Error -> handleRefreshError(it.error)
            }
        })
    }

    private fun handleRefreshAction() {
        viewModel.refreshTracks()
    }

    private fun handleRefreshLoading() {
        Log.i(TAG, "Refreshing...")
        Toast.makeText(requireContext(), getString(R.string.getting_tracks), Toast.LENGTH_LONG).show()
    }

    private fun handleRefreshSuccess() {
        Log.i(TAG, "Refresh complete")
    }

    private fun handleRefreshError(error: Throwable) {
        Log.e(TAG, "Refresh error", error)
        val errorMessage = when (error) {
            is NetworkException -> getString(R.string.error_network_connection)
            else -> getString(R.string.error_refreshing_tracks)
        }
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToTrackDetail(track: Track) {
        TrackListFragmentDirections.actionTrackListFragmentToTrackDetailFragment(track).let {
            findNavController().navigate(it)
        }
    }

    companion object {
        private const val TAG = "TrackListFragment"
    }

}
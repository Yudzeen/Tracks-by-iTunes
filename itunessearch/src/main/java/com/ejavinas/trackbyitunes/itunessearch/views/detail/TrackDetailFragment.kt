package com.ejavinas.trackbyitunes.itunessearch.views.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ejavinas.trackbyitunes.itunessearch.R
import com.ejavinas.trackbyitunes.itunessearch.databinding.FragmentTrackDetailBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment for detail view of a [Track]
 */
@AndroidEntryPoint
class TrackDetailFragment : Fragment() {

    private val viewModel: TrackDetailViewModel by viewModels()
    private lateinit var binding: FragmentTrackDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrackDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAppBar()
        bindTrack()
    }

    private fun initAppBar() {
        binding.toolbar.apply {
            title = viewModel.getTrack().trackName
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener { findNavController().navigateUp() }
        }
    }

    private fun bindTrack() {
        val item = viewModel.getTrack()
        binding.apply {
            track = item
            description.text = item.longDescription ?: getString(R.string.no_description_available)
            executePendingBindings()
        }
    }

}
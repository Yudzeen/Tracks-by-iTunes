package com.ejavinas.trackbyitunes.itunessearch.views.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ejavinas.trackbyitunes.itunessearch.databinding.ListItemTrackBinding
import com.ejavinas.trackbyitunes.itunessearch.model.Track

/**
 * Adapter for list of [Track] objects
 */
class TrackListAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<TrackListAdapter.ViewHolder>() {

    private val tracks = mutableListOf<Track>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemTrackBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tracks[position], listener)
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newTracks: List<Track>) {
        tracks.clear()
        tracks.addAll(newTracks)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onItemClicked(track: Track)
    }

    class ViewHolder(
        private val binding: ListItemTrackBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Track, listener: Listener) {
            binding.apply {
                track = item
                setClickListener { listener.onItemClicked(item) }
                executePendingBindings()
            }
        }
    }

}
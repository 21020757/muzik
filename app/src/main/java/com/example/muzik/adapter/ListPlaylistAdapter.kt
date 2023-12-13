package com.example.muzik.adapter;

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.RecyclerView
import com.example.muzik.R
import com.example.muzik.response_model.Playlist
import com.example.muzik.ui.playlist_album_fragment.PlaylistAlbumViewModel
import com.squareup.picasso.Picasso

class ListPlaylistAdapter(
    private val listPlaylist: List<Playlist>,
    private val navHostController: NavHostController
) :
    RecyclerView.Adapter<ListPlaylistAdapter.ListPlaylistViewHolder>() {

    class ListPlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPlaylistViewHolder {
        return ListPlaylistViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_playlist,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listPlaylist.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListPlaylistViewHolder, position: Int) {
        val curPlaylist = listPlaylist[position]
        val tvPlaylistName: TextView = holder.itemView.findViewById(R.id.tvPlaylistsName)
        val playlistImage: ImageView = holder.itemView.findViewById(R.id.playlist_image_item)

        if (curPlaylist.playListID.toInt() == -1) {
            playlistImage.setBackgroundResource(R.drawable.baseline_add_box_24)
            (tvPlaylistName.parent as LinearLayout).removeViewAt(1)
            tvPlaylistName.text = "Add more playlists!"
        } else {
            holder.itemView.apply {
                tvPlaylistName.text = curPlaylist.name

                if (!curPlaylist.imageURL.isNullOrEmpty()) {
                    Picasso.get()
                        .load(curPlaylist.imageURL)
                        .into(playlistImage)
                }
            }

            holder.itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("playlistAlbumID", curPlaylist.playListID.toInt())
                bundle.putString("playlistAlbumImageURL", curPlaylist.imageURL)
                bundle.putString("playlistAlbumName", curPlaylist.name)
                bundle.putSerializable("type", PlaylistAlbumViewModel.Type.ALBUM)
                navHostController.navigate(
                    R.id.playlistAlbumFragment, bundle, NavOptions.Builder()
                        .setEnterAnim(R.anim.slide_in_right)
                        .setExitAnim(R.anim.slide_out_right)
                        .setPopEnterAnim(R.anim.slide_in_right)
                        .setPopExitAnim(R.anim.slide_out_right)
                        .build()
                )
            }
        }
    }
}

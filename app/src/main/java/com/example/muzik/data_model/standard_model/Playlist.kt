package com.example.muzik.data_model.standard_model

import android.net.Uri
import com.example.muzik.data_model.retrofit_model.response.PlaylistResponse
import java.util.Date

class Playlist(
    val playListID: Long? = -1,
    val type: String? = "",
    val userID: Long? = -1,
    val name: String? = "",
    val dateAdded: Date? = Date(),
    val imageURI: Uri? = Uri.parse("")
) : Model {
    companion object {
        fun buildOnline(playlistResponse: PlaylistResponse): Playlist {
            return Playlist(
                playListID = playlistResponse.playListID,
                type = playlistResponse.type,
                userID = playlistResponse.userID,
                name = playlistResponse.name,
                dateAdded = playlistResponse.dateAdded,
                imageURI = Uri.parse(playlistResponse.imageURL)
            )
        }
    }
}
package com.example.maguilarmusicapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val cover_url: String
)

@Serializable
data class Track(
    val id: String,
    val title: String,
    val duration: String
)

@Serializable
data class AlbumDetail(
    val id: String,
    val title: String,
    val artist: String,
    val cover_url: String,
    val description: String,
    val tracks: List<Track>
)

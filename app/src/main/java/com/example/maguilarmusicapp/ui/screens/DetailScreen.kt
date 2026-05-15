package com.example.maguilarmusicapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.maguilarmusicapp.model.AlbumDetail
import com.example.maguilarmusicapp.model.Track
import com.example.maguilarmusicapp.network.RetrofitInstance
import com.example.maguilarmusicapp.ui.theme.BackgroundLavender
import com.example.maguilarmusicapp.ui.theme.CardWhite
import com.example.maguilarmusicapp.ui.theme.PrimaryPurple
import com.example.maguilarmusicapp.ui.theme.TextGray

@Composable
fun DetailScreen(albumId: String, onBack: () -> Unit) {
    var albumDetail by remember { mutableStateOf<AlbumDetail?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(albumId) {
        try {
            albumDetail = RetrofitInstance.api.getAlbumDetail(albumId)
            isLoading = false
        } catch (e: Exception) {
            errorMessage = "Error loading details: ${e.message}"
            isLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLavender)
    ) {
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = PrimaryPurple)
            }
        } else if (errorMessage != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = errorMessage!!, color = Color.Red, modifier = Modifier.padding(16.dp))
                    Button(onClick = onBack) { Text("Back") }
                }
            }
        } else {
            albumDetail?.let { album ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 100.dp)
                ) {
                    // Top Image Header
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp)
                        ) {
                            AsyncImage(
                                model = album.cover_url,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            
                            // Scrim/Gradient Overlay at the bottom
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                                            startY = 500f
                                        )
                                    )
                            )

                            // Top Action Bar
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 48.dp, start = 16.dp, end = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                IconButton(
                                    onClick = onBack,
                                    modifier = Modifier.background(Color.Black.copy(alpha = 0.3f), CircleShape)
                                ) {
                                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                                }
                                IconButton(
                                    onClick = { /* TODO */ },
                                    modifier = Modifier.background(Color.Black.copy(alpha = 0.3f), CircleShape)
                                ) {
                                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorite", tint = Color.White)
                                }
                            }

                            // Album Info Overlay
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(24.dp)
                            ) {
                                Text(
                                    text = album.title,
                                    color = Color.White,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = album.artist,
                                    color = Color.White.copy(alpha = 0.8f),
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(
                                        onClick = { /* TODO */ },
                                        modifier = Modifier
                                            .size(48.dp)
                                            .background(PrimaryPurple, CircleShape)
                                    ) {
                                        Icon(Icons.Default.PlayArrow, contentDescription = "Play", tint = Color.White)
                                    }
                                    // ... other buttons if needed
                                }
                            }
                        }
                    }

                    // About Section
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            colors = CardDefaults.cardColors(containerColor = CardWhite),
                            shape = RoundedCornerShape(24.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text(
                                    text = "About this album",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = PrimaryPurple
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = album.description,
                                    color = TextGray,
                                    fontSize = 14.sp,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }

                    // Artist Tag
                    item {
                        Surface(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = Color.White,
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                text = "Artist: ${album.artist}",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Track List
                    items(album.tracks) { track ->
                        TrackItem(track = track, artist = album.artist)
                    }
                }
            }
        }
    }
}

@Composable
fun TrackItem(track: Track, artist: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        color = CardWhite,
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Smaller placeholder or art for track
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = track.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = artist,
                    color = TextGray,
                    fontSize = 12.sp
                )
            }
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                tint = TextGray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

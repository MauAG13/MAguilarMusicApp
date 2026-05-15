package com.example.maguilarmusicapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maguilarmusicapp.ui.theme.BackgroundLavender
import com.example.maguilarmusicapp.ui.theme.CardWhite
import com.example.maguilarmusicapp.ui.theme.PrimaryPurple
import com.example.maguilarmusicapp.ui.theme.TextGray

@Composable
fun DetailScreen(albumId: Int, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLavender)
    ) {
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
                    // Placeholder for Album Art
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
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
                            text = "Título del Álbum $albumId",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Artista",
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
                            Spacer(modifier = Modifier.width(16.dp))
                            IconButton(
                                onClick = { /* TODO */ },
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(Color.White, CircleShape)
                            ) {
                                Icon(Icons.Default.PlayArrow, contentDescription = "Shuffle", tint = Color.Black)
                            }
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
                            text = "Esta es una descripción placeholder del álbum. Aquí se mostrará la información real cuando conectemos la API.",
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
                    shape = RoundedCornerShape(16.dp),
                    border = null // Will add later if needed
                ) {
                    Text(
                        text = "Artist: Artista",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Track List
            items(10) { index ->
                TrackItemSkeleton(index + 1)
            }
        }
    }
}

@Composable
fun TrackItemSkeleton(trackNumber: Int) {
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
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Título de Canción $trackNumber",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Artista",
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

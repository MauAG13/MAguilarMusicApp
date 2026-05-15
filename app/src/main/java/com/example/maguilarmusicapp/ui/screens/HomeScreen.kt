package com.example.maguilarmusicapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maguilarmusicapp.ui.theme.BackgroundLavender
import com.example.maguilarmusicapp.ui.theme.CardWhite
import com.example.maguilarmusicapp.ui.theme.PrimaryPurple
import com.example.maguilarmusicapp.ui.theme.TextGray

@Composable
fun HomeScreen(onAlbumClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLavender)
    ) {
        // Top Header Section with Gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(180.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(PrimaryPurple, PrimaryPurple.copy(alpha = 0.8f))
                    )
                )
                .padding(24.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Good Morning!",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 16.sp
                )
                Text(
                    text = "Mauricio Aguilar",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Main Scrollable Content
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp) // Space for MiniPlayer
        ) {
            // Albums Section
            item {
                SectionHeader(title = "Albums")
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedCorner(16.dp)
                ) {
                    items(5) { index ->
                        AlbumCardSkeleton(onClick = { onAlbumClick(index) })
                    }
                }
            }

            // Recently Played Section
            item {
                SectionHeader(title = "Recently Played")
            }

            items(10) { index ->
                RecentlyPlayedItemSkeleton(onClick = { onAlbumClick(index + 10) })
            }
        }
    }
}

private fun Arrangement.spacedCorner(space: androidx.compose.ui.unit.Dp): Arrangement.Horizontal {
    return Arrangement.spacedBy(space)
}

@Composable
fun SectionHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "See more", color = PrimaryPurple, fontSize = 14.sp)
    }
}

@Composable
fun AlbumCardSkeleton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(160.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        ) {
            Text(
                text = "Título",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Artista",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp
            )
        }
        
        // Play button placeholder
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
                .size(32.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.Black, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun RecentlyPlayedItemSkeleton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable { onClick() },
        color = CardWhite,
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 2.dp
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
                    text = "Título",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Artista • Popular Song",
                    color = TextGray,
                    fontSize = 12.sp
                )
            }
            Icon(
                imageVector = Icons.Default.Menu, // More vertical icon would be better
                contentDescription = null,
                tint = TextGray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

package com.example.meditationui.ui.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationui.R
import com.example.meditationui.data.model.BottomMenuItem
import com.example.meditationui.data.model.Feature
import com.example.meditationui.ui.theme.AquaBlue
import com.example.meditationui.ui.theme.Beige
import com.example.meditationui.ui.theme.BlueViolet
import com.example.meditationui.ui.theme.ButtonBlue
import com.example.meditationui.ui.theme.DarkerButtonBlue
import com.example.meditationui.ui.theme.DeepBlue
import com.example.meditationui.ui.theme.LightGreen
import com.example.meditationui.ui.theme.LightRed
import com.example.meditationui.ui.theme.OrangeYellow
import com.example.meditationui.ui.theme.TextWhite

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        Column(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
            GreetingSection()
            ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeaturedSection(
                features = listOf(
                    Feature(
                        title = "Sleep meditation",
                        color = BlueViolet,
                        iconId = R.drawable.ic_headphone
                    ),
                    Feature(
                        title = "Tips for sleeping",
                        color = LightGreen,
                        iconId = R.drawable.ic_videocam
                    ),
                    Feature(
                        title = "Night island",
                        color = OrangeYellow,
                        iconId = R.drawable.ic_headphone
                    ),
                    Feature(
                        title = "Calming sounds",
                        color = Beige,
                        iconId = R.drawable.ic_headphone
                    )
                )
            )
        }
        BottomMenu(
            items = listOf(
                BottomMenuItem("Home", R.drawable.ic_home),
                BottomMenuItem("Meditate", R.drawable.ic_bubble),
                BottomMenuItem("Sleep", R.drawable.ic_moon),
                BottomMenuItem("Music", R.drawable.ic_music),
                BottomMenuItem("Profile", R.drawable.ic_profile),
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuItem>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0

) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { Index, Item ->
            BottomMenuItem(
                item = Item,
                isSelected = Index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor

            ) {
                selectedItemIndex = Index
            }
        }

    }


}

@Composable
fun BottomMenuItem(
    item: BottomMenuItem,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onSelected: () -> Unit

) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onSelected()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(15.dp)
            )

        }

        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }

}


@Composable
fun GreetingSection(
    name: String = "Phillip"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good morning, $name",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 15.dp)
            )
            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)

            )
        }
        Icon(
            painter = painterResource(R.drawable.ic_search_foreground),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier
                .size(50.dp)
                .padding(top = 5.dp)
        )

    }
}

@Composable
fun ChipSection(
    chips: List<String>
) {
    var selectedShipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
    ) {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .clickable {
                        selectedShipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedShipIndex == it) ButtonBlue else DarkerButtonBlue
                    )
                    .padding(
                        horizontal = 15.dp, vertical = 15.dp
                    )) {
                Text(text = chips[it], color = TextWhite)
            }

        }
    }

}

@Composable
fun CurrentMeditation(color: Color = LightRed) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = "Meditation • 3-10 min",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_playarrow_foreground),
                contentDescription = "play",
                tint = Color.White,
                modifier = Modifier.size(50.dp)
            )
        }


    }

}

@Composable
fun FeaturedSection(features: List<Feature>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Featured",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            items(features.size) {
                FeatureItem(features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(feature: Feature) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(feature.color)
            .padding(7.5.dp)
            .aspectRatio(1f)

    ) {
        Text(
            text = feature.title,
            style = MaterialTheme.typography.titleMedium,
            lineHeight = 26.sp,
            modifier = Modifier.align(Alignment.TopStart)
        )
        Icon(
            painter = painterResource(id = feature.iconId),
            contentDescription = feature.title,
            tint = Color.White,
            modifier = Modifier.align(Alignment.BottomStart)
        )
        Text(
            text = "Start",
            color = TextWhite,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                    // Handle the click
                }
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(10.dp))
                .background(ButtonBlue)
                .padding(vertical = 6.dp, horizontal = 15.dp)
        )
    }

}


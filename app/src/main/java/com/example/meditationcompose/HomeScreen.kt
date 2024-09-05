package com.example.meditationcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.meditationcompose.ui.theme.AquaBlue
import com.example.meditationcompose.ui.theme.Beige1
import com.example.meditationcompose.ui.theme.Beige2
import com.example.meditationcompose.ui.theme.Beige3
import com.example.meditationcompose.ui.theme.BlueViolet1
import com.example.meditationcompose.ui.theme.BlueViolet2
import com.example.meditationcompose.ui.theme.BlueViolet3
import com.example.meditationcompose.ui.theme.DeepBlue
import com.example.meditationcompose.ui.theme.LightGreen1
import com.example.meditationcompose.ui.theme.LightGreen2
import com.example.meditationcompose.ui.theme.LightGreen3
import com.example.meditationcompose.ui.theme.OrangeYellow1
import com.example.meditationcompose.ui.theme.OrangeYellow2
import com.example.meditationcompose.ui.theme.OrangeYellow3
import com.example.meditationcompose.ui.theme.Pink80
import com.example.meditationcompose.ui.theme.Purple40
import com.example.meditationcompose.ui.theme.Purple_500

/**
 * Created by Himanshu Verma on 11/07/24.
 **/
@Preview
@Composable
fun HomeScreen() {
    val constraintSet = ConstraintSet {
        val title = createRefFor("title")
        val subtitle = createRefFor("subtitle")
        val searchIcon = createRefFor("searchIcon")
        val chipLayout = createRefFor("chipList")
        val dailyThought = createRefFor("dailyThought")
        val featured = createRefFor("featured")
        val recyclerView = createRefFor("recyclerView")
        val bottomView = createRefFor("bottomView")
        constrain(title) {
            top.linkTo(parent.top, margin = 10.dp)
            start.linkTo(parent.start, margin = 20.dp)
        }
        constrain(subtitle) {
            top.linkTo(title.bottom, margin = 5.dp)
            start.linkTo(parent.start, margin = 20.dp)
        }
        constrain(searchIcon) {
            top.linkTo(parent.top, margin = 10.dp)
            end.linkTo(parent.end, margin = 20.dp)
        }

        constrain(chipLayout) {
            top.linkTo(subtitle.bottom, margin = 20.dp)
            start.linkTo(parent.start, margin = 10.dp)
            end.linkTo(parent.end, margin = 20.dp)
            width = Dimension.fillToConstraints
        }
        constrain(dailyThought) {
            top.linkTo(chipLayout.bottom, margin = 30.dp)
            start.linkTo(parent.start, margin = 20.dp)
            end.linkTo(parent.end, margin = 20.dp)
            width = Dimension.fillToConstraints
        }
        constrain(featured) {
            top.linkTo(dailyThought.bottom, margin = 30.dp)
            start.linkTo(parent.start, margin = 20.dp)
        }
        constrain(recyclerView) {
            top.linkTo(featured.bottom, margin = 30.dp)
            end.linkTo(parent.end, margin = 16.dp)
            start.linkTo(parent.start, margin = 20.dp)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            bottom.linkTo(bottomView.top)
        }
        constrain(bottomView) {
            bottom.linkTo(parent.bottom)
        }

    }
    ConstraintLayout(
        constraintSet = constraintSet,
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        HeaderView()
        ChipSelectionView()
        DailyThoughtView()
        FeaturedView()
        BottomMenu(
            modifier = Modifier.layoutId("bottomView"),
            items = listOf(
                MenuItemContent(
                    "Home",
                    R.drawable.ic_home
                ),
                MenuItemContent(
                    "Meditate",
                    R.drawable.ic_bubble
                ),
                MenuItemContent(
                    "Sleep",
                    R.drawable.ic_headphone
                ),
                MenuItemContent(
                    "Music",
                    R.drawable.ic_music
                ),
                MenuItemContent(
                    "Profile",
                    R.drawable.ic_profile
                )
            )
        )
    }


}

@Composable
private fun FeaturedView() {
    Text(
        text = "Featured",
        modifier = Modifier
            .layoutId("featured"), color = Color.White,
        fontSize = 40.sp
    )
    val featureList = mutableListOf<Feature>(
        Feature(
            "Sleep meditation",
            iconId = R.drawable.ic_headphone,
            BlueViolet1, BlueViolet2, BlueViolet3
        ),
        Feature(
            "Tips for sleeping",
            iconId = R.drawable.ic_video,
            LightGreen1, LightGreen2, LightGreen3
        ), Feature(
            "Night island",
            iconId = R.drawable.ic_headphone,
            OrangeYellow1, OrangeYellow2, OrangeYellow3
        ),
        Feature(
            "Calming Sounds",
            iconId = R.drawable.ic_music,
            Beige1, Beige2, Beige3
        ),
        Feature(
            "Perform exercises",
            iconId = R.drawable.ic_moon,
            BlueViolet1, BlueViolet2, BlueViolet3
        )
    )

    LazyVerticalGrid(
        modifier = Modifier
            .layoutId("recyclerView")
            .fillMaxHeight(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
        state = rememberLazyGridState()
    ) {
        items(featureList) {
            ItemScreenView(it)
        }
    }
}

@Composable
private fun DailyThoughtView() {
    val constrainSet2 = ConstraintSet {
        val dtitle = createRefFor("dtitle")
        val dSubtitle = createRefFor("dSubtitle")
        val playIcon = createRefFor("playIcon")

        constrain(dtitle) {
            top.linkTo(parent.top, margin = 20.dp)
            start.linkTo(parent.start, margin = 20.dp)
        }
        constrain(dSubtitle) {
            top.linkTo(dtitle.bottom)
            start.linkTo(parent.start, margin = 20.dp)
            bottom.linkTo(parent.bottom, margin = 20.dp)
        }
        constrain(playIcon) {
            end.linkTo(parent.end, margin = 20.dp)
            top.linkTo(parent.top, margin = 10.dp)
            bottom.linkTo(parent.bottom, margin = 10.dp)
        }
    }
    ConstraintLayout(
        constraintSet = constrainSet2,
        modifier = Modifier
            .layoutId("dailyThought")
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Pink80)

    ) {
        Text(
            text = "Daily Thought",
            modifier = Modifier
                .layoutId("dtitle")
        )
        Text(
            text = "Meditation ~ 3-10 min",
            modifier = Modifier
                .layoutId("dSubtitle")
        )
        Image(
            painter = painterResource(id = R.drawable.ic_play),
            contentDescription = "Play Icon",
            modifier = Modifier.layoutId("playIcon")
        )

    }
}

@Composable
private fun HeaderView() {
    Text(
        text = "Good morning, Jack!!",
        modifier = Modifier.layoutId("title"),
        color = Color.White,
        fontSize = 24.sp
    )
    Text(
        text = "We wish you have a good day!",
        modifier = Modifier.layoutId("subtitle"),
        color = Color.LightGray,
        fontSize = 14.sp
    )
    Image(
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "Search Icon",
        modifier = Modifier.layoutId("searchIcon")
    )
}

@Composable
private fun ChipSelectionView() {
    var selectedIndex by remember {
        mutableStateOf(-1)
    }
    val chips = listOf("Sweet sleep", "Depression", "Insomnia", "Mental Peace", "Self Belief")
    LazyRow(modifier = Modifier.layoutId("chipList")) {
        items(chips.size) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedIndex = it
                    }
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (selectedIndex == it) Purple40 else Purple_500
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = Color.White)
            }
        }

    }
}

@Composable
fun BottomMenu(
    items: List<MenuItemContent>,
    modifier: Modifier = Modifier,
    activeHighLightColor: Color = Purple_500,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0,
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
        items.forEachIndexed { index, menuItemContent ->
            BottomMenuItem(
                item = menuItemContent,
                selectedItemIndex == index,
                activeHighLightColor,
                activeTextColor,
                inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }

    }

}

@Composable
fun BottomMenuItem(
    item: MenuItemContent,
    isSelected: Boolean = false,
    activeHighLightColor: Color = Purple_500,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .background(if (isSelected) activeHighLightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId), contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )

        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )

    }

}
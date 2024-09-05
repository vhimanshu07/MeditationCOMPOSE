package com.example.meditationcompose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationcompose.ui.theme.DeepBlue

/**
 * Created by Himanshu Verma on 15/07/24.
 **/
data class Feature(
    val title: String,
    @DrawableRes val iconId: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color,
)

data class MenuItemContent(
    val title: String,
    @DrawableRes val iconId: Int,
)

@Composable
fun ItemScreenView(item: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(item.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()

        }

        // light colored path
        val lightColoredPoint1 = Offset(0f, height * 0.35f)
        val lightColoredPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightColoredPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightColoredPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightColoredPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightColoredPoint1.x, lightColoredPoint1.y)
            standardQuadTo(lightColoredPoint1, lightColoredPoint2)
            standardQuadTo(lightColoredPoint2, lightColoredPoint3)
            standardQuadTo(lightColoredPoint3, lightColoredPoint4)
            standardQuadTo(lightColoredPoint4, lightColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()

        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(
                path = mediumColoredPath,
                color = item.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = item.lightColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        )
        {
            Text(
                text = item.title,
                style = MaterialTheme.typography.headlineMedium,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopEnd)
            )
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)

            )
            Text(
                text = "Start",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {

                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(8.dp))
                    .background(DeepBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )

        }
    }
}
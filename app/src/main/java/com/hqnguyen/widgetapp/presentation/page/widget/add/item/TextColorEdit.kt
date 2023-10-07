package com.hqnguyen.widgetapp.presentation.page.widget.add.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.hqnguyen.widgetapp.R

@Composable
fun TextColorEdit(
    currentColor: Int,
    updateCurrentTextColor: (color: Color) -> Unit,
) {
    val listColor =
        listOf(
            Color.White,
            Color.Red,
            Color.Yellow,
            Color.Blue,
            Color.Black,
            Color.Cyan,
            Color.DarkGray,
            Color.Gray,
            Color.Magenta,
            Color.Green,
        )

    Text(
        text = "Color text",
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LazyRow {
            items(count = listColor.size) { index ->
                ConstraintLayout(modifier = Modifier.clickable { updateCurrentTextColor(listColor[index]) }) {
                    val (color, spacer, checked) = createRefs()
                    Surface(
                        modifier = Modifier
                            .size(20.dp)
                            .constrainAs(color) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                            },
                        shape = CircleShape,
                        color = listColor[index],
                        border = BorderStroke(1.dp, color = Color.LightGray)
                    ) { }
                    Spacer(modifier = Modifier
                        .width(10.dp)
                        .constrainAs(spacer) {
                            top.linkTo(parent.top)
                            start.linkTo(color.end)
                        })

                    if (Color(currentColor) == listColor[index])
                        Image(
                            painter = painterResource(R.drawable.ic_checked),
                            contentDescription = "",
                            modifier = Modifier
                                .size(10.dp)
                                .constrainAs(checked) {
                                    top.linkTo(color.top)
                                    end.linkTo(color.end)
                                }
                        )
                }
            }
        }
    }
}

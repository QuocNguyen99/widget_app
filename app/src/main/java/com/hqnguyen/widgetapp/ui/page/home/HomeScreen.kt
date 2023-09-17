package com.hqnguyen.widgetapp.ui.page.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.data.DefaultTemplate
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme

val listDefaultTemplate = listOf<DefaultTemplate>(
    DefaultTemplate("Kỷ niệm ngày cưới", "15/08/2022", R.mipmap.bg_birthday),
    DefaultTemplate("Kỷ niệm ngày cưới", "15/08/2022", R.mipmap.bg_wedding),
    DefaultTemplate("Kỷ niệm ngày cưới", "15/08/2022", R.mipmap.bg_study),
    DefaultTemplate("Kỷ niệm ngày cưới", "15/08/2022", R.mipmap.bg_birthday),
    DefaultTemplate("Kỷ niệm ngày cưới", "15/08/2022", R.mipmap.bg_wedding),
    DefaultTemplate("Kỷ niệm ngày cưới", "15/08/2022", R.mipmap.bg_study),
)

@Composable
fun HomeScreen(onNavigate: (route: String) -> Unit = {}) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Chọn chủ đề bạn muốn hoặc tự tạo chủ đề",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        listDefaultTemplate.forEach {
            CardGuide(
                backgroundId = it.backgroundId,
                title = it.title,
                date = it.date,
                onClick = { onNavigate("add") }
            )
        }
    }
}

@Composable
fun CardGuide(
    modifier: Modifier = Modifier,
    @DrawableRes backgroundId: Int = R.mipmap.bg_wedding,
    title: String = "Kỷ niệm ngày cưới",
    date: String = "15/08/2022",
    onClick: (router: String) -> Unit = {},
    route: String = ""
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(true)
            ) { onClick(route) }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(Color.Red)
                .height(IntrinsicSize.Max)
        ) {
            val (backgroundRef, infoRef) = createRefs()

            Image(
                painter = painterResource(id = backgroundId),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .constrainAs(backgroundRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                contentScale = ContentScale.FillWidth
            )
            Column(modifier = Modifier
                .constrainAs(infoRef) {
                    top.linkTo(parent.top, margin = 12.dp)
                    start.linkTo(parent.start)
                }
                .padding(horizontal = 8.dp)) {
                Text(
                    title,
                    fontWeight = FontWeight.W700,
                    style = TextStyle(color = Color.White)
                )
                Text(
                    date,
                    fontWeight = FontWeight.W300,
                    style = TextStyle(color = Color.White)
                )
            }
        }
    }
}

@Preview
@Composable
fun CardGuidePreview() {
    WidgetAppTheme() {
        CardGuide()
    }
}

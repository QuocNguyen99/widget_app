package com.hqnguyen.widgetapp.ui.page.home

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hqnguyen.widgetapp.data.DefaultTemplate
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onNavigate: (route: String) -> Unit = {}
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true, block = {
        viewModel.onEvent(HomeEvent.GetFirstData)
    })

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

        state.defaultTemplates.forEach { defaultTemplate ->
            CardGuide(
                defaultTemplate = defaultTemplate,
                onClick = { onNavigate("add/${defaultTemplate.type.name}") }
            )
        }
    }
}

@Composable
fun CardGuide(
    modifier: Modifier = Modifier,
    defaultTemplate: DefaultTemplate = DefaultTemplate(),
    onClick: () -> Unit = {},
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
            ) { onClick() }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(Color.Red)
                .height(IntrinsicSize.Max)
        ) {
            val (backgroundRef, infoRef) = createRefs()

            Image(
                painter = painterResource(id = defaultTemplate.backgroundId),
                contentDescription = defaultTemplate.title,
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
                    defaultTemplate.title,
                    fontWeight = FontWeight.W700,
                    style = TextStyle(color = Color.White)
                )
                Text(
                    defaultTemplate.date,
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

package com.hqnguyen.widgetapp.ui.bottom_sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hqnguyen.widgetapp.HeaderItems
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.ui.main.ButtonCustomLeft

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanBottomSheet(onDismiss: () -> Unit = {}) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp

    ModalBottomSheet(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight * 0.95f),
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color(0xFFFFEED5)
    ) {
        ButtonCustomLeft(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = stringResource(R.string.cancel),
            color = Color.Red,
            colorContainer = Color(0xFFFFEED5),
            onClickHeaderItem = {}
        )

        ButtonCustomLeft(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = stringResource(R.string.done),
            color = Color.Red,
            colorContainer = Color(0xFFFFEED5),
            onClickHeaderItem = {}
        )
    }
}

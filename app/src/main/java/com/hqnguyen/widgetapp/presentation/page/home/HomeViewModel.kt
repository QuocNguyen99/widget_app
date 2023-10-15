package com.hqnguyen.widgetapp.presentation.page.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.data.model.DefaultTemplate
import com.hqnguyen.widgetapp.data.model.TypeTemplate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

val listDefaultTemplate = listOf(
    DefaultTemplate(
        "Kỷ niệm ngày cưới",
        "15/08/2022",
        R.mipmap.bg_birthday,
        TypeTemplate.BIRTHDAY.ordinal
    ),
    DefaultTemplate(
        "Kỷ niệm ngày cưới",
        "15/08/2022",
        R.mipmap.bg_wedding,
        TypeTemplate.WEDDING_ANNIVERSARY.ordinal
    ),
    DefaultTemplate("Kỷ niệm ngày cưới", "15/08/2022",
        R.mipmap.bg_study,
        TypeTemplate.STUDY.ordinal),
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val TAG = HomeViewModel::class.simpleName
    private val _state = MutableStateFlow(HomeState())
    val state = _state

    fun onEvent(event: HomeEvent) {
        Log.d(TAG, "onEvent: $event")
        when (event) {
            is HomeEvent.GetFirstData -> {
                _state.update {
                    it.copy(defaultTemplates = listDefaultTemplate)
                }
            }

            else -> Unit
        }
    }
}
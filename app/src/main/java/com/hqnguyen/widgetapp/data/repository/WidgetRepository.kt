package com.hqnguyen.widgetapp.data.repository

import com.hqnguyen.widgetapp.data.dao.WidgetDAO
import javax.inject.Inject

class WidgetRepository @Inject constructor(val widgetDAO: WidgetDAO) {
}
package com.hqnguyen.widgetapp.data

enum class NavItem(val title: String, val router: String) {
    MAIN("Home", "main"),
    ADD("Add Content", "add/{type}");

    companion object {
        fun findEnumByRouter(router: String?): NavItem? {
            if (router == null) return null
            for (enumItem in NavItem.values()) {
                if (enumItem.router == router) {
                    return enumItem
                }
            }
            return null
        }
    }
}
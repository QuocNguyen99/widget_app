package com.hqnguyen.widgetapp.data.model

enum class NavItem(val title: String, val router: String) {
    MAIN("Home", "main"),
    ADD("Add content", "add/{id}"),
    EDIT_PHOTO("Edit photo", "edit_photo"),
    CROP_PHOTO("Crop photo", "crop_photo/{path}");

    companion object {
        fun findEnumByRouter(router: String?): NavItem? {
            if (router == null) return null
            for (enumItem in entries) {
                if (enumItem.router == router) {
                    return enumItem
                }
            }
            return null
        }
    }
}
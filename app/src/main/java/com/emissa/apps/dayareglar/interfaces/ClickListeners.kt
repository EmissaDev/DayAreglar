package com.emissa.apps.dayareglar.interfaces;

interface ClickListeners {
    fun <T> onItemClicked(item: T)
    fun <T> onItemLongClicked(item: T): Boolean
}

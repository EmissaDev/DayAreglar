package com.emissa.apps.dayareglar.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to DayAreglar, the app to well organize your day." +
                "You can Create, Edit and Save notes at any time" +
                "You can manage all your events here as well!\n\n" +
                "Click the below buttons or use the menu to access your list of Notes and Events."
    }
    val text: LiveData<String> = _text
}
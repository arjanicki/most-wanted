package com.example.mostwanted.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mostwanted.models.MostWantedPerson

class MostWantedDetailViewModel(
    handle: SavedStateHandle,
) : ViewModel() {

    val person = handle.get<MostWantedPerson>("person")
}
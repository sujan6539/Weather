package com.example.myapplication

import com.example.myapplication.recyclerview.MultiTypeDataBoundAdapter

interface Callback: MultiTypeDataBoundAdapter.ActionCallback {

    fun onButtonClicked()
}
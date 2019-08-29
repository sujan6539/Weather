package com.example.myapplication.recyclerview

import androidx.annotation.LayoutRes

interface LayoutBinding {

    @LayoutRes
    fun getLayoutId():Int
}
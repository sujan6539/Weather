package com.example.myapplication.recyclerview

import androidx.databinding.ViewDataBinding

interface DynamicBinding {

    fun bind(holder: DataBoundViewHolder<ViewDataBinding>)
}
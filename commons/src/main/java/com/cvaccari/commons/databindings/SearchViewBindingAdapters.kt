package com.cvaccari.commons.databindings

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter

@BindingAdapter("bindQueryTextListener")
fun SearchView.bindOnQueryTextListener(listener: SearchView.OnQueryTextListener) {
    setOnQueryTextListener(listener)
}
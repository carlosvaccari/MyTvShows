package com.cvaccari.commons.utils

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DebouncingQueryTextListener(
    coroutineContext: CoroutineContext,
    private val onDebouncingQueryTextChange: (String?) -> Unit,
    private val onClearTextClicked : () -> Unit
) : SearchView.OnQueryTextListener {
    var debouncePeriod: Long = 2000

    private val coroutineScope = CoroutineScope(coroutineContext)

    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText?.isEmpty() == true) {
            onClearTextClicked()
        } else {
            searchJob?.cancel()
            searchJob = coroutineScope.launch {
                newText?.let {
                    delay(debouncePeriod)
                    onDebouncingQueryTextChange(newText)
                }
            }
        }
        return false
    }
}
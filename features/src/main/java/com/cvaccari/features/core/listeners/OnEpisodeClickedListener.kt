package com.cvaccari.features.core.listeners

import com.cvaccari.features.showdetails.data.model.ShowDetailsModel

interface OnEpisodeClickedListener {
    fun onClick(item : ShowDetailsModel)
}
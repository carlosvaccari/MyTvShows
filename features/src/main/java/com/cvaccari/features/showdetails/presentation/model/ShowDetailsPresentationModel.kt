package com.cvaccari.features.showdetails.presentation.model

import android.text.Spanned
import com.cvaccari.core_views.stickyrecyclerview.Section
import com.cvaccari.features.search.data.model.ShowImagesModel

data class ShowDetailsPresentationModel(
    val seasonsList: List<Section>,
    val name: String,
    val summary: Spanned,
    val images: ShowImagesModel,
    val seasonsCount: Int,
    var isFavorite: Boolean = false
)

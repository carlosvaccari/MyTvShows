package com.cvaccari.features.showdetails.presentation.model

import com.cvaccari.core_views.stickyrecyclerview.Section
import com.cvaccari.features.showdetails.data.model.ShowDetailsModel

data class ShowSeasonItemSectionModel(
    val item: ShowDetailsModel,
    val section: Int
): Section {

    override fun type() = Section.ITEM

    override fun sectionPosition() = section
}

data class ShowSeasonHeaderSectionModel(
    val sectionTitle: String,
    val section: Int
): Section {

    override fun type() = Section.HEADER

    override fun sectionPosition() = section
}
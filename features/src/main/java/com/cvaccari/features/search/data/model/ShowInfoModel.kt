package com.cvaccari.features.search.data.model

import com.cvaccari.core_local_storage.database.entities.FavoritesEntity
import java.io.Serializable

data class ShowInfoModel(
    val id: String,
    val name: String,
    val type: String,
    val genres: List<String>,
    val image: ShowImagesModel? = null,
    val summary: String?,
    val webChannel: WebChannelModel? = null,
    val _embedded: EmbededModel? = null,
    var isFavorite: Boolean = false,
): Serializable

fun FavoritesEntity.toShowInfoModel(): ShowInfoModel {
    return ShowInfoModel(
        id = this.id,
        name = this.name,
        type = this.type,
        genres = this.genres,
        image = ShowImagesModel(this.imageMedium.orEmpty(), this.imageOriginal.orEmpty()),
        summary = this.summary,
        webChannel = WebChannelModel(this.webChannelId, this.webChannelName, Country(webChannelName)),
        isFavorite = true
    )
}

fun ShowInfoModel.toFavoritiesEntity() : FavoritesEntity {
    return FavoritesEntity(
        id = this.id,
        name = this.name,
        type = this.type,
        genres = this.genres,
        imageMedium = image?.medium,
        imageOriginal = image?.original,
        summary = this.summary.orEmpty(),
        webChannelName = this.webChannel?.name.orEmpty(),
        webChannelId = this.webChannel?.id ?: 0,
        webChannelCountry = this.webChannel?.country?.name.orEmpty()
    )
}

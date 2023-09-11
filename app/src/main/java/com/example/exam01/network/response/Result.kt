package com.example.exam01.network.response

import android.os.Parcelable
import com.example.exam01.room.BookmarkEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>,
    var isBookmark: Boolean = false
) : Parcelable {
    fun toStringThumbnail(): String =
        "${thumbnail.path}.${thumbnail.extension}"
    fun toBookmarkEntity() : BookmarkEntity =
        BookmarkEntity(
            id = id,
            name = name,
            resourceURI = resourceURI,
        )

}
package com.ateam.herbacrop.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
    var id : Int = 0,
    var description: String = "",
    var image : String = "",
    var title : String = ""
) : Parcelable
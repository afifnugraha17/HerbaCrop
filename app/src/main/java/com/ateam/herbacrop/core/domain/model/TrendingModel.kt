package com.ateam.herbacrop.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrendingModel(
    var id : Int = 0,
    var image : String = "",
    var name: String = "",
) : Parcelable

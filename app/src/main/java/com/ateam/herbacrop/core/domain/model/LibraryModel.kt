package com.ateam.herbacrop.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LibraryModel(
    var id: Int = 0,
    var title : String = "",
    var image : String = "",
    var type : String = ""
) : Parcelable

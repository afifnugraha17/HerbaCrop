package com.ateam.herbacrop.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class AboutModel(
    var id:String = "",
    var name:String = "",
    var profile_photo:String ="",
    var role:String = "",
):Parcelable
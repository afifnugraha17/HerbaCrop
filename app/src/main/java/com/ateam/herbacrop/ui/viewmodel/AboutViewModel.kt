package com.ateam.herbacrop.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ateam.herbacrop.core.domain.model.AboutDescModel
import com.ateam.herbacrop.core.domain.model.AboutModel
import com.ateam.herbacrop.core.domain.usecase.PlantUseCase

class AboutViewModel constructor(private val useCase: PlantUseCase) : ViewModel() {
    fun getAboutDesc() : LiveData<AboutDescModel> = useCase.getAboutData()
    fun getDevList() : LiveData<List<AboutModel>> = useCase.getDeveloperData()
}
package io.jobtrends.jobtrends.viewmodels

import io.jobtrends.jobtrends.models.Model

interface CurriculumViewModel : ViewModel {
    fun startDialog()
    fun addModel(model: Model)
    fun removeModel(model: Model)
    fun onNextStep()
}

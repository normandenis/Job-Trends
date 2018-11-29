package io.jobtrends.jobtrends.viewmodels

import io.jobtrends.jobtrends.models.IModel

interface CurriculumViewModel : ViewModel {
    fun startDialog()
    fun addModel(model: IModel)
    fun removeModel(model: IModel)
    fun onNextStep()
}

package com.scz.planetary.domain.usecase

import com.scz.planetary.domain.model.Apod
import com.scz.planetary.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetApodDetailUseCase @Inject constructor() {

    operator fun invoke(apod: Apod): Flow<Resource<Pair<String, String>>> = flow {
        emit(Resource.Loading())
        val detail = StringBuilder()
        detail.appendLine(apod.title)
            .appendLine(apod.copyright)
            .appendLine(apod.explanation)
        emit(Resource.Success(Pair(detail.toString(), apod.hdUrl ?: "")))
    }
}
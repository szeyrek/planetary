package com.scz.planetary.domain.usecase

import com.scz.planetary.data.remote.reqres.toDomainModel
import com.scz.planetary.domain.model.Apod
import com.scz.planetary.domain.repository.ApodRepository
import com.scz.planetary.util.DateUtil
import com.scz.planetary.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetApodUseCase @Inject constructor(private val repository: ApodRepository) {

    operator fun invoke(
        startDate: String?,
        endDate: String?,
        count: String?
    ): Flow<Resource<List<Apod>?>> = flow {
        try {
            emit(Resource.Loading())
            val apodResponse = repository.getApod(
                startDate ?: DateUtil.getToday(),
                endDate ?: DateUtil.getToday(),
                count
            )
            emit(Resource.Success(apodResponse.toDomainModel()))
        } catch (e: IOError) {
            emit(Resource.Error(message = "No Connection"))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }
    }
}
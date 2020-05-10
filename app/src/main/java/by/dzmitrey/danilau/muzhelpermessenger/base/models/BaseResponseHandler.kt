package by.dzmitrey.danilau.muzhelpermessenger.base.models

import by.dzmitrey.danilau.muzhelpermessenger.network.responses.ApiResponse
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.BaseResponse
import retrofit2.Call
import retrofit2.Response

abstract class BaseResponseHandler<T : BaseResponse> {

    fun <T : BaseResponse, R> make(call: Call<T>, transform: (T) -> R): ApiResponse<T> {
       return execute(call)
    }

    private fun <T : BaseResponse, R> execute(call: Call<T>, transform: (T) -> R): ApiResponse<T> {
            when (response.isSucceed()) {
                true -> ApiResponse.Success(response)
                false -> ApiResponse.Failure.Error(response)
            }
        } catch (exception: Throwable) {
            ApiResponse.Failure.Exception(exception)
        }
    }

    private fun <T : BaseResponse> Response<T>.isSucceed(): Boolean {
        return isSuccessful && body() != null && (body() as BaseResponse).success == 1
    }
}




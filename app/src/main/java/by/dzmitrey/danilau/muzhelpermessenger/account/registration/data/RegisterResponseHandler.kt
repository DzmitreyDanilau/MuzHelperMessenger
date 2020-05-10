package by.dzmitrey.danilau.muzhelpermessenger.account.registration.data

import by.dzmitrey.danilau.muzhelpermessenger.base.models.BaseResponseHandler
import by.dzmitrey.danilau.muzhelpermessenger.network.message
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.ApiResponse
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.BaseResponse
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.RegisterResponse
import by.dzmitrey.danilau.muzhelpermessenger.network.transform
import by.dzmitrey.danilau.muzhelpermessenger.utils.ErrorsConstants.Companion.ERROR_UNKNOWN
import retrofit2.Call
import retrofit2.Response

class RegisterResponseHandler : BaseResponseHandler<RegisterResponse>() {

    companion object {
        const val REGISTRATION_SUCCESS = 1
    }

    fun handleResponse(request: Call<RegisterResponse>, onResult: (registrationResult: RegistrationResult) -> Unit) {
        make(request, {})
        request.transform {
//            when (it) {
//                is ApiResponse.Success -> onResult(isRegistrationSuccessful(it))
//                is ApiResponse.Failure.Error -> onResult(RegistrationResult.RegistrationFailed(it.message()))
//                else -> onResult(RegistrationResult.RegistrationFailed(ERROR_UNKNOWN))
//            }
        }
    }

    private fun isRegistrationSuccessful(apiResponse: ApiResponse.Success<RegisterResponse>): RegistrationResult {
        return apiResponse.data?.let { registrationResponse ->
            if (registrationResponse.success == REGISTRATION_SUCCESS) {
                RegistrationResult.RegistrationPassed(registrationResponse.message)
            } else {
                RegistrationResult.RegistrationFailed(registrationResponse.message)
            }
        } ?: RegistrationResult.RegistrationFailed(ERROR_UNKNOWN)
    }

    //TODO Under development
//    fun <T : BaseResponse> Response<T>.parseError(): ApiResponse.Failure<String> {
//        val message = (body() as BaseResponse).message
//        return when (message) {
//            "email already exists" -> ApiResponse.Failure.EmailAlreadyExistError
//            else -> ApiResponse.Failure.ServerError
//        }
//    }
}
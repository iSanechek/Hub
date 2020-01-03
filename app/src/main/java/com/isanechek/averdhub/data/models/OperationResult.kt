package com.isanechek.averdhub.data.models

sealed class OperationResult<out T: Any> {

    sealed class ErrorState {
        object Empty : ErrorState()
        object BadRequest : ErrorState()
        object NoConnection : ErrorState()
        object ResponseIsNull : ErrorState()
        object Unknown : ErrorState()
        object NotSupportCountry : ErrorState()
        object ServerError : ErrorState()
        data class Other(val message: String): ErrorState()
    }

    object Loading : OperationResult<Nothing>()
    data class Error(val error: ErrorState) : OperationResult<Nothing>()
    data class Success<out T: Any>(val data: T) : OperationResult<T>()
}
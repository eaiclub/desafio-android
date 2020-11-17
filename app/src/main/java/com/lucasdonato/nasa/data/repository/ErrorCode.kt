package br.com.versa.data.repository

import br.com.versa.R

enum class ErrorCode(val value: String, val stringCode: Int) {
    ASYNC_TASK_ERROR("Async Task Error", R.string.error_generic),
    GENERIC_ERROR("Generic Error", R.string.error_generic),
    INTERNET_CONNECTION_UNAVAILABLE("INTERNET_CONNECTION_UNAVAILABLE",
        R.string.alert_internet_unavailable_message),
    NOT_FOUND("Not Found", R.string.error_generic),
    INVALID("Invalid", R.string.error_generic),
    CELLPHONE_ALREADY_REGISTERED("cellphone_already_registerd",
        R.string.error_cellphone_already_registered),
    EMAIL_ALREADY_USED("email_already_used", R.string.email_already_exist),
    EMAIL_AND_CELLPHONE_ALREADY_REGISTERED("email_and_cellphone_already_registered",
        R.string.email_and_cellphone_already_exist),
    REGION_NOT_SUPPORTED("region_not_supported_yet", R.string.region_not_supported),
    SCHEDULE_UNAVAILABLE("this_hour_is_not_available_for_scheduling", R.string.schedule_unavailable_hour),
    USER_NOT_FOUND("user_not_found", R.string.user_not_found);


    companion object {
        fun fromString(value: String?) = values().find { it.value == value } ?: INVALID
    }
}

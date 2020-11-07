package com.example.iterativechallenger.core.utils


class Response(val status: Status,
               val data: Any?,
               val error: Throwable?) {

    companion object {
        fun loading(): Response {
            return Response(Status.LOADING, null, null)
        }

        fun <T: Any?>success(data: T): Response {
            return Response(Status.SUCCESS, data, null)
        }

        fun error(error: Throwable): Response {
            return Response(Status.ERROR, null, error)
        }

    }
}
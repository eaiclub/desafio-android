package br.com.versa.data.repository

import java.lang.RuntimeException

/**
 * MBLabsException, Exception used to describe errors occurred when try to use
 * in data layer.
 */
class MBLabsException(var errorCode: ErrorCode, var errorMessage: String? = "") : RuntimeException()



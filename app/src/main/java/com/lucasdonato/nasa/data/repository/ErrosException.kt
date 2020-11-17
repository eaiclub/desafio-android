package com.lucasdonato.nasa.data.repository

import java.lang.RuntimeException

/**
 * MBLabsException, Exception used to describe errors occurred when try to use
 * in data layer.
 */
class ErrosException(var errorCode: ErrorCode, var errorMessage: String? = "") : RuntimeException()



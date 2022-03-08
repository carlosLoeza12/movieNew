package com.example.movienew.core

import java.lang.Exception

sealed class Resource<out T> {
    class Loading<out T>: Resource<T>()
    data class Success<out T>(val data: T): Resource<T>()
    data class Fail(val exception: Exception): Resource<Nothing>()
}
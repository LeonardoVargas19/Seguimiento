package com.leoromeo.seguimiento.domain

fun validateFields(field: String, value: String): Boolean {
    return when (field) {
        "nombre" -> value.length >= 5
        "dec" -> value.length >= 10
        "motivo" -> value.length >= 10
        else -> true

    }
}

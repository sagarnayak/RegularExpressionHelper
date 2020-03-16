@file:Suppress("unused")

package com.sagar.android.regex.enums

enum class QuantityPreset(private val expression: String) {
    ZERO_OR_MORE("*"),
    ONE_OR_ZERO("?"),
    ONE_OR_MORE("+");

    fun getExpression(): String {
        return expression
    }
}
@file:Suppress("unused")

package com.sagar.android.regex.enums

enum class Numbers(private val expression: String) {
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9");

    fun getExpression(): String {
        return expression
    }
}
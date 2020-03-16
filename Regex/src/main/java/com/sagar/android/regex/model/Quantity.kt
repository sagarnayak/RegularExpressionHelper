@file:Suppress("unused")

package com.sagar.android.regex.model

sealed class Quantity {
    open fun getExpression(): String {
        throw Exception("Please provide definition for this method in child")
    }

    class Preset(private val quantityPreset: com.sagar.android.regex.enums.QuantityPreset) :
        com.sagar.android.regex.model.Quantity() {

        override fun getExpression(): String {
            return quantityPreset.getExpression()
        }
    }

    class Number(private val number: Int) : com.sagar.android.regex.model.Quantity() {

        override fun getExpression(): String {
            return "{$number}"
        }
    }

    class Range(private val from: Int, private val to: Int? = null) :
        com.sagar.android.regex.model.Quantity() {

        override fun getExpression(): String {
            return "{$from,${to ?: ""}}"
        }
    }
}
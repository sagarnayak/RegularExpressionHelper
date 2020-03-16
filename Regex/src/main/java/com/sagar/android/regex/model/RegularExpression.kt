@file:Suppress("unused")

package com.sagar.android.regex.model

import com.sagar.android.regex.core.RegularExpressionGenerator
import com.sagar.android.regex.patterns.PresetPatterns

sealed class RegularExpression {

    open fun getExpression(): String {
        throw Exception("Please define method body in child")
    }

    class FromPresets(private val preset: PresetPatterns) : RegularExpression() {

        override fun getExpression(): String {
            return preset.getExpression()
        }
    }

    class Custom(private val regExp: String) : RegularExpression() {

        override fun getExpression(): String {
            return regExp
        }
    }

    class Generator(private val regularExpressionGenerator: RegularExpressionGenerator) :
        RegularExpression() {

        override fun getExpression(): String {
            return regularExpressionGenerator.build()
        }
    }
}
package com.sagar.android.regex.model

import com.sagar.android.regex.enums.CapitalLetters
import com.sagar.android.regex.enums.Numbers
import com.sagar.android.regex.enums.SmallLetters

@Suppress("unused")
sealed class CharacterSet {
    open fun getExpression(): String {
        throw Exception("Please define method body in child")
    }

    class CapitalLetterRange(private val from: CapitalLetters, private val to: CapitalLetters) :
        CharacterSet() {

        override fun getExpression(): String {
            return "${from.getExpression()}-${to.getExpression()}"
        }
    }

    class SmallLetterRange(private val from: SmallLetters, private val to: SmallLetters) :
        CharacterSet() {

        override fun getExpression(): String {
            return "${from.getExpression()}-${to.getExpression()}"
        }
    }

    class NumberRange(private val from: Numbers, private val to: Numbers) :
        CharacterSet() {

        override fun getExpression(): String {
            return "${from.getExpression()}-${to.getExpression()}"
        }
    }

    class Presets(private val characterSetPreset: com.sagar.android.regex.enums.CharacterSetPreset) :
        CharacterSet() {

        override fun getExpression(): String {
            return characterSetPreset.getExpression()
        }
    }

    class FromChar(private val char: Char) : CharacterSet() {

        override fun getExpression(): String {
            return char.toString()
        }
    }

    class FromCharGroup(private val characterSetList: ArrayList<CharacterSet>) : CharacterSet() {

        override fun getExpression(): String {
            var toReturn = ""

            characterSetList.forEach {
                toReturn += it.getExpression()
            }

            return toReturn
        }
    }
}
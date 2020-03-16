@file:Suppress("unused")

package com.sagar.android.regex.enums

enum class CharacterSetPreset(private val expression: String) {
    ANY_EXCEPT_NEW_LINE("."),
    WHITE_SPACE("\\s"),
    NON_WHITE_SPACE("\\S"),
    WORD_CHAR("\\w"),
    NON_WORD_CHAR("\\W"),
    DIGIT("\\d"),
    NON_DIGIT("\\D");

    fun getExpression(): String {
        return expression
    }
}
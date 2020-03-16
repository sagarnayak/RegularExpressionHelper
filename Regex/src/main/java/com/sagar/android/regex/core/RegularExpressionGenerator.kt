package com.sagar.android.regex.core

import com.sagar.android.regex.enums.Presence
import com.sagar.android.regex.model.CharacterSet
import com.sagar.android.regex.model.Quantity


class RegularExpressionGenerator {
    private var regExp = ""

    @Suppress("unused")
    fun regExp(
        presence: Presence = Presence.PRESENT_IN,
        characterSet: CharacterSet,
        quantity: Quantity? = null
    ): RegularExpressionGenerator {
        var regExpToGen = characterSet.getExpression()
        var squareBracketApplied = false
        if (presence == Presence.NOT_PRESENT_IN) {
            regExpToGen = "[^$regExpToGen]"
            squareBracketApplied = true
        }
        quantity?.let {
            if (presence == Presence.NOT_PRESENT_IN)
                regExpToGen = "$regExpToGen${quantity.getExpression()}"
            else {
                regExpToGen = "[$regExpToGen]${quantity.getExpression()}"
                squareBracketApplied = true
            }
        }
        if (
            !squareBracketApplied &&
            (
                    characterSet is CharacterSet.CapitalLetterRange ||
                            characterSet is CharacterSet.SmallLetterRange ||
                            characterSet is CharacterSet.NumberRange ||
                            characterSet is CharacterSet.FromCharGroup
                    )
        ) {
            regExpToGen = "[$regExpToGen]"
        }
        regExp += regExpToGen
        return this
    }

    fun build(): String {
        return regExp
    }
}
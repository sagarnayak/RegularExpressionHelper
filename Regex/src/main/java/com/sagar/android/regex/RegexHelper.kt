@file:Suppress("unused")

package com.sagar.android.regex

import com.sagar.android.regex.enums.MatchType
import com.sagar.android.regex.enums.Result
import com.sagar.android.regex.interfaces.Callback
import com.sagar.android.regex.model.RegularExpression
import java.util.regex.Pattern

class RegexHelper {

    private var matchType = MatchType.EXACT
    private lateinit var callback: Callback
    private var caseSensitive = true
    private var regexString = ""

    private lateinit var pattern: Pattern

    fun checkThis(stringToCheck: String): Result {
        if (!this::pattern.isInitialized) {
            pattern = if (caseSensitive)
                Pattern.compile(
                    regexString
                ) else {
                Pattern.compile(
                    regexString,
                    Pattern.CASE_INSENSITIVE
                )
            }
        }
        val matcher = pattern.matcher(stringToCheck)
        return when (matchType) {
            MatchType.EXACT -> {
                if (matcher.matches()) {
                    if (this::callback.isInitialized)
                        callback.matchResult(
                            Result.EXACT_MATCH,
                            stringToCheck,
                            regexString
                        )
                    Result.EXACT_MATCH
                } else {
                    if (this::callback.isInitialized)
                        callback.matchResult(
                            Result.NO_MATCH,
                            stringToCheck,
                            regexString
                        )
                    Result.NO_MATCH
                }
            }
            MatchType.PARTIAL -> {
                if (matcher.find()) {
                    if (this::callback.isInitialized)
                        callback.matchResult(
                            Result.PARTIAL_MATCH,
                            stringToCheck,
                            regexString
                        )
                    Result.PARTIAL_MATCH
                } else {
                    if (this::callback.isInitialized)
                        callback.matchResult(
                            Result.NO_MATCH,
                            stringToCheck,
                            regexString
                        )
                    Result.NO_MATCH
                }
            }
        }
    }

    class Builder {
        private var regexHelper: RegexHelper = RegexHelper()

        fun build(): RegexHelper {
            return regexHelper
        }

        fun exactMatch(): Builder {
            regexHelper.matchType = MatchType.EXACT
            return this
        }

        fun partialMatch(): Builder {
            regexHelper.matchType = MatchType.PARTIAL
            return this
        }

        fun caseInsensitive(): Builder {
            regexHelper.caseSensitive = false
            return this
        }

        fun registerCallback(callback: Callback): Builder {
            regexHelper.callback = callback
            return this
        }

        fun regularExpression(regex: RegularExpression): Builder {
            regexHelper.regexString = regex.getExpression()
            return this
        }
    }
}
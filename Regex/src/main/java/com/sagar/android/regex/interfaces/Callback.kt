package com.sagar.android.regex.interfaces

import com.sagar.android.regex.enums.Result

interface Callback {
    fun matchResult(result: Result, forString: String, regexUsed: String)
}
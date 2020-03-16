package com.sagar.android.regularexpressionhelper

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sagar.android.regex.RegexHelper
import com.sagar.android.regex.enums.Result
import com.sagar.android.regex.interfaces.Callback
import com.sagar.android.regex.model.RegularExpression
import com.sagar.android.regex.patterns.PresetPatterns

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "regexTest"

        fun log(message: String) {
            Log.i(TAG, message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testRegex()
    }

    private fun testRegex() {
        val regex = RegexHelper
            .Builder()
            .regularExpression(
                RegularExpression.FromPresets(
                    PresetPatterns.EMAIL
                )
            )
            .registerCallback(
                object : Callback {
                    override fun matchResult(result: Result, forString: String, regexUsed: String) {

                        log(
                            """
                                result >> $result
                                for >> $forString
                                regex >> $regexUsed
                            """.trimIndent()
                        )
                    }
                }
            )
            .build()

        regex.checkThis("test@gmail.com")
    }
}

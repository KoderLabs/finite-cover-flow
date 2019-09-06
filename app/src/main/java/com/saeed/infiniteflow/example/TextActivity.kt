package com.saeed.infiniteflow.example

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import kotlinx.android.synthetic.main.activity_text.*

class TextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadEmojiFonts()
        setContentView(R.layout.activity_text)


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        Handler().postDelayed({
//            edit_text.setText("ABCD")
//            edit_text.setSelection(edit_text.text.toString().length)
//            focus_view.requestFocus()
        }, 500L)

        search_view.setOnClickListener { v ->
            (v as SearchView).apply {
                onActionViewExpanded()
                queryHint = "Search Contacts"
            }
        }

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String): Boolean {
                search_view.clearFocus()
                focus_view.requestFocus()
                Handler().postDelayed({
//                    edit_text.clearFocus()
                }, 500L)
                return false
            }

            override fun onQueryTextChange(text: String): Boolean {
                return false
            }
        })

        focus_view.requestFocus()
        search_view.clearFocus()
    }

    private fun loadEmojiFonts() {
        val fontRequest = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Noto Color Emoji Compat",
            R.array.com_google_android_gms_fonts_certs
        )
        val emojiConfig = FontRequestEmojiCompatConfig(this, fontRequest)
        EmojiCompat.init(emojiConfig)
    }

    fun setupKeyboardHiding(view: View) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideKeyboard()
                false
            }
        }

        //If its a ViewGroup, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupKeyboardHiding(innerView)
            }
        }
    }

    fun hideKeyboard() {
        val focusView = currentFocus
        focusView?.clearFocus()
        val imm = getSystemService(android.app.Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(focusView?.windowToken, 0)
    }
}

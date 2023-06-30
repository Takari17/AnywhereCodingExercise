package com.takari.anywherecodingexercise.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.takari.anywherecodingexercise.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, CharacterListFragment())
                .commit()
        }
    }
}
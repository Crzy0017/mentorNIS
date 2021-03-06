package com.example.nismentor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nismentor.presentation.ui.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, MainFragment(), "LoginFragment")
            .commit()
    }
}
package com.tempoagora.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tempoagora.R
import com.tempoagora.presentation.extensions.replaceFragment
import com.tempoagora.presentation.fragment.ClimateFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(ClimateFragment())
    }
}
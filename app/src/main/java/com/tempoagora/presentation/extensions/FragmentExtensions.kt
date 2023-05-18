package com.tempoagora.presentation.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tempoagora.R

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    @IdRes idComponent: Int = R.id.main_container
) {
    supportFragmentManager.beginTransaction()
        .replace(idComponent, fragment)
        .commitNow()
}
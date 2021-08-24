package com.idea3d.idea3d


import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.loadFragment (fragment:Fragment) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.add(R.id.fragmentContainer, fragment)
    fragmentTransaction.commit()
}
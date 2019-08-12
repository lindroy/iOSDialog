package com.lindroy.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lindroy.iosdialoglib.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnKotlin.setOnClickListener {
            startActivity(Intent(this, KotlinActivity::class.java))
        }
        btnJava.setOnClickListener {
            startActivity(Intent(this, JavaActivity::class.java))
        }
    }
}

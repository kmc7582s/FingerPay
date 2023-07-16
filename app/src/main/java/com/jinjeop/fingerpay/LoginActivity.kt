package com.jinjeop.fingerpay


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText


class LoginActivity : AppCompatActivity() {

    lateinit var id:EditText
    lateinit var pw:EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        id = findViewById(R.id.Id_edit)
        pw = findViewById(R.id.Pw_edit)
        button = findViewById(R.id.login_btn)

        button.setOnClickListener {

        }
    }
}

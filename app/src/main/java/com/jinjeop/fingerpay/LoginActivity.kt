package com.jinjeop.fingerpay


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.Button
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.android.material.textfield.TextInputEditText


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

    private fun loginRequest(id: String, pw: String) {
        var url:String="https://kmc7582s.cafe24.com/Finger_Pay/android_login.php"

    }
}

interface  SignService {

    @POST()
    fun login() : Call<String>
}
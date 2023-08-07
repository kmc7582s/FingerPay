package com.jinjeop.fingerpay


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {

    private lateinit var requestQueue: RequestQueue
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val link = findViewById<TextView>(R.id.text0)
        val pattern1 = Pattern.compile("회원가입")
        val mTransform = Linkify.TransformFilter { match, url -> "/signup.php" }

        Linkify.addLinks(link ,pattern1,"https://kmc7582s.cafe24.com/fingerpay",null,mTransform)
        val login_btn = findViewById<Button>(R.id.login_btn)

        login_btn.setOnClickListener {
            onLoginButtonClick()
        }
    }

    // 로그인 버튼 클릭 시 실행되는 함수
    fun onLoginButtonClick() {

        val Id_edit = findViewById<EditText>(R.id.Id_edit)
        val Pw_edit = findViewById<EditText>(R.id.Pw_edit)

        val id = Id_edit.text.toString()
        val pw = Pw_edit.text.toString()

        // 아이디와 비밀번호를 PHP 서버로 전송하는 코드
        val url = "https://kmc7582s.cafe24.com/fingerpay/app_login.php"
        val requestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                // 서버로부터의 응답 처리
                if (response.trim() == "success") {
                    // 로그인 성공
                    Log.d("login","성공")
                    // 원하는 액션을 수행하도록 처리
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // 로그인 실패
                    Log.d("login","실패")
                    // 에러 처리
                    Toast.makeText(this.getApplicationContext(),"로그인을 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            },
            Response.ErrorListener { error ->
                // 에러 처리
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["id"] = id
                params["pw"] = pw
                return params
            }
        }

        // 요청 추가
        requestQueue.add(stringRequest)
    }

}

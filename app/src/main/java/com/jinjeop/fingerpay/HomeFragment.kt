package com.jinjeop.fingerpay

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.android.volley.Request
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class HomeFragment : Fragment() {

    private lateinit var lineChart: LineChart
    private lateinit var requestQueue: RequestQueue
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        lineChart = rootView.findViewById(R.id.chart)

        textView = rootView.findViewById(R.id.intro_name)

        // Volley RequestQueue 초기화
        requestQueue = Volley.newRequestQueue(requireContext())
        // 서버로 요청 보내기
        val url = "https://kmc7582s.cafe24.com/fingerpay/app_login.php"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET , url, null,
            Response.Listener { response ->
                try {
                    val sessionValue = response.getString("name")
                    textView.text = "안녕하세요. $sessionValue 님"
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                textView.text = "${error.message}"
            })

        requestQueue.add(jsonObjectRequest)

        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val calculation = view.findViewById<Button>(R.id.calculator)
        val count_coin = view.findViewById<EditText>(R.id.count_coin)
        val current = view.findViewById<EditText>(R.id.current)

        calculation.setOnClickListener {
            val number = count_coin.text.toString()

            if (number.isNotEmpty()) {
                val coin1 = number.toInt()
                val coin2 = coin1 * 10
                val result = coin2.toString()
                current.setText("$result"+"원")
            } else {

            }
        }

        val xAxis = lineChart.axisRight
        xAxis.textColor = Color.WHITE

        val yAxis = lineChart.axisLeft
        yAxis.textColor = Color.WHITE

        lineChart.xAxis.textColor = Color.WHITE
        lineChart.legend.textColor = Color.WHITE

        // 선 그래프에 들어갈 데이터 생성
        val entries = mutableListOf<Entry>()
        entries.add(Entry(0f, 9.8f))
        entries.add(Entry(1f, 9.6f))
        entries.add(Entry(2f, 9.7f))
        entries.add(Entry(3f, 9.9f))
        entries.add(Entry(4f, 9.8f))
        entries.add(Entry(5f, 10f))
        entries.add(Entry(6f, 10f))

        val dataSet = LineDataSet(entries, "JJ coin")
        dataSet.color = Color.RED
        dataSet.valueTextColor = Color.WHITE

        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.setDrawGridBackground(false)
        lineChart.description.isEnabled = false
        lineChart.invalidate()
    }


}

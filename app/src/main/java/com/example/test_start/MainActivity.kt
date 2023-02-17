package com.example.test_start

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_start.databinding.ActivityMainBinding
import com.example.test_start.parsing_XML.Element
import com.example.test_start.parsing_XML.XmlPullParserHandler
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var myAdapter: XmlAdapter? = null
     var elements: ArrayList<Element>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnXml.setOnClickListener {
            binding.rvNewsList.visibility = View.VISIBLE
            Toast.makeText(applicationContext, "XML", Toast.LENGTH_LONG).show()
        }

        binding.btnJson.setOnClickListener {
            binding.rvNewsList.visibility = View.VISIBLE
            Toast.makeText(applicationContext, "JSON", Toast.LENGTH_LONG).show()
            getJSONFromAssets()
        }

        try {
            val parser = XmlPullParserHandler()
            val assets = assets.open("News.xml")
            val parsedAssets = parser.parse(assets)
            elements?.addAll(parsedAssets)
            myAdapter?.notifyDataSetChanged()

            val xmlAdapter = XmlAdapter(this, parsedAssets)
            rvNewsList.layoutManager = LinearLayoutManager(this)
            rvNewsList.adapter = xmlAdapter
        } catch (e: IOException) {
            e.printStackTrace()
        }

        try {
            val jsonString = getJSONFromAssets()!!
            val news = Gson().fromJson(jsonString, News::class.java)

            val itemAdapter = NewsAdapter(this, news.news)
            rvNewsList.layoutManager = LinearLayoutManager(this)
            rvNewsList.adapter = itemAdapter

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8

        try {
            val myUsersJSonFile = assets.open("News.json")
            val size = myUsersJSonFile.available()
            val buffer = ByteArray(size)
            myUsersJSonFile.read(buffer)
            myUsersJSonFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}




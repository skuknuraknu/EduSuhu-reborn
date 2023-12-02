package com.usk.ac.id.uas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CourseActivity : AppCompatActivity() {
    private lateinit var judul: String
    private lateinit var db: DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        auth = FirebaseAuth.getInstance()
        val webView: WebView = findViewById(R.id.webView)
        configureWebViewSettings(webView)


        var judul = intent.getStringExtra("judul")
        findViewById<TextView>(R.id.course_judul).text = judul
        db = FirebaseDatabase.getInstance().getReference("Courses")

        val id = intent.getStringExtra("id")
        val keterangan = intent.getStringExtra("keterangan")
        val gambar = intent.getStringExtra("gambar")
        val videoUrl = intent.getStringExtra("videoUrl")
        val nama_pengguna = auth.currentUser?.email.toString().split("@")[0]
        val course = Course(id, judul,
            "$keterangan", "$gambar",false,
            videoUrl)
        val youtube_url = videoUrl.toString()
        webView.loadUrl(youtube_url)
        db.child(nama_pengguna).child(course.courseId.toString()).setValue(course).addOnSuccessListener {
            Log.w("INFO", "INSERT DATABASE COURSE: OK")
            Toast.makeText(baseContext, "Berhasil memperbarui data.", Toast.LENGTH_SHORT).show()
            val courseImageView = findViewById<ImageView>(R.id.course_image)
            Glide.with(this).load(gambar)
                .into(courseImageView)
            findViewById<TextView>(R.id.course_desc).text = keterangan
        }.addOnFailureListener {
            Log.w("INFO", "INSERT DATABASE COURSE: GAGAL")
            Toast.makeText(baseContext, "Gagal memperbarui data.", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.button_complete).setOnClickListener{
            db.child(nama_pengguna).child(course.courseId.toString())
                .child("completed")
                .setValue(true)
            Toast.makeText(baseContext, "Berhasil Menyelesaikan Kursus.", Toast.LENGTH_SHORT)
                .show()
        }

    }
    private fun configureWebViewSettings(webView: WebView) {
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
    }
}
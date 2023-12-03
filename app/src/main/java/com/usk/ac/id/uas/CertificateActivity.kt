package com.usk.ac.id.uas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView

class CertificateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_certificate)
        supportActionBar?.title = "Certificate"
        // tombol back
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var image = intent.getStringExtra("image")
        var imageview= findViewById<ImageView>(R.id.imageView)
        // Get the resource ID dynamically
        val resourceId = resources.getIdentifier(image, "drawable", packageName)
        if (resourceId != 0) {
            // Set the image resource for the ImageView
            imageview.setImageResource(resourceId)
            Log.i("USERINFO", "OK")
        } else {
            Log.i("USERINFO", "NOT OK $resourceId")
            // Handle the case when the resource is not found
            // You can set a default image or show an error message
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroy() {
        super.onDestroy()
        finish() // Finish the activity when it is destroyed
    }
}
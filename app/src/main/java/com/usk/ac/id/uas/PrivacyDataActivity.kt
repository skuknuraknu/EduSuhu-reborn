package com.usk.ac.id.uas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.usk.ac.id.uas.databinding.ActivityPrivacyDataBinding

class PrivacyDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrivacyDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Privacy & Data"

        //back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ambil data user
        val email = intent.getStringExtra("email")
        val nama = intent.getStringExtra("nama")

        // menampilkan data di ui
        binding.userEmail.text = email
        binding.userNama.text = nama

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
}
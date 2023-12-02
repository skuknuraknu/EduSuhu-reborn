package com.usk.ac.id.uas

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.usk.ac.id.uas.databinding.ActivityDetailBinding
import Model.Materi

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object{
        const val EXTRA_MATPEL = "extra_matpel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.title = "Details"

        val materi = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(EXTRA_MATPEL, Materi::class.java)
        }else{
            intent.getParcelableExtra(EXTRA_MATPEL)
        }

        if (materi != null ){
            val textTitle = materi.title
            binding.tvTitleMateri.text = textTitle
            val img = materi.img
            Glide.with(binding.imgMateri).load(img).into(binding.imgMateri)
            val textDesc = materi.desc
            binding.tvDescMateri.text = textDesc
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
}
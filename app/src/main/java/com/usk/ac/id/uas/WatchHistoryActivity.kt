package com.usk.ac.id.uas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.usk.ac.id.uas.databinding.ActivityWatchHistoryBinding
class WatchHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWatchHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Watch History"

        //back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // optional/bisa hapus(no action)
        setTextViewClickListener(binding.history1)
        setTextViewClickListener(binding.history2)
        setTextViewClickListener(binding.history3)
        setTextViewClickListener(binding.history4)
        setTextViewClickListener(binding.history5)
        setTextViewClickListener(binding.history6)
    }
    //back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    // setTextViewClickListener optional/bisa hapus (gda action)
    private fun setTextViewClickListener(textView: TextView) {
        textView.setOnClickListener {
            showConfirmationDialog()
        }
    }
    // showConfirmationDialog optional/bisa hapus (gda action)
    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete History")
        builder.setMessage("Are you sure you want to delete this history item?")
        builder.setPositiveButton("Yes") { dialog, which ->
            // No action, just UI confirmation
        }
        builder.setNegativeButton("No") { dialog, which ->
            // No action, just UI confirmation
        }
        builder.show()
    }
}
package com.usk.ac.id.uas

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import android.content.Intent
import android.net.Uri

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CompletedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CompletedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var nama_pengguna: String
    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_completed, container, false)
        auth = FirebaseAuth.getInstance()
        nama_pengguna = auth.currentUser?.email.toString().split("@")[0]
        db = FirebaseDatabase.getInstance().getReference("Courses")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnGetCertMlbb).setOnClickListener{
            btnGetCertMlbb(view)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CompletedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CompletedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun btnGetCertMlbb(view: View) {
        // Replace "your_image_name" with the actual name of the image in your res/drawable folder
        val image
        val imageName = "your_image_name"
        val resourceId = resources.getIdentifier(imageName, "drawable", )
        val imageUri = Uri.parse("android.resource://$packageName/$resourceId")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                db.child(nama_pengguna).child("1")
                    .child("completed").get().addOnSuccessListener {
                    if ( it.exists() ) {
                        val status = it.getValue().toString()

                        Log.i("GET CERT MLBB", status)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("GET CERT MLBB", "${DatabaseError.UNKNOWN_ERROR}")
            }
        })

    }
}
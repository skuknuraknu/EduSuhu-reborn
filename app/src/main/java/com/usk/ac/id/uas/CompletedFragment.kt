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
import android.widget.Toast

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
        view.findViewById<Button>(R.id.btnGetCertHack).setOnClickListener{
            btnGetCertHack(view)
        }
        view.findViewById<Button>(R.id.btnGetCertDraw).setOnClickListener{
            btnGetCertDraw(view)
        }
        view.findViewById<Button>(R.id.btnGetCertMusic).setOnClickListener{
            btnGetCertMusic(view)
        }
        view.findViewById<Button>(R.id.btnGetCertNodejs).setOnClickListener{
            btnGetCertNode(view)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()

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
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                db.child(nama_pengguna).child("1")
                    .get().addOnSuccessListener {
                    if ( it.exists() ) {
                        val status = it.child("completed").getValue().toString()
                        Log.i("STATUS", status)
                        if ( status == "true" ) {
                            val image = it.child("courseImage").getValue().toString()
                            val intent = Intent(requireContext(), CertificateActivity::class.java)
                            intent.putExtra("image", "cert_mlbb")
                            requireActivity().startActivity(intent)
                        } else {
                            Toast.makeText(requireContext(), "Anda belum menyelesaikan kursus" +
                                    ".", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("GET CERT MLBB", "${DatabaseError.UNKNOWN_ERROR}")
            }
        })

    }
    fun btnGetCertHack(view: View) {
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                db.child(nama_pengguna).child("2")
                    .get().addOnSuccessListener {
                        if ( it.exists() ) {
                            val status = it.child("completed").getValue().toString()
                            Log.i("STATUS", status)
                            if ( status == "true" ) {
                                val image = it.child("courseImage").getValue().toString()
                                val intent = Intent(requireContext(), CertificateActivity::class.java)
                                intent.putExtra("image", "cert_hack")
                                requireActivity().startActivity(intent)
                            } else {
                                Toast.makeText(requireContext(), "Anda belum menyelesaikan kursus" +
                                        ".", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("GET CERT MLBB", "${DatabaseError.UNKNOWN_ERROR}")
            }
        })

    }
    fun btnGetCertDraw(view: View) {
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                db.child(nama_pengguna).child("3")
                    .get().addOnSuccessListener {
                        if ( it.exists() ) {
                            val status = it.child("completed").getValue().toString()
                            Log.i("STATUS", status)
                            if ( status == "true" ) {
                                val image = it.child("courseImage").getValue().toString()
                                val intent = Intent(requireContext(), CertificateActivity::class.java)
                                intent.putExtra("image", "cert_draw")
                                requireActivity().startActivity(intent)
                            } else {
                                Toast.makeText(requireContext(), "Anda belum menyelesaikan " +
                                        "kursus" +
                                        ".", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("GET CERT MLBB", "${DatabaseError.UNKNOWN_ERROR}")
            }
        })

    }
    fun btnGetCertMusic(view: View) {
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                db.child(nama_pengguna).child("4")
                    .get().addOnSuccessListener {
                        if ( it.exists() ) {
                            val status = it.child("completed").getValue().toString()
                            Log.i("STATUS", status)
                            if ( status == "true" ) {
                                val image = it.child("courseImage").getValue().toString()
                                val intent = Intent(requireContext(), CertificateActivity::class.java)
                                intent.putExtra("image", "cert_music")
                                requireActivity().startActivity(intent)
                            } else {
                                Toast.makeText(requireContext(), "Anda belum menyelesaikan kursus" +
                                        ".", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("GET CERT MLBB", "${DatabaseError.UNKNOWN_ERROR}")
            }
        })

    }
    fun btnGetCertNode(view: View) {
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                db.child(nama_pengguna).child("5")
                    .get().addOnSuccessListener {
                        if ( it.exists() ) {
                            val status = it.child("completed").getValue().toString()
                            Log.i("STATUS", status)
                            if ( status == "true" ) {
                                val image = it.child("courseImage").getValue().toString()
                                val intent = Intent(requireContext(), CertificateActivity::class.java)
                                intent.putExtra("image", "cert_nodejs")
                                requireActivity().startActivity(intent)
                            } else {
                                Toast.makeText(requireContext(), "Anda belum menyelesaikan kursus" +
                                        ".", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("GET CERT MLBB", "${DatabaseError.UNKNOWN_ERROR}")
            }
        })

    }
}
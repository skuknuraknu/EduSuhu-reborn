package com.usk.ac.id.uas

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.getValue

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LearningFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LearningFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference
    private lateinit var linearLayout: LinearLayout
    private lateinit var name: String

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
        val view =  inflater.inflate(R.layout.fragment_learning, container, false)
        auth = FirebaseAuth.getInstance()
        name = auth.currentUser?.email.toString().split("@")[0]
        db = FirebaseDatabase.getInstance().getReference("Courses")
        linearLayout = view.findViewById<LinearLayout>(R.id.container_learning)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load("https://www.pngall.com/wp-content/uploads/12/Avatar-Profile-Vector-PNG-Pic.png")
            .into(view.findViewById(R.id.home_profile_url))
        // Listen for changes in the data
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Clear existing views in the FrameLayout
                linearLayout.removeAllViews()
                db.child(name).get().addOnSuccessListener {
                    if ( it.exists() ) {
                        var count = it.childrenCount.toInt()
                        for (data in it.children) {
                            val imageCourse = ImageView(requireContext())
                            val name = data.child("courseName").getValue().toString()
                            val image = data.child("courseImage").getValue().toString()
                            val textView = TextView(requireContext())
                            Glide.with(requireContext())
                                .load(image)
                                .into(imageCourse)
                            textView.text = "$name"
                            textView.textSize = 20f
                            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textView.setTypeface(null, Typeface.BOLD)
                            textView.setTextColor(Color.BLACK)
                            val fontTypeface = ResourcesCompat.getFont(requireContext(), R.font.montserrat_bold)
                            textView.setTypeface(fontTypeface)
                            textView.setPadding(0,16,0,50)
                            linearLayout.addView(imageCourse)
                            linearLayout.addView(textView)
                            Log.w("USERINFO", "$name")
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LearningFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LearningFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
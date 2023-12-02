package com.usk.ac.id.uas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import androidx.recyclerview.widget.LinearLayoutManager
import com.usk.ac.id.uas.databinding.FragmentHomeBinding
import Model.Materi
import android.content.Intent

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var list = ArrayList<Materi>()
    private lateinit var imageViewProfile: ImageView
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        list.addAll(getListMateri())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Use view binding to inflate the layout
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Access the Intent from the hosting Activity
        val intent = requireActivity().intent
        auth = FirebaseAuth.getInstance()
        val imageURL = intent.getStringExtra("photo")
        var email = intent.getStringExtra("email")
        var nama = intent.getStringExtra("nama")

        // Update the TextView and ImageView using the binding object
        binding.homeHelloTextview.text = "Hello,\n$nama"
        Glide.with(this).load("https://www.pngall.com/wp-content/uploads/12/Avatar-Profile-Vector-PNG-Pic.png")
            .into(binding.homeProfileUrl)

        // Return the root view from the binding object
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        showRecyclerList()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun getListMateri(): ArrayList<Materi> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataKeterangan = resources.getStringArray(R.array.data_keterangan)
        val dataImg = resources.getStringArray(R.array.data_img)
        val dataDeskripsi = resources.getStringArray(R.array.data_deskripsi)
        val listMateri = ArrayList<Materi>()
        for (i in dataTitle.indices){
            // val postingan = Postingan(dataTitle[i], dataStatus[i], dataLike[i],
            // dataComment[i], dataImg.getResourceId(i,-1))
            val materi = Materi(
                dataTitle[i],
                dataKeterangan[i],
                dataImg[i],
                dataDeskripsi[i]
            )
            listMateri.add(materi)
        }
        return listMateri
    }
    private fun showRecyclerList(){
        binding.rvMateri.layoutManager = LinearLayoutManager(this.context)
        val listMateriAdapter = ListMateriAdapter(list)
        binding.rvMateri.adapter = listMateriAdapter

        listMateriAdapter.setOnItemClickCallback(object :
            ListMateriAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Materi) {
                showDetailSelectedMateri(data)
            }
        })
    }
    //    private fun showSelectedMateri(postingan: Postingan) {
//        Toast.makeText(context, "Kamu memilih " + postingan.title, Toast.LENGTH_SHORT).show()
//    }

    private fun showDetailSelectedMateri(materi: Materi){
        val moveIntent = Intent(this.context, DetailActivity::class.java)
        moveIntent.putExtra(DetailActivity.EXTRA_MATPEL, materi)
        startActivity(moveIntent)
    }
}
package com.usk.ac.id.uas

import Model.Materi
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ListMateriAdapter(private val listMateri: ArrayList<Materi>) :
    RecyclerView.Adapter<ListMateriAdapter.ListViewHolder>() {
    private lateinit var context: Context
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_materi, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMateri.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, keterangan, img) = listMateri[position]
        holder.tvTitle.text = title
        holder.tvKeterangan.text = keterangan
        Glide.with(holder.itemView.context).load(img).into(holder.imgPhoto)
        val backgroundColor = getBackgroundColor(holder.itemView, position)
        holder.cardView.setCardBackgroundColor(backgroundColor)

        holder.itemView.setOnClickListener {
            var id = 0
            var videoUrl: String? = null
            val intent by lazy { Intent(it.context, CourseActivity::class.java) }
            if ( "MASTER THE DRAFTING PHASE IN MOBILE LEGENDS" == title) {
                id = 1
                videoUrl = "https://www.youtube.com/embed/P2BktYmTHZE"
            } else if ( "TURN YOUR LAPTOP INTO HACKING MACHINE" == title ) {
                id = 2
                videoUrl = "https://www.youtube.com/embed/8-UEfXax4-c"
            } else if ( "LEARN TO DRAW ANIME FACES WITH MR BOMBASTIC" == title ) {
                id = 3
                videoUrl = "https://www.youtube.com/embed/jcO7tMzf-vE"
            } else if ( "LEARN TO MAKE MUSIC LIKE ALAN WALKER" == title ) {
                id = 4
                videoUrl = "https://www.youtube.com/embed/oG546nZWvGw"
            } else {
                videoUrl = "https://www.youtube.com/embed/Oe421EPjeBE"
                id = 5
            }
            intent.putExtra("id", "$id")
            intent.putExtra("judul", "$title")
            intent.putExtra("keterangan", listMateri[position].desc)
            intent.putExtra("gambar", "$img")
            intent.putExtra("videoUrl", "$videoUrl")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            it?.context?.applicationContext?.let { appContext ->
                appContext.startActivity(intent)
            } ?: run {
                Log.e("Error", "Application Context is null when trying to start CourseActivity")
                // Handle the case where application context is null
            }
//            handleOnClickCourse()
//            onItemClickCallback.onItemClicked(listMateri[holder.adapterPosition])
        }
    }
    private fun handleOnClickCourse(listMateri: ArrayList<Materi>){
        // Check if the context is not null before creating the intent
        context?.applicationContext?.let { appContext ->
            val intent = Intent(appContext, CourseActivity::class.java)
            appContext.startActivity(intent)
        } ?: run {
            Log.e("Error", "Application Context is null when trying to start CourseActivity")
            // Handle the case where application context is null
        }

    }
    private fun getBackgroundColor(itemView: View, position: Int): Int {
        val colors = itemView.resources.obtainTypedArray(R.array.card_background_colors)
        val color = colors.getColor(position % colors.length(), 0)
        colors.recycle()
        return color
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title_materi)
        val tvKeterangan: TextView = itemView.findViewById(R.id.tv_keterangan_materi)
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_materi)
        val cardView: CardView = itemView.findViewById(R.id.card_view_materi)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Materi)
    }
}

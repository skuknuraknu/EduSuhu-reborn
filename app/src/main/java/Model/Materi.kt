package Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize

data class Materi(
    val title: String,
    val keterangan: String,
    val img: String,
    val desc: String,

    ): Parcelable
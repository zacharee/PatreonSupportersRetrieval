package tk.zwander.patreonsupportersretrieval.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SupporterInfo(
    val name: String,
    val link: String
) : Parcelable
package tk.zwander.patreonsupportersretrieval.util

import android.content.Context
import android.content.Intent
import android.net.Uri

//Safely launch a URL.
//If no matching Activity is found, silently fail.
fun Context.launchUrl(url: String) {
    try {
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    } catch (e: Exception) {}
}
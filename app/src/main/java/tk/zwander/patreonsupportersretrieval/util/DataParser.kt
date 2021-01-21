package tk.zwander.patreonsupportersretrieval.util

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tk.zwander.patreonsupportersretrieval.data.SupporterInfo
import java.lang.StringBuilder
import java.net.URL

class DataParser private constructor(private val context: Context) {
    companion object {
        private var instance: DataParser? = null

        fun getInstance(context: Context): DataParser {
            return instance ?: DataParser(context.applicationContext).also {
                instance = it
            }
        }
    }

    private val gson = GsonBuilder().create()

    suspend fun parseSupporters(): List<SupporterInfo> {
        val supportersString = StringBuilder()

        withContext(Dispatchers.IO) {
            try {
                val url = URL("https://raw.githubusercontent.com/zacharee/PatreonSupportersRetrieval/master/app/src/main/assets/supporters.json")

                url.openStream().bufferedReader().useLines {
                    it.forEach { line ->
                        supportersString.appendLine(line)
                    }
                }
            } catch (e: Exception) {
                Log.e("DataParser", e.message, e)

                supportersString.clear()

                context.assets.open("supporters.json").bufferedReader().useLines {
                    it.forEach { line ->
                        supportersString.appendLine(line)
                    }
                }
            }
        }

        return gson.fromJson(
            supportersString.toString(),
            object : TypeToken<ArrayList<SupporterInfo>>() {}.type
        )
    }
}
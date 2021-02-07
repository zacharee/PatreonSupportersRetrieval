package tk.zwander.patreonsupportersretrieval.ui

import android.content.Context
import android.util.AttributeSet
import androidx.preference.Preference
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tk.zwander.patreonsupportersretrieval.R
import tk.zwander.patreonsupportersretrieval.view.SupporterView

class SupporterDialogPreference(context: Context, attrs: AttributeSet) : Preference(context, attrs) {
    init {
        setTitle(R.string.supporters)
        setSummary(R.string.supporters_desc)
    }

    override fun onClick() {
        super.onClick()

        MaterialAlertDialogBuilder(context)
                .setTitle(R.string.supporters)
                .setView(SupporterView(context))
                .show()
    }
}
package tk.zwander.patreonsupportersretrieval.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import tk.zwander.patreonsupportersretrieval.data.SupporterAdapter
import tk.zwander.patreonsupportersretrieval.util.DataParser

class SupporterView : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private val parser = DataParser.getInstance(context)
    private val adapter = SupporterAdapter()
    private val scope = MainScope()

    private var loader: Job? = null

    init {
        setAdapter(adapter)
        layoutManager = LinearLayoutManager(context)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        loader?.cancel()
        loader = scope.launch {
            adapter.setItems(parser.parseSupporters())
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        loader?.cancel()
    }
}
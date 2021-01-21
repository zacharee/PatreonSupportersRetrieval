package tk.zwander.patreonsupportersretrieval.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import tk.zwander.patreonsupportersretrieval.R
import tk.zwander.patreonsupportersretrieval.databinding.SupporterLayoutBinding
import tk.zwander.patreonsupportersretrieval.util.launchUrl

class SupporterAdapter : RecyclerView.Adapter<SupporterAdapter.VH>() {
    private val items = SortedList<SupporterInfo>(
        SupporterInfo::class.java,
        object : SortedListAdapterCallback<SupporterInfo>(this) {
            override fun compare(o1: SupporterInfo, o2: SupporterInfo): Int {
                return o1.name.compareTo(o2.name, true)
            }

            override fun areContentsTheSame(
                oldItem: SupporterInfo?,
                newItem: SupporterInfo?
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(item1: SupporterInfo?, item2: SupporterInfo?): Boolean {
                return item1 == item2
            }
        }
    )

    override fun getItemCount(): Int {
        return items.size()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.supporter_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(items[position])
    }

    fun setItems(items: Collection<SupporterInfo>) {
        this.items.replaceAll(items)
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = SupporterLayoutBinding.bind(itemView)

        fun onBind(info: SupporterInfo) {
            binding.supporterName.text = info.name
            binding.supporterCard.setOnClickListener {
                itemView.context.launchUrl(items[adapterPosition].link)
            }
        }
    }
}
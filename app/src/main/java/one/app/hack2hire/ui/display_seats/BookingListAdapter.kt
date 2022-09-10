package one.app.hack2hire.ui.display_seats

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import one.app.hack2hire.R
import one.app.hack2hire.databinding.BookingCardBinding
import one.app.hack2hire.model.ShowsModel
import one.app.hack2hire.utils.HomeUtils
import java.util.concurrent.Executors

class BookingListAdapter(private var context: Context) :
    ListAdapter<ShowsModel, BookingListAdapter.BookingItemViewHolder>(
        AsyncDifferConfig.Builder(DownloadListDiffCallback())
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
            .build()
    ){

    inner class BookingItemViewHolder constructor(private val binding: BookingCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShowsModel, context: Context) {
            binding.root.setOnClickListener {
                println("Hello World")
            }
            binding.startDate.text = HomeUtils.convertUnixTimeToDateTime(item.startDate)
            binding.bookingName.text = item.name
            Glide.with(context).load(item.imageUrl).into(binding.imageBanner)

        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookingListAdapter.BookingItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BookingCardBinding.inflate(layoutInflater, parent, false)
        return this.BookingItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookingItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, context)
    }


    override fun getItemId(position: Int): Long {
        return getItem(position).showId.hashCode().toLong()
    }

}

class DownloadListDiffCallback : DiffUtil.ItemCallback<ShowsModel>() {
    override fun areItemsTheSame(oldItem: ShowsModel, newItem: ShowsModel): Boolean {
        return oldItem.showId == newItem.showId
    }

    override fun areContentsTheSame(
        oldItem: ShowsModel,
        newItem: ShowsModel
    ): Boolean {
        return oldItem.showId == newItem.showId && oldItem.name == newItem.name && oldItem.imageUrl == newItem.imageUrl && oldItem.startDate == newItem.startDate
    }

}

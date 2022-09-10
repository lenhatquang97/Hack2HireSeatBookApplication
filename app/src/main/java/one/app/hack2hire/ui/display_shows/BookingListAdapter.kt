package one.app.hack2hire.ui.display_shows

import android.content.Context
import android.os.Build.VERSION_CODES.S
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import one.app.hack2hire.R
import one.app.hack2hire.databinding.BookingCardBinding
import one.app.hack2hire.model.ShowsModel
import one.app.hack2hire.utils.HomeUtils
import java.util.concurrent.Executors

class BookingListAdapter(private var context: Context) :
    ListAdapter<ShowsModel, BookingListAdapter.BookingItemViewHolder>(
        AsyncDifferConfig.Builder(BookingListDiffCallback())
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
            .build()
    ){

    inner class BookingItemViewHolder constructor(private val binding: BookingCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShowsModel, context: Context) {
            binding.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("showId", item.showId)
                binding.root.findNavController().navigate(R.id.action_mainFragment_to_displaySeatsFragment, bundle)
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

class BookingListDiffCallback : DiffUtil.ItemCallback<ShowsModel>() {
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

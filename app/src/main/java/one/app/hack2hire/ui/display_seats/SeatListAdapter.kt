package one.app.hack2hire.ui.display_seats

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import one.app.hack2hire.R
import one.app.hack2hire.databinding.SeatItemBinding
import one.app.hack2hire.globals.StaticBooking
import one.app.hack2hire.model.SeatsModel
import one.app.hack2hire.utils.HomeUtils
import java.util.concurrent.Executors

class SeatListAdapter(private var context: Context) :
    ListAdapter<SeatsModel, SeatListAdapter.SeatItemViewHolder>(
        AsyncDifferConfig.Builder(SeatListDiffCallback())
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
            .build()
    ){

    inner class SeatItemViewHolder constructor(private val binding: SeatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SeatsModel, context: Context) {
            binding.root.setOnClickListener {
                if(item.status != "BOOKED"){
                    binding.seatStatus.setImageResource(R.drawable.blue_couch)
                    StaticBooking.bookingSeats.add(item)
                }
            }
            if(item.status == "BOOKED"){
                binding.seatStatus.setImageResource(R.drawable.black_couch)
            } else {
                binding.seatStatus.setImageResource(R.drawable.red_couch)
            }
            binding.seatCode.text = item.seatCode
            binding.bookDate.text = HomeUtils.convertUnixTimeToDateTime(item.bookedDate)
            binding.status.text = item.status

        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeatListAdapter.SeatItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SeatItemBinding.inflate(layoutInflater, parent, false)
        return this.SeatItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeatListAdapter.SeatItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, context)
    }


    override fun getItemId(position: Int): Long {
        return getItem(position).showId.hashCode().toLong()
    }

}

class SeatListDiffCallback : DiffUtil.ItemCallback<SeatsModel>() {
    override fun areItemsTheSame(oldItem: SeatsModel, newItem: SeatsModel): Boolean {
        return oldItem.showId == newItem.showId
    }

    override fun areContentsTheSame(
        oldItem: SeatsModel,
        newItem: SeatsModel
    ): Boolean {
        return oldItem.showId == newItem.showId && oldItem.seatId == newItem.seatId && oldItem.seatCode == newItem.seatCode && oldItem.bookedDate == newItem.bookedDate && oldItem.status == newItem.status
    }

}

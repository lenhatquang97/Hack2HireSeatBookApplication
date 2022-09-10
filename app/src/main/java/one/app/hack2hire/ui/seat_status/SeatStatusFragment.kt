package one.app.hack2hire.ui.seat_status

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import one.app.hack2hire.R
import one.app.hack2hire.databinding.FragmentSeatStatusBinding
import one.app.hack2hire.globals.StaticBooking
import one.app.hack2hire.viewmodel.BookingViewModel

class SeatStatusFragment : Fragment() {
    private lateinit var binding: FragmentSeatStatusBinding
    private val viewModel: BookingViewModel by activityViewModels()
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getString("showId")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSeatStatusBinding.inflate(inflater, container, false)
        binding.seatNumber.text = "Seat Number: " + StaticBooking.bookingSeats.joinToString(separator = ",") { it.seatCode}
        binding.buttonCancel.setOnClickListener {
            val onSuccess = fun() {
                it.findNavController().navigate(R.id.action_seatStatusFragment_to_mainFragment)
            }
            for(seat in StaticBooking.bookingSeats) {
                viewModel.cancelBooking(seat.showId, id!!, onSuccess)
            }
            StaticBooking.bookingSeats.clear()

        }
        binding.buttonHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_seatStatusFragment_to_mainFragment)
        }

        return binding.root
    }

    companion object {

    }
}

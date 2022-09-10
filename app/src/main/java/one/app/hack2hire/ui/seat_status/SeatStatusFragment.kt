package one.app.hack2hire.ui.seat_status

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import one.app.hack2hire.R
import one.app.hack2hire.databinding.FragmentSeatStatusBinding
import one.app.hack2hire.globals.StaticBooking

class SeatStatusFragment : Fragment() {
    private lateinit var binding: FragmentSeatStatusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSeatStatusBinding.inflate(inflater, container, false)
        binding.seatNumber.text = "Seat Number: " + StaticBooking.bookingSeats.joinToString(separator = ",") { it.seatCode}
        binding.buttonCancel.setOnClickListener {
            StaticBooking.bookingSeats.clear()
            it.findNavController().navigate(R.id.action_seatStatusFragment_to_mainFragment)
        }
        binding.buttonHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_seatStatusFragment_to_mainFragment)
        }

        return binding.root
    }

    companion object {

    }
}

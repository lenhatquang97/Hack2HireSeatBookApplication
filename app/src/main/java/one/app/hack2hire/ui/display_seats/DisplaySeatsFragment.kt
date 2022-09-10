package one.app.hack2hire.ui.display_seats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import one.app.hack2hire.Hack2HireApplication
import one.app.hack2hire.R
import one.app.hack2hire.databinding.FragmentDisplaySeatsBinding
import one.app.hack2hire.viewmodel.BookingViewModel

class DisplaySeatsFragment : Fragment() {
    private val viewModel: BookingViewModel by activityViewModels()
    private lateinit var binding: FragmentDisplaySeatsBinding
    private lateinit var adapterVal: SeatListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getString("showId")
        if(id != null){
            viewModel.listAllSeats(id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisplaySeatsBinding.inflate(inflater, container, false)

        adapterVal = SeatListAdapter(requireContext())
        binding.seatLists.apply {
            adapter = adapterVal
            layoutManager = GridLayoutManager(requireContext(), 5)
        }

        viewModel.seatLists.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                adapterVal.submitList(it)
            }
        }
        binding.confirmButton.setOnClickListener {
            findNavController().navigate(R.id.action_displaySeatsFragment_to_seatStatusFragment)
        }

        return binding.root
    }

}

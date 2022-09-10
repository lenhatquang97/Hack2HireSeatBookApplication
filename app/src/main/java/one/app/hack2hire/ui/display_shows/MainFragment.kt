package one.app.hack2hire.ui.display_shows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import one.app.hack2hire.R
import one.app.hack2hire.databinding.FragmentMainBinding
import one.app.hack2hire.viewmodel.BookingViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: BookingViewModel by activityViewModels()
    private lateinit var adapterVal: BookingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.listAllShows()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        adapterVal = BookingListAdapter(requireContext())
        binding.bookingLists.apply {
            adapter = adapterVal
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.showLists.observe(viewLifecycleOwner){
            if(it != null){
                adapterVal.submitList(it)
            }
        }

        return binding.root
    }


}

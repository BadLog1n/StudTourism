package com.oneseed.studtourism.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oneseed.studtourism.R
import com.oneseed.studtourism.databinding.FragmentEventsBinding

class EventsFragment : Fragment() {

    private var rcAdapter = EventsAdapter()

    private var _binding: FragmentEventsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventRc: RecyclerView = view.findViewById(R.id.eventsRc)
        eventRc.adapter = rcAdapter
        val linearLayoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        eventRc.layoutManager = linearLayoutManager
        val a = EventData("21", "Центральный", "Амурская область", "4", false)
        rcAdapter.addEvent(a)
        val b = EventData("22", "Центральный", "Амурская область", "4", false)
        rcAdapter.addEvent(b)
        val c = EventData("23", "Центральный", "Амурская область", "4", false)
        rcAdapter.addEvent(c)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
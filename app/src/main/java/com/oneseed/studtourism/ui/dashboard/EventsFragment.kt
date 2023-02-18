package com.oneseed.studtourism.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oneseed.studtourism.R
import com.oneseed.studtourism.databinding.FragmentEventsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.jsoup.Connection
import org.jsoup.Jsoup

class EventsFragment : Fragment() {

    private var rcAdapter = EventsAdapter()
    private lateinit var result: ArrayList<EventData>
    private val eventApi = EventsApi()

    private var _binding: FragmentEventsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentEventsBinding.inflate(inflater, container, false)

        /*val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventRc: RecyclerView = view.findViewById(R.id.eventsRc)
        eventRc.adapter = rcAdapter
        val linearLayoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        eventRc.layoutManager = linearLayoutManager






        fun loadData() {
            try {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        val document: String
                        val sitePath =
                            "https://stud-api.sabir.pro/events/all"

                        val response: Connection.Response = Jsoup.connect(sitePath)
                            .ignoreContentType(true)
                            .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
                            .timeout(10000).execute()

                        val statusCode: Int = response.statusCode()

                        if (statusCode == 200) {
                            document =
                                Jsoup.connect(sitePath).ignoreContentType(true).get().text()
                        } else throw Exception("Error")

                        val jsonArray = JSONArray(document)
                        result = eventApi.returnJson(jsonArray)
                        withContext(Dispatchers.Main) {
                            rcAdapter.clearRecords()
                            for (item in result) {
                                rcAdapter.addEvent(item)

                            }
                        }

                    }


                }
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }

        loadData()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.oneseed.studtourism.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oneseed.studtourism.R
import com.oneseed.studtourism.databinding.FragmentSearchBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.jsoup.Connection
import org.jsoup.Jsoup

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private var rcAdapter = AccommodationAdapter()
    private val searchApi = SearchApi()
    private lateinit var result: ArrayList<TourismData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val accommRc: RecyclerView = view.findViewById(R.id.acommodationSearchResultsRc)
        accommRc.adapter = rcAdapter
        val linearLayoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        accommRc.layoutManager = linearLayoutManager

        fun changeSearchResult() {
            lifecycleScope.launch {
                withContext(Dispatchers.Main) {
                    rcAdapter.clearRecords()
                    for (item in result) {
                        if (binding.searchEditText.text.isNotBlank()
                            && binding.searchEditText.text.isNotEmpty()
                        ) {
                            if (binding.searchEditText.text.toString()
                                    .uppercase() in item.name.uppercase()
                                || binding.searchEditText.text.toString().uppercase() in item.city.uppercase()
                            ) {
                                rcAdapter.addAccomodation(item)
                            }

                        } else {
                            rcAdapter.addAccomodation(item)

                        }
                    }
                }
            }

        }


        fun loadData() {
            try {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        val document: String
                        val sitePath =
                            "https://stud-api.sabir.pro/universities/all"

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
                        result = searchApi.returnJson(jsonArray)
                        withContext(Dispatchers.Main) {
                            rcAdapter.clearRecords()
                            for (item in result) {
                                if (binding.searchEditText.text.isNotBlank()
                                    && binding.searchEditText.text.isNotEmpty()
                                ) {
                                    if (binding.searchEditText.text.toString()
                                            .uppercase() in item.name.uppercase() || binding.searchEditText.text.toString() in item.city.uppercase()
                                    ) {
                                        rcAdapter.addAccomodation(item)
                                    }

                                } else {
                                    rcAdapter.addAccomodation(item)

                                }
                            }
                            if (rcAdapter.itemCount == 0) {
                                binding.foundtv.text = "Ничего не найдено"
                            }
                        }

                    }
                }
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }

        loadData()
        binding.searchSettingsButton.setOnClickListener {
            binding.settingsLayout.visibility =
                if (binding.settingsLayout.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            changeSearchResult()
/*            val link = "https://play.google.com/store/apps/details?id=com.oneseed.studtourism"
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "Hey Check out this Great app: $link")
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Поделиться:"))*/
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                changeSearchResult()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding.fedDistrictSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    changeSearchResult()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        binding.subjectFedSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    changeSearchResult()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        binding.localitySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    changeSearchResult()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }


        super.onViewCreated(view, savedInstanceState)
    }

}
package com.oneseed.studtourism.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
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
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.Connection

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var rcAdapter = AccommodationAdapter()
    private val searchApi = SearchApi()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val accommRc: RecyclerView = view.findViewById(R.id.acommodationSearchResultsRc)
        accommRc.adapter = rcAdapter

        val linearLayoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        accommRc.layoutManager = linearLayoutManager
        /* val a = TourismData("21", "Центральный", "Амурская область", "4", "5", "6", "7", "8", "1", "1")
        rcAdapter.addAccomodation(a)
        val b = TourismData("22", "Центральный", "Амурская область", "4", "5", "6", "7", "8", "1", "1")
        rcAdapter.addAccomodation(b)
        val c = TourismData("23", "Центральный", "Амурская область", "4", "5", "6", "7", "8", "1", "1")
        rcAdapter.addAccomodation(c) */




        fun changeSearchResult() {
            if (binding.settingsLayout.visibility == View.VISIBLE) {
                val isNotSelected =
                    binding.fedDistrictSpinner.selectedItem.toString() == "" && binding.subjectFedSpinner.selectedItem.toString() == "" && binding.localitySpinner.selectedItem.toString() == ""
                if (!isNotSelected) {
                    // закомментила потому что поменялся дата класс
                    /*
                    for (item in listOf(a, b, c)) {
                        if (binding.searchEditText.text.toString() in item.name
                            && binding.fedDistrictSpinner.selectedItem.toString() in item.fedDistrict
                            && binding.subjectFedSpinner.selectedItem.toString() in item.subject
                            && binding.localitySpinner.selectedItem.toString() in item.locality
                        ) {
                            Toast.makeText(context, "Найдено, ${item.name}", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } */
                }

            } else {



                try {
                    lifecycleScope.launch(){
                        withContext(Dispatchers.IO){
                            val document: String
                            val sitePath =
                                "https://stud-api.sabir.pro/universities/all"

                            val response: Connection.Response = Jsoup.connect(sitePath)
                                .ignoreContentType(true)
                                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
                                .timeout(10000).execute()

                            val statusCode: Int = response.statusCode()

                            if (statusCode == 200) {
                                document = Jsoup.connect(sitePath).ignoreContentType(true).get().text()
                            } else throw Exception("Error")

                            val jsonArray = JSONArray(document)
                            val result =  searchApi.returnJson(jsonArray)
                            withContext(Dispatchers.Main){
                                for (item in result){
                                    rcAdapter.addAccomodation(item)
                                }
                            }

                        }
                    }
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }







                // закомментила потому что поменялся дата класс
                /*
                for (item in listOf(a, b, c)) {
                    if (binding.searchEditText.text.toString() in item.name
                        && binding.searchEditText.text.isNotBlank()
                        && binding.searchEditText.text.isNotEmpty()
                    ) {
                        Toast.makeText(context, "Найдено, ${item.name}", Toast.LENGTH_SHORT).show()
                    }
                } */
            }
        }



        changeSearchResult()

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
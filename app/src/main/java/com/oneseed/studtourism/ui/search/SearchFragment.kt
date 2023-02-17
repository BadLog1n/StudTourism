package com.oneseed.studtourism.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.oneseed.studtourism.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // закомментила потому что поменялся дата класс
        //val a = TourismData("1", "2", "Алтайский край", "Апатиты", "", "6", "7", "8")
        //val b = TourismData("21", "Центральный", "Амурская область", "4", "5", "6", "7", "8")
        //val c = TourismData("13", "Центральный", "Амурская область", "4", "5", "6", "7", "8")

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
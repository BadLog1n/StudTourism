package com.oneseed.studtourism.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oneseed.studtourism.R
import com.oneseed.studtourism.databinding.FragmentNavigationBinding

class NavigationFragment : Fragment() {
    private var rcAdapter = StoriesPhotoAdapter()

    private var _binding: FragmentNavigationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val storiesRc: RecyclerView = view.findViewById(R.id.storiesRcView)
        storiesRc.adapter = rcAdapter
        rcAdapter.recordsList = ArrayList()
        rcAdapter.addPhotoRecord(
            StoriesRecord(R.drawable.ic_dashboard_black_24dp)
        )
        rcAdapter.addPhotoRecord(
            StoriesRecord(R.drawable.ic_home_black_24dp)
        )
        rcAdapter.addPhotoRecord(
            StoriesRecord(R.drawable.ic_launcher_foreground)
        )
        rcAdapter.addPhotoRecord(
            StoriesRecord(R.drawable.baseline_search_24)
        )
        rcAdapter.addPhotoRecord(
            StoriesRecord(R.drawable.ic_launcher_background)
        )

        rcAdapter.notifyItemChanged(rcAdapter.itemCount)
        val linearLayoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, true)
        linearLayoutManager.stackFromEnd = true
        storiesRc.layoutManager = linearLayoutManager


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
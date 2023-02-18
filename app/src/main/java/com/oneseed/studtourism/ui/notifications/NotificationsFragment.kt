package com.oneseed.studtourism.ui.notifications

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
import com.oneseed.studtourism.databinding.FragmentNotificationsBinding
import com.oneseed.studtourism.ui.search.TourismData

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var rcAdapter = NotificationAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notifRc: RecyclerView = view.findViewById(R.id.notificationsRc)
        notifRc.adapter = rcAdapter
        val linearLayoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        notifRc.layoutManager = linearLayoutManager
        val a = NotificationData("Заявка 1", "Ваша заявка..", "8 часов назад", false)
        rcAdapter.addNotification(a)
        val b = NotificationData("Заявка 2", "Ваша заявка..", "8 часов назад", true)
        rcAdapter.addNotification(b)
        val c = NotificationData("Заявка 3", "Ваша заявка..", "8 часов назад", true)
        rcAdapter.addNotification(c)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.oneseed.studtourism.ui.userAccount

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.oneseed.studtourism.R

class UserAccountFragment : Fragment() {

    companion object {
        fun newInstance() = UserAccountFragment()
    }

    private lateinit var viewModel: UserAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.notifImageButton).setOnClickListener{
            this.findNavController().navigate(R.id.navigation_notifications)
            val toolbar: Toolbar? = activity?.findViewById(R.id.toolbar)
            toolbar?.title = "Уведомления"
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserAccountViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onResume() {
        super.onResume()
        //this.findNavController().navigate(R.id.navigation_notifications)
        val toolbar: Toolbar? = activity?.findViewById(R.id.toolbar)
        toolbar?.title = "Личный кабинет"
    }

}
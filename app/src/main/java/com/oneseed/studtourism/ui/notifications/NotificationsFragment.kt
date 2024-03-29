package com.oneseed.studtourism.ui.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oneseed.studtourism.MainActivity
import com.oneseed.studtourism.R
import com.oneseed.studtourism.databinding.FragmentNotificationsBinding

const val CHANNEL_NAME = "channelName"
const val CHANNEL_ID = "channelID"
const val NOTIFY_ID = 0

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
        ViewModelProvider(this)[NotificationsViewModel::class.java]

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun createNotifyChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lightColor = Color.BLUE
                enableLights(true)
            }
            val manager =
                requireContext().getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val notifRc: RecyclerView = requireView().findViewById(R.id.notificationsRc)
        notifRc.adapter = rcAdapter
        val linearLayoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        notifRc.layoutManager = linearLayoutManager
        val a = NotificationData(
            "Заявка 1",
            "Ваша заявка нуждается в уточнении данных..",
            "8 часов назад",
            false
        )
        rcAdapter.addNotification(a)
        val b = NotificationData("Заявка 1", "Ваша заявка рассматривается..", "1 день назад", true)
        rcAdapter.addNotification(b)
        val c = NotificationData("Заявка 1", "Ваша заявка получена..", "2 дня назад", true)
        rcAdapter.addNotification(c)


        binding.generateRandomPushButton.setOnClickListener {

            createNotifyChannel()

            val intent = Intent(requireContext(), MainActivity::class.java)
            val pendingIntent = TaskStackBuilder.create(requireContext()).run {
                addNextIntentWithParentStack(intent)
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            }

            val randomTextArray = arrayOf(
                "В офисе Яндекс проходит день стажера. Присоединяйся!",
                "Сегодня в Москве много интересных мероприятий! Переходи в события и участвуй!",
                "Давно Вас не видели. Как насчёт путешествия?"
            )
            val randomTextTitleArray = arrayOf(
                "День стажера",
                "Город Москва",
                "Студтуризм"
            )

            val randomTextTitle = randomTextTitleArray.random()
            val randomText = randomTextArray.random()


            rcAdapter.addNotification(
                NotificationData(randomTextTitle, randomText, "Сейчас", false)
            )

            val notify = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setContentTitle(randomTextTitle)
                .setContentText(randomText)
                .setSmallIcon(R.drawable.ic_dashboard_black_24dp)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()


            val notifyManger = NotificationManagerCompat.from(requireContext())

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        1
                    )
                }
            }
            notifyManger.notify(NOTIFY_ID, notify)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
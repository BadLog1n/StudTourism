package com.oneseed.studtourism

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.oneseed.studtourism.databinding.ActivityMainBinding
const val CHANNEL_NAME = "channelName"
const val CHANNEL_ID = "channelID"
const val NOTIFY_ID = 0
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_search -> {
                    toolbar.title = "Поиск"
                    supportActionBar?.show()
                    navController.navigate(R.id.navigation_search)
                return@setOnItemSelectedListener true}

                R.id.navigation_events -> {
                    toolbar.title = "События"
                    supportActionBar?.show()
                    navController.navigate(R.id.navigation_events)
                    return@setOnItemSelectedListener true}
                R.id.navigation_navigation -> {
                    toolbar.title = "Сервисы"
                    supportActionBar?.show()
                    navController.navigate(R.id.navigation_navigation)
                    return@setOnItemSelectedListener true}
                R.id.navigation_user_account -> {
                    toolbar.title = "Личный кабинет"
                    supportActionBar?.show()
                    navController.navigate(R.id.navigation_user_account)
                    return@setOnItemSelectedListener true}
                else -> {
                    toolbar.title = "Поиск"
                    supportActionBar?.show()
                    true}
            }
        }
        //navView.setupWithNavController(navController)

        createNotifyChannel()

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notify = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Тестовый заголовок")
            .setContentText("Это тестовое уведомление")
            .setSmallIcon(R.drawable.ic_dashboard_black_24dp)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()


        val notifyManger = NotificationManagerCompat.from(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1
                )

                return
            }
        }
        notifyManger.notify(NOTIFY_ID, notify)


    }

    private fun createNotifyChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.BLUE
                enableLights(true)
            }
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

        }
    }



}
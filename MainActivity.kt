package com.example.ayetbildirim

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.os.Build
import android.content.pm.PackageManager
import android.widget.TextView
import android.widget.LinearLayout
import android.view.Gravity

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= 33 &&
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 10)
        }

        val text = TextView(this).apply {
            text = "Ayet Bildirimi\\n\\nBildirim iznini verdiğinde, telefonu kilitten her açışında sıradaki ayet bildirim olarak gösterilir."
            textSize = 18f
            gravity = Gravity.CENTER
            setPadding(40, 40, 40, 40)
        }

        setContentView(LinearLayout(this).apply {
            gravity = Gravity.CENTER
            addView(text)
        })
    }
}

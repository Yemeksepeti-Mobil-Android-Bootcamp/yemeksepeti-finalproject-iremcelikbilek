package com.iremcelikbilek.yemeksepetiapp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.iremcelikbilek.yemeksepetiapp.utils.ifNotNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!isOnline(this)) {
            AlertDialog.Builder(this)
                .setTitle("İnternet Bağlantısı Bulunamadı")
                .setMessage("Uygulamaya devam etmek için ayarlardan Wifi bağlantınızı açmak ister misiniz ?")
                .setCancelable(false)
                .setPositiveButton("evet") { dialog, button ->
                    startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                    finish()
                    dialog.dismiss()
                }.show()
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        ifNotNull(connectivityManager) {
            val capabilities =
                it.getNetworkCapabilities(it.activeNetwork)
            ifNotNull(capabilities) { networkCapabilities ->
                return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            }
        }
        return false
    }
}


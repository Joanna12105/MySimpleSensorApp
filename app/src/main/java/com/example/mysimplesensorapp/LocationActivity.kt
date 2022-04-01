package com.example.mysimplesensorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.location.*


class LocationActivity : AppCompatActivity() {

    private val TAG = "LocationActivity"

    //Layout
    private val tvLocation : TextView by lazy { findViewById(R.id.textview_location) }
    private val fusedLocationClient : FusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(applicationContext) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        fusedLocationClient.lastLocation.addOnSuccessListener {
                if(it != null){
                    tvLocation.text = getString(R.string.locationTVtxt, it.latitude, it.longitude, it.time)
                }
                else {
                    tvLocation.text = getString(R.string.noAvailablePosition)
                }
            }


    }
}


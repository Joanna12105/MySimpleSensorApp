package com.example.mysimplesensorapp

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*


class LocationActivity : AppCompatActivity() {

    private val TAG = "LocationActivity"

    //Layout
    private val tvLocation : TextView by lazy { findViewById(R.id.textview_location) }
    private val fusedLocationClient : FusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(applicationContext) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener {
                if(it != null){
                    tvLocation.text = getString(R.string.locationTVtxt, it.latitude, it.longitude, it.time)
                }
                else {
                    tvLocation.text = getString(R.string.noAvailablePosition)
                }
            }

        val locationCallback = object :  LocationCallback() {
            override fun onLocationResult(locationResult : LocationResult) {
                val location = locationResult.lastLocation
                if(location != null){
                    tvLocation.text = getString(R.string.locationTVtxt, location.latitude, location.longitude, location.time)
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(getLocationRequest(),
            locationCallback, Looper.getMainLooper())

    }

    private fun getLocationRequest() : LocationRequest {
        val locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        return locationRequest
    }
}


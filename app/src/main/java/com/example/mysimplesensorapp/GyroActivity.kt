package com.example.mysimplesensorapp

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class GyroActivity : AppCompatActivity() {


    //Variablen
    private val textResValue : TextView by lazy { findViewById(R.id.resolutionTextView) }
    private val textRangeValue : TextView by lazy { findViewById(R.id.textRangeTextView) }
    /* private val XradsTV : TextView by lazy { findViewById(R.id.XradsTV) }
    private val YradsTV : TextView by lazy { findViewById(R.id.YradsTV) }
    private val ZradsTV : TextView by lazy { findViewById(R.id.ZradsTV) } */
    private val textXValue : TextView by lazy { findViewById(R.id.textXValue) }
    private val textYValue : TextView by lazy { findViewById(R.id.textYValue) }
    private val textZValue : TextView by lazy { findViewById(R.id.textZValue) }

    private val sensorManager : SensorManager by lazy { applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager}
    private val gyro : Sensor by lazy { sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gyro)

        textResValue.text = getString(R.string.resolutionTVtxt, gyro.resolution)
        textRangeValue.text = getString(R.string.rangeTVtxt,gyro.maximumRange)

        val gyroListener = object : SensorEventListener {
            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                //Funktion wird von uns nicht benötigt, muss aber vorhanden sein.
                Log.i(TAG, "onAccuracyChanged")
            }

            override fun onSensorChanged(sensorEvent: SensorEvent) {
                textXValue.text = sensorEvent.values[0].toString()
                textYValue.text = sensorEvent.values[1].toString()
                textZValue.text = sensorEvent.values[2].toString()

                if(sensorEvent.values[2] > 0.5f){
                    window.decorView.setBackgroundColor(Color.RED)
                }
                else if(sensorEvent.values[2] < -0.5f){
                    window.decorView.setBackgroundColor(Color.GREEN)
                }
            }
        }

        sensorManager.registerListener(gyroListener, gyro,
            SensorManager.SENSOR_DELAY_NORMAL)
    }
    }

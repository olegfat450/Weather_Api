package com.example.weather_api

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.example.weather_api.models.CurrentWeather
import com.example.weather_api.utils.RetrofitInstance
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.squareup.picasso.Picasso
import com.squareup.picasso.Request
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class MainActivity : AppCompatActivity() {

     private lateinit var toolbar: Toolbar
     private lateinit var button1: Button
     private lateinit var maxt: TextView
     private lateinit var mint: TextView
     private lateinit var town: EditText
     private lateinit var imageTv: ImageView
     private lateinit var temp: TextView
     private lateinit var wind: TextView
     private lateinit var feels_like: TextView
     private lateinit var himidity: TextView
     private lateinit var pressure: TextView
     private lateinit var visibality: TextView
     private lateinit var button2: Button

     var lat: Double = 0.0; var lon = 0.0

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        toolbar.setBackgroundColor(Color.BLUE)
        title = "Погода"
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        button1 = findViewById(R.id.button1)
        maxt = findViewById(R.id.maxt)
        mint = findViewById(R.id.mint)
        town = findViewById(R.id.townName)
        imageTv = findViewById(R.id.imageTv)
        temp = findViewById(R.id.temp)
        wind = findViewById(R.id.wind)
        feels_like = findViewById(R.id.feels_like)
        himidity = findViewById(R.id.himidity)
        pressure = findViewById(R.id.pressure)
        visibality = findViewById(R.id.visibality)
        button2 = findViewById(R.id.button2)


          val check = intent.getIntExtra("key",0)

             if (check != 111)  startActivity(Intent(this,Activity2::class.java))


        button1.setOnClickListener { getWeather(false) }

        button2.setOnClickListener {


                val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

              if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) { ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION),1)
                       return@setOnClickListener }

        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            try { lat = location?.latitude!!; lon = location.longitude } catch (_:Exception) { }
                 getWeather(true)
        }


    }

    @SuppressLint("SetTextI18n")
    @OptIn(DelicateCoroutinesApi::class)
    private fun getWeather(flag: Boolean) {

        var response: Response<CurrentWeather>? = null

        GlobalScope.launch (Dispatchers.IO) {


            if(flag) { response = try{ RetrofitInstance.api.getCurrentWeatherC( lat,lon,"metric",applicationContext.getString(R.string.api_key))
            } catch (_:Exception) { Toast.makeText(applicationContext,"Ошибка",Toast.LENGTH_LONG).show(); return@launch }}  else

                {  response = try{ RetrofitInstance.api.getCurrentWeather( town.text.toString().trim(),"metric",applicationContext.getString(R.string.api_key))
              } catch (_:Exception) { Toast.makeText(applicationContext,"Ошибка",Toast.LENGTH_LONG).show(); return@launch }}



                    if ( response!!.isSuccessful()  && response!!.body() != null) { withContext(Dispatchers.Main) {

                        val data = response!!.body()
                        town.setText(data?.name)
                        maxt.text = data!!.main.temp_max.toString()
                        mint.text = data.main.temp_min.toString()
                        temp.text = data.main.temp.toString() + " *C"
                        wind.text = data.wind.speed.toString() + " m/c"
                        feels_like.text = data.main.feels_like.toString() + " *C"
                        himidity.text = data.main.humidity.toString() + " %"
                        pressure.text = (data.main.pressure / 1.33).toInt().toString() + " рт.ст."
                        visibality.text = data.visibility.toString() + " м"

                        val icon = data.weather[0].icon
                        val imageUrl = "https://openweathermap.org/img/wn/$icon@4x.png"

                        Picasso.get().load(imageUrl).into(imageTv)


                    }}


        }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu) }

    @SuppressLint("SuspiciousIndentation")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val builder = AlertDialog.Builder(this)
            builder.setTitle("Выход из программы")
                .setPositiveButton("Да") { _,_ -> finishAffinity()}
                .setNegativeButton("Нет") { _,_ -> }
               builder.create().show()

        return super.onOptionsItemSelected(item) }
}










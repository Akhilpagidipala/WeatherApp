package com.demo.weatherapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.widget.TextView;

import com.demo.weatherapp.R;
import com.demo.weatherapp.model.Weather;

public class WeatherDetailsActivity extends AppCompatActivity {

    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            weather = bundle.getParcelable("weather");
        }
        setUpToolbar();
        setUpUI();
    }

    private void setUpToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(R.string.title_details);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpUI() {
        TextView temperatureMainTV = (TextView) findViewById(R.id.temperatureMainTV);
        TextView temperatureDescTV = (TextView) findViewById(R.id.temperatureDescTV);
        TextView temperatureTV = (TextView) findViewById(R.id.temperatureTV);
        TextView windSpeedTV = (TextView) findViewById(R.id.windSpeedTV);
        TextView cloudinessTV = (TextView) findViewById(R.id.cloudinessTV);
        TextView pressureTV = (TextView) findViewById(R.id.pressureTV);
        TextView humidityTV = (TextView) findViewById(R.id.humidityTV);
        TextView sunriseTV = (TextView) findViewById(R.id.sunriseTV);
        TextView cityNameTV = (TextView) findViewById(R.id.cityNameTV);

        temperatureMainTV.setText(weather.getWeatherCondition());
        temperatureDescTV.setText(weather.getDescription());
        temperatureTV.setText(convertKelvinToFahrenheit(weather.getTemperature()));
        windSpeedTV.setText(weather.getWindSpeed() + " m/s");
        cloudinessTV.setText(weather.getCloudiness());
        pressureTV.setText(weather.getPressure() + " hpa");
        humidityTV.setText(weather.getHumidity() + "%");
        cityNameTV.setText("Measured : " + weather.getCityName());
    }

    private String convertKelvinToFahrenheit(float tempInKelvin){
        float result = 9/5 *(tempInKelvin - 273) + 32;

        return (int)result+"° F";
    }
}

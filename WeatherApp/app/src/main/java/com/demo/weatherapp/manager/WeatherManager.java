package com.demo.weatherapp.manager;

import com.demo.weatherapp.model.Weather;

import org.json.JSONException;


public interface WeatherManager {

    Weather getWeatherDetailsByPostalCode(String postalCode) throws JSONException;
}

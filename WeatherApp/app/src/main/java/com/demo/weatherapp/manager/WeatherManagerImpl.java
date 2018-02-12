package com.demo.weatherapp.manager;

import com.demo.weatherapp.Constants;
import com.demo.weatherapp.converter.WeatherConverter;
import com.demo.weatherapp.model.Weather;
import com.demo.weatherapp.network.HttpGet;

import org.json.JSONException;


public class WeatherManagerImpl implements WeatherManager {
    @Override
    public Weather getWeatherDetailsByPostalCode(String postalCode) throws JSONException {
        String url = "http://api.openweathermap.org/data/2.5/weather?zip=" + postalCode + ",us&APPID=" + Constants.API_KEY;
        HttpGet httpGet = new HttpGet();
        String httpResponse = httpGet.execute(url);
        WeatherConverter weatherConverter = new WeatherConverter();
        Weather weather = weatherConverter.convertToWeather(httpResponse);
        return weather;
    }
}

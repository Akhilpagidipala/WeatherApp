package com.demo.weatherapp.converter;

import com.demo.weatherapp.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class WeatherConverter {

    public Weather convertToWeather(String httpResponse) throws JSONException {
        Weather weather = new Weather();
        JSONObject weatherJsonObject = new JSONObject(httpResponse);

        JSONArray weatherJsonArray = weatherJsonObject.getJSONArray("weather");
        if(weatherJsonArray.length() > 0) {
            JSONObject jsonObject = weatherJsonArray.getJSONObject(0);
            weather.setWeatherCondition(jsonObject.optString("main"));
            weather.setDescription(jsonObject.optString("description"));
        }

        JSONObject mainJsonObject = weatherJsonObject.getJSONObject("main");
        weather.setTemperature(mainJsonObject.optLong("temp"));
        weather.setHumidity(mainJsonObject.optString("humidity"));
        weather.setPressure(mainJsonObject.optString("pressure"));

        JSONObject windJsonObject = weatherJsonObject.getJSONObject("wind");
        weather.setWindSpeed(windJsonObject.optString("speed"));

        JSONObject cloudsJsonObject = weatherJsonObject.getJSONObject("clouds");
        weather.setCloudiness(cloudsJsonObject.optString("all"));

        weather.setCityName(weatherJsonObject.optString("name"));

        return weather;
    }
}

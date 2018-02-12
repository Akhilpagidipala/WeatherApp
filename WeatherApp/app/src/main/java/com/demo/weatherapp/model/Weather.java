package com.demo.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Weather implements Parcelable {

    private String weatherCondition;
    private String description;
    private float temperature;
    private String windSpeed;
    private String cloudiness;
    private String pressure;
    private String humidity;
    private String sunrise;
    private String cityName;

    public Weather() {
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(String cloudiness) {
        this.cloudiness = cloudiness;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    protected Weather(Parcel in) {
        weatherCondition = in.readString();
        description = in.readString();
        temperature = in.readFloat();
        windSpeed = in.readString();
        cloudiness = in.readString();
        pressure = in.readString();
        humidity = in.readString();
        sunrise = in.readString();
        cityName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(weatherCondition);
        dest.writeString(description);
        dest.writeFloat(temperature);
        dest.writeString(windSpeed);
        dest.writeString(cloudiness);
        dest.writeString(pressure);
        dest.writeString(humidity);
        dest.writeString(sunrise);
        dest.writeString(cityName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };
}
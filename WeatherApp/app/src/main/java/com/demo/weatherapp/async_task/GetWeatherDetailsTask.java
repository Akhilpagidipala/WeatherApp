package com.demo.weatherapp.async_task;

import android.os.AsyncTask;

import com.demo.weatherapp.Error;
import com.demo.weatherapp.manager.WeatherManager;
import com.demo.weatherapp.manager.WeatherManagerImpl;
import com.demo.weatherapp.model.Weather;


import java.lang.ref.WeakReference;


public class GetWeatherDetailsTask extends AsyncTask<String, Void, Weather> {
    private Error error;
    private WeakReference<IGetWeatherDetailsTask> listener;

    public GetWeatherDetailsTask(IGetWeatherDetailsTask listener){
        this.listener = new WeakReference<IGetWeatherDetailsTask>(listener);
    }

    @Override
    protected Weather doInBackground(String... params) {
        String postalCode = params[0];
        WeatherManager weatherManager = new WeatherManagerImpl();
        Weather weatherDetails = null;
        try {
            weatherDetails = weatherManager.getWeatherDetailsByPostalCode(postalCode);
        } catch (Exception e) {
            e.printStackTrace();
            error = new Error();
        }
        return weatherDetails;
    }

    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);
        if(listener != null){
            IGetWeatherDetailsTask reference = listener.get();
            if(reference != null){
                if(error == null){
                    reference.onGetWeatherDetailsTaskSuccess(weather);
                }else {
                    reference.onGetWeatherDetailsTaskFailure(error);
                }
            }
        }
    }

    public interface IGetWeatherDetailsTask {
        void onGetWeatherDetailsTaskSuccess(Weather weather);

        void onGetWeatherDetailsTaskFailure(Error error);
    }
}

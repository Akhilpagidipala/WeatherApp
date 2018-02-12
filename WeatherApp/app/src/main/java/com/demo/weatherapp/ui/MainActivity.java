package com.demo.weatherapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.weatherapp.R;
import com.demo.weatherapp.async_task.GetWeatherDetailsTask;
import com.demo.weatherapp.model.Weather;

public class MainActivity extends AppCompatActivity implements GetWeatherDetailsTask.IGetWeatherDetailsTask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        final EditText zipCodeET = (EditText) findViewById(R.id.zipCodeET);
        Button submitBtn = (Button) findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(zipCodeET.getText().length() < 5) {
                    Toast.makeText(MainActivity.this,"Postal code length should be 5 characters",Toast.LENGTH_SHORT).show();
                } else {
                    fetchWeatherDetails(zipCodeET.getText().toString());
                }
            }
        });
    }

    private void setUpToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(R.string.title_details);
        }
    }

    private void fetchWeatherDetails(String postalCode) {
        GetWeatherDetailsTask getWeatherDetailsTask = new GetWeatherDetailsTask(this);
        getWeatherDetailsTask.execute(postalCode);
    }

    @Override
    public void onGetWeatherDetailsTaskSuccess(Weather weather) {
        if (weather != null) {
            Intent intent = new Intent(this, WeatherDetailsActivity.class);
            intent.putExtra("weather", weather);
            startActivity(intent);
        }
    }

    @Override
    public void onGetWeatherDetailsTaskFailure(com.demo.weatherapp.Error error) {
        Toast.makeText(MainActivity.this, "Something went wrong in fetching weather details", Toast.LENGTH_SHORT).show();
    }
}

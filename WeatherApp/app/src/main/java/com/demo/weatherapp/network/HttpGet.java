package com.demo.weatherapp.network;

import android.util.Log;

import java.net.HttpURLConnection;

public class HttpGet extends HttpBase {
    private static final String TAG = "HttpGet";

    public String execute(String url) {
        return execute(url, null);
    }

    public String execute(String url, String token) {
        Log.d(TAG, "http url: " + url);
        HttpURLConnection httpUrlConnection = null;
        String rawResponseBody = null;
        try {
            httpUrlConnection = getHttpURLConnection(url, token);
            httpUrlConnection.setRequestMethod("GET");
            int statusCode = httpUrlConnection.getResponseCode();
            try {
                rawResponseBody = readHttpStream(httpUrlConnection.getInputStream());
            }catch (Exception e){
                rawResponseBody = readHttpStream(httpUrlConnection.getErrorStream());
            }
            printHeader(httpUrlConnection);
            Log.d(TAG, "statusCode:" + statusCode + " & response : " + rawResponseBody);
            if (statusCode >= 300) {
                handleHttpError(statusCode, rawResponseBody);
            }
        } catch (Exception ex) {
            if(ex instanceof ApplicationException){
                throw (ApplicationException)ex;
            }
        } finally {
            if (httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }
        }
        return rawResponseBody;
    }
}

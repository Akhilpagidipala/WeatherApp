package com.demo.weatherapp.network;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpBase {

    private static final String TAG = "HttpBase";

    protected HttpURLConnection getHttpURLConnection(String url, String token, Map<String, String> connectionProps) throws IOException {
        HttpURLConnection conn = getHttpURLConnection(url, token);
        if (connectionProps != null && !connectionProps.isEmpty()) {
            for (Map.Entry<String, String> entry : connectionProps.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        return conn;
    }

    @NonNull
    protected HttpURLConnection getHttpURLConnection(String url, String token) throws IOException {
        URL targetUrl = URI.create(url).toURL();

        HttpURLConnection httpUrlConnection = (HttpURLConnection) targetUrl.openConnection();
        httpUrlConnection.setConnectTimeout(30000);
        httpUrlConnection.setReadTimeout(30000);
        httpUrlConnection.setDoInput(true);
        httpUrlConnection.setDoOutput(false);
        httpUrlConnection.setUseCaches(false);

        httpUrlConnection.setRequestProperty("Content-Type", "application/json");

        // LogUtil.debug("HTTP Connection ", "token :" + token);
        return httpUrlConnection;
    }

    protected void handleHttpError(int statusCode, String rawResponseBody) {
        int code = 0;
        String message = "";
        if (null != rawResponseBody && !rawResponseBody.isEmpty()) {
            try {
                JSONObject obj = new JSONObject(rawResponseBody);
                if (obj.has("code")) {
                    code = obj.getInt("code");
                }
                if (obj.has("message")) {
                    message = obj.getString("message");
                }
            } catch (Exception ex) {
                Log.e(TAG, "Error : ", ex);
                // throw new JsonParseException(Constants.JSON_PARSE_ERROR_CODE, "failed to read json", ex);
            }
        }
        throw new ApplicationException(code, message);
    }

    protected void printHeader(URLConnection httpUrlConnection) {
        if (null != httpUrlConnection) {
            Map<String, List<String>> map = httpUrlConnection.getHeaderFields();

            Log.d(TAG, "Printing Response Header...");
            if (null != map) {
                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    Log.d(TAG, "Key : " + entry.getKey() + " ,Value : " + entry.getValue());
                }
            }
        }
    }

    protected String readHttpStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
                sb.append("\n");
            }
        } catch (IOException ex) {
            //throw new InternalServerException(Constants.INTERNAL_SERVER_ERROR_CODE, "failed to create json", ex);
        }
        return sb.toString();
    }
}

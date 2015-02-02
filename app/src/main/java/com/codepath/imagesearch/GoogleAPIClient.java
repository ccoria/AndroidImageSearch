package com.codepath.imagesearch;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ccoria on 2/1/15.
 */
public class GoogleAPIClient {
    private static final String TAG = ">> GoogleAPIClient";
    private static final String BASE_URL = "https://ajax.googleapis.com/ajax/services";
    private static final String IMG_ENDPOINT = "/search/images";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getImages (String query, JsonHttpResponseHandler responseHandler) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("v", "1.0");
        requestParams.put("q", query);

        client.get(BASE_URL + IMG_ENDPOINT, requestParams, responseHandler);
    }

    public static JSONArray getJSONResults(JSONObject response) {
        JSONArray resultsArray = null;
        try {
            resultsArray = response.getJSONObject("responseData").getJSONArray("results");
        } catch (JSONException e) {
            Log.e(TAG, "Error getting json results");
            e.printStackTrace();
        }

        return resultsArray;
    }

}

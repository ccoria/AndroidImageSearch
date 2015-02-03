package com.codepath.imagesearch;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by ccoria on 2/1/15.
 */
public class GoogleAPIClient {
    public static final String TAG = ">> GoogleAPIClient";
    private final String BASE_URL = "https://ajax.googleapis.com/ajax/services";
    private final String IMG_ENDPOINT = "/search/images";

    private AsyncHttpClient client;

    private final int pagesize = 8;

    private String query;
    private int currentOffset = 0;
    private ImageFiltersModel filters;

    public GoogleAPIClient(String query, ImageFiltersModel filters) {
        this.query = query;
        this.client = new AsyncHttpClient();
        this.filters = filters;
    }

    public int getCurrentOffset() {
        return currentOffset;
    }

    public void setCurrentOffset(int currentOffset) {
        this.currentOffset = currentOffset;
    }

    public void setFilter(ImageFiltersModel filter) {
        this.filters = filter;
    }

    public ImageFiltersModel getFilters() {
        return filters;
    }

    public void setNextPage() {
        this.currentOffset += this.pagesize;
    }

    public void getImages (JsonHttpResponseHandler responseHandler) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("v", "1.0");
        requestParams.put("rsz", pagesize);
        requestParams.put("start", currentOffset);
        requestParams.put("q", query);

        setParamsIfExistent(requestParams, "imgcolor", filters.getColor());
        setParamsIfExistent(requestParams, "imgsz", filters.getSize());
        setParamsIfExistent(requestParams, "imgtype", filters.getType());
        setParamsIfExistent(requestParams, "as_sitesearch", filters.getSite());

        Log.i(TAG, "REQUEST: " + requestParams.toString());

        client.get(BASE_URL + IMG_ENDPOINT, requestParams, responseHandler);
    }

    private void setParamsIfExistent(RequestParams requestParamsInstance, String key, String value) {
        if(value != null && !value.isEmpty()) {
            requestParamsInstance.put(key, value);
        }
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

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
    public static final String TAG = ">> GoogleAPIClient";
    private final String BASE_URL = "https://ajax.googleapis.com/ajax/services";
    private final String IMG_ENDPOINT = "/search/images";

    private AsyncHttpClient client = new AsyncHttpClient();

    private final int pagesize = 8;

    private String query;
    private int currentOffset = 0;
    private String filterSize;
    private String filterColor;
    private String filterType;
    private String filterSite;

    public GoogleAPIClient(String query) {
        this.query = query;
    }

    public int getCurrentOffset() {
        return currentOffset;
    }

    public void setCurrentOffset(int currentOffset) {
        this.currentOffset = currentOffset;
    }

    public String getFilterSize() {
        return filterSize;
    }

    public void setFilterSize(String filterSize) {
        this.filterSize = filterSize;
    }

    public String getFilterColor() {
        return filterColor;
    }

    public void setFilterColor(String filterColor) {
        this.filterColor = filterColor;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getFilterSite() {
        return filterSite;
    }

    public void setFilterSite(String filterSite) {
        this.filterSite = filterSite;
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

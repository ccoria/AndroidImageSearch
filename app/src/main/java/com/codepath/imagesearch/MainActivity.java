package com.codepath.imagesearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    final String TAG = ">> Main Activity";
    Context context;
    GridView gvResults;
    ImagesAdapter imgAdapter;
    GoogleAPIClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gvResults = (GridView) findViewById(R.id.gvResults);
        context = this;

        // Attach the listener to the AdapterView onCreate
        gvResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                apiClient.setNextPage();
                getImages();
                // or customLoadMoreDataFromApi(totalItemsCount);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id  == R.id.settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickSearch(final View view) {
        EditText etQuery = (EditText) findViewById(R.id.etQuery);
        String strQuery = etQuery.getText().toString();

        if (imgAdapter != null) imgAdapter.clear();

        this.apiClient = new GoogleAPIClient(strQuery);
        getImages();
    }

    public void getImages() {
        apiClient.getImages(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                if (statusCode == 200) {
                    ArrayList<ImageModel> images = new ArrayList<ImageModel>();

                    JSONArray results = GoogleAPIClient.getJSONResults(response);
                    for (int i = 0; i < results.length(); i++) {
                        try {
                            JSONObject result = results.getJSONObject(i);
                            ImageModel img = new ImageModel(result.getString("url"), result.getString("tbUrl"), result.getString("titleNoFormatting"));
                            images.add(img);
                        } catch (JSONException jsonException) {
                            Log.e(TAG, "JSON ERROR");
                            jsonException.printStackTrace();
                        }
                    }

                    if (imgAdapter == null) {
                        imgAdapter = new ImagesAdapter(context, images);
                        gvResults.setAdapter(imgAdapter);
                    } else {
                        imgAdapter.addAll(images);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i(TAG, responseString);
            }
        });
    }

    private GoogleAPIClient getApiClient(String query) {
        return (this.apiClient == null) ? new GoogleAPIClient(query) : this.apiClient;
    }
}

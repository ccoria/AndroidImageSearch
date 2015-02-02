package com.codepath.imagesearch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class SettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Spinner spnSizes = (Spinner) findViewById(R.id.spnSizes);
        spnSizes.setAdapter(ArrayAdapter.createFromResource(this,
                R.array.array_image_sizes,
                android.R.layout.simple_spinner_dropdown_item));

        Spinner spnColors = (Spinner) findViewById(R.id.spnColors);
        spnColors.setAdapter(ArrayAdapter.createFromResource(this,
                R.array.array_image_colors,
                android.R.layout.simple_spinner_dropdown_item));

        Spinner spnTypes = (Spinner) findViewById(R.id.spnTypes);
        spnTypes.setAdapter(ArrayAdapter.createFromResource(this,
                R.array.array_image_types,
                android.R.layout.simple_spinner_dropdown_item));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickSave(View view) {
        this.finish();
    }
}

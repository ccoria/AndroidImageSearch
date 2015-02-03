package com.codepath.imagesearch;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONObject;


public class SettingsActivity extends ActionBarActivity {

    Spinner spnSizes;
    Spinner spnColors;
    Spinner spnTypes;
    EditText etSite;
    ImageFiltersModel filtersModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        etSite = (EditText) findViewById(R.id.etSiteFilter);
        filtersModel = (ImageFiltersModel) getIntent().getSerializableExtra("filters");

        spnSizes = (Spinner) findViewById(R.id.spnSizes);
        ArrayAdapter adapterSizes = ArrayAdapter.createFromResource(
                this,
                R.array.array_image_sizes,
                android.R.layout.simple_spinner_dropdown_item);
        spnSizes.setAdapter(adapterSizes);

        spnColors = (Spinner) findViewById(R.id.spnColors);
        ArrayAdapter adapterColors = ArrayAdapter.createFromResource(
                this,
                R.array.array_image_colors,
                android.R.layout.simple_spinner_dropdown_item);
        spnColors.setAdapter(adapterColors);

        spnTypes = (Spinner) findViewById(R.id.spnTypes);
        ArrayAdapter adapterTypes = ArrayAdapter.createFromResource(this,
                R.array.array_image_types,
                android.R.layout.simple_spinner_dropdown_item);
        spnTypes.setAdapter(adapterTypes);

        //Setting state
        etSite.setText(filtersModel.getSite());

        int sizesPositon = adapterSizes.getPosition(filtersModel.getSize());
        spnSizes.setSelection(sizesPositon, true);

        int colorsPositon = adapterColors.getPosition(filtersModel.getColor());
        spnColors.setSelection(colorsPositon, true);

        int typesPositon = adapterTypes.getPosition(filtersModel.getType());
        spnTypes.setSelection(typesPositon, true);
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
        if (id == R.id.settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickSave(View view) {
        filtersModel.setColor(spnColors.getSelectedItem().toString());
        filtersModel.setSize(spnSizes.getSelectedItem().toString());
        filtersModel.setType(spnTypes.getSelectedItem().toString());
        filtersModel.setSite(etSite.getText().toString());

        Intent i = new Intent();
        i.putExtra("filters", filtersModel);
        setResult(RESULT_OK, i);

        this.finish();
    }
}

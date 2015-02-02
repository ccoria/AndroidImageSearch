package com.codepath.imagesearch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ccoria on 2/1/15.
 */
public class ImagesAdapter extends ArrayAdapter<ImageModel> {
    public final String TAG = ">> Images Adapter";
    public ImagesAdapter(Context context, ArrayList<ImageModel> images) {
        super(context, R.layout.thumb_view, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageModel imageModel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.thumb_view, parent, false);
        }

        ImageView ivThumb = (ImageView) convertView.findViewById(R.id.ivThumb);
        Log.i(TAG, "thumb_url: " + imageModel.getThumbnail_url());
        Picasso.with(this.getContext()).load(imageModel.getThumbnail_url()).into(ivThumb);

        return convertView;
    }
}

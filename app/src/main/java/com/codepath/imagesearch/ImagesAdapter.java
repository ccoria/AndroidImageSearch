package com.codepath.imagesearch;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ccoria on 2/1/15.
 */
public class ImagesAdapter extends ArrayAdapter<ImageModel> {
    public final String TAG = ">> Images Adapter";
    public ImagesAdapter(Context context, ArrayList<ImageModel> images) {
        super(context, R.layout.thumb_view, images);
    }

    public ArrayList<ImageView> imageViews = new ArrayList<ImageView>();

    public ImageModel getModelFromImageView(ImageView ivThumb) {
        int index = imageViews.indexOf(ivThumb);
        return this.getItem(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageModel imageModel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.thumb_view, parent, false);
        }

        // Thumbnail
        ImageView ivThumb = (ImageView) convertView.findViewById(R.id.ivThumb);
        Picasso.with(this.getContext()).load(imageModel.getThumbnail_url()).into(ivThumb);

        TextView tvURL = (TextView) convertView.findViewById(R.id.tvURL);
        tvURL.setText(imageModel.getUrl());

        // Title
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvCaption);
        tvTitle.setText(imageModel.getTitle());

        return convertView;
    }
}

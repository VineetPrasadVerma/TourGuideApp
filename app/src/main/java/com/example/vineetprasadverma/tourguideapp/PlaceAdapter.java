package com.example.vineetprasadverma.tourguideapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaceAdapter extends ArrayAdapter<Place> {

    /**
     * Create a new PlaceAdapter object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param places  is the list of places to be displayed.
     */
    public PlaceAdapter(Activity context, ArrayList<Place> places) {
        super(context, 0, places);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Check if the existing view is being reused otherwise inflate the view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }

        //Get the object located at the current position.
        Place currentPlace = getItem(position);

        //finding the text view in the listitem.xml .
        TextView placeTextView = convertView.findViewById(R.id.place_name);

        //Get the place name from the current place object and set in the placeTextView.
        placeTextView.setText(currentPlace.getPlaceName());

        TextView openingHoursTextView = convertView.findViewById(R.id.opening_hours);
        openingHoursTextView.setText(currentPlace.getOpeningHours());

        ImageView placeImageView = convertView.findViewById(R.id.image_view);
        placeImageView.setImageResource(currentPlace.getImageResourceId());

        RatingBar placeRatingBar = convertView.findViewById(R.id.rating);
        placeRatingBar.setRating(currentPlace.getRating());

        //Return the whole list item to the listview.
        return convertView;
    }
}

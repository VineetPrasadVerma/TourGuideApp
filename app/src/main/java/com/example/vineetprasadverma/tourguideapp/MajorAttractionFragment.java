package com.example.vineetprasadverma.tourguideapp;


import android.media.MediaPlayer;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MajorAttractionFragment extends Fragment {

    //Handles playback of all the sound files
    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public MajorAttractionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.places_list, container, false);

        //Create a ArrayList of places.
        final ArrayList<Place> places = new ArrayList<Place>();

        places.add(new Place("Tighra Dam", "24 Hours", R.drawable.tighra_dam, R.raw.tighra, 3.5f));
        places.add(new Place("Moti Mahal", "11 A.M.- 11 P.M.", R.drawable.motimahal, R.raw.motimahal, 2.5f));
        places.add(new Place("Gwalior Mela/fair", "Dec-feb", R.drawable.gwalior_mela, R.raw.gwaliormela, 5));
        places.add(new Place("Katora Tal", "11 A.M.- 11 P.M.", R.drawable.katora_tal, R.raw.kotoratal, 4));
        places.add(new Place("DD Mall", "11 A.M.- 10 P.M.", R.drawable.dd_mall, R.raw.ddmall, 4.5f));

        // Create an PlaceAdapter, whose data source is a list of places. The
        // adapter knows how to create list items for each item in the list.
        PlaceAdapter placeAdapter = new PlaceAdapter(getActivity(), places);

        // Find the ListView object in the view hierarchy of the {@link Activity}.
        // There should be a  ListView with the view ID called place_list, which is declared in the
        // place_list_layout file.
        ListView listView = rootView.findViewById(R.id.place_list);

        // Make the ListView use the PlaceAdapter we created above, so that the
        // ListView will display list items for each place in the list
        listView.setAdapter(placeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Release the media player before palying the current sound.
                releaseMediaPlayer();

                //Get the place object at the current location
                Place place = places.get(position);

                // Create and setup the MediaPlayer for the audio resource associated
                // with the current place
                mMediaPlayer = MediaPlayer.create(getActivity(), place.getAudioResourceId());
                mMediaPlayer.start();

                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);

            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    //Clean up the media player by releasing its resources.
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {

            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

}

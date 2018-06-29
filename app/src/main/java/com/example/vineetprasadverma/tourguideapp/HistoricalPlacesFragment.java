package com.example.vineetprasadverma.tourguideapp;


import android.content.Context;
import android.media.AudioManager;
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
public class HistoricalPlacesFragment extends Fragment {

    //Handles playback of all the sound files
    private MediaPlayer mMediaPlayer;

    //Handle audio focus when playing a file.
    private AudioManager mAudioManager;

    //This listener gets triggered when audio focus changes.
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    //This listener gets triggered when when the media player has completed playing the audio file
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public HistoricalPlacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.places_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //Create a list of places.
        final ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place("Gwalior Fort", "8:00 A.M.- 10:00 P.M.", R.drawable.fort, R.raw.fort, 4));
        places.add(new Place("Jai Vilas Place", "10 A.M.- 5 P.M.", R.drawable.jaivilaspalace, R.raw.jaivilaspalace, 4.5f));
        places.add(new Place("Mausoleums of Tansen", "8 A.M.- 8 P.M.", R.drawable.mausoleumoftansen, R.raw.mausoleumoftansen, 3.5f));
        places.add(new Place("Sas Bahu ka mandir", "8:00 A.M.- 10:00 P.M.", R.drawable.sas_bahu_ka_mandir, R.raw.sasbahukamandir, 2));
        places.add(new Place("Gopachal Parvat", "12 P.M.- 12 A.M.", R.drawable.gopachal_parvat, R.raw.gopachalparvat, 3.5f));

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

                //Request for audio focus.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //We have audio focus now.
                    // Create and setup the MediaPlayer for the audio resource associated
                    // with the current place
                    mMediaPlayer = MediaPlayer.create(getActivity(), place.getAudioResourceId());
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);

                }
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


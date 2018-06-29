package com.example.vineetprasadverma.tourguideapp;

public class Place {

    //Place name
    private String mPlaceName;

    //Opening hours
    private String mOpeningHours;

    //Image resource id for the place.
    private int mImageResourceId;

    //Audio Resource id for the place.
    private int mAudioResourceId;

    //Rating for the place.
    private float mRating;

    /**
     * Create a new Place object.
     *
     * @param placeName       is the place name of the place
     * @param openingHours    is the opening hours of the place.
     * @param imageResourceId is the id of the place image
     * @param audioResourceId is the id of the place audio
     * @param rating          is the rating of the place.
     **/
    public Place(String placeName, String openingHours, int imageResourceId, int audioResourceId, float rating) {
        mPlaceName = placeName;
        mOpeningHours = openingHours;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
        mRating = rating;
    }

    //Get the place name for the place
    public String getPlaceName() {
        return mPlaceName;
    }

    //Get the opening hour of the place
    public String getOpeningHours() {
        return mOpeningHours;
    }

    //Get the image Resource id for the place
    public int getImageResourceId() {
        return mImageResourceId;
    }

    //Get the audio resource id for the place
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    //Get the rating of the place.
    public float getRating() {
        return mRating;
    }


}
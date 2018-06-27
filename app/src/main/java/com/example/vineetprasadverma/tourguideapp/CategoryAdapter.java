package com.example.vineetprasadverma.tourguideapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * CategoryAdapter is a FragmentPagerAdapter that can provide the layout for
 * each list item based on a data source which is a list of  objects.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    //Context of the app
    private Context mContext;

    /**
     * Create a new Category Adapter Object
     *
     * @param fm      is the fragment manager that will keep each fragment's state in the adapter across swipes
     * @param context is the context of the app
     **/
    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HistoricalPublicPlacesFragment();
        }else if(position == 1){
            return new MajorAttractionFragment();
        }else if(position == 2){
            return new PlaceForFoodiesFragment();
        }else{
            return new AccommodationFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.historical_public_places);
        }else if(position == 1){
            return mContext.getString(R.string.major_attraction);
        }else if(position == 2){
            return mContext.getString(R.string.place_for_foodies);
        }else{
            return mContext.getString(R.string.accommodation);
        }
    }
}

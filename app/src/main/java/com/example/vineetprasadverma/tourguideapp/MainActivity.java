package com.example.vineetprasadverma.tourguideapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the view pager that will allow the users to swipe between fragments.
        ViewPager viewPager = findViewById(R.id.viewpager);

        //Creates an adapter which knows which fragments will open on each page.
        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(adapter);

        //find the layout that shows the tabs.
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);

        // Connect the tab layout with the view pager. This will
        //1. Update the tab layout when the view pager is swiped
        //2. Update the view pager when a tab is selected
        //3. Set the tab layout's tab names with the view pager's adapter's titles
        //   by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);
    }
}

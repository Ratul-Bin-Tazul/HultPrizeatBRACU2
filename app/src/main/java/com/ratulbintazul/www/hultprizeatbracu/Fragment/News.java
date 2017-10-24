package com.ratulbintazul.www.hultprizeatbracu.Fragment;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ratulbintazul.www.hultprizeatbracu.MainActivity;
import com.ratulbintazul.www.hultprizeatbracu.R;

import java.util.ArrayList;
import java.util.List;


public class News extends Fragment {


    private TabLayout tabLayout;

    private ViewPager viewPager;

    private FrameLayout frameLayout;

    public News() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        //Tablayout
        //viewPager = (ViewPager) view.findViewById(R.id.newsContainer);
        //setupViewPager(viewPager);

        frameLayout = (FrameLayout)view.findViewById(R.id.newsInnerFrame);
        tabLayout = (TabLayout) view.findViewById(R.id.newsTabs);
        //tabLayout.setupWithViewPager(viewPager);




        final HultNews hultNews = new HultNews();
        final IceNews iceNews = new IceNews();


        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.newsInnerFrame, hultNews).addToBackStack(null).commitAllowingStateLoss();

        tabLayout.addTab(tabLayout.newTab().setText("Hult prize at BRACU"),0);
        tabLayout.addTab(tabLayout.newTab().setText("The ICE Buisness Times"),1);

        TabLayout.Tab hultTab = tabLayout.getTabAt(0);
        TabLayout.Tab iceTab = tabLayout.getTabAt(1);

        //tabLayout.setSelectedTabIndicatorColor(Color.parseColor(""));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.newsInnerFrame, hultNews).addToBackStack(null).commitAllowingStateLoss();
                }else {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.newsInnerFrame, iceNews).addToBackStack(null).commitAllowingStateLoss();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }

}

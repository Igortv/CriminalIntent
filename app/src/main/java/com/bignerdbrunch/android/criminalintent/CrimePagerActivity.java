package com.bignerdbrunch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity implements CrimeFragment.Callbacks {

    private static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private Button mToStartPageButton;
    private Button mToEndPageButton;
    private int mIndex;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    public void onCrimeUpdated(Crime crime) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                updateButtonsVision();
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //int index = mCrimes.indexOf(CrimeLab.get(this).getCrime(crimeId));
        //int index = mCrimes.indexOf(CrimeLab.get(this).getCrime(crimeId));

        for(int i = 0; i < mCrimes.size(); i++) {
            if(mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
        mToStartPageButton = (Button) findViewById(R.id.to_start);
        mToStartPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //int index = mCrimes.indexOf(CrimeLab.get(this).getCrime(crimeId));
                mViewPager.setCurrentItem(0);
            }
        });
        mToEndPageButton = (Button) findViewById(R.id.to_end);
        mToEndPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mCrimes.size());
            }
        });
    }

    private void updateButtonsVision() {
        if(mViewPager.getCurrentItem() == 0)
            mToStartPageButton.setEnabled(false);
        else
            mToStartPageButton.setEnabled(true);

        if(mViewPager.getCurrentItem() == mCrimes.size() - 1)
            mToEndPageButton.setEnabled(false);
        else
            mToEndPageButton.setEnabled(true);
    }
}

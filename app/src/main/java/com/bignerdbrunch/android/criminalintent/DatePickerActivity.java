package com.bignerdbrunch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;
import java.util.UUID;

public class DatePickerActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        Date crimeDate = (Date) getIntent().getSerializableExtra(EXTRA_CRIME_DATE);
        return DatePickerFragment.newInstance(crimeDate);
    }

    public static final String EXTRA_CRIME_ID = "com.bignerdrunch.android.criminalintent.crime_id";
    public static final String EXTRA_CRIME_DATE = "com.bignerdrunch.android.criminalintent.crime_date";

    public static Intent newIntent(Context packageContext, Date date) {
        Intent intent = new Intent(packageContext, DatePickerActivity.class);
        intent.putExtra(EXTRA_CRIME_DATE, date);
        return intent;
    }
}

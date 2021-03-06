package com.example.amin.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeDetailActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_ID = "com.example.amin.criminalintent.crime_id";

    public static Intent newIntent(Context context, UUID crimeId){
        Intent intent = new Intent(context, CrimeDetailActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);

        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        CrimeDetailFragment crimeDetailFragment = new CrimeDetailFragment();

        crimeDetailFragment.newInstance(crimeId);

        return crimeDetailFragment;
    }
}

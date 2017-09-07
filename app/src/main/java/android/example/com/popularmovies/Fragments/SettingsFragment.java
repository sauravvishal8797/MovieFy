package android.example.com.popularmovies.Fragments;

import android.content.SharedPreferences;
import android.example.com.popularmovies.R;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by saurav on 6/9/17.
 */

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.settings_preference);
        /**SharedPreferences  sharedPreferences = getPreferenceScreen().getSharedPreferences();
        android.support.v7.preference.PreferenceScreen preferenceScreen = getPreferenceScreen();
        int count = preferenceScreen.getPreferenceCount();
        for(int i = 0; i < count; i++){
            android.support.v7.preference.Preference p  = preferenceScreen.getPreference(i);
            if(p instanceof ListPreference){
                setPreferenceSummary(p);
            }

        }*/


    }

    public void setPreferenceSummary(Preference preferenceSummary){


    }
}

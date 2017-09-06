package android.example.com.popularmovies.Fragments;

import android.example.com.popularmovies.R;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by saurav on 6/9/17.
 */

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.settings_preference);

    }
}

package com.alekhnovich.vitaliy.voalekhnovichashman;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.service.notification.NotificationListenerService;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        onSharedPreferenceChanged(sharedPreferences, getString(R.string.preferences_ghosts_added_key));
        onSharedPreferenceChanged(sharedPreferences, getString(R.string.preferences_levelone_ghosts_key));
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().
                registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().
                unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref = findPreference(key);

        switch(key)
        {
            case "ghosts_levelone":
                pref.setSummary(Integer.toString(sharedPreferences.getInt(key, 2)) + " Ghosts on Level 1");
                break;

            case "ghosts_perlevel":
                pref.setSummary("Add " + Integer.toString(sharedPreferences.getInt(key, 2)) + " Ghosts at each level.");
                break;

            default:
                pref.setSummary(sharedPreferences.getString(key, ""));
        }
    }

}

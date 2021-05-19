package com.aemiralfath.moviemade.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.aemiralfath.moviemade.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}
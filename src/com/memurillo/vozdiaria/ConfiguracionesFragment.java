package com.memurillo.vozdiaria;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class ConfiguracionesFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}

package com.memurillo.vozdiaria;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ConfiguracionesActivity extends PreferenceActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new ConfiguracionesFragment())
                .commit();
	}
}

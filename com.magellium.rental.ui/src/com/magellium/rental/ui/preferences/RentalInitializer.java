package com.magellium.rental.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.magellium.rental.ui.RentalUIActivator;

public class RentalInitializer extends AbstractPreferenceInitializer {

	public RentalInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {

		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();
		
		store.setDefault(RentalPreference.CUSTOM_COLOR, StringConverter.asString(new RGB(255, 0, 0)));
		store.setDefault(RentalPreference.RENTAL_COLOR, StringConverter.asString(new RGB(0, 255, 0)));
		store.setDefault(RentalPreference.OBJECT_COLOR, StringConverter.asString(new RGB(0, 0, 255)));

	}

}

package com.magellium.rental.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.magellium.rental.ui.RentalUIActivator;

public class RentalDefaultValuesInitializer extends AbstractPreferenceInitializer {

	public RentalDefaultValuesInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {

		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();
		
		store.setDefault(DefaultRentalPreferences.CUSTOM_COLOR, StringConverter.asString(new RGB(255, 0, 0)));
		store.setDefault(DefaultRentalPreferences.RENTAL_COLOR, StringConverter.asString(new RGB(0, 255, 0)));
		store.setDefault(DefaultRentalPreferences.OBJECT_COLOR, StringConverter.asString(new RGB(0, 0, 255)));

		// combo fiel editor : on chosit sa valeur par défaut
		store.setDefault(RentalPreferences.PREF_PALETTE, "com.magellium.rental.ui.palette2");
	}

}

package com.magellium.rental.ui.palette;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.magellium.rental.ui.RentalUIActivator;
import com.magellium.rental.ui.preferences.DefaultRentalPreferences;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;

public class DefaultPalette implements IColorProvider {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {

		IPreferenceStore preferenceStore = RentalUIActivator.getDefault().getPreferenceStore();
		if (element instanceof Customer) {
			return getColor(preferenceStore.getString(DefaultRentalPreferences.CUSTOM_COLOR));
		}
		else if (element instanceof RentalObject) {
			return getColor(preferenceStore.getString(DefaultRentalPreferences.OBJECT_COLOR));
		}
		else if (element instanceof Rental) {
			return getColor(preferenceStore.getString(DefaultRentalPreferences.RENTAL_COLOR));
		}
		
		return Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);	
		
	}

	private Color getColor(String pStringColor ) {
		
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		
		Color col = colorRegistry.get(pStringColor);
		
		if (col == null) {
			colorRegistry.put(pStringColor, StringConverter.asRGB(pStringColor));
			col = colorRegistry.get(pStringColor);
		}
		return col;
		
	}
	
	@Override
	public Color getBackground(Object element) {
		
		return Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
		
	}
}

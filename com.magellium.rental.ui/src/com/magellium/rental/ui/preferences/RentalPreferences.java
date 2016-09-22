package com.magellium.rental.ui.preferences;

import java.util.Map;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.magellium.rental.ui.PaletteDesc;
import com.magellium.rental.ui.RentalUIActivator;

public class RentalPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String PREF_PALETTE = "PALETTE";
	
	public RentalPreferences() {
		
		super(GRID);
		
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Rental Preferences");
		
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		
		Map<String, PaletteDesc> palettes = RentalUIActivator.getDefault().getPaletteManager();
		
		String[][] comboValues = new String[palettes.size()][2];
		
		int loop = 0;
		for (PaletteDesc lPaletteDesc : palettes.values()) {
			comboValues[loop][0] = lPaletteDesc.getName();
			comboValues[loop][1] = lPaletteDesc.getId();
			loop++;
		}
		
		addField(new ComboFieldEditor(PREF_PALETTE, "Palette :", comboValues, getFieldEditorParent()));

	}

}

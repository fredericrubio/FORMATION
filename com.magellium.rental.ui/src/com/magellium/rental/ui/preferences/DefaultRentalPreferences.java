package com.magellium.rental.ui.preferences;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.magellium.rental.ui.RentalUIActivator;

public class DefaultRentalPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String CUSTOM_COLOR = "C_COLOR";
	public static final String RENTAL_COLOR = "L_COLOR";
	public static final String OBJECT_COLOR = "O_COLOR";
	
	public DefaultRentalPreferences() {
		
		super(GRID);
		
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Default Rental Preferences");
		
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {

		addField(new ColorFieldEditor(CUSTOM_COLOR, "Couleur Client", getFieldEditorParent()));
		addField(new ColorFieldEditor(RENTAL_COLOR, "Couleur Location", getFieldEditorParent()));
		addField(new ColorFieldEditor(OBJECT_COLOR, "Couleur Objet", getFieldEditorParent()));

	}

}

package com.magellium.rental.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.internal.win32.GESTURECONFIG;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin {

	
	// Constants to manage object images in registry. Constant values are path to icons
		public static final String IMG_AGENCY = "icons/Agency.png";
		public static final String IMG_CUSTOMER = "icons/Customers.png";
		public static final String IMG_RENTAL = "icons/Rentals.png";
		public static final String IMG_RENTAL_OBJECT = "icons/RentalObjects.png";
		public static final String IMG_COLLAPSE_ALL = "icons/collapseall.gif";
		public static final String IMG_EXPAND_ALL = "icons/expandall.gif";

	// The plug-in ID
	public static final String PLUGIN_ID = "com.magellium.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUIActivator plugin;
	
	private Map<String, PaletteDesc> paletteManager = new HashMap<String, PaletteDesc>();
	
	public Map<String, PaletteDesc> getPaletteManager() {
		return paletteManager;
	}

	/**
	 * The constructor
	 */
	public RentalUIActivator() {
	}

	private void getConfigurationElt() {
		
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		
		for (IConfigurationElement elt : reg.getConfigurationElementsFor("org.eclipse.ui.views")) { // analogie avec le fichier xml
			
			if (elt.getName().equals("view")) {
				System.out.println("Plugin: " + elt.getNamespaceIdentifier() + "  Vue: " + elt.getAttribute("id")); // champ du configuration element (cf. ficher xml)
			}
			
		}
	}
	
	private void readPalette() {

		IExtensionRegistry reg = Platform.getExtensionRegistry();

		
		for (IConfigurationElement elt : reg.getConfigurationElementsFor("com.magellium.rental.ui.palette")) { 
			
				try {
					
					PaletteDesc lPaletteDesc = new PaletteDesc();
					lPaletteDesc.setId(elt.getAttribute("id"));
					lPaletteDesc.setName(elt.getAttribute("name"));
					
					IColorProvider lColorProvider;
					lColorProvider = (IColorProvider) elt.createExecutableExtension("paletteClass");
					lPaletteDesc.setColorProvider(lColorProvider);
					
//					System.out.println(lPaletteDesc.toString());
					
					paletteManager.put(lPaletteDesc.getId(), lPaletteDesc);
					
				} catch (InvalidRegistryObjectException | CoreException e) {
					e.printStackTrace();
				}
				
		}

	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		readPalette();
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUIActivator getDefault() {
		return plugin;
	}
	
	@Override
	protected void initializeImageRegistry(ImageRegistry reg)
	{
		Bundle b = FrameworkUtil.getBundle(getClass());

		// Then fill the values...
		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_RENTAL_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL_OBJECT)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
		reg.put(IMG_COLLAPSE_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_COLLAPSE_ALL)));
		reg.put(IMG_EXPAND_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_EXPAND_ALL)));

	}

}

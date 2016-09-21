package com.magellium.rental.ui.views;

import java.util.Collection;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.magellium.rental.ui.RentalUIActivator;
import com.magellium.rental.ui.preferences.RentalPreference;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider {

	private class Node {

		static public final String CUSTOMER = "Clients";
		static public final String LOCATION = "Locations";
		static public final String OBJECT = "Objects à louer";

		String label;
		RentalAgency rentalAgency;

		public Node(String pNode, RentalAgency pAgency) {
			label = pNode;
			rentalAgency = pAgency;
		}

		Object[] getChildren() {

			if (label == CUSTOMER) {
				return rentalAgency.getCustomers().toArray();
			} else if (label == LOCATION) {
				return rentalAgency.getRentals().toArray();
			} else if (label == OBJECT) {
				return rentalAgency.getObjectsToRent().toArray();
			}
			return null;

		}

		public String toString() {
			return label;
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {

		if (inputElement instanceof Collection<?>)
			return ((Collection<?>) inputElement).toArray();
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {

		if (parentElement instanceof RentalAgency) {
			RentalAgency a = (RentalAgency) parentElement;
			return new Node[] { new Node(Node.CUSTOMER, a), new Node(Node.LOCATION, a), new Node(Node.OBJECT, a) };
		}
		else if (parentElement instanceof Node) {
			return ((Node) parentElement).getChildren();
		}

		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {

		return (element instanceof RentalAgency) || (element instanceof Node);
		
	}

	@Override
	public String getText(Object element) {

		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		} else if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		} else if (element instanceof RentalObject ) {
			return ((RentalObject) element).getName();
		}
		return super.getText(element);

	}

	@Override
	public Color getForeground(Object element) {

		IPreferenceStore preferenceStore = RentalUIActivator.getDefault().getPreferenceStore();
		if (element instanceof Customer) {
			return getColor(preferenceStore.getString(RentalPreference.CUSTOM_COLOR));
		}
		else if (element instanceof RentalObject) {
			return getColor(preferenceStore.getString(RentalPreference.OBJECT_COLOR));
		}
		else if (element instanceof Rental) {
			return getColor(preferenceStore.getString(RentalPreference.RENTAL_COLOR));
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
		
		return Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
		
	}
	
	@Override
	public Image getImage(Object element) {
		
		if (element instanceof RentalAgency) {
			return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_AGENCY);
		} else if (element instanceof Customer) {
			return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_CUSTOMER);
		} else if (element instanceof RentalObject ) {
			return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_RENTAL_OBJECT);
		}
		
		return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_AGENCY);
		
	}

}

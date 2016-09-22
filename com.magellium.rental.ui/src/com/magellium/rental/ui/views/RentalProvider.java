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
import com.magellium.rental.ui.preferences.DefaultRentalPreferences;
import com.magellium.rental.ui.preferences.RentalPreferences;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider {

	public class Node {

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			result = prime * result + ((rentalAgency == null) ? 0 : rentalAgency.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			if (rentalAgency == null) {
				if (other.rentalAgency != null)
					return false;
			} else if (!rentalAgency.equals(other.rentalAgency))
				return false;
			return true;
		}

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

		private RentalProvider getOuterType() {
			return RentalProvider.this;
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

		String lID = preferenceStore.getString(RentalPreferences.PREF_PALETTE);
		IColorProvider lColorProvider = RentalUIActivator.getDefault().getPaletteManager().get(lID).getColorProvider();
		return lColorProvider.getForeground(element);		

//		if (element instanceof Customer) {			
//			return getColor(preferenceStore.getString(DefaultRentalPreferences.CUSTOM_COLOR));
//		}
//		else if (element instanceof RentalObject) {
//			return getColor(preferenceStore.getString(DefaultRentalPreferences.OBJECT_COLOR));
//		}
//		else if (element instanceof Rental) {
//			return getColor(preferenceStore.getString(DefaultRentalPreferences.RENTAL_COLOR));
//		}
//		
//		return Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);	
		
	}

	
	@Override
	public Color getBackground(Object element) {
		
		IPreferenceStore preferenceStore = RentalUIActivator.getDefault().getPreferenceStore();

		String lID = preferenceStore.getString(RentalPreferences.PREF_PALETTE);
		IColorProvider lColorProvider = RentalUIActivator.getDefault().getPaletteManager().get(lID).getColorProvider();
		return lColorProvider.getBackground(element);		

//		return Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
		
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

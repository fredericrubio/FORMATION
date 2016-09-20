package com.magellium.rental.ui.views;

import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider {

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

}

package com.magellium.rental.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;

public class RentalDataView extends ViewPart {

	Label rentedObjectLabel ; 
	Label customerName; 
	
	public RentalDataView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		//Premier champ
		rentedObjectLabel = new Label(infoGroup, SWT.BORDER);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		
		// Second champ : "loué à"
		Label rentedTo = new Label(infoGroup, SWT.BORDER);
		rentedTo.setText("Louré à :");
		
		// Troisième champ : loueur
		customerName = new Label(infoGroup, SWT.BORDER);
		
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));
	}

	/**
	 * Assignation des valeurs affichées
	 * 
	 * @param r : location à afficher
	 */
	private void setRental(Rental r) {

		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerName.setText(r.getCustomer().getDisplayName());
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}

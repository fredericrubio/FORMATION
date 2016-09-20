package com.magellium.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Address;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.opcoach.training.rental.helpers.RentalAgencyGenerator;

public class RentalDataTreeView extends ViewPart {

	TreeViewer treeViewer;
	
	public RentalDataTreeView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		treeViewer = new TreeViewer(parent);
		
		RentalProvider lRentalProvider = new RentalProvider();
		
		treeViewer.setLabelProvider(lRentalProvider);
		treeViewer.setContentProvider(lRentalProvider);
		
		Collection<RentalAgency>	lCollection = new ArrayList<RentalAgency>();
		lCollection.add(RentalCoreActivator.getAgency());
		
		RentalAgency lAgency = RentalAgencyGenerator.createSampleAgency();
		lAgency.setName("Lyon");
		lCollection.add(lAgency);	
		
		treeViewer.setInput(lCollection);
		
		treeViewer.expandAll();

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}

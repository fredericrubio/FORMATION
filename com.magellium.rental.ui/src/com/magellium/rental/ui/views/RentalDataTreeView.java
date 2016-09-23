package com.magellium.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.magellium.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.Address;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.opcoach.training.rental.helpers.RentalAgencyGenerator;

/**
 * 
 * @author fro
 * @deprecated Utilisation désormais de E4
 *
 */
public class RentalDataTreeView extends ViewPart implements IPropertyChangeListener  {

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
		
		// gestion de la sélection
		getSite().setSelectionProvider(treeViewer);
		
		// autoriser le popup
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuManager, treeViewer);
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		
		super.init(site);
		
		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}

	@Override
	public void dispose() {
		
		super.dispose();
		
		RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		
	}
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		
		if (treeViewer != null) {
			treeViewer.refresh();
		}
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	
}

package com.magellium.rental.e4.views;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.magellium.rental.core.RentalCoreActivator;
import com.magellium.rental.ui.views.RentalProvider;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.helpers.RentalAgencyGenerator;

public class RentalDataTreeView {

//	@Inject 
//	private ESelectionService selectionService;
	
	TreeViewer treeViewer;
	
	
	public RentalDataTreeView() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void createPartControl(Composite parent, final ESelectionService selectionService) {
		
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
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				
				selectionService.setSelection(selection.size() > 1 ? selection.toArray() : selection.getFirstElement());
				
			}
		});
		
		// autoriser le popup
//E34 : gestion popup		
//		MenuManager menuManager = new MenuManager();
//		Menu menu = menuManager.createContextMenu(treeViewer.getControl());
//		treeViewer.getControl().setMenu(menu);
//E34		getSite().registerContextMenu(menuManager, treeViewer);
	}

//E34 : gestion sélection à revoir 
//	@Override
//	public void init(IViewSite site) throws PartInitException {
//		
//		super.init(site);
//		
//		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
//	}
//
//	@Override
//	public void dispose() {
//		
//		super.dispose();
//		
//		RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
//		
//	}
//	@Override
//	public void propertyChange(PropertyChangeEvent event) {
//		
//		if (treeViewer != null) {
//			treeViewer.refresh();
//		}
//		
//	}

	@Focus
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	
}

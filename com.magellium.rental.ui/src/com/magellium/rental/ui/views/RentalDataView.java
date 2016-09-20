package com.magellium.rental.ui.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;
import org.eclipse.swt.widgets.DateTime;

public class RentalDataView extends ViewPart implements ISelectionListener {

	Label rentedObjectLabel ; 
	Label customerName; 
	private Group groupDates;
	private Label labelDateTo;
	private Label labelDateFrom;
	
	public RentalDataView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		GridData gd_infoGroup = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_infoGroup.widthHint = 137;
		infoGroup.setLayoutData(gd_infoGroup);
		
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
		
		groupDates = new Group(parent, SWT.NONE);
		groupDates.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		groupDates.setText("Dates de kication");
		groupDates.setLayout(new GridLayout(2, false));
		
		Label lblDu = new Label(groupDates, SWT.NONE);
		lblDu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblDu.setSize(20, 15);
		lblDu.setText("du :");
		
		labelDateFrom = new Label(groupDates, SWT.NONE);
		labelDateFrom.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		labelDateFrom.setBounds(0, 0, 55, 15);
		labelDateFrom.setText("New Label");
		
		Label dateTo = new Label(groupDates, SWT.NONE);
		dateTo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		dateTo.setText("au :");
		
		labelDateTo = new Label(groupDates, SWT.NONE);
		labelDateTo.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		labelDateTo.setBounds(0, 0, 55, 15);
		labelDateTo.setText("New Label");
		
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
		
		labelDateFrom.setText(r.getStartDate().toString());
		labelDateTo.setText(r.getEndDate().toString());
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * La sélection a changé
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		if (selection instanceof IStructuredSelection) {
			Object lObject = ((IStructuredSelection) selection).getFirstElement();
			
			if (lObject instanceof Rental) {
				setRental((Rental) lObject);
			}
		}
	}
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		
		super.init(site);
		
		site.getPage().addSelectionListener(this);
	}
	
	@Override
	public void dispose() {
		
		super.dispose();
		
		getSite().getPage().removeSelectionListener(this);
	}
}

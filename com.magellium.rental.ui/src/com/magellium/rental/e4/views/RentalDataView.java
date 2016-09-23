package com.magellium.rental.e4.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

public class RentalDataView {

	@Inject
	private RentalAgency agency;
	
	Label rentedObjectLabel ; 
	Label customerName; 
	private Group groupDates;
	private Label labelDateTo;
	private Label labelDateFrom;
	
	public RentalDataView() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void createPartControl(Composite parent, RentalAgency agency) {

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
		
		// Second champ : "lou� �"
		Label rentedTo = new Label(infoGroup, SWT.BORDER);
		rentedTo.setText("Lour� � :");
		
		// Troisi�me champ : loueur
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
		
		setRental(agency.getRentals().get(0));
//		setRental(RentalCoreActivator.getAgency().getRentals().get(0));
		
		// Drag'n drop
		setLabelAsDragSource(rentedObjectLabel);
		setLabelAsDragSource(customerName);
	}

	/**
	 * Assignation des valeurs affich�es
	 * 
	 * @param r : location � afficher
	 */
	private void setRental(Rental r) {

		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerName.setText(r.getCustomer().getDisplayName());
		
		labelDateFrom.setText(r.getStartDate().toString());
		labelDateTo.setText(r.getEndDate().toString());
	}
	
	@Focus
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	@Inject @Optional
	public void selectionChanged(@Named(IServiceConstants.ACTIVE_SELECTION) Rental r) {
		
		if (r != null) {
			setRental(r);
		}
		
	}
	
//E34 : gestion s�lection � revoir 
//	/**
//	 * La s�lection a chang�
//	 */
//	@Override
//	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
//
//		if (selection instanceof IStructuredSelection) {
//			Object lObject = ((IStructuredSelection) selection).getFirstElement();
//			
//			if (lObject instanceof Rental) {
//				setRental((Rental) lObject);
//			}
//		}
//	}
//	
//	@Override
//	public void init(IViewSite site) throws PartInitException {
//		
//		super.init(site);
//		
//		site.getPage().addSelectionListener(this);
//	}
//	
//	@Override
//	public void dispose() {
//		
//		super.dispose();
//		
//		getSite().getPage().removeSelectionListener(this);
//	}
//	
	public void setLabelAsDragSource(final Label label) {
		
		DragSource source = new DragSource(label,  DND.DROP_MOVE | DND.DROP_COPY);
		
		source.setTransfer(new Transfer[] {TextTransfer.getInstance()});
		
		source.addDragListener(new DragSourceAdapter(){
			@Override
			public void dragSetData(DragSourceEvent event) {
				
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = label.getText();
				}
				
			}
		});
		
	}
}

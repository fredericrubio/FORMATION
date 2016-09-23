package com.magellium.rental.e4.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.adapter.Adapter;
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

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Customer;

public class CustomerPropertyView {

	private Label customerNameValue;
	private Label customerNameLabel;
	
	public CustomerPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		GridData gd_infoGroup = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_infoGroup.widthHint = 137;
		infoGroup.setLayoutData(gd_infoGroup);
		
		infoGroup.setText("Customer Info");
		infoGroup.setLayout(new GridLayout(2, false));
		
		// Second champ : "loué à"
		Label customerName;
		customerNameLabel = new Label(infoGroup, SWT.BORDER);
		customerNameLabel.setText("Customer name:");
		
		// Troisième champ : loueur
		customerNameValue = new Label(infoGroup, SWT.BORDER);
		customerNameValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		setCustomerName(RentalCoreActivator.getAgency().getRentals().get(0).getCustomer());
		
		// Drag'n drop
		setLabelAsDragSource(customerNameValue);
	}

	/**
	 * Assignation des valeurs affichées
	 * 
	 * @param r : location à afficher
	 */
	private void setCustomerName(Customer r) {

		if (r != null)
			customerNameValue.setText(r.getDisplayName());
		
	}
	
	@Focus
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Inject @Optional
	public void selectionChanged(@Named(IServiceConstants.ACTIVE_SELECTION) Object o, Adapter adapter) {
		
		if (o == null) {
			return;
		}
		
		Customer c = adapter.adapt(o, Customer.class); 
		setCustomerName(c);
		
	}

	@Inject @Optional
	public void selectionChanged(@Named(IServiceConstants.ACTIVE_SELECTION) Object[] oArray) {
		
		if (oArray != null ) {
			for (Object o : oArray) {
				if (o instanceof Customer)
					setCustomerName((Customer) o);			
			}
		}
		
	}
	
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

package com.magellium.rental.ui.views;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
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
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import org.eclipse.swt.widgets.DateTime;

public class CustomerPropertyView extends ViewPart implements ISelectionListener {

	private Label customerNameValue;
	private Label customerNameLabel;
	
	public CustomerPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
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
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * La sélection a changé
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		if (selection.isEmpty()) {
			return;
		}
		
		if (selection instanceof IStructuredSelection) {
			Object lObject = ((IStructuredSelection) selection).getFirstElement();
			
			Customer customer = Platform.getAdapterManager().getAdapter(lObject, Customer.class);
			setCustomerName(customer);
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

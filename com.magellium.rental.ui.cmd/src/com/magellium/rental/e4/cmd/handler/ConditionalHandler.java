package com.magellium.rental.e4.cmd.handler;

import javax.inject.Named;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.URLTransfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.magellium.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.Customer;

public class ConditionalHandler {

	@Execute
//	public Object execute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional Object object)  {
	public Object execute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional Customer customer)  {
		
	 	Clipboard clipboard = new Clipboard(Display.getCurrent());
	 	
		String customerName = customerName = customer.getDisplayName();
	 			
		Image lAgencyImage = RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_AGENCY);
		
		String rtfData = "{\\rtf1\\b\\i " + customerName + "}";
		
		String request = "http://www.google.fr?query=" + customerName;
		
		TextTransfer textTransfer = TextTransfer.getInstance();
		RTFTransfer rtfTransfer = RTFTransfer.getInstance();
		ImageTransfer imgTransfer = ImageTransfer.getInstance();
		URLTransfer urlTransfer = URLTransfer.getInstance();
		
		Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer, imgTransfer, urlTransfer};
		
		Object[] data = new Object[]{customerName, rtfData, lAgencyImage.getImageData(), request};
		
		clipboard.setContents(data, transfers, DND.CLIPBOARD);
		clipboard.dispose();
		
		return null;
	}

	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional Object object) {
		
		return (object instanceof Customer);
		
	}
}

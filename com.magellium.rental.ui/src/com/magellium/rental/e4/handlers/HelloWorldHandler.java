package com.magellium.rental.e4.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class HelloWorldHandler {

	@Execute
	public Object execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell pShell) {

		MessageDialog.openInformation(pShell, "PluginID", "Hello World!");


		return null;
	}

}

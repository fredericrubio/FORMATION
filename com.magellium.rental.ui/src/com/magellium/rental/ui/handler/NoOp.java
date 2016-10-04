package com.magellium.rental.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;

public class NoOp extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
		
		Command command = service.getCommand("com.magellium.rental.ui.rental.helloworldcommand");
				
//		Command command = event.getCommand();
	     boolean oldValue = HandlerUtil.toggleCommandState(command);
	     
		return null;
	}

}

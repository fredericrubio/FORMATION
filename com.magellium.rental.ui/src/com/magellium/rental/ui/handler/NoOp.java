package com.magellium.rental.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RegistryToggleState;
import org.eclipse.core.commands.State;

public class NoOp extends AbstractHandler{

	static boolean lStatus = true;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
		
		Command command = service.getCommand("com.magellium.rental.ui.rental.helloworldcommand");
//		State state = command.getState(RegistryToggleState.STATE_ID);
		lStatus = !lStatus;
//		state.setValue(Boolean.valueOf(lStatus));
		State lState2 = command.getState("org.eclipse.ui.commands.toggleState");
		lState2.setValue(Boolean.valueOf(lStatus));
				
/*		Command command2 = event.getCommand();
		State lState2 = command2.getState("org.eclipse.ui.commands.toggleState");
		
		lState2.setValue(lStatus);
		*/
		
//		boolean oldValue = HandlerUtil.toggleCommandState(command);
	     
		return null;
	}

}

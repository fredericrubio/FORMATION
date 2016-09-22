package com.magellium.rental.rcp;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.actions.ActionFactory;

public class RentalActionBarAdvisor extends ActionBarAdvisor {
	private IAction preferencesAction;
	private IAction quitAction;

	public RentalActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		// TODO Auto-generated method stub
		super.fillMenuBar(menuBar);
		
		MenuManager menuManagerFile = new MenuManager("New MenuManager");
		menuManagerFile.setMenuText("File");
		menuBar.add(menuManagerFile);
		menuManagerFile.add(quitAction);
		
		MenuManager menuManagerWindow = new MenuManager("New MenuManager");
		menuManagerWindow.setMenuText("Window");
		menuBar.add(menuManagerWindow);
		menuManagerWindow.add(preferencesAction);
	}
	
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		// TODO Auto-generated method stub
		super.fillCoolBar(coolBar);
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {

		super.makeActions(window);
		{
			preferencesAction = ActionFactory.PREFERENCES.create(window);
			register(preferencesAction);
		}
		{
			quitAction = ActionFactory.QUIT.create(window);
			register(quitAction);
		}
	}
}

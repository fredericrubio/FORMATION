package com.magellium.rental.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class RentalPerspective implements IPerspectiveFactory {

	public static String PERSPECTIVE_ID = "com.magellium.rental.ui.perspectives.RentalPerspective";
	
	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		String editorArea = layout.getEditorArea();
		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);
		layout.addView("com.magellium.rental.ui.view", IPageLayout.LEFT, 0.5f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("com.magellium.rental.ui.view1", IPageLayout.RIGHT, 0.5f, "com.magellium.rental.ui.view");
		layout.addView("com.magellium.rental.ui.customerProperty", IPageLayout.BOTTOM, 0.5f, "com.magellium.rental.ui.view1");
	}

	/**
	 * Add fast views to the perspective.
	 */
	private void addFastViews(IPageLayout layout) {
	}

	/**
	 * Add view shortcuts to the perspective.
	 */
	private void addViewShortcuts(IPageLayout layout) {
	}

	/**
	 * Add perspective shortcuts to the perspective.
	 */
	private void addPerspectiveShortcuts(IPageLayout layout) {
	}

}

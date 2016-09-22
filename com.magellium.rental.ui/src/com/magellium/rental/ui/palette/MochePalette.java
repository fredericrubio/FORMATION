package com.magellium.rental.ui.palette;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class MochePalette implements IColorProvider {

	public MochePalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		Display.getCurrent().getSystemColor(SWT.COLOR_DARK_YELLOW);
		return null;
	}

}

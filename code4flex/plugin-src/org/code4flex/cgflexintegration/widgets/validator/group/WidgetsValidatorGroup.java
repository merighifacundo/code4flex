package org.code4flex.cgflexintegration.widgets.validator.group;

import java.util.HashMap;

import org.code4flex.cgflexintegration.widgets.validator.group.specific.Validator;
import org.eclipse.swt.widgets.Control;

public interface WidgetsValidatorGroup {

	public void addControl(Control control,Validator validatorClass);
	
	public void removeControl(Control control);
	
	public boolean validateControls();
	
	public void clearControlsToValidate();
	
	public HashMap<Control,String> giveMeReasons();
	
}

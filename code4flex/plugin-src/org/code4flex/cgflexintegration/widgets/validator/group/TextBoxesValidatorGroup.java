package org.code4flex.cgflexintegration.widgets.validator.group;

import java.util.HashMap;
import java.util.Map.Entry;

import org.code4flex.cgflexintegration.widgets.validator.group.specific.Validator;
import org.eclipse.swt.widgets.Control;


public class TextBoxesValidatorGroup implements WidgetsValidatorGroup {

	private HashMap<Control, Validator> controlValidatorList = new HashMap<Control,Validator>();
	private HashMap<Control, String> reasons = new HashMap<Control, String>();
	
	public void addControl(Control control, Validator validatorClass) {
		controlValidatorList.put(control, validatorClass);
		
	}

	public void clearControlsToValidate() {
		controlValidatorList.clear();
		
	}

	public boolean validateControls() {
		boolean okValidators = true;
		reasons.clear();
		for (Entry<Control, Validator> entry : controlValidatorList.entrySet()) {
			if(!entry.getValue().validate(entry.getKey()) ){
				
				okValidators = false;
				reasons.put(entry.getKey(), entry.getValue().getReason());
			}
				
		}
		return okValidators;
	}

	public HashMap<Control, String> giveMeReasons() {
		return reasons;
	}

	public void removeControl(Control control) {
		controlValidatorList.remove(control);
		
	}

}

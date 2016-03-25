package org.code4flex.cgflexintegration.widgets.validator.group.specific;

import org.eclipse.swt.widgets.Control;

public interface Validator {

	
	public boolean validate(Control control);
	public String getReason();
}

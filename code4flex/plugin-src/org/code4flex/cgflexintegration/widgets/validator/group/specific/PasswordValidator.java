package org.code4flex.cgflexintegration.widgets.validator.group.specific;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class PasswordValidator extends TextBoxValidator implements Validator {
	private Text confirmationbox = null;
	
	public PasswordValidator(Text conf) {
		confirmationbox = conf;
	}
	
	public String getReason() {
		return "password does not match";
	}

	public boolean validate(Control control) {
		if(super.validate(control) && super.validate(confirmationbox)){
			String passwordText = ((Text) control).getText();
			String passwordTextConf = confirmationbox.getText();
			return passwordText.equalsIgnoreCase(passwordTextConf);
			
		}
		return false;
	}

}

package org.code4flex.cgflexintegration.widgets.validator.utils;

import org.code4flex.cgflexintegration.widgets.validator.group.specific.PasswordValidator;
import org.code4flex.cgflexintegration.widgets.validator.group.specific.TextBoxValidator;
import org.code4flex.cgflexintegration.widgets.validator.group.specific.Validator;
import org.eclipse.swt.widgets.Text;


public class ValidatorUtils {

	
	public static Validator createValidator(String regExp, String notAcepted) {
		TextBoxValidator valid = new TextBoxValidator();
		if(notAcepted != null)
			valid.addNotAceptedValue(notAcepted);
		if(regExp != null)
			valid.setRegularExpresionToValidate(regExp);
		return valid;
	}

	public static Validator createDBPasswordValidator(Text txtPasswordConf) {
		PasswordValidator passValidator = new PasswordValidator(txtPasswordConf);
		
		return passValidator;
	}

	
}

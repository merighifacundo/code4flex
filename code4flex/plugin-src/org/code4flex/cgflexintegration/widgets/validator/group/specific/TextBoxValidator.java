package org.code4flex.cgflexintegration.widgets.validator.group.specific;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class TextBoxValidator implements Validator {

	
	private static final String reasonsWhy[] = {"Null or empty Value.","Regular Expresion validation.","Default value must be changed or invalid value."};
	private static final int EMPTY_REASON = 0;
	private static final int REGULAR_EXPRESION_REASON = 1;
	private static final int NOT_ACEPTED_VALUE = 2;
	
	private String regularExpresionToValidate;
	private List<String> notAceptedValues = new ArrayList<String>();
	private int validatorReason = -1;
	
	public boolean validate(Control control) {
		Text myControlToValidate = (Text) control;
		String textToValidate = myControlToValidate.getText();
		if(textToValidate == null || "".equalsIgnoreCase(textToValidate)){
			validatorReason = EMPTY_REASON;
			return false;
		}
		for (String notAcepted : notAceptedValues) {
			if(notAcepted.equalsIgnoreCase(textToValidate)){
				validatorReason = NOT_ACEPTED_VALUE;
				return false;
			}	
				
			
		}
		if(regularExpresionToValidate!=null && !"".equalsIgnoreCase(regularExpresionToValidate)){
			if(!validateWithRegExp(textToValidate)){
				validatorReason = REGULAR_EXPRESION_REASON;
				return false;
			}
		}
		return true;
	}
	
	
	private boolean validateWithRegExp(String controlText){
	      //Set the email pattern string
	      Pattern p = Pattern.compile(controlText);

	      //Match the given string with the pattern
	      Matcher m = p.matcher(controlText);

	      //check whether match is found 
	      return m.matches();
		
		
	}

	public void addNotAceptedValue(String notAcepted){
		
		notAceptedValues.add(notAcepted);
		
	}

	public void setRegularExpresionToValidate(String regularExpresionToValidate) {
		this.regularExpresionToValidate = regularExpresionToValidate;
	}


	public String getReason() {
		if(validatorReason>-1){
			
			return reasonsWhy[validatorReason];
			
		}
		return null;
	}
	
	
	
	
	
}

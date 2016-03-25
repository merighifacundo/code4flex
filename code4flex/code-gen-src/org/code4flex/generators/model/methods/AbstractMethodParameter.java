package org.code4flex.generators.model.methods;

public abstract class AbstractMethodParameter {

	protected String parameterName;
	protected String parameterType;

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterType() {

		return parameterType;

	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;

	}
}

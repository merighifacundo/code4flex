package org.code4flex.generators;

public class EntryPointCairngormConfiguration {

	private String serviceNamespace;
	private String rdsServiceName;
	private String controllerNamespace;
	private String routerControllerName;
	
	public String getServiceNamespace() {
		return serviceNamespace;
	}
	public void setServiceNamespace(String serviceNamespace) {
		this.serviceNamespace = serviceNamespace;
	}
	public String getRdsServiceName() {
		return rdsServiceName;
	}
	public void setRdsServiceName(String rdsServiceName) {
		this.rdsServiceName = rdsServiceName;
	}
	public String getControllerNamespace() {
		return controllerNamespace;
	}
	public void setControllerNamespace(String controllerNamespace) {
		this.controllerNamespace = controllerNamespace;
	}
	public String getRouterControllerName() {
		return routerControllerName;
	}
	public void setRouterControllerName(String routerControllerName) {
		this.routerControllerName = routerControllerName;
	}
}

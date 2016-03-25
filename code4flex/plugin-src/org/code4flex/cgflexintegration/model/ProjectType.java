package org.code4flex.cgflexintegration.model;

public class ProjectType {

	public static final int PHP_CAIRNGORM_PROJECT = 0;
	public static final int PHP_FLEX_MATE_PROJECT = 1;
	public static final int JSEAM_CAIRNGORM_PROJECT = 2;
	public static final int JSEAM_FLEX_MATE_PROJECT = 3;

	private int projectType;

	public int getProjectType() {
		return projectType;
	}

	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}
	
	
}

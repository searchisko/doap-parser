/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.searchisko.doap.model.person;

/**
* @author Lukas Vlcek (lvlcek@redhat.com)
*/
public enum PeopleInRole {
	LEAD("lead"),
	MAINTAIER("maintainer"),
	DEVELOPER("developer"),
	DOCUMENTER("documenter"),
	TRANSLATOR("translator"),
	TESTER("tester"),
	HELPER("helper");

	private String roleName;
	PeopleInRole(String role) {
		roleName = role;
	}

	public String getRoleName() {
		return this.roleName;
	}

}

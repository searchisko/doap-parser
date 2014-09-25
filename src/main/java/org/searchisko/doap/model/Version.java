/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */

package org.searchisko.doap.model;

import com.viceversatech.rdfbeans.annotations.RDF;
import com.viceversatech.rdfbeans.annotations.RDFBean;
import com.viceversatech.rdfbeans.annotations.RDFNamespaces;

/**
 * DOAP Version model.
 *
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */

@RDFNamespaces({
	"release = http://usefulinc.com/ns/doap#/release"
})
@RDFBean("release:Version")
public class Version {

	private String name;

	@RDF("release:name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

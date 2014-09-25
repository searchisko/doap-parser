/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */

package org.searchisko.doap.model;

import com.viceversatech.rdfbeans.annotations.RDF;
import com.viceversatech.rdfbeans.annotations.RDFBean;
import com.viceversatech.rdfbeans.annotations.RDFNamespaces;

import java.net.URI;

/**
 * FOAF Person model.
 *
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */

@RDFNamespaces({
	"foaf = http://xmlns.com/foaf/0.1/"
})
@RDFBean("foaf:Person")
public class Person {

	private String name;
	private URI mbox;

	@RDF("foaf:name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@RDF("foaf:mbox")
	public URI getMbox() {
		return this.mbox;
	}

	public void setMbox(URI mbox) {
		this.mbox = mbox;
	}

}

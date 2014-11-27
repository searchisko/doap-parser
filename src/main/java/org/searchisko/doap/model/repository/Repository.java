/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.searchisko.doap.model.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.viceversatech.rdfbeans.annotations.RDF;
import com.viceversatech.rdfbeans.annotations.RDFBean;
import com.viceversatech.rdfbeans.annotations.RDFNamespaces;

import java.net.URI;

/**
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */
@RDFNamespaces(
		"doap = http://usefulinc.com/ns/doap#"
)
@RDFBean("doap:AbstractRepository")
public interface Repository {

	@RDF("doap:location")
	public URI getLocation();

	@RDF("doap:browse")
	public URI getBrowse();

	@RDF("doap:anon-root")
	@JsonProperty("anon-root")
	public String getAnon_root();
}

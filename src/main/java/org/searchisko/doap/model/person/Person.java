/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */

package org.searchisko.doap.model.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viceversatech.rdfbeans.annotations.RDF;
import com.viceversatech.rdfbeans.annotations.RDFBean;
import com.viceversatech.rdfbeans.annotations.RDFNamespaces;

import java.net.URI;

/**
 * FOAF Person model.
 *
 * See <a href="http://xmlns.com/foaf/spec/">http://xmlns.com/foaf/spec/</a>.
 * See <a href="http://www.xml.com/pub/a/2004/02/04/foaf.html">http://www.xml.com/pub/a/2004/02/04/foaf.html</a>.
 *
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */

@RDFNamespaces({
	"foaf = http://xmlns.com/foaf/0.1/"
})
@RDFBean("foaf:Person")
public class Person {

	private String name;
	@JsonIgnore
	private URI mbox;

	/**
	 * See <a href="http://xmlns.com/foaf/spec/#term_name">foaf:name</a>
	 *
	 * @return name value
	 */
	@RDF("foaf:name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * See <a href="http://xmlns.com/foaf/spec/#term_mbox">foaf:mbox</a>
	 *
	 * @return mbox value
	 */
	@RDF("foaf:mbox")
	public URI getMbox() {
		return this.mbox;
	}

	public void setMbox(URI mbox) {
		this.mbox = mbox;
	}

	@JsonProperty("email")
	public String getEmail() {
		if (this.mbox == null) {
			return null;
		}
		if (this.mbox.isOpaque()) {
			return this.mbox.toString().substring("mailto:".length());
		}
		// if not opaque we do not know what to do
		return this.mbox.toString();
	}

}

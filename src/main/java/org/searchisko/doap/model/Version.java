/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */

package org.searchisko.doap.model;

import com.viceversatech.rdfbeans.annotations.RDF;
import com.viceversatech.rdfbeans.annotations.RDFBean;
import com.viceversatech.rdfbeans.annotations.RDFNamespaces;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Collection;

/**
 * DOAP Version model.
 *
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */

@RDFNamespaces({
	"doap = http://usefulinc.com/ns/doap#"
})
@RDFBean("doap:Version")
public class Version {

	private String name;
	private String created;
	private String revision;
	@JsonProperty("file-release")
	private Collection<String> file_release;

	@RDF("doap:name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@RDF("doap:created")
	public String getCreated() {
		return this.created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@RDF("doap:revision")
	public String getRevision() {
		return this.revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	@RDF("doap:file-release")
	public Collection<String> getFile_release() {
		return this.file_release;
	}

	public void setFile_release(Collection<String> file_release) {
		this.file_release = file_release;
	}
}

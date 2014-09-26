package org.searchisko.doap.model;

import com.viceversatech.rdfbeans.annotations.RDF;
import com.viceversatech.rdfbeans.annotations.RDFBean;
import com.viceversatech.rdfbeans.annotations.RDFNamespaces;

import java.net.URI;

/**
 * Created by lukas on 26/09/14.
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
}

package org.searchisko.doap.model;

/**
 * Created by lukas on 24/09/14.
 */

import com.viceversatech.rdfbeans.annotations.RDF;
import com.viceversatech.rdfbeans.annotations.RDFBean;
import com.viceversatech.rdfbeans.annotations.RDFNamespaces;

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

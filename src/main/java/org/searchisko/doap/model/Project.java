package org.searchisko.doap.model;

import com.viceversatech.rdfbeans.annotations.RDF;
import com.viceversatech.rdfbeans.annotations.RDFBean;
import com.viceversatech.rdfbeans.annotations.RDFNamespaces;
import org.codehaus.jackson.annotate.JsonProperty;

import java.net.URI;
import java.util.Collection;

/**
 * Project model based on DOAP specification: http://usefulinc.com/ns/doap
 *
 * We had to change characters in field names that contained dash to underscore.
 * For example: 'mailing-list' -> 'mailing_list'. We also changed field name 'implements' (which is Java lang
 * reserved word) to 'implements_'. For JSON serialization we added rules to override field names back to
 * names declared in the DOAP specification.
 *
 * @author lvlcek@redhat.com (Lukas Vlcek)
 */
@RDFNamespaces({
	"doap = http://usefulinc.com/ns/doap#"
})
@RDFBean("doap:Project")
public class Project {

	private String name;
	private URI homepage;
	@JsonProperty("old-homepage")
	private URI old_homepage;
	private String created;
	private String shortdesc;
	private String description;
	private String release;
	@JsonProperty("mailing-list")
	private URI mailing_list;
	private String category;
	private String license;
	private String repository;
	private String anon_root;
	private String browse;
	private String module;
	private String location;
	@JsonProperty("download-page")
	private URI download_page;
	@JsonProperty("download-mirror")
	private String download_mirror;
	private String revision;
	@JsonProperty("file-release")
	private String file_release;
	private String wiki;
	@JsonProperty("bug-database")
	private URI bug_database;
	private String screenshots;
	private Collection<Person> maintainer;
	private String developer;
	private String documenter;
	private String translator;
	private String tester;
	private String helper;
	@JsonProperty("programming-language")
	private Collection<String> programming_language;
	private String os;
	@JsonProperty("implements")
	private String implements_;
	@JsonProperty("service-endpoint")
	private String service_endpoint;
	private String language;
	private String vendor;
	private String platform;
	private String audience;
	private String blog;

	@RDF("doap:name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@RDF("doap:homepage")
	public URI getHomepage() {
		return this.homepage;
	}

	public void setHomepage(URI homepage) {
		this.homepage = homepage;
	}

	@RDF("doap:old_homepage")
	public URI getOld_homepage() {
		return this.old_homepage;
	}

	public void setOld_homepage(URI old_homepage) {
		this.old_homepage = old_homepage;
	}

	@RDF("doap:created")
	public String getCreated() {
		return this.created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@RDF("doap:shortdesc")
	public String getShortdesc() {
		return this.shortdesc;
	}

	public void setShortdesc(String shortdesc) {
		this.shortdesc = shortdesc;
	}

	@RDF("doap:description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@RDF("doap:implements")
	public String getImplements() {
		return this.implements_;
	}

	public void setImplements(String implements_) {
		this.implements_ = implements_;
	}

	//@RDF("doap:maintainer")
	public Collection<Person> getMaintainer() {
		return this.maintainer;
	}

	public void setMaintainer(Collection<Person> maintainer) {
		this.maintainer = maintainer;
	}

	@RDF("doap:programming-language")
	public Collection<String> getProgramming_language() {
		return this.programming_language;
	}

	public void setProgramming_language(Collection<String> programming_language) {
		this.programming_language = programming_language;
	}
}

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
import org.searchisko.doap.model.person.Person;
import org.searchisko.doap.model.repository.Repository;

import java.net.URI;
import java.util.Collection;

/**
 * Project DOAP model based on specification: http://usefulinc.com/ns/doap
 *
 * We had to change characters in field names that contained dash to underscore.
 * For example: 'mailing-list' -> 'mailing_list'. For JSON serialization we added
 * rules to override field names back to names declared in the DOAP specification.
 *
 * @author lvlcek@redhat.com (Lukas Vlcek)
 */
@RDFNamespaces({
	"doap = http://usefulinc.com/ns/doap#",
	"foaf = http://xmlns.com/foaf/0.1/"
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
	private Collection<Version> release;
	@JsonProperty("mailing-list")
	private URI mailing_list;
	private Collection<URI> category;
	private URI license;
	private Collection<Repository> repository;
	@JsonProperty("download-page")
	private URI download_page;
	@JsonProperty("download-mirror")
	private URI download_mirror;
	private URI wiki;
	@JsonProperty("bug-database")
	private URI bug_database;
	private URI screenshots;
	private Collection<Person> maintainer;
	private Collection<Person> developer;
	private Collection<Person> documenter;
	private Collection<Person> translator;
	private Collection<Person> tester;
	private Collection<Person> helper;
	@JsonProperty("programming-language")
	private Collection<String> programming_language;
	@JsonProperty("service-endpoint")
	private URI service_endpoint;
	private String language;
	private String platform;
	private String audience;
	private URI blog;

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

	// @RDF("doap:release")
	public Collection<Version> getRelease() {
		return this.release;
	}

	public void setRelease(Collection<Version> release) {
		this.release = release;
	}

	@RDF("doap:mailing-list")
	public URI getMailing_list() {
		return this.mailing_list;
	}

	public void setMailing_list(URI mailing_list) {
		this.mailing_list = mailing_list;
	}

	@RDF("doap:category")
	public Collection<URI> getCategory() {
		return this.category;
	}

	public void setCategory(Collection<URI> category) {
		this.category = category;
	}

	@RDF("doap:license")
	public URI getLicense() {
		return this.license;
	}

	public void setLicense(URI license) {
		this.license = license;
	}

	//@RDF("doap:repository")
	public Collection<Repository> getRepository() {
		return this.repository;
	}

	public void setRepository(Collection<Repository> repository) {
		this.repository = repository;
	}

	@RDF("doap:download-page")
	public URI getDownload_page() {
		return this.download_page;
	}

	public void setDownload_page(URI download_page) {
		this.download_page = download_page;
	}

	@RDF("doap:download-mirror")
	public URI getDownload_mirror() {
		return this.download_mirror;
	}

	public void setDownload_mirror(URI download_mirror) {
		this.download_mirror = download_mirror;
	}

	@RDF("doap:wiki")
	public URI getWiki() {
		return this.wiki;
	}

	public void setWiki(URI wiki) {
		this.wiki = wiki;
	}

	@RDF("doap:bug-database")
	public URI getBug_database() {
		return this.bug_database;
	}

	public void setBug_database(URI bug_database) {
		this.bug_database = bug_database;
	}

	@RDF("doap:screenshots")
	public URI getScreenshots() {
		return this.screenshots;
	}

	public void setScreenshots(URI screenshots) {
		this.screenshots = screenshots;
	}

//	@RDF("doap:maintainer")
	public Collection<Person> getMaintainer() {
		return this.maintainer;
	}

	public void setMaintainer(Collection<Person> maintainer) {
		this.maintainer = maintainer;
	}

	//@RDF("doap:developer")
	public Collection<Person> getDeveloper() {
		return this.developer;
	}

	public void setDeveloper(Collection<Person> developer) {
		this.developer = developer;
	}

	//@RDF("doap:documenter")
	public Collection<Person> getDocumenter() {
		return this.documenter;
	}

	public void setDocumenter(Collection<Person> documenter) {
		this.documenter = documenter;
	}

	//@RDF("doap:translator")
	public Collection<Person> getTranslator() {
		return this.translator;
	}

	public void setTranslator(Collection<Person> translator) {
		this.translator = translator;
	}

	//@RDF("doap:tester")
	public Collection<Person> getTester() {
		return this.tester;
	}

	public void setTester(Collection<Person> tester) {
		this.tester = tester;
	}

	//@RDF("doap:helper")
	public Collection<Person> getHelper() {
		return this.helper;
	}

	public void setHelper(Collection<Person> helper) {
		this.helper = helper;
	}

	@RDF("doap:programming-language")
	public Collection<String> getProgramming_language() {
		return this.programming_language;
	}

	public void setProgramming_language(Collection<String> programming_language) {
		this.programming_language = programming_language;
	}

	@RDF("doap:service-endpoint")
	public URI getService_endpoint() {
		return this.service_endpoint;
	}

	public void setService_endpoint(URI service_endpoint) {
		this.service_endpoint = service_endpoint;
	}

	@RDF("doap:language")
	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@RDF("doap:platform")
	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@RDF("doap:audience")
	public String getAudience() {
		return this.audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	@RDF("doap:blog")
	public URI getBlog() {
		return this.blog;
	}

	public void setBlog(URI blog) {
		this.blog = blog;
	}
}

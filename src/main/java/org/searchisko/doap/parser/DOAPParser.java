/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.searchisko.doap.parser;

import com.viceversatech.rdfbeans.RDFBeanManager;
import com.viceversatech.rdfbeans.exceptions.RDFBeanException;
import org.ontoware.aifbcommons.collection.ClosableIterator;
import org.ontoware.rdf2go.model.Model;
import org.openrdf.model.Value;
import org.openrdf.query.*;
import org.openrdf.rdf2go.RepositoryModel;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.openrdf.sail.memory.MemoryStore;
import org.searchisko.doap.model.Project;
import org.searchisko.doap.model.Version;
import org.searchisko.doap.model.person.PeopleInRole;
import org.searchisko.doap.model.person.Person;
import org.searchisko.doap.model.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * DOAP file parser.
 *
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */
public class DOAPParser {

	private static Logger log = LoggerFactory.getLogger(DOAPParser.class);

	private org.openrdf.repository.Repository rep;
	private RepositoryConnection conn;
	private Model model;
	private RDFBeanManager manager;

	public void setUp() throws RepositoryException {
		rep = new SailRepository(new MemoryStore());
		rep.initialize();
		conn = rep.getConnection();
		model = new RepositoryModel(rep);
		model.open();
		manager = new RDFBeanManager(model);
	}

	public void tearDown() throws RepositoryException {
		model.close();
		conn.close();
		rep.shutDown();
	}

	public void loadLocalFile(String localPath) throws RepositoryException, RDFParseException, IOException {
		log.info("Adding file [{}] to RDF repository.", localPath);
		File file = new File(localPath);
		if (file.exists() && file.canRead()) {
			conn.add(file, null, RDFFormat.RDFXML);
		} else {
			throw new IllegalArgumentException("File does not exists or process does not have privileges to read it.");
		}
	}

	public <T> Collection<T> deserializeFromRepository(Class<T> clazz) throws RDFBeanException {
		List<T> result = new ArrayList<T>();
		ClosableIterator iter = manager.getAll(clazz);
		while (iter.hasNext()) {
			result.add((T)iter.next());
		}
		return result;
	}

	/**
	 * Get {@link Project} from parser repository.
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Project getProject() throws RepositoryException, RDFBeanException {
		Collection<Project> projects = deserializeFromRepository(Project.class);
		if (projects.size() == 0) {
			log.error("No project found in repository");
		} else if (projects.size() == 1) {
			Project project = projects.iterator().next();
			{
				Collection<Version> versions = getVersions();
				project.setRelease(versions.isEmpty() ? null : versions);

				Collection<Repository> repositories = (Collection<Repository>) getArchRepository();
				project.setRepository(repositories.isEmpty() ? null : repositories);

				Collection<Person> maintainers = getMaintainers();
				project.setMaintainer(maintainers.isEmpty() ? null : maintainers);

				Collection<Person> developers = getDevelopers();
				project.setDeveloper(developers.isEmpty() ? null : developers);

				Collection<Person> documenters = getDocumenters();
				project.setDocumenter(documenters.isEmpty() ? null : documenters);

				Collection<Person> translators = getTranslators();
				project.setTranslator(translators.isEmpty() ? null : translators);

				Collection<Person> helpers = getHelper();
				project.setHelper(helpers.isEmpty() ? null : helpers);

				Collection<Person> testers = getTester();
				project.setTester(testers.isEmpty() ? null : testers);
			}
			return project;
		} else {
			log.error("Multiple projects found in repository");
		}
		return null;
	}

	/**
	 * Get {@link org.searchisko.doap.model.person.Person} objects found in parser repository.
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<Person> getPersons() throws RepositoryException, RDFBeanException {
		return deserializeFromRepository(Person.class);
	}

	/**
	 * Get {@link Version} objects found in parser repository.
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<Version> getVersions() throws RepositoryException, RDFBeanException {
		return deserializeFromRepository(Version.class);
	}

	public Collection<? extends Repository> getAllRepositories() {
		Collection<Repository> repositories = new ArrayList<Repository>();
		repositories.addAll(getArchRepository());
		repositories.addAll(getBazaarBranch());
		repositories.addAll(getBKRepository());
		repositories.addAll(getCVSRepository());
		repositories.addAll(getDarcsRepository());
		repositories.addAll(getGitRepository());
		repositories.addAll(getHgRepository());
		repositories.addAll(getSVNRepository());
		return repositories;
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends Repository> getArchRepository() {
		try {
			return deserializeFromRepository(ArchRepository.class);
		} catch (Exception e) {
			log.error("Error getting ArchRepository", e);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends Repository> getBazaarBranch() {
		try {
			return deserializeFromRepository(BazaarBranch.class);
		} catch (Exception e) {
			log.error("Error getting ArchRepository", e);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends Repository> getBKRepository() {
		try {
			return deserializeFromRepository(BKRepository.class);
		} catch (Exception e) {
			log.error("Error getting ArchRepository", e);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends Repository> getCVSRepository() {
		try {
			return deserializeFromRepository(CVSRepository.class);
		} catch (Exception e) {
			log.error("Error getting ArchRepository", e);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends Repository> getDarcsRepository() {
		try {
			return deserializeFromRepository(DarcsRepository.class);
		} catch (Exception e) {
			log.error("Error getting ArchRepository", e);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends Repository> getGitRepository() {
		try {
			return deserializeFromRepository(GitRepository.class);
		} catch (Exception e) {
			log.error("Error getting ArchRepository", e);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends Repository> getHgRepository() {
		try {
			return deserializeFromRepository(HgRepository.class);
		} catch (Exception e) {
			log.error("Error getting ArchRepository", e);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends Repository> getSVNRepository() {
		try {
			return deserializeFromRepository(SVNRepository.class);
		} catch (Exception e) {
			log.error("Error getting ArchRepository", e);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 *
	 * @return
	 * @throws RDFBeanException
	 */
	public Collection<Person> getMaintainers() throws RDFBeanException {
		return getPeopleInRole(PeopleInRole.MAINTAIER);
	}

	/**
	 *
	 * @return
	 * @throws RDFBeanException
	 */
	public Collection<Person> getDevelopers() throws RDFBeanException {
		return getPeopleInRole(PeopleInRole.DEVELOPER);
	}

	/**
	 *
	 * @return
	 * @throws RDFBeanException
	 */
	public Collection<Person> getDocumenters() throws RDFBeanException {
		return getPeopleInRole(PeopleInRole.DOCUMENTER);
	}

	/**
	 *
	 * @return
	 * @throws RDFBeanException
	 */
	public Collection<Person> getTranslators() throws RDFBeanException {
		return getPeopleInRole(PeopleInRole.TRANSLATOR);
	}

	/**
	 *
	 * @return
	 * @throws RDFBeanException
	 */
	public Collection<Person> getTester() throws RDFBeanException {
		return getPeopleInRole(PeopleInRole.TESTER);
	}

	/**
	 *
	 * @return
	 * @throws RDFBeanException
	 */
	public Collection<Person> getHelper() throws RDFBeanException {
		return getPeopleInRole(PeopleInRole.HELPER);
	}

	private Collection<Person> getPeopleInRole(PeopleInRole role) throws RDFBeanException {
		Collection<Person> maintainers = new ArrayList<Person>();

		String query = "" +
				"PREFIX doap: <http://usefulinc.com/ns/doap#> " +
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
				"SELECT ?name ?mbox " +
				"WHERE { " +
				"?project doap:" + role.getRoleName() + " ?person. " +
				"?person a foaf:Person; foaf:name ?name; foaf:mbox ?mbox " +
				"}";

		try {
			TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();
					Value name = bindingSet.getValue("name");
					Value mbox = bindingSet.getValue("mbox");
					Person person = new Person();
					person.setName(name.stringValue());
					person.setMbox(new URI(mbox.stringValue()));
					log.info(role.getRoleName() + " name: {}, mbox: {}", person.getName(), person.getMbox());
					maintainers.add(person);
				}
			} finally {
				result.close();
			}
		} catch (Exception e) {
			log.error("Error querying " + role.getRoleName(), e);
		}
		return maintainers;
	}
}

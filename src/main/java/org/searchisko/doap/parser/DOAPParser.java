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
import org.openrdf.rdf2go.RepositoryModel;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.openrdf.sail.memory.MemoryStore;
import org.searchisko.doap.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
			return projects.iterator().next();
		} else {
			log.error("Multiple projects found in repository");
		}
		return null;
	}

	/**
	 * Get {@link Person} objects found in parser repository.
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

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends org.searchisko.doap.model.Repository> getArchRepository() throws RepositoryException, RDFBeanException {
		return deserializeFromRepository(ArchRepository.class);
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends org.searchisko.doap.model.Repository> getBazaarBranch() throws RepositoryException, RDFBeanException {
		return deserializeFromRepository(BazaarBranch.class);
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends org.searchisko.doap.model.Repository> getBKRepository() throws RepositoryException, RDFBeanException {
		return deserializeFromRepository(BKRepository.class);
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends org.searchisko.doap.model.Repository> getCVSRepository() throws RepositoryException, RDFBeanException {
		return deserializeFromRepository(CVSRepository.class);
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends org.searchisko.doap.model.Repository> getDarcsRepository() throws RepositoryException, RDFBeanException {
		return deserializeFromRepository(DarcsRepository.class);
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends org.searchisko.doap.model.Repository> getGitRepository() throws RepositoryException, RDFBeanException {
		return deserializeFromRepository(GitRepository.class);
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends org.searchisko.doap.model.Repository> getHgRepository() throws RepositoryException, RDFBeanException {
		return deserializeFromRepository(HgRepository.class);
	}

	/**
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public Collection<? extends org.searchisko.doap.model.Repository> getSVNRepository() throws RepositoryException, RDFBeanException {
		return deserializeFromRepository(SVNRepository.class);
	}

}

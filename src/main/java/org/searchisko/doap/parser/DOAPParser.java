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
import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;
import org.openrdf.rdf2go.RepositoryModel;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.sail.memory.MemoryStore;
import org.searchisko.doap.model.Person;
import org.searchisko.doap.model.Project;
import org.searchisko.doap.model.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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

	/**
	 * Unmashall {@link Project} from given DOAP file.
	 *
	 * @param localPath
	 * @return
	 * @throws RepositoryException
	 */
	public static Project deserializeProjectFromRDFFile(String localPath) throws RepositoryException {
		Collection<Project> projects = deserializeClassFromRDFFile(localPath, Project.class);
		if (projects.size() == 0) {
			log.error("No project found in {}", localPath);
		} else if (projects.size() == 1) {
			return projects.iterator().next();
		} else {
			log.error("Multiple projects found in {}", localPath);
		}
		return null;
	}

	/**
	 * Unmarshall all {@link Person} objects found in given RDF/XML file.
	 *
	 * @param localPath
	 * @return
	 * @throws RepositoryException
	 */
	public static Collection<Person> deserializePersonFromRDFFile(String localPath) throws RepositoryException {
		return deserializeClassFromRDFFile(localPath, Person.class);
	}

	/**
	 * Unmarshall all {@link Version} objects found in given RDF/XML file.
	 *
	 * @param localPath
	 * @return
	 * @throws RepositoryException
	 */
	public static Collection<Version> deserializeVersionFromRDFFile(String localPath) throws RepositoryException {
		return deserializeClassFromRDFFile(localPath, Version.class);
	}

	private static <T> Collection<T> deserializeClassFromRDFFile(String localPath, Class<T> clazz) throws RepositoryException {
		List<T> result = new ArrayList<T>();
		Repository rep = new SailRepository(new MemoryStore());
		rep.initialize();
		try {
			log.info("DOAP file location: {}", localPath);
			File file = new File(localPath);
			RepositoryConnection con = rep.getConnection();
			Model model = new RepositoryModel(rep);
			model.open();
			RDFBeanManager manager = new RDFBeanManager(model);
			try {
				con.add(file, null, RDFFormat.RDFXML);
				ClosableIterator iter = manager.getAll(clazz);
				while (iter.hasNext()) {
					result.add((T)iter.next());
				}
			} catch (RDFBeanException e) {
				log.error("Error occurred: {}", e);
			} finally {
				model.close();
				con.close();
			}
		} catch (OpenRDFException e) {
			log.error("Unexpected error: {}", e);
		} catch (java.io.IOException e) {
			log.error("File operation error: {}", e);
		}
		return result;
	}
}

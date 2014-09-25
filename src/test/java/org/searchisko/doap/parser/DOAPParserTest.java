/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */

package org.searchisko.doap.parser;

import org.junit.Test;
import org.openrdf.repository.RepositoryException;
import org.searchisko.doap.json.Converter;
import org.searchisko.doap.model.Person;
import org.searchisko.doap.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */
public class DOAPParserTest {

	private static Logger log = LoggerFactory.getLogger(DOAPParserTest.class);

	@Test
	public void testProjectParsing() throws URISyntaxException {
		try {
			String path = getClass().getResource( "/doap-examples/doap_maven.rdf" ).getPath();
			Project project = DOAPParser.deserializeProjectFromRDFFile(path);
			try {
				String json = Converter.objectToJSON(project);
			} catch (IOException e) {
				log.error("Error converting project to JSON", e);
			}
		} catch (RepositoryException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testPersonParsing() throws URISyntaxException {
		try {
			String path = getClass().getResource( "/doap-examples/doap_maven.rdf" ).getPath();
			Collection<Person> persons = DOAPParser.deserializePersonFromRDFFile(path);
			assertEquals(14, persons.size());
			try {
				for (Person p : persons) {
					String json = Converter.objectToJSON(p);
				}
			} catch (IOException e) {
				log.error("Error converting person to JSON", e);
			}
		} catch (RepositoryException e) {
			fail(e.getMessage());
		}
	}
}

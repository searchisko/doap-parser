/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.searchisko.doap.parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openrdf.repository.RepositoryException;
import org.searchisko.doap.json.Converter;
import org.searchisko.doap.model.Project;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */
public class DOAPParserUsingInputStreamTest extends DOAPParserSupport {

	private DOAPParser parser = new DOAPParser();

	@Before
	public void setUp() throws RepositoryException {
		parser.repositoryInit();
	}

	@After
	public void tearDown() throws RepositoryException {
		parser.repositoryClose();
	}

	@Test
	public void testNarayanaProjectParsingFails() throws Exception {
//		URI uri = new URI("https://raw.githubusercontent.com/jbosstm/project-metadata/master/narayana.rdf");
		String path = "/doap-jboss-examples/narayana.rdf";
		try {
			parser.loadInputStream(getInputStreamFromClasspathFile(path));
			fail("Exception expected");
		} catch (Exception e) {
//			fail("Exception " + e.getMessage());
			assertTrue(e.getMessage().matches("(.*)Not a valid .absolute. URI:(.*)"));
		}
		Project project = parser.getProject();
		String json = Converter.objectToJSON(project);
//		JSONAssert.assertEquals(readStringFromClasspathFile("/doap-json/camel.json"), json, JSONCompareMode.NON_EXTENSIBLE);
	}
}

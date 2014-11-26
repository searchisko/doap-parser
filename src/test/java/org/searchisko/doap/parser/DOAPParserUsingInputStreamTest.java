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
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

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
	public void testNarayanaProjectParsing() throws Exception {
//		URI uri = new URI("https://raw.githubusercontent.com/jbosstm/project-metadata/master/narayana.rdf");
		String path = "/doap-jboss-examples/narayana.rdf";
		parser.loadInputStream(getInputStreamFromClasspathFile(path));
		Project project = parser.getProject();
		String json = Converter.objectToJSON(project);
		JSONAssert.assertEquals(readStringFromClasspathFile("/json-jboss-examples/narayana.json"), json, JSONCompareMode.NON_EXTENSIBLE);
	}

	@Test
	public void testSnowdropProjectParsing() throws Exception {
		String path = "/doap-jboss-examples/snowdrop.rdf";
		parser.loadInputStream(getInputStreamFromClasspathFile(path));
		Project project = parser.getProject();
		String json = Converter.objectToJSON(project);
		JSONAssert.assertEquals(readStringFromClasspathFile("/json-jboss-examples/snowdrop.json"), json, JSONCompareMode.NON_EXTENSIBLE);
	}

	@Test
	public void testLiveOakProjectParsing() throws Exception {
		String path = "/doap-jboss-examples/liveoak.rdf";
		parser.loadInputStream(getInputStreamFromClasspathFile(path));
		Project project = parser.getProject();
		String json = Converter.objectToJSON(project);
		JSONAssert.assertEquals(readStringFromClasspathFile("/json-jboss-examples/liveoak.json"), json, JSONCompareMode.NON_EXTENSIBLE);
	}
}

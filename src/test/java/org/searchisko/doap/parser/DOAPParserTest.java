/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */

package org.searchisko.doap.parser;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.searchisko.doap.json.Converter;
import org.searchisko.doap.model.Person;
import org.searchisko.doap.model.Project;
import org.searchisko.doap.model.Version;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */
public class DOAPParserTest {

	/**
	 * Read file from classpath into String. UTF-8 encoding expected.
	 *
	 * @param filePath in classpath to read data from.
	 * @return file content.
	 * @throws IOException
	 */
	public String readStringFromClasspathFile(String filePath) throws IOException {
		StringWriter stringWriter = new StringWriter();
		IOUtils.copy(getClass().getResourceAsStream(filePath), stringWriter, "UTF-8");
		return stringWriter.toString();
	}

	/**
	 * Test parsing of project
	 * @throws Exception
	 */
	@Test
	public void testProjectParsing() throws Exception {
		String path = getClass().getResource( "/doap-examples/doap_camel.rdf" ).getPath();
		Project project = DOAPParser.deserializeProjectFromRDFFile(path);
		String json = Converter.objectToJSON(project);
		JSONAssert.assertEquals(readStringFromClasspathFile("/doap-json/camel.json"), json, JSONCompareMode.NON_EXTENSIBLE);
	}

	/**
	 * Test parsing of person objects fro project
	 * @throws Exception
	 */
	@Test
	public void testPersonParsing() throws Exception {
		String path = getClass().getResource( "/doap-examples/doap_maven.rdf" ).getPath();
		Collection<Person> persons = DOAPParser.deserializePersonFromRDFFile(path);
		// try to convert each person
		for (Person p : persons) {
			String json = Converter.objectToJSON(p);
		}
		assertEquals(14, persons.size());
	}

	@Test
	public void testVersionParsing() throws Exception {
		String path = getClass().getResource( "/doap-examples/doap_maven.rdf" ).getPath();
		Collection<Version> versions = DOAPParser.deserializeVersionFromRDFFile(path);
		// try to convert each version
		for (Version p : versions) {
			String json = Converter.objectToJSON(p);
		}
		assertEquals(15, versions.size());
	}
}

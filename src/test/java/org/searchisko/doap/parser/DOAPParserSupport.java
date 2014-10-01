/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.searchisko.doap.parser;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;

/**
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */
public class DOAPParserSupport {

	/**
	 * Read file from classpath into String. UTF-8 encoding expected.
	 *
	 * @param filePath in classpath to read data from.
	 * @return file content.
	 * @throws java.io.IOException
	 */
	public String readStringFromClasspathFile(String filePath) throws IOException {
		StringWriter stringWriter = new StringWriter();
		IOUtils.copy(getClass().getResourceAsStream(filePath), stringWriter, "UTF-8");
		return stringWriter.toString();
	}

	/**
	 * Obtain {@link java.io.InputStream} from given URI location.
	 *
	 * @param uri
	 * @return
	 * @throws IOException
	 */
	public InputStream getInputStreamFromURI(URI uri) throws IOException {
		URL url = uri.toURL(); //get URL from your uri object
		return url.openStream();
	}

	/**
	 *
	 * @param filePath
	 * @return
	 */
	public InputStream getInputStreamFromClasspathFile(String filePath) {
		return getClass().getResourceAsStream(filePath);
	}
}

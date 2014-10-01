/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.searchisko.doap.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */
public class AppOptionsTest {

	@Test
	public void testNoArgs() {
		AppOptions appOptions = new AppOptions();
		appOptions.parseArgs(new String[]{});
		assertFalse(appOptions.isValid());
	}

	@Test
	public void testIncompleteArgs() {
		AppOptions appOptions = new AppOptions();
		appOptions.parseArgs(new String[]{"-doap", "http://doap.location"});
		assertFalse(appOptions.isValid());
	}

	@Test
	public void testInvalidArgs() {
		AppOptions appOptions = new AppOptions();
		appOptions.parseArgs(new String[]{"-doap", "invalid.url"});
		assertFalse(appOptions.isValid());
	}

	@Test
	public void testCompleteArgs() {
		AppOptions appOptions = new AppOptions();
		appOptions.parseArgs(new String[]{"-doap", "http://doap.location", "-server", "http://dcp.rest.api/content/"});
		assertEquals("http://doap.location", appOptions.getDoapURI().toString());
		assertEquals("http://dcp.rest.api/content/", appOptions.getServerURI().toString());
	}

	@Test
	public void testFileArgIsOkToo() {
		AppOptions appOptions = new AppOptions();
		appOptions.parseArgs(new String[]{"-doap", "file:///doap.location", "-server", "http://dcp.rest.api/content/"});
		assertEquals("file:///doap.location", appOptions.getDoapURI().toString());
		assertEquals("http://dcp.rest.api/content/", appOptions.getServerURI().toString());
	}
}

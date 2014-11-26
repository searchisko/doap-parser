/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.searchisko.doap.json;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */
public class SchemaValidatorTest {

	@Test
	public void testJSONSchema() throws IOException, ProcessingException {
		ProcessingReport report = new SchemaValidator().validate(
				getClass().getResourceAsStream("/doap-json/narayana.json")
		);
		assertFalse(report.isSuccess());
		int cnt = 0;
		Iterator<ProcessingMessage> it = report.iterator();
		while (it.hasNext()) {
			ProcessingMessage m = it.next();
			cnt++;
			System.out.println(m.toString());
		}
		assertEquals(1, cnt);
	}
}

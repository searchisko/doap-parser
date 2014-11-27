/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */

package org.searchisko.doap.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */
public class Converter {

	private static Logger log = LoggerFactory.getLogger(Converter.class);
	private static ObjectMapper mapper = new ObjectMapper();

	// configure JSON output format
	static {
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	public static String objectToJSON(Object object) throws IOException {
		String json = mapper.writeValueAsString(object);
		log.debug("{}", json);
		return json;
	}
}

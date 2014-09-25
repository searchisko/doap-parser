/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */

package org.searchisko.doap.json;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
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
		mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
	}

	public static String objectToJSON(Object object) throws IOException {
		String json = mapper.writeValueAsString(object);
		log.debug("{}", json);
		return json;
	}
}

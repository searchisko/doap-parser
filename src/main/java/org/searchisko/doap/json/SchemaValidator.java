/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.searchisko.doap.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JacksonUtils;
import com.github.fge.jackson.JsonNodeReader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */
public class SchemaValidator {

	public ProcessingReport validate(InputStream is) throws IOException, ProcessingException {
		ObjectMapper mapper = JacksonUtils.newMapper();
		mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS,true);
		final JsonNodeReader reader = new JsonNodeReader(mapper);
		final JsonNode project_info_schema = reader.fromInputStream(
				getClass().getResourceAsStream("/json-schema/project_info.json")
		);

		final JsonNode project_info = reader.fromInputStream(is);

		JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
		JsonSchema schema = factory.getJsonSchema(project_info_schema);

		return schema.validate(project_info);
	}

}

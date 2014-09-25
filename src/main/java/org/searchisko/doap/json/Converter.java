package org.searchisko.doap.json;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.searchisko.doap.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by lukas on 25/09/14.
 */
public class Converter {

	private static Logger log = LoggerFactory.getLogger(Converter.class);
	private static ObjectMapper mapper = new ObjectMapper();

	// configure JSON output format
	static {
		mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
	}

	public static String projectToJSON(Project project) throws IOException {
		String json = mapper.writeValueAsString(project);
		log.debug("{}", json);
		return json;
	}
}

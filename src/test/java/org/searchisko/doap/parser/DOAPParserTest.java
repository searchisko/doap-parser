package org.searchisko.doap.parser;

import org.junit.Test;
import org.openrdf.repository.RepositoryException;

import java.net.URISyntaxException;

import static org.junit.Assert.assertTrue;

/**
 * Created by lukas on 22/09/14.
 */
public class DOAPParserTest {

	@Test
	public void testIt() throws URISyntaxException {
		try {
			String path = getClass().getResource( "/doap-examples/doap_maven.rdf" ).getPath();
			DOAPParser.deserializeFromFile(path);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}
}

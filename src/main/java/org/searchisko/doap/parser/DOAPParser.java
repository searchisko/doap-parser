package org.searchisko.doap.parser;

import com.viceversatech.rdfbeans.RDFBeanManager;
import com.viceversatech.rdfbeans.exceptions.RDFBeanException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.ontoware.aifbcommons.collection.ClosableIterator;
import org.ontoware.rdf2go.model.Model;
import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;
import org.openrdf.rdf2go.RepositoryModel;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.sail.memory.MemoryStore;
import org.searchisko.doap.model.Person;
import org.searchisko.doap.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by lukas on 22/09/14.
 */
public class DOAPParser {

	private static Logger log = LoggerFactory.getLogger(DOAPParser.class);

	public static Project deserializeFromFile(String localPath) throws RepositoryException {
		Repository rep = new SailRepository(new MemoryStore());
		rep.initialize();
		try {
			log.info("DOAP file location: {}", localPath);
			File file = new File(localPath);
			RepositoryConnection con = rep.getConnection();

			Model model = new RepositoryModel(rep);
			model.open();
			RDFBeanManager manager = new RDFBeanManager(model);

			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
//			mapper.enable(SerializationConfig.Feature.SORT_PROPERTIES_ALPHABETICALLY );
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
//			mapper.setSerializationInclusion();

			try {
				con.add(file, null, RDFFormat.RDFXML);

				ClosableIterator iter;

				iter = manager.getAll(Project.class);
				while (iter.hasNext()) {
					Project p = (Project)iter.next();
					log.info("Project: {}", mapper.writeValueAsString(p));
				}

				iter = manager.getAll(Person.class);
				while (iter.hasNext()) {
					Person p = (Person)iter.next();
					log.info("Person: {}", mapper.writeValueAsString(p));
				}

			} catch (RDFBeanException e) {
				log.error("Error occurred: {}", e);
			} finally {
				model.close();
				con.close();
			}
		} catch (OpenRDFException e) {
			log.error("Unexpected error: {}", e);
		} catch (java.io.IOException e) {
			log.error("File operation error: {}", e);
		}
		return null;
	}
}
